package me.venjerlu.gankio.modules.gank.today.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.di.scope.PreFragment;
import me.venjerlu.gankio.common.fragment.BaseFragment;
import me.venjerlu.gankio.modules.gank.today.model.TodaySectionModel;
import me.venjerlu.gankio.modules.gank.today.presenter.TodayPresenter;

/**
 * Author/Date: venjerLu / 2016/12/10 19:59
 * Email:       alwjlola@gmail.com
 * Description:
 */
@PreFragment
public class TodayFragment extends BaseFragment<TodayPresenter> implements ITodayView {
  @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
  @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;
  public static TodayFragment newInstance() {
    Bundle args = new Bundle();
    TodayFragment fragment = new TodayFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected int getLayout() {
    return R.layout.frgament_gank_today;
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initData() {
    mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
    mPresenter.setAdapter(mRecyclerView);
    mPresenter.getDataByDate("2016","12","08");
  }

  @Override public void showError(String msg) {

  }

  @Override public void setRefreshing(boolean refresh) {

  }

  @Override public void onGetLatestData(List<TodaySectionModel> list) {

  }
}
