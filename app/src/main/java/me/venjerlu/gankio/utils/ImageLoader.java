package me.venjerlu.gankio.utils;

import android.net.Uri;
import android.text.TextUtils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Author/Date: venjerLu / 2016/12/14 09:42
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class ImageLoader {
  private ImageLoader() {
  }

  public static ImageLoader getInstance() {
    return SingleLoader.INSTANCE;
  }

  public void load(SimpleDraweeView draweeView, String url) {
    if (!TextUtils.isEmpty(url)) draweeView.setImageURI(Uri.parse(url));
  }

  private static class SingleLoader {
    private static final ImageLoader INSTANCE = new ImageLoader();
  }
}
