package me.venjerlu.gankio.modules.gank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.Arrays;
import me.venjerlu.gankio.common.fragment.BaseTabFragment;
import me.venjerlu.gankio.modules.gank.meizhi.view.MeizhiFragment;
import me.venjerlu.gankio.modules.gank.tech.view.TechFragment;
import me.venjerlu.gankio.modules.gank.today.view.TodayFragment;

/**
 * Author/Date: venjerLu / 2016/12/10 19:57
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class GankFragment extends BaseTabFragment {
  public static GankFragment newInstance() {
    Bundle args = new Bundle();
    GankFragment fragment = new GankFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override protected void setTitles() {
    String[] strings = {
        "今日干货", "妹纸", "Android", "iOS", "前端", "拓展资源", "休息视频", "App", "瞎推荐"
    };
    mTitles.addAll(Arrays.asList(strings));
  }

  @Override protected Fragment initFragments(int position) {
    if (position == 0) {
      return TodayFragment.newInstance();
    } else if (position == 1) {
      return MeizhiFragment.newInstance();
    } else {
      return TechFragment.newInstance(mTitles.get(position));
    }
  }

  @Override protected void initData() {
    super.initData();
    //viewPager.setOffscreenPageLimit(5);
  }
}
