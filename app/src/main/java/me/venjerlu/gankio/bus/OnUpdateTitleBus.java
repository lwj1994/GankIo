package me.venjerlu.gankio.bus;

/**
 * Author/Date: venjerLu / 2016/12/13 23:01
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class OnUpdateTitleBus {
  private String title;

  public OnUpdateTitleBus(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
