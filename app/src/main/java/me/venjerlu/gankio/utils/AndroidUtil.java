package me.venjerlu.gankio.utils;

import android.content.Context;
import java.io.File;
import java.util.HashSet;
import me.venjerlu.gankio.common.activity.BaseActivity;

/**
 * Author/Date: venjerLu / 2016/12/7 13:56
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class AndroidUtil {

  private static HashSet<BaseActivity> mActivities;

  static {
    mActivities = new HashSet<>();
  }

  public static void addActivity(BaseActivity activity) {
    mActivities.add(activity);
  }

  public static void removeActivity(BaseActivity activity) {
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
}
