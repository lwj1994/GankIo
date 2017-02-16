package me.venjerlu.gankio.modules.gank;

/**
 * Author/Date: venjerLu / 2017/2/16 14:13
 * Email:       alwjlola@gmail.com
 * Description: 跳转至WebActivity
 */
public class OnIntentToWebViewActBus {
  private static final String TAG = "OnIntentToWebViewActBus";
  public String url, title;

  public OnIntentToWebViewActBus(String url, String title) {
    this.url = url;
    this.title = title;
  }
}
