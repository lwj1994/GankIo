package me.venjerlu.gankio.common.di.module;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import me.venjerlu.gankio.common.di.scope.PreFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author/Date: venjerLu / 2016/12/7 14:26
 * Email:       alwjlola@gmail.com
 * Description:
 */
@Module public class FragmentModule {
  private SupportFragment mFragment;

  public FragmentModule(SupportFragment fragment) {
    mFragment = fragment;
  }

  @Provides @PreFragment Activity provideActivity() {
    return mFragment.getActivity();
  }
}
