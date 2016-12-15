package me.venjerlu.gankio.utils.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Author/Date: venjerLu / 6/27/2016 20:23
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class GlideDiskCacheModule implements GlideModule {
  @Override public void applyOptions(Context context, GlideBuilder builder) {
    // 设置内存缓存大小
    MemorySizeCalculator calculator = new MemorySizeCalculator(context);
    int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
    int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

    int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
    int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

    builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
    builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

    int cacheSize100MegaBytes = 500 * 1024 * 1024;
    builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));
  }

  @Override public void registerComponents(Context context, Glide glide) {

  }
}
