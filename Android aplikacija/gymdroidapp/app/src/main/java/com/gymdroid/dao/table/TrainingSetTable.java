package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class TrainingSetTable extends Table {

    private ArrayList<TrainingSet> trainingSetArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "trainingSet";
    private static final String KEY_TRAINING_SET_ID = "trainingSetId";
    private static final String KEY_TRAINING_SET_TIME = "trainingSetTime";
    private static final String KEY_TRAINING_SET_NUMBER_OF_REPS = "trainingSetNumberOfReps";
    private static final String KEY_TRAINING_SET_WEIGHT = "trainingSetWeight";
    private static final String KEY_TRAINING_WORKOUT_ID = "trainingWorkoutId";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_TRAINING_SET_ID + " integer,"
            + KEY_TRAINING_SET_TIME + " long,"
            + KEY_TRAINING_SET_NUMBER_OF_REPS + " integer,"
            + KEY_TRAINING_SET_WEIGHT + " trainingSetWeight,"
            + KEY_TRAINING_WORKOUT_ID + " integer,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public TrainingSetTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public boolean insertTrainingSet(TrainingSet trainingSet) {
        ContentValues contentValues = toContentValues(trainingSet);
        if(databaseHelper.insert(contentValues)){
            trainingSetArrayList.add(trainingSet);
            return true;
        }
        return false;
    }

    public boolean updateTrainingSet(TrainingSet trainingSet) {
        ContentValues contentValues = toContentValues(trainingSet);
        if  (databaseHelper.update(contentValues, KEY_TRAINING_SET_ID + "=" + trainingSet.getTrainingSetId())) {
            for (int i = 0; i < trainingSetArrayList.size(); i++) {
                if (trainingSetArrayList.get(i).getTrainingSetId() == trainingSet.getTrainingSetId()) {
                    trainingSetArrayList.set(i, trainingSet);
                }
            }
            return  true;
        }
        return false;
    }

    public void deleteTrainingSet(int trainingSetId) {
        databaseHelper.deleteRowByRowId(KEY_TRAINING_SET_ID, trainingSetId);
        for (int i = 0; i < trainingSetArrayList.size(); i++) {
            if (trainingSetArrayList.get(i).getTrainingSetId() == trainingSetId) {
                trainingSetArrayList.remove(i);
                break;
            }
        }
    }

    public ArrayList<TrainingSet> getTrainingSetArrayList(int trainingWorkoutId) {
        ArrayList<TrainingSet> arrayList = new ArrayList<>();
        for (int i = 0; i < trainingSetArrayList.size(); i++) {
            if (trainingSetArrayList.get(i).getTrainingWorkoutId() == trainingWorkoutId)
                arrayList.add(trainingSetArrayList.get(i));
        }
        return arrayList;
    }

    public ArrayList<TrainingSet> getTrainingSetArrayList() {
        return trainingSetArrayList;
    }

    private ContentValues toContentValues(TrainingSet trainingSet) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TRAINING_SET_ID, trainingSet.getTrainingSetId());
        contentValues.put(KEY_TRAINING_SET_TIME, trainingSet.getTrainingSetTime());
        contentValues.put(KEY_TRAINING_SET_NUMBER_OF_REPS, trainingSet.getTrainingSetNumberOfReps());
        contentValues.put(KEY_TRAINING_WORKOUT_ID, trainingSet.getTrainingWorkoutId());
        contentValues.put(CREATED_AT, calendarService.dateToString(trainingSet.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(trainingSet.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(trainingSet.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        trainingSetArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            TrainingSet trainingSet = new TrainingSet();
            trainingSet.setTrainingSetId(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_SET_ID)));
            trainingSet.setTrainingSetTime(cursor.getLong(cursor.getColumnIndex(KEY_TRAINING_SET_TIME)));
            trainingSet.setTrainingSetNumberOfReps(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_SET_NUMBER_OF_REPS)));
            trainingSet.setTrainingSetWeight(cursor.getDouble(cursor.getColumnIndex(KEY_TRAINING_SET_WEIGHT)));
            trainingSet.setTrainingWorkoutId(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_WORKOUT_ID)));
            trainingSet.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
            trainingSet.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
            trainingSet.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
            trainingSetArrayList.add(trainingSet);
            cursor.moveToNext();
        }
    }

    public void clearTrainingSets() {
        for (int i = 0; i < trainingSetArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("trainingSetId", trainingSetArrayList.get(i).getTrainingSetId());
        }
        trainingSetArrayList.clear();
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(trainingSetArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = trainingSetArrayList.get(0).getCreatedAt();
        for(int i = 1; i < trainingSetArrayList.size(); i++) {
            if(searchDate.before(trainingSetArrayList.get(i).getCreatedAt())) {
                searchDate = trainingSetArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(trainingSetArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = trainingSetArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < trainingSetArrayList.size(); i++) {
            if(searchDate.before(trainingSetArrayList.get(i).getUpdatedAt())) {
                searchDate = trainingSetArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }
}
