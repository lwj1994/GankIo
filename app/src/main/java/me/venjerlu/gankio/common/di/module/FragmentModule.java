package me.venjerlu.gankio.common.di.module;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;
import me.venjerlu.gankio.common.di.scope.PreFragment;
import me.venjerlu.gankio.common.fragment.BaseFragment;

/**
 * Author/Date: venjerLu / 2016/12/7 14:26
 * Email:       alwjlola@gmail.com
 * Description:
 */
@Module public class FragmentModule {
  private BaseFragment mFragment;

  public FragmentModule(BaseFragment fragment) {
    mFragment = fragment;
  }

  @Provides @PreFragment  Activity provideActivity() {
    return mFragment.getActivity();
  }
}
