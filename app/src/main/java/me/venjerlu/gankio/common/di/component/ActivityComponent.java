package me.venjerlu.gankio.common.di.component;

import android.app.Activity;
import dagger.Component;
import me.venjerlu.gankio.common.di.module.ActivityModule;
import me.venjerlu.gankio.common.di.scope.PreActivity;

/**
 * Author/Date: venjerLu / 2016/12/7 14:23
 * Email:       alwjlola@gmail.com
 * Description:
 */
@PreActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
  Activity getActivty();

  void inject(Activity activity);
}
