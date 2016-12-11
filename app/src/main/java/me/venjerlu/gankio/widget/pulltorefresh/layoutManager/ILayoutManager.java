package me.venjerlu.gankio.widget.pulltorefresh.layoutManager;

import android.support.v7.widget.RecyclerView;
import me.venjerlu.gankio.widget.pulltorefresh.BaseListAdapter;

/**
 * LayoutManager 的接口类
 */
public interface ILayoutManager {
  RecyclerView.LayoutManager getLayoutManager();

  int findLastVisiblePosition();

  int findFirstCompletelyVisibleItemPosition();

  void setUpAdapter(BaseListAdapter adapter);
}
