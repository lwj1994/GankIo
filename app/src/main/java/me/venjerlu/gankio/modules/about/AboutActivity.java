package me.venjerlu.gankio.modules.about;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.Items;
import me.drakeet.support.about.AbsAboutActivity;
import me.drakeet.support.about.Card;
import me.drakeet.support.about.Category;
import me.drakeet.support.about.Contributor;
import me.drakeet.support.about.License;
import me.drakeet.support.about.Line;
import me.venjerlu.gankio.BuildConfig;
import me.venjerlu.gankio.R;

/**
 * Author/Date: venjerLu / 2017/2/16 19:10
 * Email:       alwjlola@gmail.com
 * Description: 关于
 */
public class AboutActivity extends AbsAboutActivity {
  private static final String TAG = "AboutActivity";

  public static void startMe(Activity activity) {
    activity.startActivity(new Intent(activity, AboutActivity.class));
  }

  @SuppressLint("SetTextI18n") @Override
  protected void onCreateHeader(ImageView icon, TextView slogan, TextView version) {
    setHeaderContentColor(ContextCompat.getColor(this, R.color.colorPrimary));
    setNavigationIcon(R.drawable.ic_back_white_20dp);
    icon.setImageResource(R.mipmap.ic_launcher);
    slogan.setText("About Page By Amaze");
    version.setText("v" + BuildConfig.VERSION_NAME);
  }

  @Override protected void onItemsCreated(@NonNull Items items) {
    items.add(new Category("介绍与帮助"));
    items.add(new Card(getString(R.string.card_content), "分享"));

    items.add(new Line());

    items.add(new Category("Developers"));
    items.add(new Contributor(R.mipmap.ic_launcher, "Amaze", "Developer"));
    items.add(new Contributor(R.mipmap.ic_launcher, "zishuai Liu", "UI设计"));

    items.add(new Line());

    items.add(new Category("Open Source Licenses"));
    items.add(new License("MultiType", "drakeet", License.APACHE_2,
        "https://github.com/drakeet/MultiType"));
    items.add(new License("about-page", "drakeet", License.APACHE_2,
        "https://github.com/drakeet/about-page"));
  }
}
