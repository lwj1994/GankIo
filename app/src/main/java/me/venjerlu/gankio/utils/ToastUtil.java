package me.venjerlu.gankio.utils;

import com.blankj.utilcode.utils.ToastUtils;
import me.venjerlu.gankio.App;

/**
 * Author/Date: venjerLu / 2016/12/10 16:01
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class ToastUtil {

  public static void shortMsg(String msg){
    ToastUtils.showShortToastSafe(App.getAppComponent().getContext(),msg);
  }

  public static void shortMsg(int msg) {
    ToastUtils.showShortToastSafe(App.getAppComponent().getContext(), msg);
  }

  public static void longMsg(String msg){
    ToastUtils.showLongToastSafe(App.getAppComponent().getContext(), msg);
  }

  public static void longMsg(int msg) {
    ToastUtils.showLongToastSafe(App.getAppComponent().getContext(),msg);
  }
}
