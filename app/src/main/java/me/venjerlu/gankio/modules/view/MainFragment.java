package me.venjerlu.gankio.modules.view;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.fragment.BaseFragment;
import me.venjerlu.gankio.common.http.GankApi;
import me.venjerlu.gankio.common.mvp.GankModel;
import me.venjerlu.gankio.modules.presenter.MainPresenter;

/**
 * Author/Date: venjerLu / 2016/12/9 10:47
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class MainFragment extends BaseFragment<MainPresenter> implements IMainView {
  private static final int sSize = 10;
  @BindView(R.id.main_textView) TextView mMainTextView;
  private int mPage = 1;

  public static MainFragment newInstance() {
    Bundle args = new Bundle();
    MainFragment fragment = new MainFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected int getLayout() {
    return R.layout.frgament_main;
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

  @Override public void setText(String text) {
    mMainTextView.setText(text);
  }

  @Override public void onGetData(List<GankModel> list) {
    for (int i = 0; i < list.size(); i++) {
      mMainTextView.append(list.get(i).getUrl() + "\n");
    }
  }
}
