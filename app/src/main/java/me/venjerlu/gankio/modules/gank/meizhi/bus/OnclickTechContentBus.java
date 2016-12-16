package me.venjerlu.gankio.modules.gank.meizhi.bus;

/**
 * Author/Date: venjerLu / 2016/12/16 10:26
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class OnclickTechContentBus {
  private String url;

  public OnclickTechContentBus(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}
