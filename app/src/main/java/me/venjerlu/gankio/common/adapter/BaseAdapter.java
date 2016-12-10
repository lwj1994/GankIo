package me.venjerlu.gankio.common.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/**
 * Author/Date: venjerLu / 2016/12/10 21:10
 * Email:       alwjlola@gmail.com
 * Description:
 */

public abstract class BaseAdapter<T> extends BaseQuickAdapter<T,BaseViewHolder> {
  public BaseAdapter(int layoutResId, List<T> data) {
    super(layoutResId, data);
  }

  public BaseAdapter(List<T> data) {
    super(data);
  }
}
