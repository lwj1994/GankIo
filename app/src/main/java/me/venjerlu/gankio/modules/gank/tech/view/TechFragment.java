package me.venjerlu.gankio.modules.gank.tech.view;

import android.os.Bundle;
import java.util.List;
import me.venjerlu.gankio.common.fragment.BaseListFragment;
import me.venjerlu.gankio.modules.gank.common.TypePresenter;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.tech.adapter.TechAdapter;

/**
 * Author/Date: venjerLu / 2016/12/16 10:59
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class TechFragment extends BaseListFragment<TypePresenter<ITechView>, TechAdapter>
    implements ITechView {
  private static final String TAG = "TechFragment";
  private static final String TYPE = TAG + "type";
  private static final int sSize = 10;
  private int mPage;

  public static TechFragment newInstance(String type) {
    Bundle args = new Bundle();
    args.putString(TYPE, type);
    TechFragment fragment = new TechFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void initData() {
    super.initData();
    mPresenter.setOnClickItemBus(_mActivity);
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override public void onRefresh() {
    mPage = 1;
    mPresenter.getData(getArguments().getString(TYPE), sSize, mPage);
    mPullToRefreshLayout.enableLoadMore(true);
  }

  @Override protected void onLoadMore() {
    mPage ++;
    mPresenter.getData(getArguments().getString(TYPE), sSize, mPage);
  }

  @Override public void showError(String msg) {

  }

  @Override public void setRefreshing(boolean refresh) {

  }

  @Override public void onGetData(List<Gank> mGanks) {
    mAdapter.insertData(mGanks);
  }
}