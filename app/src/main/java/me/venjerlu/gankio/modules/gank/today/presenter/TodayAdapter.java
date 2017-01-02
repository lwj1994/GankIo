package me.venjerlu.gankio.modules.gank.today.presenter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import javax.inject.Inject;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.modules.gank.meizhi.bus.OnclickTechContentBus;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.utils.AndroidUtil;
import me.venjerlu.gankio.utils.glide.ImgLoader;
import me.venjerlu.gankio.widget.pulltorefresh.BaseSectionListAdapter;
import me.venjerlu.gankio.widget.pulltorefresh.BaseViewHolder;
import me.venjerlu.gankio.widget.pulltorefresh.section.SectionData;

/**
 * Author/Date: venjerLu / 2016/12/11 14:48
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TodayAdapter extends BaseSectionListAdapter<Gank> {
  private static final String TAG = "TodayAdapter";

  @Inject TodayAdapter() {
  }

  @Override protected BaseViewHolder onCreateNormalHeaderViewHolder(View view) {
    return new HeaderViewHolder(view);
  }

  @Override protected BaseViewHolder onCreateNormalFooterViewHolder(View view) {
    return new ContentViewHolder(view);
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
      viewHolder.bind(item.header);
    } else if (holder instanceof ContentViewHolder) {
      ContentViewHolder viewHolder = (ContentViewHolder) holder;
      viewHolder.bind(item.t);
    } else if (holder instanceof HeaderViewHolder) {
      HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
      viewHolder.bind(item.t);
    } else if (holder instanceof FooterViewHolder) {
      HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
      viewHolder.bind(item.t);
    }
  }

  static class ContentViewHolder extends BaseViewHolder<Gank> {
    @BindView(R.id.today_tech_title) TextView mTitle;
    @BindView(R.id.today_tech_content) TextView mContent;
    @BindView(R.id.today_tech_time) TextView mTime;
    @BindView(R.id.today_tech_img) ImageView mImg;

    ContentViewHolder(View itemView) {
      super(itemView);
      mTime.setVisibility(View.GONE);
    }

    @Override protected void bind(final Gank gank) {
      reset();
      String imgUrl = null;
      if (!AndroidUtil.isEmptyList(gank.getImages())) {
        imgUrl = gank.getImages().get(0);
      }
      ImgLoader.getInstance().normal(mContext, imgUrl, mImg);
      mTitle.setText(gank.getDesc());
      if (!TextUtils.isEmpty(gank.getWho())) {
        mContent.append(gank.getWho());
      }
      if (!TextUtils.isEmpty(gank.getSource())) {
        mContent.append("\n" + gank.getSource());
      }
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          RxBus.getDefault().post(new OnclickTechContentBus(gank.getUrl()));
        }
      });
    }

    private void reset() {
      mTitle.setText("");
      mContent.setText("");
    }
  }

  static class TitleViewHolder extends BaseViewHolder<String> {
    @BindView(R.id.today_title) TextView mTitle;

    TitleViewHolder(View itemView) {
      super(itemView);
    }

    @Override protected void bind(String s) {
      mTitle.setText(s);
    }
  }

  static class HeaderViewHolder extends BaseViewHolder<Gank> {
    @BindView(R.id.today_meizhi) ImageView mImg;

    HeaderViewHolder(View view) {
      super(view);
    }

    @Override protected void bind(Gank gank) {
      ImgLoader.getInstance().normal(mContext, gank.getUrl(), mImg);
    }
  }

  static class FooterViewHolder extends BaseViewHolder<Gank> {
    FooterViewHolder(View view) {
      super(view);
    }

    @Override protected void bind(Gank gank) {

    }
  }
}
