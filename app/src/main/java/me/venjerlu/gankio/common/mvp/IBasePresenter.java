package me.venjerlu.gankio.common.mvp;

/**
 * Author/Date: venjerLu / 2016/12/6 23:23
 * Email:       alwjlola@gmail.com
 * Description:
 */

public interface IBasePresenter<T extends IBaseView> {
  void attachView(T view);

  void detachView();
}
