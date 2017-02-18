package me.venjerlu.gankio.modules.gank.meizhi.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import java.util.List;
import me.venjerlu.gankio.common.fragment.BaseListFragment;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.modules.gank.meizhi.adapter.MeizhiAdapter;
import me.venjerlu.gankio.modules.gank.meizhi.presenter.MeizhiPresenter;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.widget.pulltorefresh.layoutManager.BaseStaggeredGridLayoutManager;
import me.venjerlu.gankio.widget.pulltorefresh.layoutManager.ILayoutManager;

/**
 * Author/Date: venjerLu / 2016/12/9 10:47
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class MeizhiFragment extends BaseListFragment<MeizhiPresenter, MeizhiAdapter>
    implements IMeizhiView {
  private static final int sSize = 10;
  private int mPage;

  public static MeizhiFragment newInstance() {
    Bundle args = new Bundle();
    MeizhiFragment fragment = new MeizhiFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected RecyclerView.ItemDecoration getItemDecoration() {
    return null;
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initData() {
    super.initData();
  }

  @Override public void showError(String msg) {

  }

  @Override public void setRefreshing(boolean refresh) {

  }

  @Override public void onGetData(List<Gank> list) {
    mAdapter.insertData(list);
  }

  @Override protected ILayoutManager getLayoutManager() {
    return new BaseStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
  }

  @Override public void onRefresh() {
    mPage = 1;
    mPresenter.getData(GankApi.福利, sSize, mPage);
    mPullToRefreshLayout.enableLoadMore(true);
  }

  @Override protected void onLoadMore() {
    mPage++;
    mPresenter.getData(GankApi.福利, sSize, mPage);
  }
}
