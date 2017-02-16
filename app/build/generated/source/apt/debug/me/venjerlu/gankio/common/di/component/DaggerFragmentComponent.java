// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package me.venjerlu.gankio.common.di.component;

import android.app.Activity;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.venjerlu.gankio.common.di.module.FragmentModule;
import me.venjerlu.gankio.common.di.module.FragmentModule_ProvideActivityFactory;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.modules.gank.meizhi.adapter.MeizhiAdapter;
import me.venjerlu.gankio.modules.gank.meizhi.adapter.MeizhiAdapter_Factory;
import me.venjerlu.gankio.modules.gank.meizhi.view.MeizhiFragment;
import me.venjerlu.gankio.modules.gank.meizhi.view.MeizhiFragment_MembersInjector;
import me.venjerlu.gankio.modules.gank.normal.adapter.NormalAdapter;
import me.venjerlu.gankio.modules.gank.normal.adapter.NormalAdapter_Factory;
import me.venjerlu.gankio.modules.gank.normal.view.INormalView;
import me.venjerlu.gankio.modules.gank.normal.view.NormalFragment;
import me.venjerlu.gankio.modules.gank.normal.view.NormalFragment_MembersInjector;
import me.venjerlu.gankio.modules.gank.normal.view.NormalPresenter;
import me.venjerlu.gankio.modules.gank.normal.view.NormalPresenter_Factory;
import me.venjerlu.gankio.modules.gank.today.presenter.TodayAdapter;
import me.venjerlu.gankio.modules.gank.today.presenter.TodayAdapter_Factory;
import me.venjerlu.gankio.modules.gank.today.presenter.TodayPresenter;
import me.venjerlu.gankio.modules.gank.today.presenter.TodayPresenter_Factory;
import me.venjerlu.gankio.modules.gank.today.view.TodayFragment;
import me.venjerlu.gankio.modules.gank.today.view.TodayFragment_MembersInjector;

public final class DaggerFragmentComponent implements FragmentComponent {
  private Provider<Activity> provideActivityProvider;

  private Provider<GankApi> getGankApiProvider;

  private Provider<TodayPresenter> todayPresenterProvider;

  private Provider<TodayAdapter> todayAdapterProvider;

  private MembersInjector<TodayFragment> todayFragmentMembersInjector;

  @SuppressWarnings("rawtypes")
  private Provider normalPresenterProvider;

  private Provider<MeizhiAdapter> meizhiAdapterProvider;

  private MembersInjector<MeizhiFragment> meizhiFragmentMembersInjector;

  private Provider<NormalPresenter<INormalView>> normalPresenterProvider2;

  private Provider<NormalAdapter> normalAdapterProvider;

  private MembersInjector<NormalFragment> normalFragmentMembersInjector;

  private DaggerFragmentComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.provideActivityProvider =
        DoubleCheck.provider(FragmentModule_ProvideActivityFactory.create(builder.fragmentModule));

    this.getGankApiProvider =
        new Factory<GankApi>() {
          private final AppComponent appComponent = builder.appComponent;

          @Override
          public GankApi get() {
            return Preconditions.checkNotNull(
                appComponent.getGankApi(),
                "Cannot return null from a non-@Nullable component method");
          }
        };

    this.todayPresenterProvider =
        TodayPresenter_Factory.create(MembersInjectors.<TodayPresenter>noOp(), getGankApiProvider);

    this.todayAdapterProvider = TodayAdapter_Factory.create(MembersInjectors.<TodayAdapter>noOp());

    this.todayFragmentMembersInjector =
        TodayFragment_MembersInjector.create(todayPresenterProvider, todayAdapterProvider);

    this.normalPresenterProvider =
        NormalPresenter_Factory.create(
            ((MembersInjector) MembersInjectors.noOp()), getGankApiProvider);

    this.meizhiAdapterProvider =
        MeizhiAdapter_Factory.create(MembersInjectors.<MeizhiAdapter>noOp());

    this.meizhiFragmentMembersInjector =
        MeizhiFragment_MembersInjector.create(normalPresenterProvider, meizhiAdapterProvider);

    this.normalPresenterProvider2 =
        NormalPresenter_Factory.create(
            MembersInjectors.<NormalPresenter<INormalView>>noOp(), getGankApiProvider);

    this.normalAdapterProvider =
        NormalAdapter_Factory.create(MembersInjectors.<NormalAdapter>noOp());

    this.normalFragmentMembersInjector =
        NormalFragment_MembersInjector.create(normalPresenterProvider2, normalAdapterProvider);
  }

  @Override
  public Activity getActivty() {
    return provideActivityProvider.get();
  }

  @Override
  public void inject(TodayFragment fragment) {
    todayFragmentMembersInjector.injectMembers(fragment);
  }

  @Override
  public void inject(MeizhiFragment fragment) {
    meizhiFragmentMembersInjector.injectMembers(fragment);
  }

  @Override
  public void inject(NormalFragment normalFragment) {
    normalFragmentMembersInjector.injectMembers(normalFragment);
  }

  public static final class Builder {
    private FragmentModule fragmentModule;

    private AppComponent appComponent;

    private Builder() {}

    public FragmentComponent build() {
      if (fragmentModule == null) {
        throw new IllegalStateException(FragmentModule.class.getCanonicalName() + " must be set");
      }
      if (appComponent == null) {
        throw new IllegalStateException(AppComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerFragmentComponent(this);
    }

    public Builder fragmentModule(FragmentModule fragmentModule) {
      this.fragmentModule = Preconditions.checkNotNull(fragmentModule);
      return this;
    }

    public Builder appComponent(AppComponent appComponent) {
      this.appComponent = Preconditions.checkNotNull(appComponent);
      return this;
    }
  }
}
