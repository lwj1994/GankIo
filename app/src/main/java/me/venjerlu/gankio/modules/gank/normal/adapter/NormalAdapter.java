package me.venjerlu.gankio.modules.gank.normal.adapter;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import javax.inject.Inject;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.bus.OnStartWebActivityBus;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.model.Gank;
import me.venjerlu.gankio.utils.AndroidUtil;
import me.venjerlu.gankio.utils.glide.ImgLoader;
import me.venjerlu.gankio.widget.pulltorefresh.BaseListAdapter;
import me.venjerlu.gankio.widget.pulltorefresh.BaseViewHolder;

/**
 * Author/Date: venjerLu / 2016/12/16 11:00
 * Email:       alwjlola@gmail.com
 * Description: 默认列表适配器
 */

public class NormalAdapter extends BaseListAdapter<Gank> {
  @Inject NormalAdapter() {
  }

  @Override protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
    return new TechViewHolder(getInflate(parent, R.layout.item_today_tech));
  }

  @Override protected void bindData(BaseViewHolder holder, int position) {
    if (holder instanceof TechViewHolder) {
      TechViewHolder viewHolder = (TechViewHolder) holder;
      viewHolder.bind(mList.get(position));
    }
  }

  static class TechViewHolder extends BaseViewHolder<Gank> {
    @BindView(R.id.today_tech_title) TextView mTitle;
    @BindView(R.id.today_tech_content) TextView mContent;
    @BindView(R.id.today_tech_time) TextView mTime;
    @BindView(R.id.today_tech_img) ImageView mImg;

    TechViewHolder(View itemView) {
      super(itemView);
    }

    @Override protected void bind(final Gank gank) {
      reset();
      String imgUrl = null;
      if (!AndroidUtil.isEmptyList(gank.getImages())) {
        imgUrl = gank.getImages().get(0);
      }
      if (TextUtils.isEmpty(imgUrl)) {
        mImg.setImageResource(R.drawable.ic_placeholder_black_24dp);
      } else {
        ImgLoader.getInstance().normal(mContext, imgUrl + "?imageView2/0/w/200", mImg);
      }
      mTitle.setText(gank.getDesc());
      if (!TextUtils.isEmpty(gank.getWho())) {
        mContent.append(gank.getWho());
      }
      if (!TextUtils.isEmpty(gank.getSource())) {
        mContent.append("\n" + gank.getSource());
      }
      mTime.setText(gank.getPublishedAt().subSequence(0, 10));
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          RxBus.getDefault().post(new OnStartWebActivityBus(gank.getUrl(), gank.getDesc()));
        }
      });
      if (Build.VERSION.SDK_INT >= 21) {
        itemView.setBackgroundResource(R.drawable.ripple_normal_selecor);
      }
    }

    private void reset() {
      mTitle.setText("");
      mContent.setText("");
    }
  }
}
