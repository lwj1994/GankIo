package me.venjerlu.gankio.common.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import java.util.ArrayList;
import java.util.List;
import me.venjerlu.gankio.R;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

/**
 * Author/Date: venjerLu / 2016/12/10 19:37
 * Email:       alwjlola@gmail.com
 * Description:
 */

public abstract class BaseTabFragment extends BaseSimpleFragment {
  protected List<String> mTitles = new ArrayList<>();
  @BindView(R.id.tab) TabLayout tab;
  @BindView(R.id.viewPager) ViewPager viewPager;

  @Override protected int getLayout() {
    return R.layout.frgament_tab_gank;
  }

  @Override protected void initData() {
    setTitles();
    setTabAndViewPager(tab,viewPager);
  }

  protected abstract void setTitles();

  /**
   * must call this method to set up TabLayout & ViewPager
   */
  protected void setTabAndViewPager(final TabLayout tabLayout, final ViewPager viewPager) {
    for (String title : mTitles) {
      tabLayout.addTab(tabLayout.newTab().setText(title));
    }
    tabLayout.setTabMode(MODE_SCROLLABLE);
    viewPager.setAdapter(new PagerFragmentAdapter(getChildFragmentManager()));
    tabLayout.post(new Runnable() {
      @Override public void run() {
        tabLayout.setupWithViewPager(viewPager);
      }
    });
  }

  protected abstract Fragment initFragments(int position);

  class PagerFragmentAdapter extends FragmentPagerAdapter {

    PagerFragmentAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override public Fragment getItem(int position) {
      return initFragments(position);
    }

    @Override public int getCount() {
      return mTitles.size();
    }

    @Override public CharSequence getPageTitle(int position) {
      return mTitles.get(position);
    }
  }
}
