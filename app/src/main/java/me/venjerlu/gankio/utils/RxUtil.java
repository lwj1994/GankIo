package me.venjerlu.gankio.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import me.venjerlu.gankio.Constants;
import me.venjerlu.gankio.utils.glide.ImgLoader;
import org.reactivestreams.Publisher;

/**
 * Author/Date: venjerLu / 8/10/2016 10:12
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class RxUtil {
  /**
   * timer
   *
   * @param time total time
   */
  public static Observable<Integer> countdown(int time) {
    if (time < 0) time = 0;
    final int countTime = time;
    return Observable.interval(0, 1, TimeUnit.SECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map(new Function<Long, Integer>() {
          @Override public Integer apply(Long aLong) throws Exception {
            return countTime - aLong.intValue();
          }
        })
        .take(countTime + 1);
  }

  public static <T> FlowableTransformer<T, T> applySchedulers() {
    return new FlowableTransformer<T, T>() {
      @Override public Publisher<T> apply(Flowable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  private static <T> Observable<T> createData(final T data) {
    return Observable.create(new ObservableOnSubscribe<T>() {
      @Override public void subscribe(ObservableEmitter<T> e) throws Exception {
        e.onNext(data);
        e.onComplete();
      }
    });
  }

  /**
   * 保存照片并通知图库更新
   */
  public static Observable<Uri> saveImageAndGetPathObservable(final Context context,
      final String url, final String title) {
    return Observable.create(new ObservableOnSubscribe<Bitmap>() {
                               @Override public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                                 Bitmap bitmap = null;

                                 try {
                                   bitmap = ImgLoader.getInstance().getBitmap(context, url);
                                 } catch (Exception exception) {
                                   e.onError(exception);
                                 }

                                 if (bitmap == null) {
                                   e.onError(new Exception("无法下载到图片"));
                                 }
                                 e.onNext(bitmap);
                                 e.onComplete();
                               }
                             }

    ).filter(new Predicate<Bitmap>() {
      @Override public boolean test(Bitmap bitmap) throws Exception {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState.equals(Environment.MEDIA_MOUNTED);
      }
    }).flatMap(new Function<Bitmap, ObservableSource<Uri>>() {
      @Override public ObservableSource<Uri> apply(Bitmap bitmap) throws Exception {
        File appDir = new File(Environment.getExternalStorageDirectory(), Constants.ROOT_DIR);
        if (!appDir.exists()) {
          boolean mkdir = appDir.mkdir();
          if (!mkdir) return Observable.just(Uri.EMPTY);
        }

        String fileName = title.replace('/', '-') + ".jpg";
        File file = new File(appDir, fileName);
        try {
          FileOutputStream outputStream = new FileOutputStream(file);
          assert bitmap != null;
          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
          outputStream.flush();
          outputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }

        // 保存后要扫描一下文件，及时更新到系统目录（一定要加绝对路径，这样才能更新）
        MediaScannerConnection.scanFile(context, new String[] {
            Environment.getExternalStorageDirectory()
                + File.separator
                + Constants.ROOT_DIR
                + File.separator
                + fileName
        }, null, null);

        Uri uri = Uri.fromFile(file);
        return Observable.just(uri);
      }
    }).subscribeOn(Schedulers.io());
  }
}