package me.venjerlu.gankio.bus;

/**
 * Author/Date: venjerLu / 2017/3/2 14:35
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class OnUpdateTodayBus {
  private static final String TAG = "OnUpdateTodayBus";
  public String year;
  public String month;
  public String day;

  public OnUpdateTodayBus(String year, String month, String day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }
}
