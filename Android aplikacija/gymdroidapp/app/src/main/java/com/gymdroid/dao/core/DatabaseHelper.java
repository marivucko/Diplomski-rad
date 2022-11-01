package com.gymdroid.dao.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private String tableQuery;
    private String tableName;
    private SQLiteDatabase readableDatabase;

    public DatabaseHelper(Context context, String tableQuery, String tableName, int databaseVersion) {
        super(context, tableName + ".db", null, databaseVersion);
        this.tableQuery = tableQuery;
        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(tableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(database);
    }

    public Cursor getAllData() {
        readableDatabase = this.getReadableDatabase();
        return readableDatabase.rawQuery( "select * from " + tableName, null );
    }

    public void closeReadableDatabase() {
        readableDatabase.close();
    }

    public Boolean insert(ContentValues values) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.insert(tableName, null, values);
        sqLiteDatabase.close();
        return result != -1;
    }

    public Boolean update(ContentValues values, String updateQuery) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.update(tableName,values,updateQuery,null);
        sqLiteDatabase.close();
        return result != -1;
    }

    public int getNextId() {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery( "select * from " + tableName, null );
        int size = cursor.getCount() + 1;
        cursor.close();
        return size;
    }

    public void deleteRowByRowId(String tableId, int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(tableName,tableId + " = " + id, null);
    }

}
