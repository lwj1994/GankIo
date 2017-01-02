// Generated code from Butter Knife. Do not modify!
package me.venjerlu.gankio.modules.gank.today.presenter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.venjerlu.gankio.R;

public class TodayAdapter$HeaderViewHolder_ViewBinding<T extends TodayAdapter.HeaderViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TodayAdapter$HeaderViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mImg = Utils.findRequiredViewAsType(source, R.id.today_meizhi, "field 'mImg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mImg = null;

    this.target = null;
  }
}
