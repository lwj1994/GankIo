// Generated code from Butter Knife. Do not modify!
package me.venjerlu.gankio.modules.gank.today.presenter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.venjerlu.gankio.R;

public class TodayAdapter$ContentViewHolder_ViewBinding<T extends TodayAdapter.ContentViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public TodayAdapter$ContentViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mTitle = Utils.findRequiredViewAsType(source, R.id.today_tech_title, "field 'mTitle'", TextView.class);
    target.mContent = Utils.findRequiredViewAsType(source, R.id.today_tech_content, "field 'mContent'", TextView.class);
    target.mTime = Utils.findRequiredViewAsType(source, R.id.today_tech_time, "field 'mTime'", TextView.class);
    target.mImg = Utils.findRequiredViewAsType(source, R.id.today_tech_img, "field 'mImg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTitle = null;
    target.mContent = null;
    target.mTime = null;
    target.mImg = null;

    this.target = null;
  }
}
