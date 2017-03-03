package me.venjerlu.gankio.model;

/**
 * Author/Date: venjerLu / 2017/3/3 17:10
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class PostGankModel {
  private static final String TAG = "PostGankModel";

  /**
   * error : true
   * msg : url存在问题
   */

  private boolean error;
  private String msg;

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
