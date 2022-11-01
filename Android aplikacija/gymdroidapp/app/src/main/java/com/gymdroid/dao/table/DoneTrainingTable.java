package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class DoneTrainingTable extends Table {

    private ArrayList<DoneTraining> doneTrainingArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "doneTraining";
    private static final String KEY_DONE_TRAINING_ID = "doneTrainingId";
    private static final String KEY_DONE_TRAINING_AT = "doneTrainingAt";
    private static final String KEY_TRAINING_ID = "trainingId";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_DONE_TRAINING_ID + " integer,"
            + KEY_DONE_TRAINING_AT + " text,"
            + KEY_TRAINING_ID + " integer,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public DoneTrainingTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public boolean insertDoneTraining(DoneTraining doneTraining) {
        ContentValues contentValues = toContentValues(doneTraining);
        if(databaseHelper.insert(contentValues)) {
            for(int i = 0; i < doneTrainingArrayList.size(); i++) {
                if(doneTraining.getDoneTrainingAt().before(doneTrainingArrayList.get(i).getDoneTrainingAt())) {
                    doneTrainingArrayList.add(i,doneTraining);
                    return true;
                }
            }
            doneTrainingArrayList.add(doneTraining);
            return true;
        }
        return false;
    }

    public boolean updateDoneTraining(DoneTraining doneTraining) {
        ContentValues contentValues = toContentValues(doneTraining);
        if  (databaseHelper.update(contentValues, KEY_DONE_TRAINING_ID + "=" + doneTraining.getDoneTrainingId())) {
            for (int i = 0; i < doneTrainingArrayList.size(); i++) {
                if (doneTrainingArrayList.get(i).getDoneTrainingId() == doneTraining.getDoneTrainingId()) {
                    doneTrainingArrayList.set(i, doneTraining);
                }
            }
            return  true;
        }
        return false;
    }


    public void deleteDoneTraining(int trainingDoneId) {
        databaseHelper.deleteRowByRowId(KEY_DONE_TRAINING_ID, trainingDoneId);
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            if (doneTrainingArrayList.get(i).getTrainingId() == trainingDoneId) {
                doneTrainingArrayList.remove(i);
                break;
            }
        }
    }

    public ArrayList<DoneTraining> getDoneTrainingArrayList() {
        return doneTrainingArrayList;
    }

    public ArrayList<DoneTraining> getDoneTrainingByTrainingId(int trainingId) {
        ArrayList<DoneTraining> doneTrainings = new ArrayList<>();
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            if (doneTrainingArrayList.get(i).getTrainingId() == trainingId) {
                doneTrainings.add(doneTrainingArrayList.get(i));
            }
        }
        return doneTrainings;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(doneTrainingArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = doneTrainingArrayList.get(0).getCreatedAt();
        for(int i = 1; i < doneTrainingArrayList.size(); i++) {
            if(searchDate.before(doneTrainingArrayList.get(i).getCreatedAt())) {
                searchDate = doneTrainingArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(doneTrainingArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = doneTrainingArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < doneTrainingArrayList.size(); i++) {
            if(searchDate.before(doneTrainingArrayList.get(i).getUpdatedAt())) {
                searchDate = doneTrainingArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    private ContentValues toContentValues(DoneTraining doneTraining) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DONE_TRAINING_ID, doneTraining.getDoneTrainingId());
        contentValues.put(KEY_DONE_TRAINING_AT, calendarService.dateToString(doneTraining.getDoneTrainingAt()));
        contentValues.put(KEY_TRAINING_ID, doneTraining.getTrainingId());
        contentValues.put(CREATED_AT, calendarService.dateToString(doneTraining.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(doneTraining.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(doneTraining.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        doneTrainingArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                DoneTraining doneTraining = new DoneTraining();
                doneTraining.setDoneTrainingId(cursor.getInt(cursor.getColumnIndex(KEY_DONE_TRAINING_ID)));
                doneTraining.setDoneTrainingAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(KEY_DONE_TRAINING_AT))));
                doneTraining.setTrainingId(cursor.getInt(cursor.getColumnIndex(KEY_TRAINING_ID)));
                doneTraining.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                doneTraining.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                doneTraining.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                doneTrainingArrayList.add(doneTraining);
            }
            cursor.moveToNext();
        }
    }

    public void clearDoneSets() {
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("doneTrainingId", doneTrainingArrayList.get(i).getDoneTrainingId());
        }
        doneTrainingArrayList.clear();
    }

    public void clearDoneTrainings() {
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("doneTrainingId", doneTrainingArrayList.get(i).getDoneTrainingId());
        }
        doneTrainingArrayList.clear();
    }
}
