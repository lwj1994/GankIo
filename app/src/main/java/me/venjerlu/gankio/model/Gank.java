package me.venjerlu.gankio.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import me.venjerlu.gankio.http.QiniuApi;

/**
 * Author/Date: venjerLu / 2016/12/10 15:46
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class Gank implements Parcelable {
  public static final int MEIZHI = 2;
  public static final int TECHNIQUE = 3;
  public static final int VEDIO = 4;
  public static final Creator<Gank> CREATOR = new Creator<Gank>() {
    @Override public Gank createFromParcel(Parcel in) {
      return new Gank(in);
    }

    @Override public Gank[] newArray(int size) {
      return new Gank[size];
    }
  };
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
  private List<String> images;
  private String publishedAt;
  private String source;
  private String type;
  private String url;
  private boolean used;
  private String who;
  private int width;
  private int height;

  protected Gank(Parcel in) {
    _id = in.readString();
    createdAt = in.readString();
    desc = in.readString();
    images = in.createStringArrayList();
    publishedAt = in.readString();
    source = in.readString();
    type = in.readString();
    url = in.readString();
    used = in.readByte() != 0;
    who = in.readString();
    width = in.readInt();
    height = in.readInt();
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

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

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(_id);
    dest.writeString(createdAt);
    dest.writeString(desc);
    dest.writeStringList(images);
    dest.writeString(publishedAt);
    dest.writeString(source);
    dest.writeString(type);
    dest.writeString(url);
    dest.writeByte((byte) (used ? 1 : 0));
    dest.writeString(who);
    dest.writeInt(width);
    dest.writeInt(height);
  }

  public String getPicName() {
    return getUrl().substring(QiniuApi.QINIU_URL.length());
  }
}
