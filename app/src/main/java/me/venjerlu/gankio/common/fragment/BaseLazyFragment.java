package me.venjerlu.gankio.common.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.elvishew.xlog.XLog;
import me.venjerlu.gankio.R;
import me.venjerlu.gankio.modules.gank.GankLazyFragment;
import me.venjerlu.gankio.utils.ToastUtil;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * lazy load
 * Created by lwj on 7/15/2016.
 */
public abstract class BaseLazyFragment extends SupportFragment {
  private static final long WAIT_TIME = 2000L;
  private static final String TAG = "BaseLazyFragment";
  protected OnBackToFirstListener _mBackToFirstListener;
  private long TOUCH_TIME = 0;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnBackToFirstListener) {
      _mBackToFirstListener = (OnBackToFirstListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnBackToFirstListener");
    }
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(getLayout(), null);
  }

  @Override public void onDetach() {
    super.onDetach();
    _mBackToFirstListener = null;
  }

  @Override public void onSupportVisible() {
    XLog.tag(TAG).d(this + "：onSupportVisible");
    super.onSupportVisible();
  }

  @Override public boolean onBackPressedSupport() {
    if (getChildFragmentManager().getBackStackEntryCount() > 1) {
      popChild();
    } else {
      if (this instanceof GankLazyFragment) {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
          _mActivity.finish();
        } else {
          TOUCH_TIME = System.currentTimeMillis();
          ToastUtil.shortMsg(getString(R.string.press_again_exit));
        }
      } else {
        _mBackToFirstListener.onBackToFirstFragment();
      }
    }
    return true;
  }

  public abstract int getLayout();

  public interface OnBackToFirstListener {
    void onBackToFirstFragment();
  }
}