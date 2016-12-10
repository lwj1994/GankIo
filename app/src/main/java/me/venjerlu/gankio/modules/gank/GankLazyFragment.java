package me.venjerlu.gankio.modules.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.fragment.BaseLazyFragment;

/**
 * Author/Date: venjerLu / 2016/12/10 19:00
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class GankLazyFragment extends BaseLazyFragment {
  public static GankLazyFragment newInstance() {
    Bundle args = new Bundle();
    GankLazyFragment fragment = new GankLazyFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onLazyInitView(@Nullable Bundle savedInstanceState) {
    super.onLazyInitView(savedInstanceState);
    if (savedInstanceState == null) {
      loadRootFragment(R.id.content_lazy_gank, GankFragment.newInstance());
    }
  }

  @Override public int getLayout() {
    return R.layout.frgament_lazy_gank;
  }
}
