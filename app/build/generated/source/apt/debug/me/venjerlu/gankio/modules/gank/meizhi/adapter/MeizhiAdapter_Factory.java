// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package me.venjerlu.gankio.modules.gank.meizhi.adapter;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

public final class MeizhiAdapter_Factory implements Factory<MeizhiAdapter> {
  private final MembersInjector<MeizhiAdapter> meizhiAdapterMembersInjector;

  public MeizhiAdapter_Factory(MembersInjector<MeizhiAdapter> meizhiAdapterMembersInjector) {
    assert meizhiAdapterMembersInjector != null;
    this.meizhiAdapterMembersInjector = meizhiAdapterMembersInjector;
  }

  @Override
  public MeizhiAdapter get() {
    return MembersInjectors.injectMembers(meizhiAdapterMembersInjector, new MeizhiAdapter());
  }

  public static Factory<MeizhiAdapter> create(
      MembersInjector<MeizhiAdapter> meizhiAdapterMembersInjector) {
    return new MeizhiAdapter_Factory(meizhiAdapterMembersInjector);
  }
}