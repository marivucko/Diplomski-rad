package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class DoneWorkoutTable extends Table {

    private ArrayList<DoneWorkout> doneWorkoutArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "doneWorkout";
    private static final String KEY_DONE_WORKOUT_ID = "doneWorkoutId";
    private static final String KEY_WORKOUT_ID = "workoutId";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_DONE_WORKOUT_DATE = "doneWorkoutDate";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_DONE_WORKOUT_ID + " integer,"
            + KEY_WORKOUT_ID + " integer,"
            + KEY_USER_ID + " integer,"
            + KEY_DONE_WORKOUT_DATE + " text,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public DoneWorkoutTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public boolean insertDoneWorkout(DoneWorkout doneWorkout) {
        ContentValues contentValues = toContentValues(doneWorkout);
        if(databaseHelper.insert(contentValues)) {
            for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
                if(doneWorkout.getDoneWorkoutDate().after(doneWorkoutArrayList.get(i).getDoneWorkoutDate())) {
                    doneWorkoutArrayList.add(i,doneWorkout);
                    return true;
                }
            }
            doneWorkoutArrayList.add(doneWorkout);
            return true;
        }
        return false;
    }

    public boolean updateDoneWorkout(DoneWorkout doneWorkout) {
        ContentValues contentValues = toContentValues(doneWorkout);
        if  (databaseHelper.update(contentValues, KEY_DONE_WORKOUT_ID + "=" + doneWorkout.getDoneWorkoutId())) {
            for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
                if (doneWorkoutArrayList.get(i).getDoneWorkoutId() == doneWorkout.getDoneWorkoutId()) {
                    doneWorkoutArrayList.set(i, doneWorkout);
                }
            }
            return  true;
        }
        return false;
    }

    public void deleteDoneWorkout(DoneWorkout doneWorkout) {
        databaseHelper.deleteRowByRowId(KEY_DONE_WORKOUT_ID, doneWorkout.getDoneWorkoutId());
        for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
            if (doneWorkoutArrayList.get(i).getDoneWorkoutId() == doneWorkout.getDoneWorkoutId()) {
                doneWorkoutArrayList.remove(i);
                break;
            }
        }
    }

    public ArrayList<DoneWorkout> getDoneWorkoutArrayList() {
        return doneWorkoutArrayList;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(doneWorkoutArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = doneWorkoutArrayList.get(0).getCreatedAt();
        for(int i = 1; i < doneWorkoutArrayList.size(); i++) {
            if(searchDate.before(doneWorkoutArrayList.get(i).getCreatedAt())) {
                searchDate = doneWorkoutArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(doneWorkoutArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = doneWorkoutArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < doneWorkoutArrayList.size(); i++) {
            if(searchDate.before(doneWorkoutArrayList.get(i).getUpdatedAt())) {
                searchDate = doneWorkoutArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    private ContentValues toContentValues(DoneWorkout doneWorkout) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DONE_WORKOUT_ID, doneWorkout.getDoneWorkoutId());
        contentValues.put(KEY_WORKOUT_ID, doneWorkout.getWorkoutId());
        contentValues.put(KEY_USER_ID, doneWorkout.getUserId());
        contentValues.put(KEY_DONE_WORKOUT_DATE, calendarService.dateToString(doneWorkout.getDoneWorkoutDate()));
        contentValues.put(CREATED_AT, calendarService.dateToString(doneWorkout.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(doneWorkout.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(doneWorkout.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        doneWorkoutArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                DoneWorkout doneWorkout = new DoneWorkout();
                doneWorkout.setDoneWorkoutId(cursor.getInt(cursor.getColumnIndex(KEY_DONE_WORKOUT_ID)));
                doneWorkout.setWorkoutId(cursor.getInt(cursor.getColumnIndex(KEY_WORKOUT_ID)));
                doneWorkout.setUserId(cursor.getInt(cursor.getColumnIndex(KEY_USER_ID)));
                doneWorkout.setDoneWorkoutDate(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(KEY_DONE_WORKOUT_DATE))));
                doneWorkout.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                doneWorkout.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                doneWorkout.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                doneWorkoutArrayList.add(doneWorkout);
            }
            cursor.moveToNext();
        }

        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            for(int j = i + 1; j < doneWorkoutArrayList.size(); j++) {
                if(doneWorkoutArrayList.get(i).getDoneWorkoutDate().before(doneWorkoutArrayList.get(j).getDoneWorkoutDate())) {
                    DoneWorkout tempPractice = doneWorkoutArrayList.get(i);
                    doneWorkoutArrayList.set(i,doneWorkoutArrayList.get(j));
                    doneWorkoutArrayList.set(j,tempPractice);
                }
            }
        }

    }

    public void clearDoneWorkouts() {
        for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("doneWorkoutId", doneWorkoutArrayList.get(i).getDoneWorkoutId());
        }
        doneWorkoutArrayList.clear();
    }
}
