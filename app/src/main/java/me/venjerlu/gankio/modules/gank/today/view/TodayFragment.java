package me.venjerlu.gankio.modules.gank.today.view;

import android.os.Bundle;
import java.util.List;
import me.venjerlu.gankio.common.fragment.BaseListFragment;
import me.venjerlu.gankio.modules.gank.model.DateModel;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.today.presenter.TodayAdapter;
import me.venjerlu.gankio.modules.gank.today.presenter.TodayPresenter;

/**
 * Author/Date: venjerLu / 2016/12/10 19:59
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TodayFragment extends BaseListFragment<TodayPresenter, TodayAdapter>
    implements ITodayView {

  public static TodayFragment newInstance() {
    Bundle args = new Bundle();
    TodayFragment fragment = new TodayFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initData() {
    super.initData();
    mPullToRefreshLayout.enableLoadMore(false);
    mRecyclerView.setVerticalScrollBarEnabled(false);
    //mAdapter.addHeader(R.layout.item_today_meizhi);
    //mAdapter.addFooter(R.layout.item_today_meizhi);
  }

  @Override public void showError(String msg) {

  }

  @Override public void setRefreshing(boolean refresh) {

  }

  @Override public void onRefresh() {
    mPresenter.getLatestData();
  }

  public void addToList(int i, String title, List<Gank> list) {
    if (list != null && list.size() > 0) {
      mAdapter.addSection(i, title);
      mAdapter.addContent(list);
    }
  }

  @Override public void onGetLatestData(DateModel results) {
    List<Gank> android = results.getAndroid();
    List<Gank> ios = results.getiOS();
    List<Gank> front = results.getFront();
    List<Gank> expand = results.getExpand();
    addToList(0, "Android", android);
    addToList(1, "iOS", ios);
    addToList(2, "前端", front);
    addToList(3, "拓展资源", expand);
    mAdapter.notifyDataSetChanged();
  }
}
