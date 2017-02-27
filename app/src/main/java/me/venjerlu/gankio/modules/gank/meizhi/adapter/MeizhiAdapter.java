package me.venjerlu.gankio.modules.gank.meizhi.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import com.blankj.utilcode.utils.ScreenUtils;
import javax.inject.Inject;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.bus.OnStartGalleryFragmentBus;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.model.Gank;
import me.venjerlu.gankio.modules.gallery.GalleryActivity;
import me.venjerlu.gankio.utils.glide.ImgLoader;
import me.venjerlu.gankio.widget.pulltorefresh.BaseListAdapter;
import me.venjerlu.gankio.widget.pulltorefresh.BaseViewHolder;

/**
 * Author/Date: venjerLu / 2016/12/15 11:03
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class MeizhiAdapter extends BaseListAdapter<Gank> {

  @Inject MeizhiAdapter() {
  }

  @Override protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
    return new MeizhiViewHolder(getInflate(parent, R.layout.item_meizhi));
  }

  @Override protected void bindData(BaseViewHolder holder, int position) {
    if (holder instanceof MeizhiViewHolder) {
      MeizhiViewHolder viewHolder = (MeizhiViewHolder) holder;
      viewHolder.bind(mList.get(position));
    }
  }

  @Override public void clearData() {
    int count = (isEmpty() ? 1 : 0) + mList.size();
    super.clearData();
    notifyItemRangeRemoved(0, count);
  }

  class MeizhiViewHolder extends BaseViewHolder<Gank> {
    @BindView(R.id.meizhi_img) ImageView mImageView;

    MeizhiViewHolder(View inflate) {
      super(inflate);
    }

    @Override protected void bind(final Gank gank) {
      FrameLayout.LayoutParams layoutParams =
          (FrameLayout.LayoutParams) mImageView.getLayoutParams();
      int originHeight = gank.getHeight();
      int originWidth = gank.getWidth();
      if (originHeight > 0 && originWidth > 0) {
        float scale = (float) originHeight / originWidth;
        layoutParams.width = ScreenUtils.getScreenWidth(mContext) / 2;
        layoutParams.height = (int) (layoutParams.width * scale);
      }
      mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      ImgLoader.getInstance().normal(mContext, gank.getUrl(), mImageView);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          RxBus.getDefault()
              .post(new OnStartGalleryFragmentBus(GalleryActivity.TYPE_URL, 0, gank.getUrl(),
                  gank.getPublishedAt()));
        }
      });
    }
  }
}
