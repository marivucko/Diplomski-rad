package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class MuscleTable extends Table {

    private ArrayList<Muscle> muscleArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "muscle";
    private static final String KEY_MUSCLE_ID = "muscleId";
    private static final String KEY_MUSCLE_NAME = "muscleName";
    private static final String KEY_MUSCLE_SIZE = "muscleSize";
    private static final String KEY_MUSCLE_GROUP_ID = "muscleGroupId";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_MUSCLE_ID + " int,"
            + KEY_MUSCLE_NAME + " text,"
            + KEY_MUSCLE_SIZE + " double,"
            + KEY_MUSCLE_GROUP_ID + " int,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public MuscleTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public Boolean insertMuscle(Muscle muscle) {
        ContentValues contentValues = toContentValues(muscle);
        if (databaseHelper.insert(contentValues)) {
            muscleArrayList.add(muscle);
            return true;
        }
        return false;
    }

    public boolean updateMuscle(Muscle muscle) {
        ContentValues contentValues = toContentValues(muscle);
        if  (databaseHelper.update(contentValues, KEY_MUSCLE_ID + "=" + muscle.getMuscleId())) {
            for (int i = 0; i < muscleArrayList.size(); i++) {
                if (muscleArrayList.get(i).getMuscleId() == muscle.getMuscleId()) {
                    muscleArrayList.set(i, muscle);
                }
            }
            return  true;
        }
        return false;
    }

    public ArrayList<Muscle> getMuscleArrayList() {
        return muscleArrayList;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(muscleArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = muscleArrayList.get(0).getCreatedAt();
        for(int i = 1; i < muscleArrayList.size(); i++) {
            if(searchDate.before(muscleArrayList.get(i).getCreatedAt())) {
                searchDate = muscleArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(muscleArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = muscleArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < muscleArrayList.size(); i++) {
            if(searchDate.before(muscleArrayList.get(i).getUpdatedAt())) {
                searchDate = muscleArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    private ContentValues toContentValues(Muscle muscle) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MUSCLE_ID, muscle.getMuscleId());
        contentValues.put(KEY_MUSCLE_NAME, muscle.getMuscleName());
        contentValues.put(KEY_MUSCLE_SIZE, muscle.getMuscleSize());
        contentValues.put(KEY_MUSCLE_GROUP_ID, muscle.getMuscleGroupId());
        contentValues.put(CREATED_AT, calendarService.dateToString(muscle.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(muscle.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(muscle.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        muscleArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                Muscle muscle = new Muscle();
                muscle.setMuscleId(cursor.getInt(cursor.getColumnIndex(KEY_MUSCLE_ID)));
                muscle.setMuscleName(cursor.getString(cursor.getColumnIndex(KEY_MUSCLE_NAME)));
                muscle.setMuscleSize(cursor.getDouble(cursor.getColumnIndex(KEY_MUSCLE_SIZE)));
                muscle.setMuscleGroupId(cursor.getInt(cursor.getColumnIndex(KEY_MUSCLE_GROUP_ID)));
                muscle.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                muscle.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                muscle.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                muscleArrayList.add(muscle);
            }
            cursor.moveToNext();
        }
    }

    public void clearMuscles() {
        for (int i = 0; i < muscleArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("muscleId", muscleArrayList.get(i).getMuscleId());
        }
        muscleArrayList.clear();
    }
}
