package me.venjerlu.gankio.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.appeaser.sublimepickerlibrary.SublimePicker;
import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.helpers.SublimeListenerAdapter;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import java.util.Locale;
import java.util.TimeZone;
import me.venjerlu.gankio.R;

/**
 * Author/Date: venjerLu / 2017/3/2 10:57
 * Email:       alwjlola@gmail.com
 * Description:
 */
public class SublimePickerFragment extends DialogFragment {
  public static final String TAG = "SublimePickerFragment";
  public static final String OPTIONS = "SUBLIME_OPTIONS";

  // Date & Time formatter used for formatting
  // text on the switcher button
  java.text.DateFormat mDateFormatter, mTimeFormatter;

  // Picker
  SublimePicker mSublimePicker;

  // Callback to activity
  Callback mCallback;

  SublimeListenerAdapter mListener = new SublimeListenerAdapter() {
    @Override public void onCancelled() {
      if (mCallback != null) {
        mCallback.onCancelled();
      }

      // Should actually be called by activity inside `Callback.onCancelled()`
      dismiss();
    }

    @Override public void onDateTimeRecurrenceSet(SublimePicker sublimeMaterialPicker,
        SelectedDate selectedDate, int hourOfDay, int minute,
        SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {
      if (mCallback != null) {
        mCallback.onDateTimeRecurrenceSet(selectedDate, hourOfDay, minute, recurrenceOption,
            recurrenceRule);
      }

      // Should actually be called by activity inside `Callback.onCancelled()`
      dismiss();
    }
    // You can also override 'formatDate(Date)' & 'formatTime(Date)'
    // to supply custom formatters.
  };

  public SublimePickerFragment() {
    // Initialize formatters
    mDateFormatter =
        java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM, Locale.getDefault());
    mTimeFormatter =
        java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT, Locale.getDefault());
    mTimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
  }

  // Set activity callback
  public void setCallback(Callback callback) {
    mCallback = callback;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
        /*try {
            //getActivity().getLayoutInflater()
                    //.inflate(R.layout.sublime_recurrence_picker, new FrameLayout(getActivity()), true);
            getActivity().getLayoutInflater()
                    .inflate(R.layout.sublime_date_picker, new FrameLayout(getActivity()), true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }*/

    mSublimePicker = (SublimePicker) getActivity().getLayoutInflater()
        .inflate(R.layout.sublime_picker, container);

    // Retrieve SublimeOptions
    Bundle arguments = getArguments();
    SublimeOptions options = null;

    // Options can be null, in which case, default
    // options are used.
    if (arguments != null) {
      options = arguments.getParcelable("SUBLIME_OPTIONS");
    }

    mSublimePicker.initializePicker(options, mListener);
    return mSublimePicker;
  }

  // For communicating with the activity
  public interface Callback {
    void onCancelled();

    void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay, int minute,
        SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule);
  }
}
