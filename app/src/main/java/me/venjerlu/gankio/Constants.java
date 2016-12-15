package me.venjerlu.gankio;

import com.blankj.utilcode.utils.FileUtils;
import com.blankj.utilcode.utils.SDCardUtils;
import java.io.File;

/**
 * Author/Date: venjerLu / 2016/12/7 10:40
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class Constants {
  // path root
  public static final String ROOT_SDCARD = SDCardUtils.getSDCardPath() + "venjer" + File.separator;
  // path
  public static final String PATH_SDCARD = ROOT_SDCARD + "GankIo";

  static {
    if (FileUtils.createOrExistsDir(Constants.ROOT_SDCARD)) {
      FileUtils.createOrExistsDir(Constants.PATH_SDCARD);
    }
  }
}
