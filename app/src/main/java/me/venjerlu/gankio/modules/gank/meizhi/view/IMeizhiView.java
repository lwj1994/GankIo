package me.venjerlu.gankio.modules.gank.meizhi.view;

import java.util.List;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.normal.view.INormalView;

/**
 * Author/Date: venjerLu / 2016/12/9 11:23
 * Email:       alwjlola@gmail.com
 * Description:
 */
public interface IMeizhiView extends INormalView {
  void onNotifyData(List<Gank> datas);
}
