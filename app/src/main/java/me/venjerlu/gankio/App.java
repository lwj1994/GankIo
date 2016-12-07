package me.venjerlu.gankio;

import android.app.Application;
import com.blankj.utilcode.utils.CrashUtils;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import me.venjerlu.gankio.common.di.component.AppComponent;
import me.venjerlu.gankio.common.di.component.DaggerAppComponent;
import me.venjerlu.gankio.common.di.module.AppModule;
import me.venjerlu.gankio.common.http.RetrofitModule;
import me.venjerlu.gankio.widget.AppBlockCanaryContext;

/**
 * Author/Date: venjerLu / 2016/12/6 21:27
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class App extends Application {
  private AppComponent mAppComponent;

  public AppComponent getAppComponent() {
    return mAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();
    mAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .retrofitModule(new RetrofitModule(this))
        .build();
    LeakCanary.install(this);
    BlockCanary.install(this, new AppBlockCanaryContext()).start();
    CrashUtils.getInstance().init(this);
    XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);
  }
}
