package me.venjerlu.gankio.modules.gallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import butterknife.BindView;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.activity.BaseActivity;

/**
 * Author/Date: venjerLu / 2017/2/17 12:14
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class GalleryActivity extends BaseActivity<GalleryPresenter> implements IGalleryView {
  public static final int TYPE_URL = 0; //网络 url
  public static final int TYPE_RES = 1; //res 文件
  public static final int TYPE_PATH = 2; //本地sd卡路径
  private static final String TAG = "GalleryActivity";
  public static final String EXTRA_URL = TAG + "url";
  public static final String EXTRA_URLS = TAG + "urls";
  public static final String EXTRA_RES = TAG + "res";
  public static final String EXTRA_TYPE = TAG + "type";
  public static final String EXTRA_TITLE = TAG + "title";
  public static final String EXTRA_POSITION = TAG + "position";
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.gallery_viewpager) ViewPager mViewPager;

  private int mType;
  private String mUrl;
  private List<String> mUrls;
  private String mTitle;
  private @DrawableRes int mRes;
  private int mPosition;

  /**
   * 启动此Activity，打开本地Res资源图片
   *
   * @param res 资源文件
   */
  public static void startMe(Activity activity, @DrawableRes int res, String title) {
    startMe(activity, TYPE_RES, null, res, title);
  }

  /**
   * 启动此Activity，根据type打开图片，type可选为TYPE_URL或者TYPE_PATH
   *
   * @param type 文件类型
   * @param url 文件资源字符串
   */
  public static void startMe(Activity activity, int type, String url, String title) {
    startMe(activity, type, url, 0, title);
  }

  /**
   * 启动此Activity
   *
   * @param type 需要打开的图片类型
   * @param url String 字符串地址
   * @param res res 资源
   */
  private static void startMe(Activity activity, int type, String url, @DrawableRes int res,
      String title) {
    Intent intent = new Intent(activity, GalleryActivity.class);
    intent.putExtra(EXTRA_TYPE, type);
    intent.putExtra(EXTRA_URL, url);
    intent.putExtra(EXTRA_RES, res);
    intent.putExtra(EXTRA_TITLE, title);
    activity.startActivity(intent);
  }

  @Override protected int getLayoutRes() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
      window.setStatusBarColor(Color.BLACK);
    }
    return R.layout.activity_gallery;
  }

  @Override protected int getMenuRes() {
    return R.menu.gallery;
  }

  @Override protected void initInject() {
    getActivityComponent().inject(this);
  }

  @Override protected void initData(Bundle savedInstanceState) {
    initBundle();
    initToolbar(mToolbar, "");
    mViewPager.setAdapter(new GalleryPagerAdapter(mUrls));
    mViewPager.setCurrentItem(mPosition);
    //switch (mType) {
    //  case TYPE_URL:
    //    ImgLoader.getInstance().loadSubsamplingScaleImage(this, mUrl, mImageView, mProgressBar);
    //    break;
    //  case TYPE_PATH:
    //    break;
    //  case TYPE_RES:
    //    break;
    //}
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.gallery_action_save:
        mPresenter.save(this, mUrls.get(mPosition), mTitle);
        break;
      case R.id.gallery_action_share:
        mPresenter.share(this, mUrls.get(mPosition), mTitle);
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * 初始化Toolbar
   */
  @Override protected void initToolbar(Toolbar toolbar, String title) {
    super.initToolbar(toolbar, title);
    toolbar.setBackgroundResource(android.R.color.black);
    toolbar.setNavigationIcon(R.drawable.ic_back_white_20dp);
  }

  /**
   * 初始化bundle数据
   */
  private void initBundle() {
    mType = getIntent().getIntExtra(EXTRA_TYPE, 0);
    mUrl = getIntent().getStringExtra(EXTRA_URL);
    mRes = getIntent().getIntExtra(EXTRA_RES, 0);
    mTitle = getIntent().getStringExtra(EXTRA_TITLE);
    mUrls = getIntent().getStringArrayListExtra(EXTRA_URLS);
    mPosition = getIntent().getIntExtra(EXTRA_POSITION, 0);
  }

  @Override public void showError(String msg) {

  }

  @Override public void setRefreshing(boolean refresh) {

  }
}
