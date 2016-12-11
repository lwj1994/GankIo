package me.venjerlu.gankio.widget.pulltorefresh.layoutManager;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import me.venjerlu.gankio.widget.pulltorefresh.BaseListAdapter;
import me.venjerlu.gankio.widget.pulltorefresh.FooterSpanSizeLookup;

public class BaseGridLayoutManager extends GridLayoutManager implements ILayoutManager {

  public BaseGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public BaseGridLayoutManager(Context context, int spanCount) {
    super(context, spanCount);
  }

  public BaseGridLayoutManager(Context context, int spanCount, int orientation,
      boolean reverseLayout) {
    super(context, spanCount, orientation, reverseLayout);
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {
    return this;
  }

  @Override public int findLastVisiblePosition() {
    return findLastVisibleItemPosition();
  }

  @Override public void setUpAdapter(BaseListAdapter adapter) {
    FooterSpanSizeLookup lookup = new FooterSpanSizeLookup(adapter, getSpanCount());
    setSpanSizeLookup(lookup);
  }

  @Override public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
    try {
      super.onLayoutChildren(recycler, state);
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    }
  }
}
