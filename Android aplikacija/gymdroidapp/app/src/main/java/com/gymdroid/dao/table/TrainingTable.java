package com.gymdroid.dao.table;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.R;
import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class TrainingTable extends Table {

    public static final int INTENSITY_LEVEL_BEGINNER = 0;
    public static final int INTENSITY_LEVEL_INTERMEDIATE = 1;
    public static final int INTENSITY_LEVEL_ADVANCED = 2;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "training";
    private static final String KEY_TRAINING_ID = "trainingId";
    private static final String KEY_TRAINING_NAME = "trainingName";
    private static final String KEY_TRAINING_DESCRIPTION = "trainingDescription";
    private static final String KEY_TRAINING_INTENSITY_LEVEL = "trainingIntensityLevel";
    private static final String KEY_DURATION_OF_PAUSE_BETWEEN_WORKOUTS = "durationOfPauseBetweenWorkouts";
    private static final String KEY_USER_CREATOR_ID = "idUserCreator";

    private ArrayList<Training> trainingArrayList;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_TRAINING_ID + " integer,"
            + KEY_TRAINING_NAME + " text,"
            + KEY_TRAINING_DESCRIPTION + " text,"
            + KEY_TRAINING_INTENSITY_LEVEL + " integer,"
            + KEY_DURATION_OF_PAUSE_BETWEEN_WORKOUTS + " long,"
            + KEY_USER_CREATOR_ID + " integer,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public TrainingTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public boolean insertTraining(Training training) {
        //int trainingId = databaseHelper.getNextId();
        //training.setTrainingId(trainingId);
        ContentValues contentValues = toContentValues(training);
        if(databaseHelper.insert(contentValues)){
            trainingArrayList.add(training);
            return true;
        }
        return false;
    }

    public boolean updateTraining(Training training) {
        ContentValues contentValues = toContentValues(training);
        if  (databaseHelper.update(contentValues, KEY_TRAINING_ID + "=" + training.getTrainingId())) {
            for (int i = 0; i < trainingArrayList.size(); i++) {
                if (trainingArrayList.get(i).getTrainingId() == training.getTrainingId()) {
                    trainingArrayList.set(i, training);
                }
            }
            return  true;
        }
        return false;
    }

    public void deleteTraining(int trainingId) {
        databaseHelper.deleteRowByRowId(KEY_TRAINING_ID, trainingId);
        for (int i = 0; i < trainingArrayList.size(); i++) {
            if (trainingArrayList.get(i).getTrainingId() == trainingId) {
                trainingArrayList.remove(i);
                break;
            }
        }
    }

    public ArrayList<Training> getTrainingArrayList() {
        return trainingArrayList;
    }

    public Training getTrainingArrayList(TrainingWorkout trainingWorkout) {
        for (int i = 0; i < trainingArrayList.size(); i++) {
            if (trainingArrayList.get(i).getTrainingId() == trainingWorkout.getTrainingId()) {
                return trainingArrayList.get(i);
            }
        }
        return null;
    }

    private ContentValues toContentValues(Training training) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TRAINING_ID, training.getTrainingId());
        contentValues.put(KEY_TRAINING_NAME, training.getTrainingName());
        contentValues.put(KEY_TRAINING_DESCRIPTION, training.getTrainingDescription());
        contentValues.put(KEY_TRAINING_INTENSITY_LEVEL, training.getTrainingIntensityLevel());
        contentValues.put(KEY_DURATION_OF_PAUSE_BETWEEN_WORKOUTS, training.getDurationOfPauseBetweenWorkouts());
        contentValues.put(KEY_USER_CREATOR_ID, training.getUserCreatorId());
        contentValues.put(CREATED_AT, calendarService.dateToString(training.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(training.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(training.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        trainingArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Training training = new Training();
            training.setTrainingId(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_ID)));
            training.setTrainingName(cursor.getString(cursor.getColumnIndex(KEY_TRAINING_NAME)));
            training.setTrainingDescription(cursor.getString(cursor.getColumnIndex(KEY_TRAINING_DESCRIPTION)));
            training.setTrainingIntensityLevel(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_INTENSITY_LEVEL)));
            training.setDurationOfPauseBetweenWorkouts(cursor.getLong(cursor.getColumnIndex(KEY_DURATION_OF_PAUSE_BETWEEN_WORKOUTS)));
            training.setUserCreatorId(cursor.getInt(cursor.getColumnIndex(KEY_USER_CREATOR_ID)));
            training.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
            training.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
            training.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
            trainingArrayList.add(training);
            cursor.moveToNext();
        }
    }

    public static String getIntensityName(Activity activity, int intensityLevel) {
        switch (intensityLevel) {
            case INTENSITY_LEVEL_BEGINNER: {
                return activity.getResources().getString( R.string.training_intensity_level_beginner);
            }
            case INTENSITY_LEVEL_INTERMEDIATE: {
                return activity.getResources().getString(R.string.training_intensity_level_intermediate);
            }
            case INTENSITY_LEVEL_ADVANCED: {
                return activity.getResources().getString(R.string.training_intensity_level_advanced);
            }
            default:
                return activity.getResources().getString(R.string.training_intensity_level_error);
        }
    }

    public void clearTrainingTable() {
        for (int i = 0; i < trainingArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("trainingId", trainingArrayList.get(i).getTrainingId());
        }
        trainingArrayList.clear();
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(trainingArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = trainingArrayList.get(0).getCreatedAt();
        for(int i = 1; i < trainingArrayList.size(); i++) {
            if(searchDate.before(trainingArrayList.get(i).getCreatedAt())) {
                searchDate = trainingArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(trainingArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = trainingArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < trainingArrayList.size(); i++) {
            if(searchDate.before(trainingArrayList.get(i).getUpdatedAt())) {
                searchDate = trainingArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }
}
