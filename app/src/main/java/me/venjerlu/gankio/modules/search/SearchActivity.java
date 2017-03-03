package me.venjerlu.gankio.modules.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import com.elvishew.xlog.XLog;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;
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
  //private boolean is100;

  @Override public int getLayout() {
    return R.layout.search;
  }

  @Override protected void initData(Bundle savedInstanceState) {
    initWebview();
    initSearchBar();
    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mWebView.reload();
      }
    });
  }

  private void initSearchBar() {

    mSearchBar.setSuggstionsClickListener(new SuggestionsAdapter.OnItemViewClickListener() {
      @Override public void OnItemClickListener(int position, View v) {
        String s = mSearchBar.getLastSuggestions().get(position).toString();
        mSearchBar.setText(s);
        loadUrl(s);
        mSearchBar.disableSearch();
      }

      @Override public void OnItemDeleteListener(int position, View v) {
      }
    });
    mSearchBar.setCardViewElevation(10);
    mSearchBar.setOnSearchActionListener(this);
    mSearchBar.enableSearch();
  }

  @Override public void onSearchStateChanged(boolean enabled) {

  }

  @Override public void onSearchConfirmed(CharSequence text) {
    XLog.tag(TAG).d("text = " + text);
    loadUrl(text);
    setRefreshing(true);
    mSearchBar.disableSearch();
  }

  private void loadUrl(CharSequence text) {
    //is100 = false;
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
      //if (newProgress == 100 && !is100) {
      //  is100 = true;
      //  mWebView.flingScroll(0, AndroidUtil.dp2px(1440));
      //}
    }

    @Override public void onReceivedTitle(WebView view, String title) {
      super.onReceivedTitle(view, title);
    }
  }

  private class LoveClient extends WebViewClient {
    @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        String url = request.getUrl().toString();
        if (!TextUtils.isEmpty(url)) {
          view.loadUrl(url);
        }
        return true;
      }
      return false;
    }

    @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
      if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
        if (!TextUtils.isEmpty(url)) {
          view.loadUrl(url);
          return true;
        }
      }
      return false;
    }
  }
}
