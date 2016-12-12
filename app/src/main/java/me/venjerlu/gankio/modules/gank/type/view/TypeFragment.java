package me.venjerlu.gankio.modules.gank.type.view;

import android.os.Bundle;
import java.util.List;
import me.venjerlu.gankio.common.fragment.BaseListFragment;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.type.presenter.TypeGAdapter;
import me.venjerlu.gankio.modules.gank.type.presenter.TypePresenter;

/**
 * Author/Date: venjerLu / 2016/12/9 10:47
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class TypeFragment extends BaseListFragment<TypePresenter,TypeGAdapter> implements ITypeView {
  private static final int sSize = 10;
  private int mPage = 1;

  public static TypeFragment newInstance() {
    Bundle args = new Bundle();
    TypeFragment fragment = new TypeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initData() {
    super.initData();
  }

  @Override public void showError(String msg) {

  }

  @Override public void setRefreshing(boolean refresh) {

  }

  @Override public void onGetData(List<Gank> list) {

  }

  @Override public void onRefresh() {
    mPresenter.getData(GankApi.ANDROID, sSize, mPage);

  }
}
