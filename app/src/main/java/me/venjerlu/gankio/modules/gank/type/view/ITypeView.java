package me.venjerlu.gankio.modules.gank.type.view;

import java.util.List;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.common.mvp.IBaseView;

/**
 * Author/Date: venjerLu / 2016/12/9 11:23
 * Email:       alwjlola@gmail.com
 * Description:
 */
interface ITypeView extends IBaseView {

  void onGetData(List<Gank> list);
}
