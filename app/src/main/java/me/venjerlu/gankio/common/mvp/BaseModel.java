package me.venjerlu.gankio.common.mvp;

/**
 * Author/Date: venjerLu / 2016/12/6 22:44
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class BaseModel<T> {
  private T data;

  public BaseModel(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
