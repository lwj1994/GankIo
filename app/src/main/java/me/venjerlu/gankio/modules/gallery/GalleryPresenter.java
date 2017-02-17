package me.venjerlu.gankio.modules.gallery;

import android.content.Context;
import android.net.Uri;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import javax.inject.Inject;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.mvp.RxPresenter;
import me.venjerlu.gankio.utils.AndroidUtil;
import me.venjerlu.gankio.utils.RxUtil;
import me.venjerlu.gankio.utils.ToastUtil;

/**
 * Author/Date: venjerLu / 2017/2/17 12:15
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class GalleryPresenter extends RxPresenter<IGalleryView> {
  private static final String TAG = "GalleryPresenter";

  @Inject GalleryPresenter() {
  }

  void save(Context context, final String url, final String title) {
    addDisposable(RxUtil.saveImageAndGetPathObservable(context, url, title)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Uri>() {
          @Override public void accept(Uri uri) throws Exception {
            ToastUtil.shortMsg("已保存至" + uri.getPath());
          }
        }));
  }

  void share(final Context context, final String url, final String title) {
    addDisposable(RxUtil.saveImageAndGetPathObservable(context, url, title)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Uri>() {
          @Override public void accept(Uri uri) throws Exception {
            AndroidUtil.shareImage(context, uri, context.getString(R.string.share_meizhi_to));
          }
        }));
  }
}
