package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class UserWeightTable extends Table {

    private ArrayList<UserWeight> userWeightArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "userWeight";
    private static final String KEY_WEIGHT_ID = "weightId";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_WEIGHT_NOTE = "weightNote";
    private static final String KEY_WEIGHT_DATE = "weightDate";
    private static final String KEY_USER_ID = "userId";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_WEIGHT_ID + " int,"
            + KEY_WEIGHT + " double,"
            + KEY_WEIGHT_NOTE + " databaseHelpertext,"
            + KEY_WEIGHT_DATE + " text,"
            + KEY_USER_ID + " int,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public UserWeightTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public void clearUserWeights() {
        for (int i = 0; i < userWeightArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId(KEY_WEIGHT_ID, userWeightArrayList.get(i).getWeightId());
        }
        userWeightArrayList.clear();
    }

    public void deleteUserWeight(UserWeight userWeight) {
        databaseHelper.deleteRowByRowId(KEY_WEIGHT_ID, userWeight.getWeightId());
        for (int i = 0; i < userWeightArrayList.size(); i++) {
            if (userWeightArrayList.get(i).getWeightId() == userWeight.getWeightId()) {
                userWeightArrayList.remove(i);
                break;
            }
        }
    }

    public Boolean insertUserWeight(UserWeight userWeight) {
        ContentValues contentValues = toContentValues(userWeight);
        for(int i = 0; i < userWeightArrayList.size(); i++) {
            UserWeight current = userWeightArrayList.get(i);
            if(current.getWeightId() == userWeight.getWeightId()){
                userWeightArrayList.set(i,userWeight);
                return databaseHelper.update(toContentValues(userWeight),KEY_WEIGHT_ID + "=" + userWeight.getWeightId());
            }
        }
        if (databaseHelper.insert(contentValues)) {
            for(int i = 0; i < userWeightArrayList.size(); i++) {
                if(userWeight.getWeightDate().after(userWeightArrayList.get(i).getWeightDate())) {
                    userWeightArrayList.add(i,userWeight);
                    return true;
                }
            }
            userWeightArrayList.add(userWeight);
            return true;
        }
        return false;
    }
    public boolean updateUserWeight(UserWeight userWeight) {
        ContentValues contentValues = toContentValues(userWeight);
        if  (databaseHelper.update(contentValues, KEY_WEIGHT_ID + "=" + userWeight.getWeightId())) {
            for (int i = 0; i < userWeightArrayList.size(); i++) {
                if (userWeightArrayList.get(i).getWeightId() == userWeight.getWeightId()) {
                    userWeightArrayList.set(i, userWeight);
                }
            }
            return  true;
        }
        return false;
    }

    public ArrayList<UserWeight> getUserWeightArrayList() {
        return userWeightArrayList;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(userWeightArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = userWeightArrayList.get(0).getCreatedAt();
        for(int i = 1; i < userWeightArrayList.size(); i++) {
            if(searchDate.before(userWeightArrayList.get(i).getCreatedAt())) {
                searchDate = userWeightArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(userWeightArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = userWeightArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < userWeightArrayList.size(); i++) {
            if(searchDate.before(userWeightArrayList.get(i).getUpdatedAt())) {
                searchDate = userWeightArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    private ContentValues toContentValues(UserWeight userWeight) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_WEIGHT_ID, userWeight.getWeightId());
        contentValues.put(KEY_WEIGHT, userWeight.getWeight());
        contentValues.put(KEY_WEIGHT_NOTE, userWeight.getWeightNote());
        contentValues.put(KEY_WEIGHT_DATE, calendarService.dateToString(userWeight.getWeightDate()));
        contentValues.put(KEY_USER_ID, userWeight.getUserId());
        contentValues.put(CREATED_AT, calendarService.dateToString(userWeight.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(userWeight.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(userWeight.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        userWeightArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                UserWeight userWeight = new UserWeight();
                userWeight.setWeightId(cursor.getInt(cursor.getColumnIndex(KEY_WEIGHT_ID)));
                userWeight.setWeight(cursor.getDouble(cursor.getColumnIndex(KEY_WEIGHT)));
                userWeight.setWeightNote(cursor.getString(cursor.getColumnIndex(KEY_WEIGHT_NOTE)));
                userWeight.setWeightDate(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(KEY_WEIGHT_DATE))));
                userWeight.setUserId(cursor.getInt(cursor.getColumnIndex(KEY_USER_ID)));
                userWeight.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                userWeight.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                userWeight.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                userWeightArrayList.add(userWeight);
            }
            cursor.moveToNext();
        }

        for(int i = 0; i < userWeightArrayList.size(); i++) {
            for(int j = i + 1; j < userWeightArrayList.size(); j++) {
                if(userWeightArrayList.get(i).getWeightDate().before(userWeightArrayList.get(j).getWeightDate())) {
                    UserWeight tempPractice = userWeightArrayList.get(i);
                    userWeightArrayList.set(i,userWeightArrayList.get(j));
                    userWeightArrayList.set(j,tempPractice);
                }
            }
        }
    }

}
