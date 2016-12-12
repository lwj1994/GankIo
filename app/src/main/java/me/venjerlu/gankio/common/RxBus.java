package me.venjerlu.gankio.common;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Author/Date: venjerLu / 2016/12/11 20:18
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class RxBus {

  private Subject<Object> mBus;

  private RxBus() {
    mBus = PublishSubject.create().toSerialized();
  }

  public static RxBus getDefault() {
    return SingleLoader.INSTANCE;
  }

  public void post(final Object event) {
    mBus.onNext(event);
  }

  public <T> Observable<T> toObservable(Class<T> eventType) {
    return mBus.ofType(eventType);
  }

  public Observable<Object> toObservable() {
    return mBus;
  }

  public boolean hasObservers() {
    return mBus.hasObservers();
  }

  private static class SingleLoader {
    private static final RxBus INSTANCE = new RxBus();
  }
}
