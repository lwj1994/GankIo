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
import me.venjerlu.gankio.widget.pulltorefresh.section.SectionData;

/**
 * Author/Date: venjerLu / 2016/12/11 14:48
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TodayAdapter extends BaseSectionListAdapter<Gank> {
  @Inject TodayAdapter() {
  }

  @Override protected BaseViewHolder onCreateNormalHeaderViewHolder(View view) {
    return new HeaderViewHolder(view);
  }

  @Override protected BaseViewHolder onCreateNormalFooterViewHolder(View view) {
    return new FooterViewHolder(view);
  }

  @Override protected BaseViewHolder onCreateTitleViewHolder(ViewGroup parent) {
    return new TitleViewHolder(getInflate(parent, R.layout.item_today_title));
  }

  @Override protected BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType) {
    return new ContentViewHolder(getInflate(parent, R.layout.item_today_tech));
  }

  @Override protected void bindData(BaseViewHolder holder, int position) {
    SectionData<Gank> item = mList.get(position);
    if (holder instanceof TitleViewHolder) {
      TitleViewHolder viewHolder = (TitleViewHolder) holder;
      viewHolder.setTitle(item.header);
    } else if (holder instanceof ContentViewHolder) {
      ContentViewHolder viewHolder = (ContentViewHolder) holder;
      viewHolder.setContent(item.t.getDesc());
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

  class HeaderViewHolder extends BaseViewHolder {
    HeaderViewHolder(View view) {
      super(view);
    }
  }

  class FooterViewHolder extends BaseViewHolder {
    FooterViewHolder(View view) {
      super(view);
    }
  }
}
