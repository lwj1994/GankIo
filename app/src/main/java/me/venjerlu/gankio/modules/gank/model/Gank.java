package me.venjerlu.gankio.modules.gank.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import me.venjerlu.gankio.common.http.GankApi;

/**
 * Author/Date: venjerLu / 2016/12/10 15:46
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class Gank implements MultiItemEntity{
  public static final int MEIZHI = 2;
  public static final int TECHNIQUE = 3;
  public static final int VEDIO = 4;

  /**
   * _id : 584a0130421aa963f321b040
   * createdAt : 2016-12-09T08:56:16.913Z
   * desc : 12-9
   * publishedAt : 2016-12-09T11:33:12.481Z
   * source : chrome
   * type : 福利
   * url : http://ww2.sinaimg.cn/large/610dc034jw1fak99uh554j20u00u0n09.jpg
   * used : true
   * who : 代码家
   */
  private String _id;
  private String createdAt;
  private String desc;
  private String publishedAt;
  private String source;
  private String type;
  private String url;
  private boolean used;
  private String who;

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(String publishedAt) {
    this.publishedAt = publishedAt;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isUsed() {
    return used;
  }

  public void setUsed(boolean used) {
    this.used = used;
  }

  public String getWho() {
    return who;
  }

  public void setWho(String who) {
    this.who = who;
  }

  @Override public int getItemType() {
    switch (getType()){
      case GankApi.福利:
        return MEIZHI;
      case GankApi.ANDROID:
      case GankApi.IOS:
      case GankApi.前端:
      case GankApi.拓展资源:
        return TECHNIQUE;
      case GankApi.休息视频:
        return VEDIO;
    }
    return 0;

  }
}
