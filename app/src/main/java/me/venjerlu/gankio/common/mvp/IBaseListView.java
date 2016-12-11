package me.venjerlu.gankio.common.mvp;

/**
 * Author/Date: venjerLu / 2016/12/11 14:20
 * Email:       alwjlola@gmail.com
 * Description:
 */
public interface IBaseListView  extends IBaseView{
  void onRefresh();
  void onRefreshCompleted();
}
