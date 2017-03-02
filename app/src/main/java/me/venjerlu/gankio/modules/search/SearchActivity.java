package me.venjerlu.gankio.modules.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import com.elvishew.xlog.XLog;
import com.mancj.materialsearchbar.MaterialSearchBar;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.activity.BaseSimpleActivity;

/**
 * Author/Date: venjerLu / 2017/3/2 17:34
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class SearchActivity extends BaseSimpleActivity
    implements MaterialSearchBar.OnSearchActionListener {
  private static final String TAG = "SearchActivity";
  @BindView(R.id.search_searchbar) MaterialSearchBar mSearchBar;
  @BindView(R.id.search_webview) WebView mWebView;
  @BindView(R.id.search_swipe) SwipeRefreshLayout mSwipeRefreshLayout;

  @Override public int getLayout() {
    return R.layout.search;
  }

  @Override protected void initData(Bundle savedInstanceState) {
    mSearchBar.setCardViewElevation(10);
    mSearchBar.setOnSearchActionListener(this);
    //mSwipeRefreshLayout.setEnabled(false);
  }

  @Override protected void onResume() {
    super.onResume();
    mSearchBar.enableSearch();
  }

  @Override public void onSearchStateChanged(boolean enabled) {

  }

  @Override public void onSearchConfirmed(CharSequence text) {
    XLog.tag(TAG).d("text = " + text);
    mWebView.loadUrl("http://gank.io/search?q=" + text);
  }

  @Override public void onButtonClicked(int buttonCode) {

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

  private class ChromeClient extends WebChromeClient {
    @Override public void onProgressChanged(WebView view, int newProgress) {
      super.onProgressChanged(view, newProgress);

      setRefreshing(newProgress != 100);
      //mSwipeRefreshLayout.setEnabled(newProgress != 100);
    }

    @Override public void onReceivedTitle(WebView view, String title) {
      super.onReceivedTitle(view, title);
    }
  }

  private class LoveClient extends WebViewClient {
    @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
      String url = null;
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        url = request.getUrl().toString();
      }
      if (!TextUtils.isEmpty(url)) {
        view.loadUrl(url);
      }
      return true;
    }

    //@Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
    //  if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
    //    if (!TextUtils.isEmpty(url)) {
    //      view.loadUrl(url);
    //    }
    //  }
    //  return true;
    //}
  }
}
