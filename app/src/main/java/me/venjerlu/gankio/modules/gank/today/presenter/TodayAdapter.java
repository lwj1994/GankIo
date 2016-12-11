package me.venjerlu.gankio.modules.gank.today.presenter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import javax.inject.Inject;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.widget.pulltorefresh.BaseSectionListAdapter;
import me.venjerlu.gankio.widget.pulltorefresh.BaseViewHolder;

/**
 * Author/Date: venjerLu / 2016/12/11 14:48
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TodayAdapter extends BaseSectionListAdapter<Gank> {
  @Inject public TodayAdapter() {
  }

  @Override protected BaseViewHolder onCreateTitleViewHolder(ViewGroup parent) {
    return new TitleViewHolder(getInflate(parent, R.layout.item_today_title));
  }

  @Override protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
    return new ContentViewHolder(getInflate(parent, R.layout.item_today_tech));
  }

  @Override protected void bindData(BaseViewHolder holder, int position) {
    if (holder instanceof TitleViewHolder) {
      TitleViewHolder viewHolder = (TitleViewHolder) holder;
      viewHolder.setTitle(mList.get(position).header);
    } else if (holder instanceof ContentViewHolder) {
      ContentViewHolder viewHolder = (ContentViewHolder) holder;
      viewHolder.setContent(mList.get(position).t.getDesc());
    }
  }

  class TitleViewHolder extends BaseViewHolder {
    @BindView(R.id.today_title) TextView mTitle;

    TitleViewHolder(View itemView) {
      super(itemView);
    }

    public void setTitle(String title) {
      mTitle.setText(title);
    }
  }

  class ContentViewHolder extends BaseViewHolder {
    @BindView(R.id.today_content_des) TextView mDes;

    ContentViewHolder(View itemView) {
      super(itemView);
    }

    public void setContent(String content) {
      mDes.setText(content);
    }
  }
}
