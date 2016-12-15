package me.venjerlu.gankio.widget.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
  protected Context mContext;
  public BaseViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this,itemView);
    mContext = itemView.getContext();
  }

  protected abstract void bind(T t);
}
