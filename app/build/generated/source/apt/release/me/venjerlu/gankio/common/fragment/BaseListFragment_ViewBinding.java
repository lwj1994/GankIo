// Generated code from Butter Knife. Do not modify!
package me.venjerlu.gankio.common.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.widget.pulltorefresh.PullRecyclerLayout;

public class BaseListFragment_ViewBinding<T extends BaseListFragment> implements Unbinder {
  protected T target;

  @UiThread
  public BaseListFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mPullToRefreshLayout = Utils.findRequiredViewAsType(source, R.id.pullToRefreshLayout, "field 'mPullToRefreshLayout'", PullRecyclerLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mPullToRefreshLayout = null;

    this.target = null;
  }
}
