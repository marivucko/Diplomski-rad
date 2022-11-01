package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class EquipmentTypeTable extends Table {

    private ArrayList<EquipmentType> equipmentTypeArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "equipmentType";
    private static final String KEY_EQUIPMENT_TYPE_ID = "equipmentTypeId";
    private static final String KEY_EQUIPMENT_TYPE_NAME = "equipmentTypeName";
    private static final String KEY_EQUIPMENT_IS_UPGRADABLE = "equipmentIsUpgradable";
    private static final String KEY_EQUIPMENT_IS_WEIGHT = "equipmentIsWeight";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_EQUIPMENT_TYPE_ID + " int,"
            + KEY_EQUIPMENT_TYPE_NAME + " text,"
            + KEY_EQUIPMENT_IS_UPGRADABLE + " int,"
            + KEY_EQUIPMENT_IS_WEIGHT + " int,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public EquipmentTypeTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public Boolean insertEquipmentType(EquipmentType equipmentType) {
        ContentValues contentValues = toContentValues(equipmentType);
        if (databaseHelper.insert(contentValues)) {
            equipmentTypeArrayList.add(equipmentType);
            return true;
        }
        return false;
    }

    public boolean updateEquipmentType(EquipmentType equipmentType) {
        ContentValues contentValues = toContentValues(equipmentType);
        if  (databaseHelper.update(contentValues, KEY_EQUIPMENT_TYPE_ID + "=" + equipmentType.getEquipmentTypeId())) {
            for (int i = 0; i < equipmentTypeArrayList.size(); i++) {
                if (equipmentTypeArrayList.get(i).getEquipmentTypeId() == equipmentType.getEquipmentTypeId()) {
                    equipmentTypeArrayList.set(i, equipmentType);
                }
            }
            return  true;
        }
        return false;
    }

    public ArrayList<EquipmentType> getEquipmentTypeArrayList() {
        return equipmentTypeArrayList;
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(equipmentTypeArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = equipmentTypeArrayList.get(0).getCreatedAt();
        for(int i = 1; i < equipmentTypeArrayList.size(); i++) {
            if(searchDate.before(equipmentTypeArrayList.get(i).getCreatedAt())) {
                searchDate = equipmentTypeArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(equipmentTypeArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = equipmentTypeArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < equipmentTypeArrayList.size(); i++) {
            if(searchDate.before(equipmentTypeArrayList.get(i).getUpdatedAt())) {
                searchDate = equipmentTypeArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }

    private ContentValues toContentValues(EquipmentType equipmentType) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EQUIPMENT_TYPE_ID, equipmentType.getEquipmentTypeId());
        contentValues.put(KEY_EQUIPMENT_TYPE_NAME, equipmentType.getEquipmentTypeName());
        contentValues.put(KEY_EQUIPMENT_IS_UPGRADABLE, equipmentType.getEquipmentIsUpgradable());
        contentValues.put(KEY_EQUIPMENT_IS_WEIGHT, equipmentType.getEquipmentIsWeight());
        contentValues.put(CREATED_AT, calendarService.dateToString(equipmentType.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(equipmentType.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(equipmentType.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        equipmentTypeArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                EquipmentType equipmentType = new EquipmentType();
                equipmentType.setEquipmentTypeId(cursor.getInt(cursor.getColumnIndex(KEY_EQUIPMENT_TYPE_ID)));
                equipmentType.setEquipmentTypeName(cursor.getString(cursor.getColumnIndex(KEY_EQUIPMENT_TYPE_NAME)));
                equipmentType.setEquipmentIsUpgradable(cursor.getInt(cursor.getColumnIndex(KEY_EQUIPMENT_IS_UPGRADABLE)));
                equipmentType.setEquipmentIsWeight(cursor.getInt(cursor.getColumnIndex(KEY_EQUIPMENT_IS_WEIGHT)));
                equipmentType.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                equipmentType.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                equipmentType.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                equipmentTypeArrayList.add(equipmentType);
            }
            cursor.moveToNext();
        }
    }

    public void clearEquipmentTypes() {
        for (int i = 0; i < equipmentTypeArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("equipmentTypeId", equipmentTypeArrayList.get(i).getEquipmentTypeId());
        }
        equipmentTypeArrayList.clear();
    }
}
