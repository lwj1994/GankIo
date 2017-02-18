package me.venjerlu.gankio.modules.about;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import me.venjerlu.gankio.BuildConfig;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.activity.BaseSimpleActivity;
import me.venjerlu.gankio.utils.AndroidUtil;

/**
 * Author/Date: venjerLu / 2017/2/16 19:10
 * Email:       alwjlola@gmail.com
 * Description: 关于
 */
public class AboutActivity extends BaseSimpleActivity {
  private static final String TAG = "AboutActivity";
  @BindView(R.id.about_version) TextView mVersion;
  @BindView(R.id.about_toolbar) Toolbar mToolbar;
  @BindView(R.id.about_collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
  @BindView(R.id.about_appbar) AppBarLayout mAppbar;
  @BindView(R.id.about_avatar_amaze) ImageView mAvatarAmaze;
  @BindView(R.id.about_avatar_zishuai) ImageView mAvatarZishuai;

  public static void startMe(Activity activity) {
    activity.startActivity(new Intent(activity, AboutActivity.class));
  }

  @Override public int getLayout() {
    return R.layout.activity_about;
  }

  @Override protected int getMenuRes() {
    return R.menu.about;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.about_action_share) {
      AndroidUtil.share(this, "https://github.com/lwj1994/GankIo");
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @SuppressLint("SetTextI18n") @Override protected void initData(Bundle savedInstanceState) {
    initToolbar(mToolbar, "关于");
    mToolbar.setNavigationIcon(R.drawable.ic_back_white_20dp);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressedSupport();
      }
    });

    mCollapsingToolbar.setTitle(getString(R.string.app_name));
    mVersion.setText("Version " + BuildConfig.VERSION_NAME);
  }
}
