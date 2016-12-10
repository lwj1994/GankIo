package me.venjerlu.gankio.common.mvp;

/**
 * Author/Date: venjerLu / 2016/12/6 22:44
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class BaseModel<T> {
  private boolean error;
  private T results;

  public T getResults() {
    return results;
  }

  public void setResults(T results) {
    this.results = results;
  }

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }
}
