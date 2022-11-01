package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class MuscleGroupTable extends Table {

    private ArrayList<MuscleGroup> muscleGroupArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "muscleGroup";
    private static final String KEY_MUSCLE_GROUP_ID = "muscleGroupId";
    private static final String KEY_MUSCLE_GROUP_NAME = "muscleGroupName";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_MUSCLE_GROUP_ID + " int,"
            + KEY_MUSCLE_GROUP_NAME + " text,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public MuscleGroupTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public Boolean insertMuscleGroup(MuscleGroup muscleGroup) {
        ContentValues contentValues = toContentValues(muscleGroup);
        if (databaseHelper.insert(contentValues)) {
            muscleGroupArrayList.add(muscleGroup);
            return true;
        }
        return false;
    }

    public boolean updateMuscleGroup(MuscleGroup muscleGroup) {
        ContentValues contentValues = toContentValues(muscleGroup);
        if  (databaseHelper.update(contentValues, KEY_MUSCLE_GROUP_ID + "=" + muscleGroup.getMuscleGroupId())) {
            for (int i = 0; i < muscleGroupArrayList.size(); i++) {
                if (muscleGroupArrayList.get(i).getMuscleGroupId() == muscleGroup.getMuscleGroupId()) {
                    muscleGroupArrayList.set(i, muscleGroup);
                }
            }
            return  true;
        }
        return false;
    }

    public ArrayList<MuscleGroup> getMuscleGroupArrayList() {
        return muscleGroupArrayList;
    }

    private ContentValues toContentValues(MuscleGroup muscleGroup) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MUSCLE_GROUP_ID, muscleGroup.getMuscleGroupId());
        contentValues.put(KEY_MUSCLE_GROUP_NAME, muscleGroup.getMuscleGroupName());
        contentValues.put(CREATED_AT, calendarService.dateToString(muscleGroup.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(muscleGroup.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(muscleGroup.getDeletedAt()));
        return contentValues;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(muscleGroupArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = muscleGroupArrayList.get(0).getCreatedAt();
        for(int i = 1; i < muscleGroupArrayList.size(); i++) {
            if(searchDate.before(muscleGroupArrayList.get(i).getCreatedAt())) {
                searchDate = muscleGroupArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(muscleGroupArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = muscleGroupArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < muscleGroupArrayList.size(); i++) {
            if(searchDate.before(muscleGroupArrayList.get(i).getUpdatedAt())) {
                searchDate = muscleGroupArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        muscleGroupArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                MuscleGroup muscleGroup = new MuscleGroup();
                muscleGroup.setMuscleGroupId(cursor.getInt(cursor.getColumnIndex(KEY_MUSCLE_GROUP_ID)));
                muscleGroup.setMuscleGroupName(cursor.getString(cursor.getColumnIndex(KEY_MUSCLE_GROUP_NAME)));
                muscleGroup.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                muscleGroup.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                muscleGroup.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                muscleGroupArrayList.add(muscleGroup);
            }
            cursor.moveToNext();
        }
    }

    public void clearMuscleGroups() {
        for (int i = 0; i < muscleGroupArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("muscleGroupId", muscleGroupArrayList.get(i).getMuscleGroupId());
        }
        muscleGroupArrayList.clear();
    }
}
