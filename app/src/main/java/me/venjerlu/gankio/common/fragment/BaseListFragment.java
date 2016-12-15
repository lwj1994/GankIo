package me.venjerlu.gankio.common.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.elvishew.xlog.XLog;
import javax.inject.Inject;
import me.venjerlu.gankio.App;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.common.di.component.AppComponent;
import me.venjerlu.gankio.common.di.component.DaggerFragmentComponent;
import me.venjerlu.gankio.common.di.component.FragmentComponent;
import me.venjerlu.gankio.common.di.module.FragmentModule;
import me.venjerlu.gankio.common.mvp.IBaseListView;
import me.venjerlu.gankio.common.mvp.IBasePresenter;
import me.venjerlu.gankio.widget.pulltorefresh.BaseListAdapter;
import me.venjerlu.gankio.widget.pulltorefresh.DividerItemDecoration;
import me.venjerlu.gankio.widget.pulltorefresh.PullRecyclerLayout;
import me.venjerlu.gankio.widget.pulltorefresh.layoutManager.BaseLinearLayoutManager;
import me.venjerlu.gankio.widget.pulltorefresh.layoutManager.BaseStaggeredGridLayoutManager;
import me.venjerlu.gankio.widget.pulltorefresh.layoutManager.ILayoutManager;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author/Date: venjerLu / 2016/12/11 14:14
 * Email:       alwjlola@gmail.com
 * Description:
 */

public abstract class BaseListFragment<T extends IBasePresenter, D extends BaseListAdapter>
    extends SupportFragment implements IBaseListView, PullRecyclerLayout.OnRecyclerRefreshListener {
  private static final String TAG = "BaseListFragment";
  @Inject protected T mPresenter;
  @Inject protected D mAdapter;
  @BindView(R.id.pullToRefreshLayout) protected PullRecyclerLayout mPullToRefreshLayout;
  protected RecyclerView mRecyclerView;
  private Unbinder mUnbinder;
  private boolean isInited;
  private ILayoutManager mILayoutManager;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    XLog.tag(TAG).d("onCreateView");
    View view = inflater.inflate(getLayout(), null);
    mUnbinder = ButterKnife.bind(this, view);
    mRecyclerView = mPullToRefreshLayout.getRecyclerView();
    initInject();
    return view;
  }

  @SuppressWarnings("unchecked") @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.attachView(this);
    if (savedInstanceState == null) {
      if (!isHidden()) {
        isInited = true;
        initData();
      }
    } else {
      if (!isSupportVisible()) {
        isInited = true;
        initData();
      }
    }
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (mUnbinder != null) mUnbinder.unbind();
    if (mPullToRefreshLayout != null) mPullToRefreshLayout.onDestroy();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (mPresenter != null) mPresenter.detachView();
  }

  @Override public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!isInited && !hidden) {
      isInited = true;
      initData();
    }
  }

  protected FragmentComponent getFragmentComponent() {
    return DaggerFragmentComponent.builder()
        .appComponent(getAppComponent())
        .fragmentModule(new FragmentModule(this))
        .build();
  }

  protected int getLayout() {
    return R.layout.frgament_baselist;
  }

  protected abstract void initInject();

  protected void initData() {
    mPullToRefreshLayout.setOnRefreshListener(this);
    mILayoutManager = getLayoutManager();
    mPullToRefreshLayout.setLayoutManager(mILayoutManager);
    mPullToRefreshLayout.addItemDecoration(getItemDecoration());
    mPullToRefreshLayout.setAdapter(mAdapter);
    mPullToRefreshLayout.setOnScrolledListener(new PullRecyclerLayout.OnScrolledListener() {
      @Override public void onScrolled(int newState) {
        if (mILayoutManager instanceof BaseStaggeredGridLayoutManager) {
          //防止第一行到顶部有空白区域
          //((BaseStaggeredGridLayoutManager) mILayoutManager).invalidateSpanAssignments();
        }
      }
    });
  }

  @Override protected void onEnterAnimationEnd(Bundle savedInstanceState) {
    super.onEnterAnimationEnd(savedInstanceState);
    mPullToRefreshLayout.setRefreshing();
  }

  protected ILayoutManager getLayoutManager() {
    return new BaseLinearLayoutManager(getContext());
  }

  protected RecyclerView.ItemDecoration getItemDecoration() {
    return new DividerItemDecoration(getContext(), R.drawable.shape_list_divider);
  }

  @Override public void onRefresh(int action) {
    switch (action) {
      case PullRecyclerLayout.ACTION_PULL_TO_REFRESH:
        mAdapter.clearData();
        onRefresh();
        break;
      case PullRecyclerLayout.ACTION_LOAD_MORE_REFRESH:
        onLoadMore();
        break;
      case PullRecyclerLayout.ACTION_IDLE:
        break;
    }
  }

  protected void onLoadMore() {

  }

  @Override public void onRefreshCompleted() {
    if (mPullToRefreshLayout != null) mPullToRefreshLayout.onRefreshCompleted();
  }

  protected AppComponent getAppComponent() {
    return App.getAppComponent();
  }
}
