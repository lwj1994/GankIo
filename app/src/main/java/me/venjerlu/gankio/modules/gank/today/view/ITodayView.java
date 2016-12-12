package me.venjerlu.gankio.modules.gank.today.view;

import me.venjerlu.gankio.common.mvp.IBaseListView;
import me.venjerlu.gankio.modules.gank.model.DateModel;

/**
 * Author/Date: venjerLu / 2016/12/10 20:16
 * Email:       alwjlola@gmail.com
 * Description:
 */
public interface ITodayView extends IBaseListView {
  void onGetLatestData(DateModel model);
}
