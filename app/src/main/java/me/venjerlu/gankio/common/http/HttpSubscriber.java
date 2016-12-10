package me.venjerlu.gankio.common.http;

import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import io.reactivex.subscribers.ResourceSubscriber;
import java.io.IOException;
import java.net.SocketTimeoutException;
import me.venjerlu.gankio.common.mvp.BaseModel;
import me.venjerlu.gankio.utils.ToastUtil;

/**
 * Created by lwj on 8/14/2016.
 */
public abstract class HttpSubscriber<T> extends ResourceSubscriber<BaseModel<T>>
    implements INetResult<T> {
  private static final String TAG = "HttpObserver";

  @Override protected void onStart() {
    XLog.d(TAG, "onStart");
    super.onStart();
  }

  @Override public void onNext(BaseModel<T> tBaseModel) {
    XLog.d(TAG, "onNext");
    if (tBaseModel.isError()) {
      onFail((String) tBaseModel.getResults());
    } else {
      onSuccess(tBaseModel.getResults());
    }
  }

  private void onFail(String msg) {
    ToastUtil.shortMsg(msg);
  }

  @Override public void onComplete() {
    XLog.d(TAG, "onComplete");
    onCompleted();
  }

  @Override public void onCompleted() {

  }

  @Override public void onError(Throwable e) {
    XLog.d(TAG, "onError");
    if (e instanceof SocketTimeoutException) {
      onDisconnect();
    } else if (e instanceof HttpException) {
      HttpException exception = (HttpException) e;
      XLog.e(TAG, exception.code() + "");
      XLog.e(TAG, exception.message() + "");
      if (exception.response() != null && exception.response().errorBody() != null) {
        try {
          XLog.e(TAG, exception.response().message());
          String bodyStr = exception.response().errorBody().string();
          XLog.e(TAG, bodyStr);
          BaseModel errorModel = new Gson().fromJson(bodyStr, BaseModel.class);
          if (errorModel != null) onFail(exception.code(), errorModel.getResults());
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    } else {
      XLog.e(TAG, e.toString());
      onDisconnect();
    }
    onComplete();
  }

  @Override public void onFail(int errorCode, Object msg) {
    XLog.e(TAG, "code = " + errorCode + ", msg = " + msg);
  }

  @Override public void onDisconnect() {
    ToastUtil.shortMsg("网络错误，请检查您的网络连接是否正常");
  }
}
