package me.venjerlu.gankio.common.di.component;

import dagger.Component;
import me.venjerlu.gankio.common.di.module.ActivityModule;
import me.venjerlu.gankio.common.di.scope.PreActivity;
import me.venjerlu.gankio.modules.gallery.GalleryActivity;
import me.venjerlu.gankio.modules.main.MainActivity;

/**
 * Author/Date: venjerLu / 2016/12/7 14:23
 * Email:       alwjlola@gmail.com
 * Description:
 */
@PreActivity @Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  void inject(MainActivity mainActivity);

  void inject(GalleryActivity galleryActivity);
}
