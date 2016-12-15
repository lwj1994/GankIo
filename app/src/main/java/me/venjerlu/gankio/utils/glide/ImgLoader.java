package me.venjerlu.gankio.utils.glide;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Author/Date: venjerLu / 2016/12/14 18:24
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class ImgLoader {
  public static final String ANDROID_RESOURCE = "android.resource://";
  public static final String FOREWARD_SLASH = "/";

  private ImgLoader() {
  }

  public static ImgLoader getInstance() {
    return SingleLoader.INSTANCE;
  }

  private static Uri resourceIdToUri(Context context, int resourceId) {
    return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
  }

  public void load(Context context, String url, ImageView imageView) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .crossFade()
        .diskCacheStrategy(DiskCacheStrategy.RESULT)
        .into(imageView);
  }

  //加载网络图片并设置大小
  public void displayImage(Context context, String url, ImageView imageView, int width,
      int height) {
    Glide.with(context).load(url).centerCrop().override(width, height).
        diskCacheStrategy(DiskCacheStrategy.RESULT).crossFade().into(imageView);
  }

  private static class SingleLoader {
    private static final ImgLoader INSTANCE = new ImgLoader();
  }
}
