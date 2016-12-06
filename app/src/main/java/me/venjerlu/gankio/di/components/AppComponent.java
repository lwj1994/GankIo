package me.venjerlu.gankio.di.components;

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import me.venjerlu.gankio.di.modules.AppModule;

/**
 * Author/Date: venjerLu / 2016/12/6 22:20
 * Email:       alwjlola@gmail.com
 * Description:
 */
@Singleton @Component(modules = AppModule.class) public interface AppComponent {
  Context getContext();
}
