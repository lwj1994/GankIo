package me.venjerlu.gankio.common.http;

import android.content.Context;
import com.blankj.utilcode.utils.NetworkUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import me.venjerlu.gankio.BuildConfig;
import me.venjerlu.gankio.utils.AndroidUtil;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author/Date: venjerLu / 2016/12/7 10:27
 * Email:       alwjlola@gmail.com
 * Description:
 */
@Module public class RetrofitModule {
  private Context mContext;

  public RetrofitModule(Context mContext) {
    this.mContext = mContext;
  }

  @Provides @Singleton  OkHttpClient provideOkHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
      builder.addInterceptor(loggingInterceptor);
    }

    // set Cache
    File cacheFile = new File(AndroidUtil.getNetCacheDir(mContext));
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
    Interceptor cacheInterceptor = new Interceptor() {
      @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isConnected(mContext)) {
          request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response response = chain.proceed(request);
        if (NetworkUtils.isConnected(mContext)) {
          int maxAge = 0;
          // 有网络时, 不缓存, 最大保存时长为0
          response.newBuilder()
              .header("Cache-Control", "public, max-age=" + maxAge)
              .removeHeader("Pragma")
              .build();
        } else {
          // 无网络时，设置超时为4周
          int maxStale = 60 * 60 * 24 * 28;
          response.newBuilder()
              .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
              .removeHeader("Pragma")
              .build();
        }
        return response;
      }
    };
    return builder.cache(cache)
        .addNetworkInterceptor(cacheInterceptor)
        .addInterceptor(cacheInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build();
  }

  @Singleton @Provides  GankApi provideGankApi(OkHttpClient okHttpClient) {
    return new Retrofit.Builder().baseUrl(GankApi.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(GankApi.class);
  }
}
