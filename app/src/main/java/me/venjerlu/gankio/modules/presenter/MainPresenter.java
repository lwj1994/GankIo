package me.venjerlu.gankio.modules.presenter;

import java.util.List;
import javax.inject.Inject;
import me.venjerlu.gankio.common.di.scope.PreFragment;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.common.http.HttpSubscriber;
import me.venjerlu.gankio.common.mvp.BaseModel;
import me.venjerlu.gankio.common.mvp.GankModel;
import me.venjerlu.gankio.common.mvp.RxPresenter;
import me.venjerlu.gankio.modules.view.MainFragment;
import me.venjerlu.gankio.utils.RxUtil;

/**
 * Author/Date: venjerLu / 2016/12/9 10:43
 * Email:       alwjlola@gmail.com
 * Description:
 */
@PreFragment public class MainPresenter extends RxPresenter<MainFragment> {

  @Inject MainPresenter(GankApi gankApi) {
    mGankApi = gankApi;
  }

  public void getData(String type, int size, int page) {
    addDisposable(mGankApi.getData(type, size, page)
        .compose(RxUtil.<BaseModel<List<GankModel>>>applySchedulers())
        .subscribeWith(new HttpSubscriber<List<GankModel>>() {
          @Override public void onSuccess(List<GankModel> result) {
            mView.onGetData(result);
          }
        }));
  }
}
