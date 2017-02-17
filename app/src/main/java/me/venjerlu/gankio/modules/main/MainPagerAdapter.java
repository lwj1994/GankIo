package me.venjerlu.gankio.modules.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.List;
import me.venjerlu.gankio.modules.gank.meizhi.view.MeizhiFragment;
import me.venjerlu.gankio.modules.gank.normal.view.NormalFragment;
import me.venjerlu.gankio.modules.gank.today.view.TodayFragment;

/**
 * Author/Date: venjerLu / 2017/2/17 14:58
 * Email:       alwjlola@gmail.com
 * Description:
 */
class MainPagerAdapter extends FragmentStatePagerAdapter {
  private static final String TAG = "MainPagerAdapter";
  private List<String> mTitles;

  MainPagerAdapter(FragmentManager fm, List<String> titles) {
    super(fm);
    mTitles = titles;
  }

  @Override public Fragment getItem(int position) {
    if (position == 0) {
      return TodayFragment.newInstance();
    } else if (position == 1) {
      return MeizhiFragment.newInstance();
    } else {
      return NormalFragment.newInstance(mTitles.get(position));
    }
  }

  @Override public int getCount() {
    return mTitles.size();
  }

  @Override public CharSequence getPageTitle(int position) {
    return mTitles.get(position);
  }
}
