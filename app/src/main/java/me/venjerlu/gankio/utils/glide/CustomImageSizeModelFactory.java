package me.venjerlu.gankio.utils.glide;

import android.content.Context;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;

/**
 * Author/Date: venjerLu / 2016/12/14 20:19
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class CustomImageSizeModelFactory implements
    com.bumptech.glide.load.model.ModelLoaderFactory<CustomImageSizeModel, java.io.InputStream> {
  @Override public ModelLoader<CustomImageSizeModel, InputStream> build(Context context,
      GenericLoaderFactory factories) {
    return new CustomImageSizeUrlLoader(context);
  }

  @Override public void teardown() {

  }
}
