package com.gymdroid.services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.format.DateFormat;
import android.widget.EditText;

import com.gymdroid.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CalendarService extends BaseService {

    public String getYear(Date date) {
        return (String) DateFormat.format("yyyy", date);
    }

    public String getMonth(Date date) {
        return (String) DateFormat.format("mm", date);
    }

    public String getDay(Date date){
        return (String) DateFormat.format("dd", date);
    }

    public Date stringToDate(String day, String month, String year) {
        if(INSTANCE.getStringService().isStringEmpty(day)) return null;
        if(INSTANCE.getStringService().isStringEmpty(month)) return null;
        if(INSTANCE.getStringService().isStringEmpty(year)) return null;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(year+"-"+month+"-"+day);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date stringToDate(String string) {
        if(INSTANCE.getStringService().isStringEmpty(string)) return null;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public Date stringToDateOrNull(String string) {
        if(INSTANCE.getStringService().isStringEmpty(string)) return null;
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(string);
        } catch (ParseException e) {
            return null;
        }
    }

    public String dateToStringOnlyDate(Date date) {
        @SuppressLint("SimpleDateFormat")
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public String dateToStringOnlyDateDMY(Date date) {
        @SuppressLint("SimpleDateFormat")
        java.text.DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(date);
    }

    public String dateToTime(Date date) {
        @SuppressLint("SimpleDateFormat")
        java.text.DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

    public String dateToString(Date date) {
        @SuppressLint("SimpleDateFormat")
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public String dateOrNullToString(Date date) {
        if(date == null) {
            return "";
        }
        @SuppressLint("SimpleDateFormat")
        java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public Date getDateWithoutTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public String formatDay(Activity activity, EditText editText) {
        String day = editText.getText().toString();
        if(INSTANCE.getStringService().isStringEmpty(day)) {
            editText.setError(activity.getResources().getString( R.string.calendar_service_errors_day_is_empty));
            return null;
        }
        if(day.length() < 2){
            day = "0" + day;
        }
        int dayInteger = Integer.parseInt(day);
        if(dayInteger < 1 || dayInteger > 31) {
            editText.setError(activity.getResources().getString(R.string.calendar_service_errors_day_range));
            return null;
        }
        return day;
    }

    public String formatMonth(Activity activity, EditText editText) {
        String month = editText.getText().toString();
        if(INSTANCE.getStringService().isStringEmpty(month)) {
            editText.setError(activity.getResources().getString(R.string.calendar_service_errors_month_is_empty));
            return null;
        }
        if(month.length() < 2){
            month = "0" + month;
        }
        int monthInteger = Integer.parseInt(month);
        if(monthInteger < 1 || monthInteger > 12) {
            editText.setError(activity.getResources().getString(R.string.calendar_service_errors_month_range));
            return null;
        }
        return month;
    }

    public String formatYear(Activity activity, EditText editText) {
        String year = editText.getText().toString();
        if(INSTANCE.getStringService().isStringEmpty(year)) {
            editText.setError(activity.getResources().getString(R.string.calendar_service_errors_year_is_empty));
            return null;
        }
        int yearInteger = Integer.parseInt(year);
        if(yearInteger < 1990) {
            editText.setError(activity.getResources().getString(R.string.calendar_service_errors_year_range));
            return null;
        }
        return year;
    }

    public Calendar yesterday() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return calendar;
    }

    public boolean isInTheSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

    public String millisecondsToMinuteSecondString(int timeInMilliseconds) {
        int milliseconds = timeInMilliseconds % 1000;
        timeInMilliseconds -= milliseconds;
        int seconds = ( timeInMilliseconds / 1000 ) % 60;
        timeInMilliseconds -= seconds * 1000;
        int minutes = ( timeInMilliseconds / 60000 ) % 60;
        return minutesToString(minutes) + ":" + secondsToString(seconds);
    }

    public String millisecondsToHourMinuteSecondString(int timeInMilliseconds) {
        int milliseconds = timeInMilliseconds % 1000;
        timeInMilliseconds -= milliseconds;
        int seconds = ( timeInMilliseconds / 1000 ) % 60;
        timeInMilliseconds -= seconds * 1000;
        int minutes = ( timeInMilliseconds / 60000 ) % 60;
        timeInMilliseconds -= minutes * 60000;
        int hours = ( timeInMilliseconds / 3600000 ) % 24;
        return hoursToString(hours) + ":" + minutesToString(minutes) + ":" + secondsToString(seconds);
    }

    public String timeHoursMinutesSecondsToString(int hour, int minute, int second) {
        return hoursToString(hour) + ":" + minutesToString(minute) + ":" + secondsToString(second);
    }

    public long timeHoursMinutesSecondsToMilliSeconds(String timeFormat) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return format.parse(timeFormat).getTime();
        } catch (ParseException e) {
            return 0;
        }
    }

    private String hoursToString(int hour) {
        if(hour < 10) {
            return "0" + hour;
        }
        else{
            return String.valueOf(hour);
        }
    }

    private String minutesToString(int minutes) {
        if(minutes < 10) {
            return "0" + minutes;
        }
        else{
            return String.valueOf(minutes);
        }
    }

    private String secondsToString(int seconds) {
        if (seconds < 10) {
            return "0" + seconds;
        } else {
            return String.valueOf(seconds);
        }
    }

    private String millisecondsToString(int milliseconds) {
        if(milliseconds < 10) {
            return "00" + milliseconds;
        }
        if(milliseconds < 100) {
            return "0" + milliseconds;
        }
        return String.valueOf(milliseconds);
    }

}
