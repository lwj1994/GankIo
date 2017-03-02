package me.venjerlu.gankio.modules.gank.today.presenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import me.venjerlu.gankio.bus.OnUpdateTitleBus;
import me.venjerlu.gankio.bus.OnUpdateTodayBus;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.common.mvp.RxPresenter;
import me.venjerlu.gankio.http.GankApi;
import me.venjerlu.gankio.http.GankSubscriber;
import me.venjerlu.gankio.model.DateModel;
import me.venjerlu.gankio.model.GankModel;
import me.venjerlu.gankio.modules.gank.today.view.ITodayView;
import me.venjerlu.gankio.utils.RxUtil;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

/**
 * Author/Date: venjerLu / 2016/12/10 20:15
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TodayPresenter extends RxPresenter<ITodayView> {
  private static final String TAG = "TodayPresenter";

  @Inject TodayPresenter(GankApi api) {
    mGankApi = api;
  }

  /**
   * 根据日期得到数据
   */
  private void getDataByDate(String year, String month, String day) {
    addDisposable(mGankApi.getDataOnSomeday(year, month, day)
        .compose(RxUtil.<GankModel<DateModel>>applySchedulers())
        .doOnSubscribe(new Consumer<Subscription>() {
          @Override public void accept(@NonNull Subscription subscription) throws Exception {
            mView.setRefreshing(true);
          }
        })
        .subscribeWith(new GankSubscriber<DateModel>() {
          @Override public void onSuccess(DateModel dateModel) {
            mView.onGetLatestData(dateModel);
          }

          @Override public void onCompleted() {
            mView.onRefreshCompleted();
          }
        }));
  }

  /**
   * 得到最近一天的数据
   */
  public void getLatestData() {
    addDisposable(mGankApi.getHistoryDate()
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .flatMap(new Function<GankModel<List<String>>, Publisher<GankModel<DateModel>>>() {
          @Override
          public Publisher<GankModel<DateModel>> apply(GankModel<List<String>> listGankModel)
              throws Exception {
            RxBus.getDefault().post(new OnUpdateTitleBus(listGankModel.getResults().get(0)));
            String[] split = listGankModel.getResults().get(0).split("-");
            return mGankApi.getDataOnSomeday(split[0], split[1], split[2]);
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new GankSubscriber<DateModel>() {
          @Override public void onSuccess(DateModel dateModel) {
            mView.onGetLatestData(dateModel);
          }

          @Override public void onCompleted() {
            mView.onRefreshCompleted();
          }
        }));
  }

  public void setOnUpdateToday() {
    addDisposable(RxBus.getDefault()
        .toObservable(OnUpdateTodayBus.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<OnUpdateTodayBus>() {
          @Override public void accept(OnUpdateTodayBus bus) throws Exception {
            getDataByDate(bus.year, bus.month, bus.day);
          }
        }));
  }
}
