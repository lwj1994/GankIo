package me.venjerlu.gankio.widget.pulltorefresh;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.elvishew.xlog.XLog;
import java.util.ArrayList;
import java.util.List;
import me.venjerlu.gankio.R;

public abstract class BaseListAdapter<S> extends RecyclerView.Adapter<BaseViewHolder> {
  private static final String TAG = "BaseListAdapter";
  private static final int VIEW_TYPE_LOAD_MORE_FOOTER = 100;
  private static final int VIEW_TYPE_NORMAL_FOOTER = 101;
  private static final int VIEW_TYPE_NORNAL_HEADER = 102;
  private static final int VIEW_TYPE_EMPTY = 103;
  protected List<S> mList = new ArrayList<>();
  private boolean isLoadMoreFooterShown;
  private OnItemClickListener mOnItemClickListener;
  private @LayoutRes int mLayoutHeader, mLayoutFooter, mLayoutEmpty;

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == VIEW_TYPE_LOAD_MORE_FOOTER) {
      return onCreateLoadMoreFooterViewHolder(parent);
    } else if (viewType == VIEW_TYPE_NORNAL_HEADER) {
      return onCreateNormalHeaderViewHolder(getInflate(parent, mLayoutHeader));
    } else if (viewType == VIEW_TYPE_NORMAL_FOOTER) {
      return onCreateNormalFooterViewHolder(getInflate(parent, mLayoutFooter));
    } else if (viewType == VIEW_TYPE_EMPTY) {
      return onCreateEmptyViewHolder(getInflate(parent, mLayoutEmpty));
    } else {
      return onCreateNormalViewHolder(parent, viewType);
    }
  }

  protected BaseViewHolder onCreateEmptyViewHolder(View view) {
    return new EmptyViewHolder(view);
  }

  protected BaseViewHolder onCreateNormalHeaderViewHolder(View view) {
    return null;
  }

  protected BaseViewHolder onCreateNormalFooterViewHolder(View view) {
    return null;
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
    if (getDataCount() > 0) {
      if (hasHeader()) {
        if (hasFooter()) {
          if (position > 0 && position < getItemCount() - 1) {
            XLog.tag(TAG).d("position = " + position);
            bindData(holder, position - 1);
          }
        } else {
          if (position > 0) {
            bindData(holder, position - 1);
          }
        }
      } else {
        if (hasFooter()) {
          if (position < getItemCount() - 1) {
            bindData(holder, position);
          }
        } else {
          bindData(holder, position);
        }
      }
    } else {
      if (mLayoutEmpty != 0) {
        if (holder.itemView.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
          StaggeredGridLayoutManager.LayoutParams params =
              (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
          params.setFullSpan(true);
        }
      }
    }
  }

  @Override public int getItemCount() {
    return (mLayoutEmpty != 0 ? 1 : 0)
        + (hasHeader() ? 1 : 0)
        + (hasFooter() ? 1 : 0)
        + getDataCount()
        + (isLoadMoreFooterShown ? 1 : 0);
  }

  @Override public int getItemViewType(int position) {
    if (isLoadMoreFooterShown && position == getItemCount() - 1) {
      return VIEW_TYPE_LOAD_MORE_FOOTER;
    } else if (mLayoutHeader != 0 && position == 0) {
      return VIEW_TYPE_NORNAL_HEADER;
    } else if (mLayoutFooter != 0 && position == getItemCount() - 1) {
      return VIEW_TYPE_NORMAL_FOOTER;
    } else if (getDataCount() == 0 && mLayoutEmpty != 0) {
      if (mLayoutHeader != 0 && mLayoutFooter == 0) {  //有头无尾
        if (position > 0) {
          return VIEW_TYPE_EMPTY;
        } else {
          return VIEW_TYPE_NORNAL_HEADER;
        }
      } else if (mLayoutHeader != 0 && mLayoutFooter != 0) { // 有头有尾
        if (position == 0) {
          return VIEW_TYPE_NORNAL_HEADER;
        } else if (position == getItemCount() - 1) {
          return VIEW_TYPE_NORMAL_FOOTER;
        } else {
          return VIEW_TYPE_EMPTY;
        }
      } else if (mLayoutHeader == 0 && mLayoutFooter != 0) {  // 无头有尾
        if (position == getItemCount() - 1) {
          return VIEW_TYPE_NORMAL_FOOTER;
        } else {
          return VIEW_TYPE_EMPTY;
        }
      } else {     // 无头无尾
        return VIEW_TYPE_EMPTY;
      }
    } else {
      return getDataViewType(position);
    }
  }

  void addEmpty(@LayoutRes int layoutRes) {
    this.mLayoutEmpty = layoutRes;
    if (mLayoutFooter == 0) { // 无尾  无头/有头
      notifyItemInserted(getItemCount());
    } else {  // 有尾  有头/无头
      notifyItemInserted(getItemCount() - 1); // 插到尾巴之前
    }
  }

  public void addHeader(@LayoutRes int layoutRes) {
    this.mLayoutHeader = layoutRes;
    notifyItemInserted(0);
  }

  public void addFooter(@LayoutRes int layoutRes) {
    this.mLayoutFooter = layoutRes;
    notifyItemInserted(getItemCount());
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

  public void clearData() {
    mList.clear();
  }

  protected View getInflate(ViewGroup parent, @LayoutRes int layoutRes) {
    return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
  }

  protected boolean hasHeader() {
    return mLayoutHeader != 0;
  }

  protected boolean hasFooter() {
    return mLayoutFooter != 0;
  }

  protected int getDataPosition(int position) {
    if (hasHeader()) {
      return position - 1;
    } else {
      return position;
    }
  }

  public boolean isSectionHeader(int position) {
    return false;
  }

  public interface OnItemClickListener {
    void onClick(int position, BaseViewHolder viewHolder);
  }

  private class LoadMoreFooterViewHolder extends BaseViewHolder {
    LoadMoreFooterViewHolder(View view) {
      super(view);
    }
  }

  private class EmptyViewHolder extends BaseViewHolder {
    EmptyViewHolder(View view) {
      super(view);
    }
  }
}
