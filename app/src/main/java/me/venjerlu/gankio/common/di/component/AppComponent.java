package me.venjerlu.gankio.common.di.component;

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import me.venjerlu.gankio.common.di.module.AppModule;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.common.http.RetrofitModule;

/**
 * Author/Date: venjerLu / 2016/12/6 22:20
 * Email:       alwjlola@gmail.com
 * Description:
 */
@Singleton @Component(modules = { AppModule.class, RetrofitModule.class })
public interface AppComponent {
  Context getContext();

  GankApi getGankApi();
}
