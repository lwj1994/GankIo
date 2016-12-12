package me.venjerlu.gankio.common.http;

/**
 * Created by lwj on 8/14/2016.
 */
interface INetResult<Result> {
  void onFail(int errorCode, Object msg);

  void onSuccess(Result t);

  void onCompleted();

  void onDisconnect();
}
