package me.venjerlu.gankio.modules.gank.bus;

import java.util.List;
import me.venjerlu.gankio.modules.gank.model.Gank;

/**
 * Author/Date: venjerLu / 2017/2/18 16:42
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class OnNotifyDataBus {
  private static final String TAG = "OnNotifyDataBus";
  public List<Gank> datas;

  public OnNotifyDataBus(List<Gank> datas) {
    this.datas = datas;
  }
}
