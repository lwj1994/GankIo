package me.venjerlu.gankio.utils;

import com.blankj.utilcode.utils.ToastUtils;

/**
 * Author/Date: venjerLu / 2016/12/10 16:01
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class ToastUtil {

  public static void shortMsg(String msg){
    ToastUtils.showShortToastSafe(msg);
  }

  public static void shortMsg(int msg) {
    ToastUtils.showShortToastSafe( msg);
  }

  public static void longMsg(String msg){
    ToastUtils.showLongToastSafe(msg);
  }

  public static void longMsg(int msg) {
    ToastUtils.showLongToastSafe(msg);
  }
}
