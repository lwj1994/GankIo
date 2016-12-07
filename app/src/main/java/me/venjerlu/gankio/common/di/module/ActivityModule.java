package me.venjerlu.gankio.common.di.module;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import me.venjerlu.gankio.common.di.scope.PreActivity;

/**
 * Author/Date: venjerLu / 2016/12/7 14:26
 * Email:       alwjlola@gmail.com
 * Description:
 */
@Module public class ActivityModule {
  private Activity mActivity;

  public ActivityModule(Activity activity) {
    mActivity = activity;
  }

  @Provides @PreActivity public Activity provideActivity() {
    return mActivity;
  }
}
