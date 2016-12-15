package me.venjerlu.gankio.utils.glide;

import android.content.Context;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import java.io.InputStream;

/**
 * Author/Date: venjerLu / 2016/12/14 20:20
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class CustomImageSizeUrlLoader extends BaseGlideUrlLoader<CustomImageSizeModel> {
  public CustomImageSizeUrlLoader(Context context) {
    super(context);
  }

  public CustomImageSizeUrlLoader(Context context,
      ModelCache<CustomImageSizeModel, GlideUrl> modelCache) {
    super(context, modelCache);
  }

  public CustomImageSizeUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader) {
    super(concreteLoader);
  }

  public CustomImageSizeUrlLoader(ModelLoader<GlideUrl, InputStream> concreteLoader,
      ModelCache<CustomImageSizeModel, GlideUrl> modelCache) {
    super(concreteLoader, modelCache);
  }

  @Override protected String getUrl(CustomImageSizeModel model, int width, int height) {
    return model.requestCustomSizeUrl(width, height);
  }
}
