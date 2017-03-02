package me.venjerlu.gankio.http;

import android.text.TextUtils;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import io.reactivex.subscribers.DisposableSubscriber;
import java.io.IOException;
import java.net.SocketTimeoutException;
import me.venjerlu.gankio.model.GankModel;
import me.venjerlu.gankio.utils.ToastUtil;
import retrofit2.HttpException;

/**
 * Author/Date: venjerLu / 8/14/2016 17:41
 * Email:       alwjlola@gmail.com
 * Description:
 */
public abstract class GankSubscriber<T> extends DisposableSubscriber<GankModel<T>>
    implements INetResult<T> {
  private static final String TAG = "GankSubscriber";

  @Override protected void onStart() {
    XLog.tag(TAG).d("onStart");
    super.onStart();
  }

  @Override public void onNext(GankModel<T> tBaseModel) {
    XLog.tag(TAG).d("onNext");
    if (tBaseModel.isError()) {
      onError(new GankException("当日没有干货"));
    } else {
      if (tBaseModel.getResults() != null && !TextUtils.isEmpty(
          tBaseModel.getResults().toString())) {
        onSuccess(tBaseModel.getResults());
      } else {
        onError(new GankException("当日没有干货"));
      }
    }
  }

  private void onFail(String msg) {
    ToastUtil.shortMsg(msg);
  }

  @Override public void onComplete() {
    XLog.tag(TAG).d("onComplete");
    onCompleted();
  }

  @Override public void onCompleted() {

  }

  @Override public void onError(Throwable e) {
    XLog.tag(TAG).d("onError");
    if (e instanceof SocketTimeoutException) {
      onDisconnect();
    } else if (e instanceof HttpException) {
      HttpException exception = (HttpException) e;
      XLog.e(exception.code() + "");
      XLog.e(exception.message() + "");
      if (exception.response() != null && exception.response().errorBody() != null) {
        try {
          XLog.e(exception.response().message());
          String bodyStr = exception.response().errorBody().string();
          XLog.e(bodyStr);
          GankModel errorModel = new Gson().fromJson(bodyStr, GankModel.class);
          if (errorModel != null) onFail(exception.code(), errorModel.getResults());
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    } else if (e instanceof GankException) {
      ToastUtil.shortMsg(e.getMessage());
    } else {
      XLog.e(e.toString());
      onDisconnect();
    }
    onCompleted();
  }

  @Override public void onFail(int errorCode, Object msg) {
    XLog.tag(TAG).e("code = " + errorCode + ", msg = " + msg);
  }

  @Override public void onDisconnect() {
    ToastUtil.shortMsg("网络错误，请检查您的网络连接是否正常");
  }
}
