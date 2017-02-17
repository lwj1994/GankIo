package me.venjerlu.gankio.modules.gank.bus;

/**
 * Author/Date: venjerLu / 2017/2/16 14:13
 * Email:       alwjlola@gmail.com
 * Description: 跳转至WebActivity
 */
public class OnStartWebActivityBus {
  private static final String TAG = "OnStartWebActivityBus";
  public String url, title;

  public OnStartWebActivityBus(String url, String title) {
    this.url = url;
    this.title = title;
  }
}
