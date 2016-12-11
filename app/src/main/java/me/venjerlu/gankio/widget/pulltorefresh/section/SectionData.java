package me.venjerlu.gankio.widget.pulltorefresh.section;

public class SectionData<T> {
  public boolean isHeader;
  public int headerIndex;//用于索引ABC...的index定位
  public T t; // 实体类
  public String header, content;

  /**
   * @param isHeader 是否是标题
   * @param headerIndex 标题的顺序
   * @param header 标题
   */
  public SectionData(boolean isHeader, int headerIndex, String header) {
    this.isHeader = isHeader;
    this.header = header;
    this.headerIndex = headerIndex;
    this.t = null;
  }

  public SectionData(boolean isHeader, int headerIndex, String header, String content) {
    this.isHeader = isHeader;
    this.header = header;
    this.headerIndex = headerIndex;
    this.t = null;
    this.content = content;
  }

  public SectionData(boolean isHeader, int headerIndex, T t, String header) {
    this.isHeader = isHeader;
    this.headerIndex = headerIndex;
    this.t = t;
    this.header = header;
  }

  public SectionData() {
    this.isHeader = true;
    this.t = null;
  }

  public SectionData(T t) {
    this.isHeader = false;
    this.header = null;
    this.t = t;
  }

  public int getFirstPosition() {
    return headerIndex;
  }
}
