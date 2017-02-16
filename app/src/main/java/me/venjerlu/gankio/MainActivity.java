package me.venjerlu.gankio;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.common.activity.BaseSimpleActivity;
import me.venjerlu.gankio.common.fragment.BaseLazyFragment;
import me.venjerlu.gankio.modules.gank.GankLazyFragment;
import me.venjerlu.gankio.modules.gank.OnIntentToWebViewActBus;
import me.venjerlu.gankio.modules.gank.bus.BackToFirstFragmentBus;
import me.venjerlu.gankio.modules.gank.bus.TitleBus;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseSimpleActivity
    implements BaseLazyFragment.OnBackToFirstListener {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.content_main) FrameLayout contentMain;

  @Override public int getLayout() {
    return R.layout.activity_main;
  }

  @Override protected void initData(Bundle savedInstanceState) {
    setToolbar(toolbar, "干货集中营");
    //setDrawerLayout();
    if (savedInstanceState == null) {
      loadRootFragment(R.id.content_main, GankLazyFragment.newInstance());
    }
    setLatestDate();
    onIntentToWebActivity();
  }

  /**
   * 设置最新的日期
   */
  private void setLatestDate() {
    addDisposable(RxBus.getDefault()
        .toObservable(TitleBus.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<TitleBus>() {
          @Override public void accept(TitleBus titleBus) throws Exception {
            toolbar.setTitle(titleBus.getTitle());
          }
        }));
  }

  /**
   * 跳转至WebActivity
   */
  public void onIntentToWebActivity() {
    addDisposable(RxBus.getDefault()
        .toObservable(OnIntentToWebViewActBus.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<OnIntentToWebViewActBus>() {
          @Override public void accept(OnIntentToWebViewActBus bus) throws Exception {
            WebActivity.startMe(MainActivity.this, bus.url, bus.title);
          }
        }));
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void onBackToFirstFragment() {
    RxBus.getDefault().post(new BackToFirstFragmentBus());
  }

  /**
   * 设置Fragment跳转动画
   */
  @Override protected FragmentAnimator onCreateFragmentAnimator() {
    return new DefaultNoAnimator();
  }
}
