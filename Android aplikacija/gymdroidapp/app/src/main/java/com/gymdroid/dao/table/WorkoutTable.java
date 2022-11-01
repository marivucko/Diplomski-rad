package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class WorkoutTable extends Table {

    private ArrayList<Workout> workoutArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "workout";
    private static final String KEY_WORKOUT_ID = "workoutId";
    private static final String KEY_WORKOUT_NAME = "workoutName";
    private static final String KEY_WORKOUT_DESCRIPTION = "workoutDescription";
    private static final String KEY_WORKOUT_STATUS_IS_APPROVED = "workoutStatusIsApproved";
    private static final String KEY_WORKOUT_NEED_TIME = "workoutNeedTime";
    private static final String KEY_WORKOUT_NEED_WEIGHT = "workoutNeedWeight";
    private static final String KEY_USER_CREATOR_ID = "userCreatorId";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_WORKOUT_ID + " integer,"
            + KEY_WORKOUT_NAME + " text,"
            + KEY_WORKOUT_DESCRIPTION + " text,"
            + KEY_WORKOUT_STATUS_IS_APPROVED + " int,"
            + KEY_WORKOUT_NEED_TIME + " int,"
            + KEY_WORKOUT_NEED_WEIGHT + " int,"
            + KEY_USER_CREATOR_ID + " int,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public WorkoutTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public Boolean insertWorkout(Workout workout) {
        ContentValues contentValues = toContentValues(workout);
        if (databaseHelper.insert(contentValues)) {
            workoutArrayList.add(workout);
            return true;
        }
        return false;
    }

    public boolean updateWorkout(Workout workout) {
        ContentValues contentValues = toContentValues(workout);
        if  (databaseHelper.update(contentValues, KEY_WORKOUT_ID + "=" + workout.getWorkoutId())) {
            for (int i = 0; i < workoutArrayList.size(); i++) {
                if (workoutArrayList.get(i).getWorkoutId() == workout.getWorkoutId()) {
                    workoutArrayList.set(i, workout);
                }
            }
            return  true;
        }
        return false;
    }

    public void deleteWorkout(int workoutId) {
        databaseHelper.deleteRowByRowId(KEY_WORKOUT_ID, workoutId);
        for (int i = 0; i < workoutArrayList.size(); i++) {
            if (workoutArrayList.get(i).getWorkoutId() == workoutId) {
                workoutArrayList.remove(i);
                break;
            }
        }
    }

    public ArrayList<Workout> getWorkoutArrayList() {
        return workoutArrayList;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(workoutArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = workoutArrayList.get(0).getCreatedAt();
        for(int i = 1; i < workoutArrayList.size(); i++) {
            if(searchDate.before(workoutArrayList.get(i).getCreatedAt())) {
                searchDate = workoutArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(workoutArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = workoutArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < workoutArrayList.size(); i++) {
            if(searchDate.before(workoutArrayList.get(i).getUpdatedAt())) {
                searchDate = workoutArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    private ContentValues toContentValues(Workout workout) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_WORKOUT_ID, workout.getWorkoutId());
        contentValues.put(KEY_WORKOUT_NAME, workout.getWorkoutName());
        contentValues.put(KEY_WORKOUT_DESCRIPTION, workout.getWorkoutDescription());
        contentValues.put(KEY_WORKOUT_STATUS_IS_APPROVED, workout.getWorkoutStatusIsApproved());
        contentValues.put(KEY_WORKOUT_NEED_TIME, workout.getWorkoutNeedTime());
        contentValues.put(KEY_WORKOUT_NEED_WEIGHT, workout.getWorkoutNeedWeight());
        contentValues.put(KEY_USER_CREATOR_ID, workout.getUserCreatorId());
        contentValues.put(CREATED_AT, calendarService.dateToString(workout.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(workout.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(workout.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        workoutArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                Workout workout = new Workout();
                workout.setWorkoutId(cursor.getInt(cursor.getColumnIndex(KEY_WORKOUT_ID)));
                workout.setWorkoutName(cursor.getString(cursor.getColumnIndex(KEY_WORKOUT_NAME)));
                workout.setWorkoutDescription(cursor.getString(cursor.getColumnIndex(KEY_WORKOUT_DESCRIPTION)));
                workout.setWorkoutStatusIsApproved(cursor.getInt(cursor.getColumnIndex(KEY_WORKOUT_STATUS_IS_APPROVED)));
                workout.setWorkoutNeedTime(cursor.getInt(cursor.getColumnIndex(KEY_WORKOUT_NEED_TIME)));
                workout.setWorkoutNeedWeight(cursor.getInt(cursor.getColumnIndex(KEY_WORKOUT_NEED_WEIGHT)));
                workout.setUserCreatorId(cursor.getInt(cursor.getColumnIndex(KEY_USER_CREATOR_ID)));
                workout.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                workout.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                workout.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                workoutArrayList.add(workout);
            }
            cursor.moveToNext();
        }
    }

    public void clearWorkouts() {
        for (int i = 0; i < workoutArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("workoutId", workoutArrayList.get(i).getWorkoutId());
        }
        workoutArrayList.clear();
    }

    public boolean workoutInWorkoutArrayList(ArrayList<Workout> workouts, Workout workout) {
        for (int i = 0; i < workouts.size(); i++) {
            if (workouts.get(i).getWorkoutId() == workout.getWorkoutId()) {
                return true;
            }
        }
        return false;
    }
}
