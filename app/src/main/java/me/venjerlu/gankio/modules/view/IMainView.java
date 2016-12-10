package me.venjerlu.gankio.modules.view;

import java.util.List;
import me.venjerlu.gankio.common.mvp.GankModel;
import me.venjerlu.gankio.common.mvp.IBaseView;

/**
 * Author/Date: venjerLu / 2016/12/9 11:23
 * Email:       alwjlola@gmail.com
 * Description:
 */
interface IMainView extends IBaseView {
  void setText(String text);

  void onGetData(List<GankModel> list);
}
