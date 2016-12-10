package me.venjerlu.gankio.modules.gank.today.view;

import java.util.List;
import me.venjerlu.gankio.common.mvp.IBaseView;
import me.venjerlu.gankio.modules.gank.today.model.TodaySectionModel;

/**
 * Author/Date: venjerLu / 2016/12/10 20:16
 * Email:       alwjlola@gmail.com
 * Description:
 */
public interface ITodayView extends IBaseView {
  void onGetLatestData(List<TodaySectionModel> list);
}
