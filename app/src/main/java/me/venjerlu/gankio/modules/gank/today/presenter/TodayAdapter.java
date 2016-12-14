package me.venjerlu.gankio.modules.gank.today.presenter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import javax.inject.Inject;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.utils.AndroidUtil;
import me.venjerlu.gankio.utils.ImageLoader;
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
      viewHolder.bind(item.header);
    } else if (holder instanceof ContentViewHolder) {
      ContentViewHolder viewHolder = (ContentViewHolder) holder;
      viewHolder.bind(item.t);
    }else if (holder instanceof HeaderViewHolder){
      HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
      viewHolder.bind(item.t);
    }
  }

  static class ContentViewHolder extends BaseViewHolder<Gank> {
    @BindView(R.id.today_tech_title) TextView mTitle;
    @BindView(R.id.today_tech_content) TextView mContent;
    @BindView(R.id.today_tech_time) TextView mTime;
    @BindView(R.id.today_tech_img) SimpleDraweeView mImg;

    ContentViewHolder(View itemView) {
      super(itemView);
    }

    @Override protected void bind(Gank gank) {
      reset();
      String imgUrl = null;
      if (!AndroidUtil.isEmptyList(gank.getImages())) {
        imgUrl = gank.getImages().get(0);
      }
      ImageLoader.getInstance().load(mImg, imgUrl);
      mTitle.setText(gank.getDesc());
      if (!TextUtils.isEmpty(gank.getWho())) {
        mContent.append(gank.getWho());
      }
      if (!TextUtils.isEmpty(gank.getSource())) {
        mContent.append("\n" + gank.getSource());
      }
    }

    private void reset() {
      mImg.setImageURI("res://me.venjerLu.gankio/" + R.drawable.ic_github);
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
    @BindView(R.id.today_meizhi) SimpleDraweeView mImg;

    HeaderViewHolder(View view) {
      super(view);
    }

    @Override protected void bind(Gank gank) {
      ImageLoader.getInstance().load(mImg, gank.getUrl());
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
