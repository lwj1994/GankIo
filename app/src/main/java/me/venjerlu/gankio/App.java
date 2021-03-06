package me.venjerlu.gankio;

import android.app.Application;
import com.blankj.utilcode.utils.CrashUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.blankj.utilcode.utils.Utils;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import me.venjerlu.gankio.common.di.component.AppComponent;
import me.venjerlu.gankio.common.di.component.DaggerAppComponent;
import me.venjerlu.gankio.common.di.module.AppModule;
import me.venjerlu.gankio.http.RetrofitModule;
import me.venjerlu.gankio.widget.AppBlockCanaryContext;
import me.yokeyword.fragmentation.Fragmentation;

/**
 * Author/Date: venjerLu / 2016/12/6 21:27
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class App extends Application {
  private static AppComponent mAppComponent;

  public static AppComponent getAppComponent() {
    return mAppComponent;
  }

  @Override public void onCreate() {
    super.onCreate();
    mAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this)).retrofitModule(new RetrofitModule())
        .build();
    ToastUtils.init(false);
    LeakCanary.install(this);
    BlockCanary.install(this, new AppBlockCanaryContext()).start();
    Utils.init(this);
    CrashUtils.getInstance().init();
    XLog.init(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE);

    if (BuildConfig.DEBUG) {
      Fragmentation.builder()
          // 显示悬浮球 ; 其他Mode:SHAKE: 摇一摇唤出   NONE：隐藏
          .stackViewMode(Fragmentation.BUBBLE).install();
    }
    Stetho.initializeWithDefaults(this);

    CrashReport.initCrashReport(getApplicationContext(), Constants.BUGLY_APP_ID, false);

  }
}
