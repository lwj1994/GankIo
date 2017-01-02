// Generated code from Butter Knife. Do not modify!
package me.venjerlu.gankio.common.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.venjerlu.gankio.R;

public class BaseTabFragment_ViewBinding<T extends BaseTabFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BaseTabFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tab = Utils.findRequiredViewAsType(source, R.id.tab, "field 'tab'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tab = null;
    target.viewPager = null;

    this.target = null;
  }
}
