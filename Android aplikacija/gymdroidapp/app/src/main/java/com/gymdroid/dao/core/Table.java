package com.gymdroid.dao.core;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

public abstract class Table {

    protected static final String KEY_ID = "keyId";
    protected static final String CREATED_AT = "createdAt";
    protected static final String UPDATED_AT = "updatedAt";
    protected static final String DELETED_AT = "deletedAt";

    protected DatabaseHelper databaseHelper;
    protected Activity activity;

    public Table(Context context, String createTableQuery, String tableName, int databaseVersion) {
        databaseHelper = new DatabaseHelper(context, createTableQuery, tableName, databaseVersion);
        activity = (Activity) context;
        Cursor cursor = databaseHelper.getAllData();
        initiateArrayList(cursor);
        databaseHelper.closeReadableDatabase();
        cursor.close();
    }

    protected abstract void initiateArrayList(Cursor cursor);

    protected boolean isNotDeleted(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        return null == calendarService.
                stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT)));
    }
}
