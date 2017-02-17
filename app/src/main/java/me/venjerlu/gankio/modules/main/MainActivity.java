package me.venjerlu.gankio.modules.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.common.activity.BaseSimpleActivity;
import me.venjerlu.gankio.common.activity.WebActivity;
import me.venjerlu.gankio.common.fragment.BaseLazyFragment;
import me.venjerlu.gankio.modules.about.AboutActivity;
import me.venjerlu.gankio.modules.gallery.GalleryActivity;
import me.venjerlu.gankio.modules.gank.bus.OnStartGalleryFragmentBus;
import me.venjerlu.gankio.modules.gank.bus.OnStartWebActivityBus;
import me.venjerlu.gankio.modules.gank.bus.OnUpdateTitleBus;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;
import static me.venjerlu.gankio.R.id.tab;
import static me.venjerlu.gankio.R.id.viewPager;

public class MainActivity extends BaseSimpleActivity
    implements BaseLazyFragment.OnBackToFirstListener {

  protected List<String> mTitles = new ArrayList<>();
  @BindView(tab) TabLayout mTabLayout;
  @BindView(viewPager) ViewPager mViewPager;
  @BindView(R.id.toolbar) Toolbar mToolbar;

  @Override public int getLayout() {
    return R.layout.activity_main;
  }

  @Override protected void initData(Bundle savedInstanceState) {
    initToolbar(mToolbar, "干货集中营");
    initLatestDate();
    initTabAndViewPager(mTabLayout, mViewPager);
    setOnClickNormalItem();
    setOnClickMeizhi();
  }

  /**
   * 设置Tablayout和ViewPager
   */
  protected void initTabAndViewPager(final TabLayout tabLayout, final ViewPager viewPager) {
    String[] strings = {
        "今日干货", "妹纸", "Android", "iOS", "前端", "拓展资源", "休息视频", "App", "瞎推荐"
    };
    mTitles.addAll(Arrays.asList(strings));
    for (String title : mTitles) {
      tabLayout.addTab(tabLayout.newTab().setText(title));
    }
    tabLayout.setTabMode(MODE_SCROLLABLE);
    viewPager.setOffscreenPageLimit(4);
    viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), mTitles));
    tabLayout.post(new Runnable() {
      @Override public void run() {
        tabLayout.setupWithViewPager(viewPager);
      }
    });
  }

  /**
   * 设置最新的日期
   */
  private void initLatestDate() {
    addDisposable(RxBus.getDefault().toObservable(OnUpdateTitleBus.class)
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<OnUpdateTitleBus>() {
          @Override public void accept(OnUpdateTitleBus onUpdateTitleBus) throws Exception {
            mToolbar.setTitle(onUpdateTitleBus.getTitle());
          }
        }));
  }

  /**
   * 跳转至WebActivity
   */
  public void setOnClickNormalItem() {
    addDisposable(RxBus.getDefault().toObservable(OnStartWebActivityBus.class)
        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<OnStartWebActivityBus>() {
          @Override public void accept(OnStartWebActivityBus bus) throws Exception {
            WebActivity.startMe(MainActivity.this, bus.url, bus.title);
          }
        }));
  }

  /**
   * 设置妹纸图片的点击事件
   */
  public void setOnClickMeizhi() {
    addDisposable(RxBus.getDefault()
        .toObservable(OnStartGalleryFragmentBus.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<OnStartGalleryFragmentBus>() {
          @Override public void accept(OnStartGalleryFragmentBus bus) throws Exception {
            GalleryActivity.startMe(MainActivity.this, bus.type, bus.url, bus.title);
          }
        }));
  }

  @Override protected int getMenuRes() {
    return R.menu.main;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      AboutActivity.startMe(this);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void onBackToFirstFragment() {
  }

  /**
   * 设置Fragment跳转动画
   */
  @Override protected FragmentAnimator onCreateFragmentAnimator() {
    return new DefaultNoAnimator();
  }
}
