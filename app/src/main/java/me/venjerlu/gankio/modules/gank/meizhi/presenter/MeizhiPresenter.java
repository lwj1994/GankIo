package me.venjerlu.gankio.modules.gank.meizhi.presenter;

import android.graphics.Bitmap;
import com.elvishew.xlog.XLog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import me.venjerlu.gankio.App;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.common.mvp.RxPresenter;
import me.venjerlu.gankio.modules.gank.bus.OnNotifyDataBus;
import me.venjerlu.gankio.modules.gank.meizhi.view.IMeizhiView;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.model.GankModel;
import me.venjerlu.gankio.utils.glide.ImgLoader;

/**
 * Author/Date: venjerLu / 2017/2/18 16:50
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class MeizhiPresenter extends RxPresenter<IMeizhiView> {
  private static final String TAG = "MeizhiPresenter";

  @Inject MeizhiPresenter(GankApi gankApi) {
    mGankApi = gankApi;
  }

  public void getData(String type, int size, int page) {
    addDisposable(mGankApi.getData(type, size, page)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .map(new Function<GankModel<List<Gank>>, List<Gank>>() {
          @Override public List<Gank> apply(GankModel<List<Gank>> listGankModel) throws Exception {

            List<Gank> results = listGankModel.getResults();
            for (final Gank data : results) {
              Bitmap bitmap;
              try {
                bitmap = ImgLoader.getInstance()
                    .getBitmap(App.getAppComponent().getContext(), data.getUrl());
                if (bitmap != null) {
                  XLog.tag(TAG)
                      .d("width = " + bitmap.getWidth() + "\nheight = " + bitmap.getHeight());
                  data.setWidth(bitmap.getWidth());
                  data.setHeight(bitmap.getHeight());
                }
              } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                XLog.tag(TAG).d(e.getMessage());
              }
            }

            return results;
          }
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSubscriber<List<Gank>>() {
          @Override public void onNext(List<Gank> list) {
            mView.onGetData(list);
          }

          @Override public void onError(Throwable t) {

          }

          @Override public void onComplete() {
            mView.onRefreshCompleted();
          }
        }));
  }

  public void setOnNotifyData() {
    addDisposable(RxBus.getDefault()
        .toObservable(OnNotifyDataBus.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<OnNotifyDataBus>() {
          @Override public void accept(OnNotifyDataBus onNotifyDataBus) throws Exception {
            mView.onNotifyData(onNotifyDataBus.datas);
          }
        }));
  }
}
