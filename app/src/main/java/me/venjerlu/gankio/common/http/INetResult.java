package me.venjerlu.gankio.common.http;

/**
 * Created by lwj on 8/14/2016.
 */
public interface INetResult<S> {
  void onFail(int errorCode, Object msg);

  void onSuccess(S result);

  void onCompleted();

  void onDisconnect();
}
