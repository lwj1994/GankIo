package me.venjerlu.gankio.common.di.component;

import android.app.Activity;
import dagger.Component;
import me.venjerlu.gankio.common.di.module.FragmentModule;
import me.venjerlu.gankio.common.di.scope.PreFragment;
import me.venjerlu.gankio.modules.gank.meizhi.view.MeizhiFragment;
import me.venjerlu.gankio.modules.gank.today.view.TodayFragment;

/**
 * Author/Date: venjerLu / 2016/12/7 14:29
 * Email:       alwjlola@gmail.com
 * Description:
 */
@PreFragment @Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
  Activity getActivty();

  void inject(TodayFragment fragment);

  void inject(MeizhiFragment fragment);
}