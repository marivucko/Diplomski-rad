package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class DoneSetTable extends Table {

    private ArrayList<DoneSet> doneSetArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "doneSet";
    private static final String KEY_DONE_SET_ID = "doneSetId";
    private static final String KEY_DONE_SET_NUMBER_OF_REPS = "doneSetNumberOfReps";
    private static final String KEY_DONE_SET_WEIGHT = "doneSetWeight";
    private static final String KEY_DONE_SET_TIME = "doneSetTimeInMilliseconds";
    private static final String KEY_DONE_WORKOUT_ID = "doneWorkoutId";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_DONE_SET_ID + " integer,"
            + KEY_DONE_SET_NUMBER_OF_REPS + " integer,"
            + KEY_DONE_SET_WEIGHT + " double,"
            + KEY_DONE_SET_TIME + " integer,"
            + KEY_DONE_WORKOUT_ID + " integer,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public DoneSetTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public boolean insertDoneSet(DoneSet doneSet) {
        ContentValues contentValues = toContentValues(doneSet);
        if(databaseHelper.insert(contentValues)){
            doneSetArrayList.add(doneSet);
            return true;
        }
        return false;
    }

    public boolean updateDoneSet(DoneSet doneSet) {
        ContentValues contentValues = toContentValues(doneSet);
        if  (databaseHelper.update(contentValues, KEY_DONE_SET_ID + "=" + doneSet.getDoneSetId())) {
            for (int i = 0; i < doneSetArrayList.size(); i++) {

                if (doneSetArrayList.get(i).getDoneSetId() == doneSet.getDoneSetId()) {
                    doneSetArrayList.set(i, doneSet);
                }
                System.out.println( "```````````` " + doneSetArrayList.get(i).getDoneSetId() + " " + doneSetArrayList.get(i).getDoneSetNumberOfReps());
            }
            return  true;
        }
        return false;
    }

    public void deleteDoneSet(DoneSet doneSet) {
        databaseHelper.deleteRowByRowId(KEY_DONE_SET_ID, doneSet.getDoneSetId());
        for (int i = 0; i < doneSetArrayList.size(); i++) {
            if (doneSetArrayList.get(i).getDoneSetId() == doneSet.getDoneSetId()) {
                doneSetArrayList.remove(i);
                break;
            }
        }
    }

    public ArrayList<DoneSet> getDoneSetArrayList() {
        return doneSetArrayList;
    }

    public ArrayList<DoneSet> getDoneSetArrayList(DoneWorkout doneWorkout) {
        ArrayList<DoneSet> doneSets = new ArrayList<>();
        for (int i = 0; i < doneSetArrayList.size(); i++) {
            if (doneSetArrayList.get(i).getDoneWorkoutId() == doneWorkout.getDoneWorkoutId()) {
                doneSets.add(doneSetArrayList.get(i));
            }
        }
        return doneSets;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(doneSetArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = doneSetArrayList.get(0).getCreatedAt();
        for(int i = 1; i < doneSetArrayList.size(); i++) {
            if(searchDate.before(doneSetArrayList.get(i).getCreatedAt())) {
                searchDate = doneSetArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(doneSetArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = doneSetArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < doneSetArrayList.size(); i++) {
            if(searchDate.before(doneSetArrayList.get(i).getUpdatedAt())) {
                searchDate = doneSetArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    private ContentValues toContentValues(DoneSet doneSet) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DONE_SET_ID, doneSet.getDoneSetId());
        contentValues.put(KEY_DONE_SET_NUMBER_OF_REPS, doneSet.getDoneSetNumberOfReps());
        contentValues.put(KEY_DONE_SET_WEIGHT, doneSet.getDoneSetWeight());
        contentValues.put(KEY_DONE_SET_TIME, doneSet.getDoneSetTimeInMilliseconds());
        contentValues.put(KEY_DONE_WORKOUT_ID, doneSet.getDoneWorkoutId());
        contentValues.put(CREATED_AT, calendarService.dateToString(doneSet.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(doneSet.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(doneSet.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        doneSetArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                DoneSet doneSet = new DoneSet();
                doneSet.setDoneSetId(cursor.getInt(cursor.getColumnIndex(KEY_DONE_SET_ID)));
                doneSet.setDoneSetNumberOfReps(cursor.getInt(cursor.getColumnIndex(KEY_DONE_SET_NUMBER_OF_REPS)));
                doneSet.setDoneSetWeight(cursor.getDouble(cursor.getColumnIndex(KEY_DONE_SET_WEIGHT)));
                doneSet.setDoneSetTimeInMilliseconds(cursor.getInt(cursor.getColumnIndex(KEY_DONE_SET_TIME)));
                doneSet.setDoneWorkoutId(cursor.getInt(cursor.getColumnIndex(KEY_DONE_WORKOUT_ID)));
                doneSet.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                doneSet.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                doneSet.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                doneSetArrayList.add(doneSet);
            }
            cursor.moveToNext();
        }
    }

    public void clearDoneSets() {
        for (int i = 0; i < doneSetArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("doneSetId", doneSetArrayList.get(i).getDoneSetId());
        }
        doneSetArrayList.clear();
    }
}
