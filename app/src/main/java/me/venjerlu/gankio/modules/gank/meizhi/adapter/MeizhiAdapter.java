package me.venjerlu.gankio.modules.gank.meizhi.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import com.blankj.utilcode.utils.ScreenUtils;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import javax.inject.Inject;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.modules.gallery.GalleryActivity;
import me.venjerlu.gankio.modules.gank.bus.OnStartGalleryFragmentBus;
import me.venjerlu.gankio.modules.gank.model.Gank;
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
      itemView.setTag(gank.getUrl());
      ImgLoader.getInstance()
          .bitmap(mContext, gank.getUrl(),
              new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                @Override public void onResourceReady(Bitmap bitmap,
                    GlideAnimation<? super Bitmap> glideAnimation) {
                  if (bitmap != null) {
                    int originHeight = bitmap.getHeight();
                    int originWidth = bitmap.getWidth();
                    if (originHeight > 0 && originWidth > 0) {
                      float scale = (float) originHeight / originWidth;
                      int width = ScreenUtils.getScreenWidth(mContext) / 2;
                      FrameLayout.LayoutParams layoutParams =
                          (FrameLayout.LayoutParams) mImageView.getLayoutParams();
                      layoutParams.width = width;
                      layoutParams.height = (int) (layoutParams.width * scale);
                      mImageView.setImageBitmap(bitmap);
                      mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                  }
                }
              });
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
