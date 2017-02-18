package me.venjerlu.gankio.modules.gank;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import com.elvishew.xlog.XLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import me.venjerlu.gankio.common.RxBus;
import me.venjerlu.gankio.common.http.QiniuApi;
import me.venjerlu.gankio.common.http.RetrofitModule;
import me.venjerlu.gankio.modules.gank.bus.OnNotifyDataBus;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.utils.glide.ImgLoader;

/**
 * Author/Date: venjerLu / 2017/2/18 15:16
 * Email:       alwjlola@gmail.com
 * Description: 在后台获取妹纸图的尺寸
 */
public class MeizhiService extends IntentService {
  private static final String TAG = "MeizhiService";
  private static final String DATA = TAG + "data";
  private QiniuApi mApi;

  public MeizhiService() {
    super(TAG);
  }

  public static void startService(Context context, List<Gank> datas) {
    Intent intent = new Intent(context, MeizhiService.class);
    intent.putParcelableArrayListExtra(DATA, (ArrayList<? extends Parcelable>) datas);
    context.startService(intent);
  }

  @Override public void onCreate() {
    super.onCreate();
    mApi = RetrofitModule.getInstance(getApplicationContext())
        .provideGankApi(QiniuApi.QINIU_URL, QiniuApi.class);
  }

  @Override protected void onHandleIntent(Intent intent) {
    if (intent == null) return;
    List<Gank> datas = intent.getParcelableArrayListExtra(DATA);
    handleMeizhidata(datas);
  }

  private void handleMeizhidata(List<Gank> datas) {
    if (datas.size() == 0) {
      return;
    }

    for (final Gank data : datas) {
      Bitmap bitmap;
      try {
        bitmap = ImgLoader.getInstance().getBitmap(getApplicationContext(), data.getUrl());
        if (bitmap != null) {
          XLog.tag(TAG).d("width = " + bitmap.getWidth() + "\nheight = " + bitmap.getHeight());
          data.setWidth(bitmap.getWidth());
          data.setHeight(bitmap.getHeight());
        }
      } catch (ExecutionException | InterruptedException e) {
        e.printStackTrace();
        XLog.tag(TAG).d(e.getMessage());
      }
    }

    RxBus.getDefault().post(new OnNotifyDataBus(datas));
  }
}
