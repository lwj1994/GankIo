package me.venjerlu.gankio.utils.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import me.venjerlu.gankio.R;

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

  public void normal(Context context, String url, ImageView imageView) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_github)
        .crossFade()
        .into(imageView);
  }

  public void circle(Context context, String url, ImageView imageView) {
    Glide.with(context)
        .load(url)
        .bitmapTransform(new CropCircleTransformation(context))
        .crossFade()
        .into(imageView);
  }

  public void roundedCorners(Context context, String url, ImageView imageView, int corner,
      int margin) {
    Glide.with(context)
        .load(url)
        .bitmapTransform(new RoundedCornersTransformation(context, corner, margin))
        .crossFade()
        .into(imageView);
  }

  public void size(Context context, String url, ImageView imageView,
      SizeReadyCallback sizeReadyCallback) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .crossFade()
        .placeholder(R.drawable.ic_github)
        .into(imageView)
        .getSize(sizeReadyCallback);
  }

  public void bitmap(Context context, String url, SimpleTarget<Bitmap> target) {
    Glide.with(context).load(url).asBitmap().placeholder(R.drawable.ic_github).into(target);
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
