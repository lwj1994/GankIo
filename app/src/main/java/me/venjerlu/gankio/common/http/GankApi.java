package me.venjerlu.gankio.common.http;

import io.reactivex.Flowable;
import java.util.List;
import me.venjerlu.gankio.modules.gank.model.DateModel;
import me.venjerlu.gankio.modules.gank.model.Gank;
import me.venjerlu.gankio.modules.gank.model.GankModel;
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
  String 瞎推荐 = "瞎推荐";
  String SApp = "App";

  /**
   * 获取不同类型的干货
   *
   * @param type 类型
   * @param size 数量
   * @param page 页数
   */
  @GET("data/{type}/{size}/{page}") Flowable<GankModel<List<Gank>>> getData(
      @Path("type") String type, @Path("size") int size, @Path("page") int page);

  /**
   * 获取发过干货日期接口
   */
  @GET("day/history") Flowable<GankModel<List<String>>> getHistoryDate();

  /**
   * 获取某一天的数据
   */
  @GET("day/{year}/{month}/{day}") Flowable<GankModel<DateModel>> getDataOnSomeday(
      @Path("year") String year, @Path("month") String month, @Path("day") String day);
}
