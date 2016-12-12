package me.venjerlu.gankio.widget.pulltorefresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.widget.pulltorefresh.layoutManager.ILayoutManager;

public class PullRecyclerLayout extends FrameLayout
    implements SwipeRefreshLayout.OnRefreshListener {
  public static final int ACTION_PULL_TO_REFRESH = 1;
  public static final int ACTION_LOAD_MORE_REFRESH = 2;
  public static final int ACTION_IDLE = 0;
  public int mCurrentState = ACTION_IDLE;
  public ILayoutManager mLayoutManager;
  public boolean isTop = true; // 是否在顶部
  private SwipeRefreshLayout mSwipeRefreshLayout;
  private RecyclerView mRecyclerView;
  private OnRecyclerRefreshListener listener;
  private OnScrolledListener mScrolledListener;
  private boolean isLoadMoreEnabled = false;
  private boolean isPullToRefreshEnabled = true;
  private BaseListAdapter adapter;
  private int mScrollTotal;

  public PullRecyclerLayout(Context context) {
    super(context);
    setUpView();
  }

  public PullRecyclerLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    setUpView();
  }

  public PullRecyclerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
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
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
          if (mScrolledListener != null) {
            mScrolledListener.onScrolled();
          }
        }
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
          // TODO: 2016/12/11 恢复加载图片
          //ImageLoader.getInstance().resume(App.mContext);
        } else {
          // TODO: 2016/12/11 暂停加载图片
          //ImageLoader.getInstance().pause(App.mContext);
        }
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //                mSwipeRefreshLayout.setEnabled(mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
        if (mCurrentState == ACTION_IDLE && isLoadMoreEnabled && checkIfLoadMore()) {
          mCurrentState = ACTION_LOAD_MORE_REFRESH;
          mRecyclerView.post(new Runnable() {
            @Override public void run() {
              adapter.onLoadMoreStateChanged(true);
              mSwipeRefreshLayout.setEnabled(false);
              listener.onRefresh(ACTION_LOAD_MORE_REFRESH);
            }
          });
        }
        mScrollTotal += dy;
        isTop = mScrollTotal <= 0;
      }
    });
  }

  private boolean checkIfLoadMore() {
    int lastVisibleItemPosition = mLayoutManager.findLastVisiblePosition();
    int totalCount = mLayoutManager.getLayoutManager().getItemCount();
    return totalCount - lastVisibleItemPosition < 5;
  }

  public void enableLoadMore(boolean enable) {
    isLoadMoreEnabled = enable;
  }

  public void enablePullToRefresh(boolean enable) {
    isPullToRefreshEnabled = enable;
    mSwipeRefreshLayout.setEnabled(enable);
  }

  public void setLayoutManager(ILayoutManager manager) {
    this.mLayoutManager = manager;
    mRecyclerView.setLayoutManager(manager.getLayoutManager());
  }

  public void addItemDecoration(RecyclerView.ItemDecoration decoration) {
    if (decoration != null) {
      mRecyclerView.addItemDecoration(decoration);
    }
  }

  public void setAdapter(BaseListAdapter adapter) {
    this.adapter = adapter;
    mRecyclerView.setAdapter(adapter);
    mLayoutManager.setUpAdapter(adapter);
  }

  public void setRefreshing() {
    setSwipeRefreshing(true);
    onRefresh();
  }

  public void setSwipeRefreshing(final boolean isRefreshing) {
    mSwipeRefreshLayout.post(new Runnable() {
      @Override public void run() {
        mSwipeRefreshLayout.setRefreshing(isRefreshing);
      }
    });
  }

  public void setOnRefreshListener(OnRecyclerRefreshListener listener) {
    this.listener = listener;
  }

  public void setOnScrolledListener(OnScrolledListener listener) {
    this.mScrolledListener = listener;
  }

  @Override public void onRefresh() {
    mCurrentState = ACTION_PULL_TO_REFRESH;
    listener.onRefresh(ACTION_PULL_TO_REFRESH);
  }

  public void onRefreshCompleted() {
    if (adapter.getDataCount() == 0) adapter.addEmpty(R.layout.item_empty);
    switch (mCurrentState) {
      case ACTION_PULL_TO_REFRESH:
        postDelayed(new Runnable() {
          @Override public void run() {
            mSwipeRefreshLayout.setRefreshing(false);
          }
        }, 500);
        break;
      case ACTION_LOAD_MORE_REFRESH:
        adapter.onLoadMoreStateChanged(false);
        if (isPullToRefreshEnabled) {
          mSwipeRefreshLayout.setEnabled(true);
        }
        break;
      default:
        postDelayed(new Runnable() {
          @Override public void run() {
            mSwipeRefreshLayout.setRefreshing(false);
          }
        }, 500);
        break;
    }
    mCurrentState = ACTION_IDLE;
  }

  /**
   * 滑动到顶部
   */
  public void scrollToTop() {
    mRecyclerView.smoothScrollToPosition(0);
  }

  public void scrollToPositionOffset(int position, int offset) {
    ((LinearLayoutManager) mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(position,
        offset);
  }

  public void scrollToPosition(int position) {
    mRecyclerView.scrollToPosition(position);
  }

  public void onDestroy() {
    mRecyclerView.setAdapter(null);
  }

  public interface OnRecyclerRefreshListener {
    void onRefresh(int action);
  }

  public interface OnScrolledListener {
    void onScrolled();
  }
}
