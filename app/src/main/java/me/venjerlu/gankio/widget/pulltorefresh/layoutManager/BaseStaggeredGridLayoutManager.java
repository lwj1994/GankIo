package me.venjerlu.gankio.widget.pulltorefresh.layoutManager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import me.venjerlu.gankio.widget.pulltorefresh.BaseListAdapter;

public class BaseStaggeredGridLayoutManager extends StaggeredGridLayoutManager
    implements ILayoutManager {

  public BaseStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public BaseStaggeredGridLayoutManager(int spanCount, int orientation) {
    super(spanCount, orientation);
    //setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE); // 防止位置发生变化
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {
    return this;
  }

  @Override public int findLastVisiblePosition() {
    int[] positions = null;
    positions = findLastVisibleItemPositions(positions);
    return positions[0];
  }

  @Override public int findFirstCompletelyVisibleItemPosition() {
    return 0;
  }

  @Override public void setUpAdapter(BaseListAdapter adapter) {

  }
}
