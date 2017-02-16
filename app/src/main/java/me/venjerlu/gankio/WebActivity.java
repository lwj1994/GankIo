package me.venjerlu.gankio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import butterknife.BindView;
import me.venjerlu.gankio.common.activity.BaseSimpleActivity;
import me.venjerlu.gankio.utils.AndroidUtil;
import me.venjerlu.gankio.utils.ToastUtil;

import static me.venjerlu.gankio.R.id.toolbar;

/**
 * Author/Date: venjerLu / 2017/2/16 14:17
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class WebActivity extends BaseSimpleActivity {
  private static final String TAG = "WebActivity";
  private static final String EXTRA_URL = TAG + "url";
  private static final String EXTRA_TITLE = TAG + "title";

  @BindView(toolbar) Toolbar mToolbar;
  @BindView(R.id.web_swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  @BindView(R.id.web_webView) WebView mWebView;
  @BindView(R.id.textSwitcher_title) TextSwitcher mTextSwitcher;

  private String mUrl;
  private String mTitle;

  public static void startMe(Activity context, String url, String title) {
    Intent intent = new Intent(context, WebActivity.class);
    intent.putExtra(EXTRA_URL, url);
    intent.putExtra(EXTRA_TITLE, title);
    context.startActivity(intent);
  }

  @Override public int getLayout() {
    return R.layout.activity_webview;
  }

  @Override protected int getMenuLayout() {
    return R.menu.activity_web_main;
  }

  @Override protected void initData(Bundle savedInstanceState) {
    mUrl = getIntent().getStringExtra(EXTRA_URL);
    mTitle = getIntent().getStringExtra(EXTRA_TITLE);

    initToolbar();
    initWebview();
  }

  @SuppressLint("SetJavaScriptEnabled") private void initWebview() {
    WebSettings settings = mWebView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setLoadWithOverviewMode(true);
    settings.setAppCacheEnabled(true);
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    settings.setSupportZoom(true);
    mWebView.setWebChromeClient(new ChromeClient());
    mWebView.setWebViewClient(new LoveClient());
    mWebView.loadUrl(mUrl);
  }

  private void initToolbar() {
    mToolbar.setTitle("");
    setSupportActionBar(mToolbar);
    mToolbar.setNavigationIcon(R.drawable.ic_close);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });
    setRefreshing(true);

    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        refresh();
      }
    });
    //设置标题
    mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
      @Override public View makeView() {
        final TextView textView = new TextView(WebActivity.this);
        TextSwitcher.LayoutParams layoutParams =
            new TextSwitcher.LayoutParams(TextSwitcher.LayoutParams.WRAP_CONTENT,
                TextSwitcher.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(layoutParams);
        textView.setLayoutParams(layoutParams);
        textView.setSingleLine(true);
        textView.setTextSize(15);
        textView.setGravity(Gravity.START);
        textView.setTextColor(Color.WHITE);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.postDelayed(new Runnable() {
          @Override public void run() {
            textView.setSelected(true);
          }
        }, 1738);
        return textView;
      }
    });
    mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
    mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);
    if (!TextUtils.isEmpty(mTitle)) setTitle(mTitle);
  }

  /**
   * 设置SwipeRefreshLayout的刷新状态
   *
   * @param refreshing 是否正在刷新
   */
  private void setRefreshing(boolean refreshing) {
    if (refreshing) {
      if (mSwipeRefreshLayout != null && !mSwipeRefreshLayout.isRefreshing()) {
        mSwipeRefreshLayout.post(new Runnable() {
          @Override public void run() {
            mSwipeRefreshLayout.setRefreshing(true);
          }
        });
      }
    } else {
      if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
        mSwipeRefreshLayout.setRefreshing(false);
      }
    }
  }

  @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      if (mWebView.canGoBack()) {
        mWebView.goBack();
      } else {
        finish();
      }
    }
    return true;
  }

  /**
   * 设置标题
   */
  private void setTitle(String title) {
    mTextSwitcher.setText(title);
  }

  @Override protected void onResume() {
    super.onResume();
    if (mWebView != null) mWebView.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
    if (mWebView != null) mWebView.onPause();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (mWebView != null) mWebView.destroy();
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.webview_action_copy_url:
        String copyDone = getString(R.string.tip_copy_done);
        AndroidUtil.copyToClipBoard(this, mWebView.getUrl(), copyDone);
        return true;
      case R.id.webview_action_open_url:
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(mUrl);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
          startActivity(intent);
        } else {
          ToastUtil.longMsg(R.string.tip_open_fail);
        }
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void refresh() {
    mWebView.reload();
  }

  private class ChromeClient extends WebChromeClient {
    @Override public void onProgressChanged(WebView view, int newProgress) {
      super.onProgressChanged(view, newProgress);

      setRefreshing(newProgress != 100);
    }

    @Override public void onReceivedTitle(WebView view, String title) {
      super.onReceivedTitle(view, title);
    }
  }

  private class LoveClient extends WebViewClient {
    @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
      String url = request.getUrl().toString();
      if (!TextUtils.isEmpty(url)) {
        view.loadUrl(url);
      }
      return true;
    }
  }
}
