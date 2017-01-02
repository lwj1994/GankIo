// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package me.venjerlu.gankio.modules.gank.common;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Provider;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.modules.gank.common.view.IGankTypeView;

public final class TypePresenter_Factory<T extends IGankTypeView>
    implements Factory<TypePresenter<T>> {
  private final MembersInjector<TypePresenter<T>> typePresenterMembersInjector;

  private final Provider<GankApi> gankApiProvider;

  public TypePresenter_Factory(
      MembersInjector<TypePresenter<T>> typePresenterMembersInjector,
      Provider<GankApi> gankApiProvider) {
    assert typePresenterMembersInjector != null;
    this.typePresenterMembersInjector = typePresenterMembersInjector;
    assert gankApiProvider != null;
    this.gankApiProvider = gankApiProvider;
  }

  @Override
  public TypePresenter<T> get() {
    return MembersInjectors.injectMembers(
        typePresenterMembersInjector, new TypePresenter<T>(gankApiProvider.get()));
  }

  public static <T extends IGankTypeView> Factory<TypePresenter<T>> create(
      MembersInjector<TypePresenter<T>> typePresenterMembersInjector,
      Provider<GankApi> gankApiProvider) {
    return new TypePresenter_Factory<T>(typePresenterMembersInjector, gankApiProvider);
  }
}