package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class RelationWorkoutMuscleTable extends Table {

    private ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "relationWorkoutMuscle";
    private static final String KEY_RELATION_ID = "relationId";
    private static final String KEY_WORKOUT_ID = "workoutId";
    private static final String KEY_MUSCLE_ID = "muscleId";
    private static final String KEY_MUSCLE_TARGET_PRIORITY = "muscleTargetPriority";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_RELATION_ID + " int,"
            + KEY_WORKOUT_ID + " int,"
            + KEY_MUSCLE_ID + " int,"
            + KEY_MUSCLE_TARGET_PRIORITY + " int,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public RelationWorkoutMuscleTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public Boolean insertRelationWorkoutMuscle(RelationWorkoutMuscle relationWorkoutMuscle) {
        ContentValues contentValues = toContentValues(relationWorkoutMuscle);
        if (databaseHelper.insert(contentValues)) {
            relationWorkoutMuscleArrayList.add(relationWorkoutMuscle);
            return true;
        }
        return false;
    }

    public boolean updateRelationWorkoutMuscle(RelationWorkoutMuscle relationWorkoutMuscle) {
        ContentValues contentValues = toContentValues(relationWorkoutMuscle);
        if  (databaseHelper.update(contentValues, KEY_RELATION_ID + "=" + relationWorkoutMuscle.getRelationId())) {
            for (int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
                if (relationWorkoutMuscleArrayList.get(i).getRelationId() == relationWorkoutMuscle.getRelationId()) {
                    relationWorkoutMuscleArrayList.set(i, relationWorkoutMuscle);
                }
            }
            return true;
        }
        return false;
    }

    public void deleteRelationWorkoutMuscle(int relationWorkoutMuscleId) {
        databaseHelper.deleteRowByRowId(KEY_RELATION_ID, relationWorkoutMuscleId);
        for (int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            if (relationWorkoutMuscleArrayList.get(i).getRelationId() == relationWorkoutMuscleId) {
                relationWorkoutMuscleArrayList.remove(i);
                break;
            }
        }
    }

    public ArrayList<RelationWorkoutMuscle> getRelationWorkoutMuscleArrayList() {
        return relationWorkoutMuscleArrayList;
    }

    public ArrayList<RelationWorkoutMuscle> getAllRelationWorkoutMuscleArrayList() {
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscles = new ArrayList<>();
        for (int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            relationWorkoutMuscles.add(relationWorkoutMuscleArrayList.get(i));
        }
        return relationWorkoutMuscles;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(relationWorkoutMuscleArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = relationWorkoutMuscleArrayList.get(0).getCreatedAt();
        for(int i = 1; i < relationWorkoutMuscleArrayList.size(); i++) {
            if(searchDate.before(relationWorkoutMuscleArrayList.get(i).getCreatedAt())) {
                searchDate = relationWorkoutMuscleArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(relationWorkoutMuscleArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = relationWorkoutMuscleArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < relationWorkoutMuscleArrayList.size(); i++) {
            if(searchDate.before(relationWorkoutMuscleArrayList.get(i).getUpdatedAt())) {
                searchDate = relationWorkoutMuscleArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    private ContentValues toContentValues(RelationWorkoutMuscle relationWorkoutMuscle) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_RELATION_ID, relationWorkoutMuscle.getRelationId());
        contentValues.put(KEY_WORKOUT_ID, relationWorkoutMuscle.getWorkoutId());
        contentValues.put(KEY_MUSCLE_ID, relationWorkoutMuscle.getMuscleId());
        contentValues.put(KEY_MUSCLE_TARGET_PRIORITY, relationWorkoutMuscle.getMuscleTargetPriority());
        contentValues.put(CREATED_AT, calendarService.dateToString(relationWorkoutMuscle.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(relationWorkoutMuscle.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(relationWorkoutMuscle.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        relationWorkoutMuscleArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                RelationWorkoutMuscle relationWorkoutMuscle = new RelationWorkoutMuscle();
                relationWorkoutMuscle.setRelationId(cursor.getInt(cursor.getColumnIndex(KEY_RELATION_ID)));
                relationWorkoutMuscle.setWorkoutId(cursor.getInt(cursor.getColumnIndex(KEY_WORKOUT_ID)));
                relationWorkoutMuscle.setMuscleId(cursor.getInt(cursor.getColumnIndex(KEY_MUSCLE_ID)));
                relationWorkoutMuscle.setMuscleTargetPriority(cursor.getInt(cursor.getColumnIndex(KEY_MUSCLE_TARGET_PRIORITY)));
                relationWorkoutMuscle.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                relationWorkoutMuscle.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                relationWorkoutMuscle.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                relationWorkoutMuscleArrayList.add(relationWorkoutMuscle);
            }
            cursor.moveToNext();
        }
    }

    public void clearRelationsWorkoutMuscle() {
        for (int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("relationId", relationWorkoutMuscleArrayList.get(i).getRelationId());
        }
        relationWorkoutMuscleArrayList.clear();
    }
}
