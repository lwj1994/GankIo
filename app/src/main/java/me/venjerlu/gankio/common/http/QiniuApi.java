package me.venjerlu.gankio.common.http;

import io.reactivex.Flowable;
import me.venjerlu.gankio.model.ImageInfoModel;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author/Date: venjerLu / 2017/2/18 15:41
 * Email:       alwjlola@gmail.com
 * Description:
 */
public interface QiniuApi {
  String QINIU_URL = "http://7xi8d6.com1.z0.glb.clouddn.com/";
  String TAG = "QiniuApi";

  @GET("{picName}?imageinfo") Flowable<ImageInfoModel> getMeizhiSize(
      @Path("picName") String picName);
}
