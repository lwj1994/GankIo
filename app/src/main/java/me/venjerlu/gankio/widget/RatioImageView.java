package me.venjerlu.gankio.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Author/Date: venjerLu / 2016/12/13 19:55
 * Email:       alwjlola@gmail.com
 * Description:
 */

public class RatioImageView extends ImageView{
  private int originalWidth;
  private int originalHeight;
  public RatioImageView(Context context) {
    super(context);
  }

  public RatioImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public RatioImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public void setOriginalSize(int originalWidth, int originalHeight) {
    this.originalWidth = originalWidth;
    this.originalHeight = originalHeight;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (originalWidth > 0 && originalHeight > 0) {
      float ratio = (float) originalWidth / (float) originalHeight;

      int width = MeasureSpec.getSize(widthMeasureSpec);
      int height = MeasureSpec.getSize(heightMeasureSpec);
      if (width > 0) {
        height = (int) ((float) width / ratio);
      }

      setMeasuredDimension(width, height);
    } else {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }  }
}
