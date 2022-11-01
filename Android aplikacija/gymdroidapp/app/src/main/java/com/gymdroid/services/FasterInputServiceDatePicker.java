package com.gymdroid.services;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

public class FasterInputServiceDatePicker extends BaseService {

    public void setClickListeners(final Activity activity, RadioButton todayDateRadioButton, RadioButton yesterdayDateRadioButton, RadioButton customDate, final EditText dayEditText, final EditText monthEditText, final EditText yearEditText ) {

        Calendar calendar = Calendar.getInstance();
        dayEditText.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        monthEditText.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
        yearEditText.setText(String.valueOf(calendar.get(Calendar.YEAR)));

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dayEditText.setText(String.valueOf(dayOfMonth));
                monthEditText.setText(String.valueOf(monthOfYear + 1));
                yearEditText.setText(String.valueOf(year));
            }

        };

        todayDateRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Calendar calendar = Calendar.getInstance();
                    dayEditText.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                    monthEditText.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
                    yearEditText.setText(String.valueOf(calendar.get(Calendar.YEAR)));
                }
            }
        });

        yesterdayDateRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Calendar calendar = INSTANCE.getCalendarService().yesterday();
                    dayEditText.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                    monthEditText.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
                    yearEditText.setText(String.valueOf(calendar.get(Calendar.YEAR)));
                }
            }
        });

        customDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Calendar calendar = Calendar.getInstance();
                    new DatePickerDialog(activity, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
    }

    public Date generateDate(Activity activity, LinearLayout linearLayout, RadioButton todayDateRadioButton, RadioButton yesterdayDateRadioButton, RadioButton customDate, EditText dayEditText, EditText monthEditText, EditText yearEditText, String errorMessage, boolean detailedTime) {

        Date date;
        if(detailedTime) {
            date = new Date();
        }
        else {
            date = INSTANCE.getCalendarService().getDateWithoutTime();
        }

        if(todayDateRadioButton.isChecked()) {
            return date;
        }

        if(yesterdayDateRadioButton.isChecked()) {
            date = INSTANCE.getCalendarService().yesterday().getTime();
        }

        if(customDate.isChecked()) {
            String day = INSTANCE.getCalendarService().formatDay(activity, dayEditText);
            if(day == null) {
                return null;
            }
            String month = INSTANCE.getCalendarService().formatMonth(activity, monthEditText);
            if(month == null) {
                return null;
            }
            String year = INSTANCE.getCalendarService().formatYear(activity, yearEditText);
            if(year == null) {
                return null;
            }
            date = INSTANCE.getCalendarService().stringToDate(day, month, year);
            if(date == null) {
                return null;
            }
        }

        Date now = new Date();
        if(date.after(now)) {
            Snackbar.make(linearLayout, errorMessage, Snackbar.LENGTH_LONG).show();
            return null;
        }

        return date;
    }

}
