package me.venjerlu.gankio.modules.gank.type.presenter;

import android.view.ViewGroup;
import javax.inject.Inject;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.widget.pulltorefresh.BaseListAdapter;
import me.venjerlu.gankio.widget.pulltorefresh.BaseViewHolder;

/**
 * Author/Date: venjerLu / 2016/12/12 11:47
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TypeGAdapter extends BaseListAdapter<Gank> {
  @Inject public TypeGAdapter() {
  }

  @Override protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override protected void bindData(BaseViewHolder holder, int position) {

  }
}
