package me.venjerlu.gankio.widget.pulltorefresh;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.venjerlu.gankio.R;

public abstract class BaseListAdapter<S> extends RecyclerView.Adapter<BaseViewHolder> {
  private static final String TAG = "BaseListAdapter";
  private static final int VIEW_TYPE_LOAD_MORE_FOOTER = 100;
  protected List<S> mList = new ArrayList<>();
  private boolean isLoadMoreFooterShown;
  private OnItemClickListener mOnItemClickListener;

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == VIEW_TYPE_LOAD_MORE_FOOTER) {
      return onCreateLoadMoreFooterViewHolder(parent);
    }
    return onCreateNormalViewHolder(parent, viewType);
  }

  @Override public void onBindViewHolder(final BaseViewHolder holder, int position) {
    if (isLoadMoreFooterShown && position == getItemCount() - 1) {
      if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
        StaggeredGridLayoutManager.LayoutParams params =
            (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        params.setFullSpan(true);
      }
    }

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (mOnItemClickListener != null) {
          mOnItemClickListener.onClick(holder.getAdapterPosition(), holder);
        }
      }
    });
    bindData(holder, position);
  }

  @Override public int getItemCount() {
    return getDataCount() + (isLoadMoreFooterShown ? 1 : 0);
  }

  @Override public int getItemViewType(int position) {
    if (isLoadMoreFooterShown && position == getItemCount() - 1) {
      return VIEW_TYPE_LOAD_MORE_FOOTER;
    }
    return getDataViewType(position);
  }

  protected abstract BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType);

  protected abstract void bindData(BaseViewHolder holder, int position);

  protected int getDataCount() {
    return mList != null ? mList.size() : 0;
  }

  protected BaseViewHolder onCreateLoadMoreFooterViewHolder(ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.widget_pull_to_refresh_footer, parent, false);
    return new LoadMoreFooterViewHolder(view);
  }

  protected int getDataViewType(int position) {
    return 0;
  }

  public void onLoadMoreStateChanged(boolean isShown) {
    this.isLoadMoreFooterShown = isShown;
    if (isShown) {
      notifyItemInserted(getItemCount());
    } else {
      notifyItemRemoved(getItemCount());
    }
  }

  public boolean isLoadMoreFooter(int position) {
    return isLoadMoreFooterShown && position == getItemCount() - 1;
  }

  public boolean isSectionHeader(int position) {
    return false;
  }

  public void clearData() {
    mList.clear();
  }

  protected View getInflate(ViewGroup parent, @LayoutRes int layoutRes) {
    return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
  }

  public interface OnItemClickListener {
    void onClick(int position, BaseViewHolder viewHolder);
  }

  private class LoadMoreFooterViewHolder extends BaseViewHolder {
    LoadMoreFooterViewHolder(View view) {
      super(view);
    }
  }
}
