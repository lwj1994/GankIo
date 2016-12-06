package me.venjerlu.gankio.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import me.venjerlu.gankio.model.Data;

/**
 * Author/Date: venjerLu / 8/10/2016 10:12
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class RxUtil {
  /**
   * 定时器
   *
   * @param time 定时总时间
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

  public static <T> ObservableTransformer<T, T> applySchedulers() {
    return new ObservableTransformer<T, T>() {
      @Override public ObservableSource<T> apply(Observable<T> upstream) {
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
}