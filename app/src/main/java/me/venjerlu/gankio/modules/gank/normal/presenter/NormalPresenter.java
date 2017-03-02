package me.venjerlu.gankio.modules.gank.normal.presenter;

import java.util.List;
import javax.inject.Inject;
import me.venjerlu.gankio.common.mvp.RxPresenter;
import me.venjerlu.gankio.http.GankApi;
import me.venjerlu.gankio.http.GankSubscriber;
import me.venjerlu.gankio.model.Gank;
import me.venjerlu.gankio.model.GankModel;
import me.venjerlu.gankio.modules.gank.normal.view.INormalView;
import me.venjerlu.gankio.utils.RxUtil;

/**
 * Author/Date: venjerLu / 2016/12/9 10:43
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class NormalPresenter extends RxPresenter<INormalView> {
  private static final String TAG = "NormalPresenter";

  @Inject NormalPresenter(GankApi gankApi) {
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
}
