package me.venjerlu.gankio.widget.pulltorefresh;

import android.view.ViewGroup;
import java.util.List;
import me.venjerlu.gankio.widget.pulltorefresh.section.SectionData;

/**
 * Created by lwj on 7/19/2016.
 */
public abstract class BaseSectionListAdapter<SectionModel>
    extends BaseListAdapter<SectionData<SectionModel>> {
  protected static final int VIEW_TYPE_SECTION_HEADER = 1;
  protected static final int VIEW_TYPE_SECTION_CONTENT = 2;

  @Override protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
    if (viewType == VIEW_TYPE_SECTION_HEADER) {
      return onCreateTitleViewHolder(parent);
    } else {
      return onCreateContentViewHolder(parent, viewType);
    }
  }

  protected abstract BaseViewHolder onCreateTitleViewHolder(ViewGroup parent);

  protected abstract BaseViewHolder onCreateContentViewHolder(ViewGroup parent, int viewType);

  @Override protected int getDataViewType(int position) {
    return isSectionHeader(position) ? VIEW_TYPE_SECTION_HEADER : VIEW_TYPE_SECTION_CONTENT;
  }

  @Override public boolean isSectionHeader(int position) {
    return mList.get(getDataPosition(position)).isHeader;
  }

  public void addSection(int i, String header) {
    mList.add(new SectionData<SectionModel>(true, i, header));
  }

  public void addContent(List<SectionModel> list) {
    for (SectionModel sectionModel : list) {
      mList.add(new SectionData<>(sectionModel));
    }
  }
}
