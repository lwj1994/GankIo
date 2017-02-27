package me.venjerlu.gankio.bus;

import android.support.annotation.DrawableRes;

/**
 * Author/Date: venjerLu / 2017/2/17 15:28
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class OnStartGalleryFragmentBus {
  private static final String TAG = "OnStartGalleryFragmentBus";
  public int type;
  public @DrawableRes int res;
  public String url;
  public String title;

  public OnStartGalleryFragmentBus(int type, int res, String url, String title) {
    this.type = type;
    this.res = res;
    this.url = url;
    this.title = title;
  }
}
