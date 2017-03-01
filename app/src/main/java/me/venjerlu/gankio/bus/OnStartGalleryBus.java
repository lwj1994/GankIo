package me.venjerlu.gankio.bus;

import android.support.annotation.DrawableRes;
import java.util.List;

/**
 * Author/Date: venjerLu / 2017/2/17 15:28
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class OnStartGalleryBus {
  private static final String TAG = "OnStartGalleryBus";
  public int type;
  public @DrawableRes int res;
  public String url;
  public List<String> urls;
  public String title;
  public int position;

  public OnStartGalleryBus(int type, int res, String url, String title) {
    this.type = type;
    this.res = res;
    this.url = url;
    this.title = title;
  }

  public OnStartGalleryBus(int type, List<String> urls, int position, String title) {
    this.type = type;
    this.urls = urls;
    this.position = position;
    this.title = title;
  }
}
