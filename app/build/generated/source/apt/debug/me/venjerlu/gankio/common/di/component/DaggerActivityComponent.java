// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package me.venjerlu.gankio.common.di.component;

import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import me.venjerlu.gankio.common.di.module.ActivityModule;
import me.venjerlu.gankio.modules.gallery.GalleryActivity;
import me.venjerlu.gankio.modules.gallery.GalleryActivity_MembersInjector;
import me.venjerlu.gankio.modules.gallery.GalleryPresenter;
import me.venjerlu.gankio.modules.gallery.GalleryPresenter_Factory;
import me.venjerlu.gankio.modules.main.MainActivity;

public final class DaggerActivityComponent implements ActivityComponent {
  private Provider<GalleryPresenter> galleryPresenterProvider;

  private MembersInjector<GalleryActivity> galleryActivityMembersInjector;

  private DaggerActivityComponent(Builder builder) {
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {

    this.galleryPresenterProvider =
        GalleryPresenter_Factory.create(MembersInjectors.<GalleryPresenter>noOp());

    this.galleryActivityMembersInjector =
        GalleryActivity_MembersInjector.create(galleryPresenterProvider);
  }

  @Override
  public void inject(MainActivity mainActivity) {
    MembersInjectors.<MainActivity>noOp().injectMembers(mainActivity);
  }

  @Override
  public void inject(GalleryActivity galleryActivity) {
    galleryActivityMembersInjector.injectMembers(galleryActivity);
  }

  public static final class Builder {
    private AppComponent appComponent;

    private Builder() {}

    public ActivityComponent build() {
      if (appComponent == null) {
        throw new IllegalStateException(AppComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerActivityComponent(this);
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder activityModule(ActivityModule activityModule) {
      Preconditions.checkNotNull(activityModule);
      return this;
    }

    public Builder appComponent(AppComponent appComponent) {
      this.appComponent = Preconditions.checkNotNull(appComponent);
      return this;
    }
  }
}
