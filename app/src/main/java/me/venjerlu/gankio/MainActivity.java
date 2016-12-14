package me.venjerlu.gankio;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import me.venjerlu.gankio.modules.gank.bus.TitleBus;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseSimpleActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    BaseLazyFragment.OnBackToFirstListener {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.content_main) FrameLayout contentMain;
  @BindView(R.id.nav_view) NavigationView navView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

  @Override public int getLayout() {
    return R.layout.activity_main;
  }

  @Override protected void initData(Bundle savedInstanceState) {
    setToolbar(toolbar, " 干货集中营");
    setDrawerLayout();
    if (savedInstanceState == null) {
      loadRootFragment(R.id.content_main, GankLazyFragment.newInstance());
    }
    addDisposable(RxBus.getDefault()
        .toObservable(TitleBus.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<TitleBus>() {
          @Override public void accept(TitleBus titleBus) throws Exception {
            toolbar.setTitle(titleBus.getTitle());
          }
        }));
  }

  private void setDrawerLayout() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public void onBackPressedSupport() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressedSupport();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void onBackToFirstFragment() {

  }

  @Override protected FragmentAnimator onCreateFragmentAnimator() {
    return new DefaultHorizontalAnimator();
  }
}
