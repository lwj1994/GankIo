package me.venjerlu.gankio.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.venjerlu.gankio.App;
import me.venjerlu.gankio.common.di.component.AppComponent;
import me.venjerlu.gankio.utils.AndroidUtil;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author/Date: venjerLu / 2016/12/6 23:21
 * Email:       alwjlola@gmail.com
 * Description:
 */

public abstract class BaseSimpleActivity extends SupportActivity {
  protected Activity mContext;
  private Unbinder mUnbinder;
  private CompositeDisposable mCompositeDisposable;

  @SuppressWarnings("unchecked") @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    mContext = this;
    mUnbinder = ButterKnife.bind(this);

    AndroidUtil.addActivity(this);
    initData(savedInstanceState);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    if (getMenuRes() != 0) getMenuInflater().inflate(getMenuRes(), menu);
    return true;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
    AndroidUtil.removeActivity(this);
    clearDisposable();
  }

  protected void initToolbar(Toolbar toolbar, String title) {
    toolbar.setTitle(title);
    setSupportActionBar(toolbar);
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

  protected AppComponent getAppComponent() {
    return App.getAppComponent();
  }

  public abstract int getLayout();

  protected abstract void initData(Bundle savedInstanceState);

  protected int getMenuRes() {
    return 0;
  }
}
