package me.venjerlu.gankio.modules.gallery;

import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.utils.glide.ImgLoader;

/**
 * Author/Date: venjerLu / 2017/3/1 12:44
 * Email:       alwjlola@gmail.com
 * Description:
 */
class GalleryPagerAdapter extends PagerAdapter {
  private static final String TAG = "GalleryPagerAdapter";
  private List<String> mUrls;

  GalleryPagerAdapter(List<String> urls) {
    mUrls = urls;
  }

  @Override public int getCount() {
    return mUrls.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    View inflate =
        LayoutInflater.from(container.getContext()).inflate(R.layout.view_gallery_imageview, null);
    container.addView(inflate);
    SubsamplingScaleImageView imageView =
        (SubsamplingScaleImageView) inflate.findViewById(R.id.gallery_imageview);
    ContentLoadingProgressBar progressBar =
        (ContentLoadingProgressBar) inflate.findViewById(R.id.content_loading_progressbar);
    ImgLoader.getInstance()
        .loadSubsamplingScaleImage(container.getContext(), mUrls.get(position), imageView,
            progressBar);
    return inflate;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    View inflate =
        LayoutInflater.from(container.getContext()).inflate(R.layout.view_gallery_imageview, null);
    container.removeView(inflate);
  }
}

