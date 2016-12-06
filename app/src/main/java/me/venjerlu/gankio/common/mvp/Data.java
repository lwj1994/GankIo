package me.venjerlu.gankio.model;

/**
 * Author/Date: venjerLu / 2016/12/6 22:44
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class Data<T> {
  private T data;

  public Data(T data) {
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
