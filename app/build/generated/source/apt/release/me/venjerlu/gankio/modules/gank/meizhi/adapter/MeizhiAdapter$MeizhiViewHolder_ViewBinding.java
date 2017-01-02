// Generated code from Butter Knife. Do not modify!
package me.venjerlu.gankio.modules.gank.meizhi.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.venjerlu.gankio.R;

public class MeizhiAdapter$MeizhiViewHolder_ViewBinding<T extends MeizhiAdapter.MeizhiViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MeizhiAdapter$MeizhiViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.mImageView = Utils.findRequiredViewAsType(source, R.id.meizhi_img, "field 'mImageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mImageView = null;

    this.target = null;
  }
}
