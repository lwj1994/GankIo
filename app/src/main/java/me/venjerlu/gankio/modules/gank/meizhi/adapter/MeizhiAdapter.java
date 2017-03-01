package me.venjerlu.gankio.modules.gank.meizhi.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import com.blankj.utilcode.utils.ScreenUtils;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.bus.OnStartGalleryBus;
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
  private Disposable mDisposable;

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
    super.clearData();
  }

  public void onDestroy() {
    if (mDisposable != null) mDisposable.dispose();
  }

  class MeizhiViewHolder extends BaseViewHolder<Gank> {
    @BindView(R.id.meizhi_img) ImageView mImageView;

    MeizhiViewHolder(View inflate) {
      super(inflate);
    }

    @Override protected void bind(final Gank gank) {
      mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      FrameLayout.LayoutParams layoutParams =
          (FrameLayout.LayoutParams) mImageView.getLayoutParams();
      int originHeight = gank.getHeight();
      int originWidth = gank.getWidth();
      if (originHeight > 0 && originWidth > 0) {
        float scale = (float) originHeight / originWidth;
        layoutParams.width = ScreenUtils.getScreenWidth(mContext) / 2;
        layoutParams.height = (int) (layoutParams.width * scale);
      }

      ImgLoader.getInstance().normal(mContext, gank.getUrl(), mImageView);
      final List<String> urls = new ArrayList<>();
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {

          mDisposable = Flowable.fromIterable(mList)
              .subscribeOn(Schedulers.io())
              .observeOn(Schedulers.io()).subscribeWith(new DisposableSubscriber<Gank>() {
                @Override public void onNext(Gank gank) {
                  urls.add(gank.getUrl());
                }

                @Override public void onError(Throwable t) {

                }

                @Override public void onComplete() {
                  RxBus.getDefault()
                      .post(new OnStartGalleryBus(GalleryActivity.TYPE_URL, urls,
                          getAdapterPosition(), gank.getPicName()));
                }
              });
        }
      });
    }
  }
}
