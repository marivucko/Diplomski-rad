package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class TrainingWorkoutTable extends Table {

    private ArrayList<TrainingWorkout> trainingWorkoutArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "trainingWorkout";
    private static final String KEY_TRAINING_WORKOUT_ID = "trainingWorkoutId";
    private static final String KEY_TRAINING_WORKOUT_ORDER_NUMBER = "trainingWorkoutOrderNumber";
    private static final String KEY_DURATION_OF_PAUSE_BETWEEN_SETS = "durationOfPauseBetweenSets";
    private static final String KEY_WORKOUT_ID = "workoutId";
    private static final String KEY_TRAINING_ID = "trainingId";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_TRAINING_WORKOUT_ID + " integer,"
            + KEY_TRAINING_WORKOUT_ORDER_NUMBER + " integer,"
            + KEY_DURATION_OF_PAUSE_BETWEEN_SETS + " long,"
            + KEY_WORKOUT_ID + " integer,"
            + KEY_TRAINING_ID + " integer,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public TrainingWorkoutTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public boolean insertTrainingWorkout(TrainingWorkout trainingWorkout) {
        ContentValues contentValues = toContentValues(trainingWorkout);
        if(databaseHelper.insert(contentValues)){
            trainingWorkoutArrayList.add(trainingWorkout);
            return true;
        }
        return false;
    }

    public boolean updateTrainingWorkout(TrainingWorkout trainingWorkout) {
        ContentValues contentValues = toContentValues(trainingWorkout);
        if  (databaseHelper.update(contentValues, KEY_TRAINING_WORKOUT_ID + "=" + trainingWorkout.getTrainingWorkoutId())) {
            for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
                if (trainingWorkoutArrayList.get(i).getTrainingWorkoutId() == trainingWorkout.getTrainingWorkoutId()) {
                    trainingWorkoutArrayList.set(i, trainingWorkout);
                }
            }
            return  true;
        }
        return false;
    }

    public void deleteTrainingWorkout(int trainingWorkoutId) {
        databaseHelper.deleteRowByRowId(KEY_TRAINING_WORKOUT_ID, trainingWorkoutId);
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            if (trainingWorkoutArrayList.get(i).getTrainingWorkoutId() == trainingWorkoutId) {
                trainingWorkoutArrayList.remove(i);
                break;
            }
        }
    }

    public ArrayList<TrainingWorkout> getTrainingWorkoutArrayList(int trainingId) {
        ArrayList<TrainingWorkout> arrayList = new ArrayList<>();
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            if (trainingWorkoutArrayList.get(i).getTrainingId() == trainingId)
                arrayList.add(trainingWorkoutArrayList.get(i));
        }
        return arrayList;
    }

    public ArrayList<TrainingWorkout> getTrainingWorkoutArrayList() {
        return trainingWorkoutArrayList;
    }

    private ContentValues toContentValues(TrainingWorkout trainingWorkout) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TRAINING_WORKOUT_ID, trainingWorkout.getTrainingWorkoutId());
        contentValues.put(KEY_TRAINING_WORKOUT_ORDER_NUMBER, trainingWorkout.getOrderNumber());
        contentValues.put(KEY_DURATION_OF_PAUSE_BETWEEN_SETS, trainingWorkout.getDurationOfPauseBetweenSets());
        contentValues.put(KEY_WORKOUT_ID, trainingWorkout.getWorkoutId());
        contentValues.put(KEY_TRAINING_ID, trainingWorkout.getTrainingId());
        contentValues.put(CREATED_AT, calendarService.dateToString(trainingWorkout.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(trainingWorkout.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(trainingWorkout.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        trainingWorkoutArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TrainingWorkout trainingWorkout = new TrainingWorkout();
            trainingWorkout.setTrainingWorkoutId(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_WORKOUT_ID)));
            trainingWorkout.setOrderNumber(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_WORKOUT_ORDER_NUMBER)));
            trainingWorkout.setDurationOfPauseBetweenSets(cursor.getLong(cursor.getColumnIndex(KEY_DURATION_OF_PAUSE_BETWEEN_SETS)));
            trainingWorkout.setWorkoutId(cursor.getInt(cursor.getColumnIndex(KEY_WORKOUT_ID)));
            trainingWorkout.setTrainingId(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_ID)));
            trainingWorkout.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
            trainingWorkout.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
            trainingWorkout.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
            trainingWorkoutArrayList.add(trainingWorkout);
            cursor.moveToNext();
        }
    }

    public void clearTrainingTable() {
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("trainingWorkoutId", trainingWorkoutArrayList.get(i).getTrainingWorkoutId());
        }
        trainingWorkoutArrayList.clear();
    }

    public TrainingWorkout getTrainingWorkout(int trainingWorkoutId) {
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            if (trainingWorkoutArrayList.get(i).getTrainingWorkoutId() == trainingWorkoutId)
                return trainingWorkoutArrayList.get(i);
        }
        return null;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(trainingWorkoutArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = trainingWorkoutArrayList.get(0).getCreatedAt();
        for(int i = 1; i < trainingWorkoutArrayList.size(); i++) {
            if(searchDate.before(trainingWorkoutArrayList.get(i).getCreatedAt())) {
                searchDate = trainingWorkoutArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(trainingWorkoutArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = trainingWorkoutArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < trainingWorkoutArrayList.size(); i++) {
            if(searchDate.before(trainingWorkoutArrayList.get(i).getUpdatedAt())) {
                searchDate = trainingWorkoutArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    public ArrayList<TrainingWorkout> getTrainingWorkoutArrayList(Workout workout) {
        ArrayList<TrainingWorkout> trainingWorkouts = new ArrayList<>();
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            if (trainingWorkoutArrayList.get(i).getWorkoutId() == workout.getWorkoutId()) {
                trainingWorkouts.add(trainingWorkoutArrayList.get(i));
            }
        }
        return trainingWorkouts;
    }
}
