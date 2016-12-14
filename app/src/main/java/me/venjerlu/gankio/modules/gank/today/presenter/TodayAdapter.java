package me.venjerlu.gankio.modules.gank.today.presenter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
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
    //XLog.tag(TAG).d("mList.size() = " + mList.size());
    //XLog.tag(TAG).d("position" + position);
    SectionData<Gank> item = mList.get(position);
    if (holder instanceof TitleViewHolder) {
      TitleViewHolder viewHolder = (TitleViewHolder) holder;
      viewHolder.setTitle(item.header);
    } else if (holder instanceof ContentViewHolder) {
      ContentViewHolder viewHolder = (ContentViewHolder) holder;
      viewHolder.bind(item.t.getImages(), item.t.getDesc(), item.t.getWho(), item.t.getSource(),
          item.t.getPublishedAt());
    }
  }

  static class ContentViewHolder extends BaseViewHolder {
    @BindView(R.id.today_tech_title) TextView mTitle;
    @BindView(R.id.today_tech_content) TextView mContent;
    @BindView(R.id.today_tech_time) TextView mTime;
    @BindView(R.id.today_tech_img) SimpleDraweeView mImg;

    ContentViewHolder(View itemView) {
      super(itemView);
    }

    void bind(List imgUrls, String title, String who, String source, String time) {
      reset();
      String imgUrl = null;
      if (!AndroidUtil.isEmptyList(imgUrls)) {
        imgUrl = (String) imgUrls.get(0);
      }
      ImageLoader.getInstance().load(mImg, imgUrl);
      mTitle.setText(title);
      if (!TextUtils.isEmpty(who)) {
        mContent.append(who);
      }
      if (!TextUtils.isEmpty(source)) {
        mContent.append("\n" + source);
      }
      //mTime.setText(time.subSequence(0,10));
    }

    private void reset() {
      mImg.setImageURI("res://me.venjerLu.gankio/" + R.drawable.ic_github);
      mTitle.setText("");
      mContent.setText("");
    }
  }

  static class TitleViewHolder extends BaseViewHolder {
    @BindView(R.id.today_title) TextView mTitle;

    TitleViewHolder(View itemView) {
      super(itemView);
    }

    public void setTitle(String title) {
      mTitle.setText(title);
    }
  }

  private class HeaderViewHolder extends BaseViewHolder {
    HeaderViewHolder(View view) {
      super(view);
    }
  }

  private class FooterViewHolder extends BaseViewHolder {
    FooterViewHolder(View view) {
      super(view);
    }
  }
}
