package me.venjerlu.gankio.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import javax.inject.Inject;
import me.venjerlu.gankio.App;
import me.venjerlu.gankio.common.di.component.ActivityComponent;
import me.venjerlu.gankio.common.di.component.AppComponent;
import me.venjerlu.gankio.common.di.component.DaggerActivityComponent;
import me.venjerlu.gankio.common.di.module.ActivityModule;
import me.venjerlu.gankio.common.mvp.IBasePresenter;
import me.venjerlu.gankio.common.mvp.IBaseView;
import me.venjerlu.gankio.utils.AndroidUtil;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author/Date: venjerLu / 2016/12/6 23:21
 * Email:       alwjlola@gmail.com
 * Description:
 */

public abstract class BaseActivity<T extends IBasePresenter> extends SupportActivity
    implements IBaseView {
  protected Activity mContext;
  @Inject protected T mPresenter;
  private Unbinder mUnbinder;

  @SuppressWarnings("unchecked") @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    mContext = this;
    mUnbinder = ButterKnife.bind(this);
    initInject();
    if (mPresenter != null) {
      mPresenter.attachView(this);
    }
    AndroidUtil.addActivity(this);
    initData();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
    if (mPresenter != null) mPresenter.detachView();
    AndroidUtil.removeActivity(this);
  }

  protected void setToolbar(Toolbar toolbar, String title) {
    toolbar.setTitle(title);
    setSupportActionBar(toolbar);
    assert getSupportActionBar() != null;
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        onBackPressedSupport();
      }
    });
  }

  protected ActivityComponent getActivityComponent() {
    return DaggerActivityComponent.builder()
        .appComponent(getAppComponent())
        .activityModule(new ActivityModule(this))
        .build();
  }

  protected AppComponent getAppComponent() {
    return ((App) getApplication()).getAppComponent();
  }

  public abstract int getLayout();

  protected abstract void initInject();

  protected abstract void initData();
}
