package me.venjerlu.gankio.common.di.module;

import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;
import me.venjerlu.gankio.modules.gank.model.Gank;

/**
 * Author/Date: venjerLu / 2016/12/11 15:00
 * Email:       alwjlola@gmail.com
 * Description:
 */

@Module  class ModelModule {

  @Provides List<Gank> provideGankList() {
    return new ArrayList<>();
  }
}
