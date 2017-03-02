package me.venjerlu.gankio.modules.gank.today.view;

import com.rohitarya.glide.facedetection.transformation.core.GlideFaceDetector;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.bus.OnUpdateTitleBus;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.common.fragment.BaseListFragment;
import me.venjerlu.gankio.model.DateModel;
import me.venjerlu.gankio.model.Gank;
import me.venjerlu.gankio.modules.gank.today.adapter.TodayAdapter;
import me.venjerlu.gankio.modules.gank.today.presenter.TodayPresenter;
import me.venjerlu.gankio.utils.ToastUtil;
import me.venjerlu.gankio.widget.pulltorefresh.section.SectionData;

/**
 * Author/Date: venjerLu / 2016/12/10 19:59
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TodayFragment extends BaseListFragment<TodayPresenter, TodayAdapter>
    implements ITodayView {

  public static TodayFragment newInstance() {
    return new TodayFragment();
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initData() {
    super.initData();
    GlideFaceDetector.initialize(_mActivity);
    mPullToRefreshLayout.enableLoadMore(false);
    mRecyclerView.setVerticalScrollBarEnabled(false);
    mPresenter.setOnUpdateToday();
  }

  @Override public void showError(String msg) {

  }

  @Override public void setRefreshing(boolean refresh) {
    mPullToRefreshLayout.setSwipeRefreshing(refresh);
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
    List<Gank> meizhi = results.getMeizhi();
    if (meizhi == null) {
      ToastUtil.shortMsg("当日没有干货");
      RxBus.getDefault().post(new OnUpdateTitleBus(""));
      return;
    }
    List<Gank> vedio = results.getVedio();
    List<Gank> android = results.getAndroid();
    List<Gank> ios = results.getiOS();
    List<Gank> front = results.getFront();
    List<Gank> expand = results.getExpand();
    List<Gank> app = results.getApp();
    List<Gank> recommend = results.getRecommend();
    mAdapter.clearData();
    addToList(0, "Android", android);
    addToList(1, "iOS", ios);
    addToList(2, "前端", front);
    addToList(3, "App", app);
    addToList(4, "拓展资源", expand);
    addToList(5, "瞎推荐", recommend);
    mAdapter.notifyDataSetChanged();
    mAdapter.addHeader(R.layout.item_today_meizhi, new SectionData<>(meizhi.get(0)));
    mAdapter.addSection(6, "休息视频");
    mAdapter.addFooter(R.layout.item_today_tech, new SectionData<>(vedio.get(0)));
  }

  @Override public void onDestroy() {
    super.onDestroy();
    GlideFaceDetector.releaseDetector();
  }
}
