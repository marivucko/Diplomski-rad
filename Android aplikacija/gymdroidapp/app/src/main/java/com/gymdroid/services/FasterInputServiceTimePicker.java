package com.gymdroid.services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.gymdroid.R;
import com.ikovac.timepickerwithseconds.MyTimePickerDialog;

public class FasterInputServiceTimePicker extends BaseService {

    @SuppressLint("ClickableViewAccessibility")
    public void setOnClickListeners(final Activity activity, final EditText timeEditText) {
        timeEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    MyTimePickerDialog timePickerDialog = new MyTimePickerDialog (activity, new MyTimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                            timeEditText.setText(INSTANCE.getCalendarService().timeHoursMinutesSecondsToString(hourOfDay,minute,seconds));
                        }
                    }, 0, 0, 0, true);
                    timePickerDialog.setTitle(activity.getResources().getString( R.string.select_time));
                    timePickerDialog.show();
                }
                return true;
            }
        });
    }

    public long getTimeFromEditText(EditText timerEditText) {
        String timeString = timerEditText.getText().toString();
        if(INSTANCE.getStringService().isStringEmpty(timeString)) {
            return -1;
        }
        long time = INSTANCE.getCalendarService().timeHoursMinutesSecondsToMilliSeconds(timeString);
        if(time <= 0) {
            return -1;
        }
        return time;
    }

    public void setTimeEditTextTime(final EditText timeEditText, long timeInMilliseconds) {
        timeEditText.setText(INSTANCE.getCalendarService().millisecondsToHourMinuteSecondString((int) timeInMilliseconds));
    }

}
