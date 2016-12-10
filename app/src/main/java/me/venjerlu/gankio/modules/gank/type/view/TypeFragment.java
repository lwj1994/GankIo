package me.venjerlu.gankio.modules.gank.type.view;

import android.os.Bundle;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.di.scope.PreFragment;
import me.venjerlu.gankio.common.fragment.BaseFragment;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.type.presenter.TypePresenter;

/**
 * Author/Date: venjerLu / 2016/12/9 10:47
 * Email:       alwjlola@gmail.com
 * Description:
 */
@PreFragment
public class TypeFragment extends BaseFragment<TypePresenter> implements ITypeView {
  private static final int sSize = 10;
  private int mPage = 1;

  public static TypeFragment newInstance() {
    Bundle args = new Bundle();
    TypeFragment fragment = new TypeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected int getLayout() {
    return R.layout.frgament_gank;
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initData() {
    mPresenter.getData(GankApi.ANDROID, sSize, mPage);
  }

  @Override public void showError(String msg) {

  }

  @Override public void setRefreshing(boolean refresh) {

  }

  @Override public void onGetData(List<Gank> list) {
  }
}
