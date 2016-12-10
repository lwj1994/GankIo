package me.venjerlu.gankio.common.http;

import io.reactivex.Flowable;
import java.util.List;
import me.venjerlu.gankio.common.mvp.BaseModel;
import me.venjerlu.gankio.common.mvp.GankModel;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author/Date: venjerLu / 2016/12/7 11:22
 * Email:       alwjlola@gmail.com
 * Description:
 */
public interface GankApi {
  String BASE_URL = "http://gank.io/api/";
  String ANDROID = "Android";
  String 福利 = "福利";
  String IOS = "iOS";
  String 休息视频 = "休息视频";
  String 拓展资源 = "拓展资源";
  String 前端 = "前端";
  String ALL = "All";

  @GET("data/{type}/{size}/{page}") Flowable<BaseModel<List<GankModel>>> getData(
      @Path("type") String type, @Path("size") int size, @Path("page") int page);
}
