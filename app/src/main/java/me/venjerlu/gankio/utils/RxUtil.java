package me.venjerlu.gankio.utils;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
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
}