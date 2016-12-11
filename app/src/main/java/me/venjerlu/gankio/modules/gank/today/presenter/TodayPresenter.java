package me.venjerlu.gankio.modules.gank.today.presenter;

import android.support.v7.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import java.util.List;
import javax.inject.Inject;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.common.http.GankSubscriber;
import me.venjerlu.gankio.common.mvp.RxPresenter;
import me.venjerlu.gankio.modules.gank.model.DateModel;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.model.GankModel;
import me.venjerlu.gankio.modules.gank.today.view.ITodayView;
import me.venjerlu.gankio.utils.RxUtil;
import org.reactivestreams.Publisher;

/**
 * Author/Date: venjerLu / 2016/12/10 20:15
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TodayPresenter extends RxPresenter<ITodayView> {
  @Inject TodayPresenter(GankApi api) {
    mGankApi = api;
    //mList = new ArrayList<>();
    //mAdapter = new TodaySectionAdapter(mList);
  }

  public void setAdapter(RecyclerView recyclerView) {
  }

  /**
   * 根据日期得到数据
   */
  public void getDataByDate(String year, String month, String day) {
    addDisposable(mGankApi.getDataOnSomeday(year, month, day)
        .compose(RxUtil.<GankModel<DateModel>>applySchedulers())
        .subscribeWith(new GankSubscriber<DateModel>() {
          @Override public void onSuccess(DateModel results) {
            List<Gank> android = results.getAndroid();
            List<Gank> ios = results.getiOS();
            List<Gank> front = results.getFront();
            List<Gank> expande = results.getExpand();
          }
        }));
  }

  /**
   * 得到最近一天的数据
   */
  public void getLatestData() {
    mGankApi.getHistoryDate()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .flatMap(new Function<GankModel<List<String>>, Publisher<?>>() {
          @Override public Publisher<?> apply(GankModel<List<String>> listGankModel)
              throws Exception {
            String latestDate = listGankModel.getResults().get(0);
            String[] split = latestDate.split("-");
            String year = split[0];
            String month = split[1];
            String day = split[2];
            XLog.d(year);
            XLog.d(month);
            XLog.d(day);
            return mGankApi.getDataOnSomeday(year, month, day);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new ResourceSubscriber<Object>() {
          @Override public void onNext(Object o) {
            GankModel<DateModel> model = (GankModel) o;
            DateModel results = model.getResults();
            mView.onGetLatestData(results);
          }

          @Override public void onError(Throwable t) {
            mView.showError(t.getMessage());
          }

          @Override public void onComplete() {
            mView.onRefreshCompleted();
          }
        });
  }
}
