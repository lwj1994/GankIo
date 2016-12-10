package me.venjerlu.gankio.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.adapter.BaseAdapter;

/**
 * Author/Date: venjerLu / 2016/12/10 20:58
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class PullToRefreshLayout extends FrameLayout
    implements SwipeRefreshLayout.OnRefreshListener {
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private RecyclerView mRecyclerView;
  private BaseAdapter mAdapter;
  public PullToRefreshLayout(Context context) {
    super(context);
    setUpView();
  }

  public PullToRefreshLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    setUpView();
  }

  public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setUpView();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public PullToRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    setUpView();
  }

  private void setUpView() {
    LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_to_refresh, this, true);
    mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
      }
    });
  }

  @Override public void onRefresh() {

  }
}
