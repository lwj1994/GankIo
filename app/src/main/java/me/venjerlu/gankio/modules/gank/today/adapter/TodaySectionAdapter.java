package me.venjerlu.gankio.modules.gank.today.adapter;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.modules.gank.today.model.TodaySectionModel;

/**
 * Author/Date: venjerLu / 2016/12/10 22:47
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class TodaySectionAdapter
    extends BaseSectionQuickAdapter<TodaySectionModel, BaseViewHolder> {

  private TodaySectionAdapter(int layoutResId, int sectionHeadResId, List<TodaySectionModel> data) {
    super(layoutResId, sectionHeadResId, data);
  }

  public TodaySectionAdapter(List<TodaySectionModel> data) {
    this(R.layout.item_today_tech, R.layout.item_today_header,data);
  }

  @Override
  protected void convertHead(BaseViewHolder baseViewHolder, TodaySectionModel todaySectionModel) {
    baseViewHolder.setText(R.id.today_header,todaySectionModel.header);
  }

  @Override
  protected void convert(BaseViewHolder baseViewHolder, TodaySectionModel todaySectionModel) {
    ContentViewHolder viewHolder = (ContentViewHolder) baseViewHolder;
    viewHolder.mTextView.setText(todaySectionModel.t.getDesc());
  }

  public class ContentViewHolder extends BaseViewHolder{
    @BindView(R.id.today_content_des) TextView mTextView;

    public ContentViewHolder(View view) {
      super(view);
      ButterKnife.bind(this,view);
    }
  }
}
