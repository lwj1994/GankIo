package me.venjerlu.gankio.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import java.util.List;

/**
 * Author/Date: venjerLu / 2016/12/10 21:10
 * Email:       alwjlola@gmail.com
 * Description:
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  protected List mList;
  protected Context mContext;
}
