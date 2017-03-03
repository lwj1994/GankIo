package me.venjerlu.gankio.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.blankj.utilcode.utils.SizeUtils;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import me.venjerlu.gankio.App;
import me.venjerlu.gankio.R;

/**
 * Author/Date: venjerLu / 2016/12/7 13:56
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class AndroidUtil {

  private static HashSet<Activity> mActivities = null;

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

  public static int px2dp(float px) {
    return SizeUtils.px2dp(App.getAppComponent().getContext(), px);
  }
  public static boolean isEmptyList(List list) {
    return list == null || list.size() == 0;
  }

  /**
   * 复制到剪贴板
   *
   * @param context 上下文
   * @param text 文本
   * @param success 成功的提示文字
   */
  public static void copyToClipBoard(Context context, String text, String success) {
    ClipData clipData = ClipData.newPlainText("Venjer_copy", text);
    ClipboardManager manager =
        (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    manager.setPrimaryClip(clipData);
    ToastUtil.shortMsg(success);
  }

  public static void share(Context context, int stringRes) {
    share(context, context.getString(stringRes));
  }

  public static void shareImage(Context context, Uri uri, String title) {
    Intent shareIntent = new Intent();
    shareIntent.setAction(Intent.ACTION_SEND);
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
    shareIntent.setType("image/jpeg");
    context.startActivity(Intent.createChooser(shareIntent, title));
  }

  public static void share(Context context, String extraText) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.action_share));
    intent.putExtra(Intent.EXTRA_TEXT, extraText);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(Intent.createChooser(intent, context.getString(R.string.action_share)));
  }
}
