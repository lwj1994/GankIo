package me.venjerlu.gankio;

import android.app.Application;
import me.venjerlu.gankio.di.components.AppComponent;
import me.venjerlu.gankio.di.components.DaggerAppComponent;
import me.venjerlu.gankio.di.modules.AppModule;

/**
 * Author/Date: venjerLu / 2016/12/6 21:27
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class App extends Application {
  private AppComponent mAppComponent;

  @Override public void onCreate() {
    super.onCreate();
    mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }
}
