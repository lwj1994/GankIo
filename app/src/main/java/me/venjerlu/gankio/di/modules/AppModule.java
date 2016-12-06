package me.venjerlu.gankio.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Author/Date: venjerLu / 2016/12/6 22:20
 * Email:       alwjlola@gmail.com
 * Description:
 */
@Module public class AppModule {
  private Context mContext;

  public AppModule(Context mContext) {
    this.mContext = mContext;
  }

  @Provides @Singleton public Context provideContext() {
    return mContext;
  }
}
