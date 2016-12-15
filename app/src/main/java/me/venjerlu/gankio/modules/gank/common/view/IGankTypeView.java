package me.venjerlu.gankio.modules.gank.common.view;

import java.util.List;
import me.venjerlu.gankio.common.mvp.IBaseListView;
import me.venjerlu.gankio.modules.gank.model.Gank;

/**
 * Author/Date: venjerLu / 2016/12/15 11:07
 * Email:       alwjlola@gmail.com
 * Description:
 */

public interface IGankTypeView extends IBaseListView {
  void onGetData(List<Gank> mGanks);
}
