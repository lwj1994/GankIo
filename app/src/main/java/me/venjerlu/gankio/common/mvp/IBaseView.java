package me.venjerlu.gankio.common.mvp;

/**
 * Author/Date: venjerLu / 2016/12/6 23:23
 * Email:       alwjlola@gmail.com
 * Description:
 */

public interface IBaseView {
  void showError(String msg);

  void setRefreshing(boolean refresh);
}
