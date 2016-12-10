package me.venjerlu.gankio.modules.gank.model;

import java.util.List;

/**
 * Author/Date: venjerLu / 2016/12/6 22:44
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class GankModel<T> {
  private List<String> category;

  public List<String> getCategory() {
    return category;
  }

  public void setCategory(List<String> category) {
    this.category = category;
  }

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
