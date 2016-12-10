package me.venjerlu.gankio.modules.gank.today.model;

import com.chad.library.adapter.base.entity.SectionEntity;
import me.venjerlu.gankio.modules.gank.model.Gank;

/**
 * Author/Date: venjerLu / 2016/12/10 22:46
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class TodaySectionModel extends SectionEntity<Gank> {
  public TodaySectionModel(boolean isHeader, String header) {
    super(isHeader, header);
  }

  public TodaySectionModel(Gank gank) {
    super(gank);
  }
}
