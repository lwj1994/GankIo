package me.venjerlu.gankio.modules.gank.today.adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.modules.gank.model.Gank;

/**
 * Author/Date: venjerLu / 2016/12/10 21:17
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class TodayAdapter extends
    BaseMultiItemQuickAdapter<Gank,TodayAdapter.TodayViewHolder> {

  public TodayAdapter(List<Gank> data) {
    super(data);
    addItemType(Gank.MEIZHI, R.layout.item_today_meizhi);
    addItemType(Gank.TECHNIQUE, R.layout.item_today_tech);
    addItemType(Gank.VEDIO, R.layout.item_today_meizhi);
  }

  @Override protected void convert(TodayViewHolder todayViewHolder, Gank gank) {

  }

  class TodayViewHolder extends BaseViewHolder{
    public TodayViewHolder(View view) {
      super(view);
    }
  }
}
