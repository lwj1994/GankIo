package me.venjerlu.gankio.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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

  @SuppressWarnings("unchecked") @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayout());
    mContext = this;
    mUnbinder = ButterKnife.bind(this);

    AndroidUtil.addActivity(this);
    initData(savedInstanceState);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mUnbinder.unbind();
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

  protected AppComponent getAppComponent() {
    return App.getAppComponent();
  }

  public abstract int getLayout();

  protected abstract void initData(Bundle savedInstanceState);
}
