package me.venjerlu.gankio.utils;

import android.app.Activity;
import android.content.Context;
import com.blankj.utilcode.utils.SizeUtils;
import java.io.File;
import java.util.HashSet;
import me.venjerlu.gankio.App;

/**
 * Author/Date: venjerLu / 2016/12/7 13:56
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class AndroidUtil {

  private static HashSet<Activity> mActivities;

  static {
    mActivities = new HashSet<>();
  }

  public static void addActivity(Activity activity) {
    mActivities.add(activity);
  }

  public static void removeActivity(Activity activity) {
    mActivities.remove(activity);
  }

  /**
   * get data of Internal Storage
   */
  public static String getDataDirOfInternalStorage(Context context) {
    return context.getCacheDir().getAbsolutePath() + File.separator + "data";
  }

  public static String getNetCacheDir(Context context) {
    return getDataDirOfInternalStorage(context) + File.separator + "netCache";
  }

  public static int dp2px(float dp) {
    return SizeUtils.dp2px(App.getAppComponent().getContext(), dp);
  }
}
