// Generated code from Butter Knife. Do not modify!
package me.venjerlu.gankio.modules.gank.today.presenter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.venjerlu.gankio.R;

public class TodayAdapter$TitleViewHolder_ViewBinding<T extends TodayAdapter.TitleViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TodayAdapter$TitleViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mTitle = Utils.findRequiredViewAsType(source, R.id.today_title, "field 'mTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTitle = null;

    this.target = null;
  }
}
