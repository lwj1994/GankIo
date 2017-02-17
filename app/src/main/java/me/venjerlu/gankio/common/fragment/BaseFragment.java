package me.venjerlu.gankio.common.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import javax.inject.Inject;
import me.venjerlu.gankio.App;
import me.venjerlu.gankio.common.di.component.AppComponent;
import me.venjerlu.gankio.common.di.component.DaggerFragmentComponent;
import me.venjerlu.gankio.common.di.component.FragmentComponent;
import me.venjerlu.gankio.common.di.module.FragmentModule;
import me.venjerlu.gankio.common.mvp.IBasePresenter;
import me.venjerlu.gankio.common.mvp.IBaseView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author/Date: venjerLu / 2016/12/6 23:21
 * Email:       alwjlola@gmail.com
 * Description:
 */

public abstract class BaseFragment<T extends IBasePresenter> extends SupportFragment
    implements IBaseView {
  @Inject protected T mPresenter;
  protected View mView;
  protected Unbinder mUnbinder;
  protected boolean isInited;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(getMenuRes() != 0);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    mView = inflater.inflate(getLayoutRes(), null);
    initInject();
    return mView;
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    if (getMenuRes() != 0) {
      inflater.inflate(getMenuRes(), menu);
    }
  }

  protected int getMenuRes() {
    return 0;
  }

  @SuppressWarnings("unchecked") @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.attachView(this);
    mUnbinder = ButterKnife.bind(this, view);

    if (savedInstanceState == null) {
      if (!isHidden()) {
        isInited = true;
        initData();
      }
    } else {
      if (!isSupportVisible()) {
        isInited = true;
        initData();
      }
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mUnbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (mPresenter != null) mPresenter.detachView();
  }

  @Override public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!isInited && !hidden) {
      isInited = true;
      initData();
    }
  }

  protected FragmentComponent getFragmentComponent() {
    return DaggerFragmentComponent.builder()
        .appComponent(getAppComponent())
        .fragmentModule(new FragmentModule(this))
        .build();
  }

  protected abstract int getLayoutRes();

  protected abstract void initInject();

  protected abstract void initData();

  protected AppComponent getAppComponent() {
    return App.getAppComponent();
  }
}

