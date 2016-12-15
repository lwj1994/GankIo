package me.venjerlu.gankio.common.di.module;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import me.venjerlu.gankio.common.di.scope.ForApplication;

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

  @Provides @Singleton @ForApplication Context provideContext() {
    return mContext;
  }
}
