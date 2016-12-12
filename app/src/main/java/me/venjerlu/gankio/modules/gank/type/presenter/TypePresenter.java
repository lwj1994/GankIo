package me.venjerlu.gankio.modules.gank.type.presenter;

import java.util.List;
import javax.inject.Inject;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.common.http.GankSubscriber;
import me.venjerlu.gankio.common.mvp.RxPresenter;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.model.GankModel;
import me.venjerlu.gankio.modules.gank.type.view.TypeFragment;
import me.venjerlu.gankio.utils.RxUtil;

/**
 * Author/Date: venjerLu / 2016/12/9 10:43
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TypePresenter extends RxPresenter<TypeFragment> {

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
}
