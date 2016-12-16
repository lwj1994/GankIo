package me.venjerlu.gankio.modules.gank.common;

import android.app.Activity;
import com.elvishew.xlog.XLog;
import com.thefinestartist.finestwebview.FinestWebView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.List;
import javax.inject.Inject;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.common.http.GankSubscriber;
import me.venjerlu.gankio.common.mvp.RxPresenter;
import me.venjerlu.gankio.modules.gank.common.view.IGankTypeView;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.model.GankModel;
import me.venjerlu.gankio.modules.gank.tech.bus.OnclickTechBus;
import me.venjerlu.gankio.utils.RxUtil;

/**
 * Author/Date: venjerLu / 2016/12/9 10:43
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TypePresenter<T extends IGankTypeView> extends RxPresenter<T> {
  private static final String TAG = "TypePresenter";
  @Inject TypePresenter(GankApi gankApi) {
    mGankApi = gankApi;
  }

  public void getData(String type, int size, int page) {
    addDisposable(mGankApi.getData(type, size, page)
        .compose(RxUtil.<GankModel<List<Gank>>>applySchedulers())
        .subscribeWith(new GankSubscriber<List<Gank>>() {
          @Override public void onSuccess(List<Gank> result) {
            mView.onGetData(result);
          }

          @Override public void onCompleted() {
            mView.onRefreshCompleted();
          }
        }));
  }

  public void setOnClickItemBus(final Activity activity) {
    addDisposable(RxBus.getDefault()
        .toObservable(OnclickTechBus.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<OnclickTechBus>() {
          @Override public void accept(OnclickTechBus onclickTechContentBus)
              throws Exception {
            XLog.tag(TAG).d(mView);
            new FinestWebView.Builder(activity).show(onclickTechContentBus.getUrl());
          }
        }));
  }
}
