package me.venjerlu.gankio.common.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.venjerlu.gankio.http.GankApi;

/**
 * Author/Date: venjerLu / 2016/12/7 10:03
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class RxPresenter<T extends IBaseView> implements IBasePresenter<T>{
  protected T mView;
  protected GankApi mGankApi;
  private CompositeDisposable mCompositeDisposable;

  @Override public void attachView(T view) {
    this.mView = view;
  }

  @Override public void detachView() {
    this.mView = null;
    clearDisposable();
  }

  private void clearDisposable() {
    if (mCompositeDisposable != null) {
      mCompositeDisposable.clear();
    }
  }

  protected void addDisposable(Disposable disposable) {
    if (mCompositeDisposable == null) {
      mCompositeDisposable = new CompositeDisposable();
    }
    mCompositeDisposable.add(disposable);
  }
}
