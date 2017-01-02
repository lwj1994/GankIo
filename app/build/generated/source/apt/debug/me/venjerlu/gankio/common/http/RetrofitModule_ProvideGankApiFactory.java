// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package me.venjerlu.gankio.common.http;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class RetrofitModule_ProvideGankApiFactory implements Factory<GankApi> {
  private final RetrofitModule module;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public RetrofitModule_ProvideGankApiFactory(
      RetrofitModule module, Provider<OkHttpClient> okHttpClientProvider) {
    assert module != null;
    this.module = module;
    assert okHttpClientProvider != null;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public GankApi get() {
    return Preconditions.checkNotNull(
        module.provideGankApi(okHttpClientProvider.get()),
        "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<GankApi> create(
      RetrofitModule module, Provider<OkHttpClient> okHttpClientProvider) {
    return new RetrofitModule_ProvideGankApiFactory(module, okHttpClientProvider);
  }

  /** Proxies {@link RetrofitModule#provideGankApi(OkHttpClient)}. */
  public static GankApi proxyProvideGankApi(RetrofitModule instance, OkHttpClient okHttpClient) {
    return instance.provideGankApi(okHttpClient);
  }
}