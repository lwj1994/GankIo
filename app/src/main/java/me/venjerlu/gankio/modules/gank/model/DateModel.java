package me.venjerlu.gankio.modules.gank.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Author/Date: venjerLu / 2016/12/10 23:35
 * Email:       alwjlola@gmail.com
 * Description: 按日期获取数据的 model
 */
public class DateModel {
  @SerializedName("Android") private List<Gank> android;
  @SerializedName("iOS") private List<Gank> iOS;
  @SerializedName("福利") private List<Gank> meizhi;
  @SerializedName("休息视频") private List<Gank> vedio;
  @SerializedName("拓展资源") private List<Gank> expand;
  @SerializedName("前端") private List<Gank> front;

  public List<Gank> getAndroid() {
    return android;
  }

  public List<Gank> getiOS() {
    return iOS;
  }

  public List<Gank> getMeizhi() {
    return meizhi;
  }

  public List<Gank> getVedio() {
    return vedio;
  }

  public List<Gank> getExpand() {
    return expand;
  }

  public List<Gank> getFront() {
    return front;
  }
}
