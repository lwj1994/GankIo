package me.venjerlu.gankio.widget.pulltorefresh.layoutManager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import me.venjerlu.gankio.widget.pulltorefresh.BaseListAdapter;

public class BaseLinearLayoutManager extends LinearLayoutManager implements ILayoutManager {
  public BaseLinearLayoutManager(Context context) {
    super(context);
  }

  public BaseLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
    super(context, orientation, reverseLayout);
  }

  public BaseLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override public RecyclerView.LayoutManager getLayoutManager() {
    return this;
  }

  @Override public int findLastVisiblePosition() {
    return findLastVisibleItemPosition();
  }

  @Override public void setUpAdapter(BaseListAdapter adapter) {

  }
}
