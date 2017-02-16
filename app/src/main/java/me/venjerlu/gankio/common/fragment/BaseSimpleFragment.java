package me.venjerlu.gankio.common.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.venjerlu.gankio.App;
import me.venjerlu.gankio.common.di.component.AppComponent;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author/Date: venjerLu / 2016/12/6 23:21
 * Email:       alwjlola@gmail.com
 * Description:
 */

public abstract class BaseSimpleFragment extends SupportFragment {
  protected Unbinder mUnbinder;
  protected boolean isInited;
  private CompositeDisposable mCompositeDisposable;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(getLayout(), null);
  }

  @SuppressWarnings("unchecked") @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
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
  }

  @Override public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!isInited && !hidden) {
      isInited = true;
      initData();
    }
  }

  private void clearDisposable() {
    if (mCompositeDisposable != null) {
      mCompositeDisposable.clear();
    }
  }

  protected void addDisposable(Disposable disposable) {
    if (mCompositeDisposable == null) {
      mCompositeDisposable = new CompositeDisposable();
    }
    mCompositeDisposable.add(disposable);
  }

  protected abstract int getLayout();

  protected abstract void initData();

  protected AppComponent getAppComponent() {
    return App.getAppComponent();
  }
}

