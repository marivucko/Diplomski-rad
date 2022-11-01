package com.gymdroid.dao.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gymdroid.dao.core.Table;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.CalendarService;

import java.util.ArrayList;
import java.util.Date;

public class EquipmentTable extends Table {

    private ArrayList<Equipment> equipmentArrayList;
    private ArrayList<Equipment> upgradableEquipmentArrayList;
    private ArrayList<Equipment> dumbbellEquipmentArrayList;

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "equipment";
    private static final String KEY_EQUIPMENT_ID = "equipmentId";
    private static final String KEY_EQUIPMENT_TYPE_ID = "equipmentTypeId";
    private static final String KEY_EQUIPMENT_COUNT = "equipmentCount";
    private static final String KEY_EQUIPMENT_WEIGHT = "equipmentWeight";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
            + KEY_ID + " integer primary key autoincrement,"
            + KEY_EQUIPMENT_ID + " integer,"
            + KEY_EQUIPMENT_TYPE_ID + " integer,"
            + KEY_EQUIPMENT_COUNT + " integer,"
            + KEY_EQUIPMENT_WEIGHT + " double,"
            + CREATED_AT + " text,"
            + UPDATED_AT + " text,"
            + DELETED_AT + " text"
            + ");";

    public EquipmentTable(Context context) {
        super(context, CREATE_TABLE, TABLE_NAME, DATABASE_VERSION);
    }

    public void clearEquipments() {
        for (int i = 0; i < equipmentArrayList.size(); i++) {
            databaseHelper.deleteRowByRowId("equipmentId", equipmentArrayList.get(i).getEquipmentId());
        }
        equipmentArrayList.clear();
    }

    public void deleteEquipment(Equipment equipment) {
        databaseHelper.deleteRowByRowId(KEY_EQUIPMENT_ID, equipment.getEquipmentId());
        for (int i = 0; i < equipmentArrayList.size(); i++) {
            if (equipmentArrayList.get(i).getEquipmentId() == equipment.getEquipmentId()) {
                equipmentArrayList.remove(i);
                break;
            }
        }
    }

    public void deleteUpgradableEquipment(Equipment equipment) { ;
        for (int i = 0; i < upgradableEquipmentArrayList.size(); i++) {
            if (upgradableEquipmentArrayList.get(i).getEquipmentId() == equipment.getEquipmentId()) {
                upgradableEquipmentArrayList.remove(i);
                break;
            }
        }
    }

    public Boolean insertEquipment(Equipment equipment, ArrayList<EquipmentType> equipmentTypeArrayList) {
        int index = isExist(equipment);
        if(index == -1) {
            Boolean result = insert(equipment);
            if (result) {
                equipmentArrayList.add(equipment);
            }
            return result;
        }
        else {
            equipmentArrayList.get(index).setEquipmentCount(equipment.getEquipmentCount());
            return update(equipment);
        }
    }

    public ArrayList<Equipment> getEquipmentArrayList() {
        return equipmentArrayList;
    }

    public ArrayList<Equipment> getUpgradableEquipmentArrayList(ArrayList<EquipmentType> equipmentTypeArrayList) {
        insertToUpgradableEquipmentArrayList(equipmentTypeArrayList);
        return upgradableEquipmentArrayList;
    }

    public void insertToUpgradableEquipmentArrayList(ArrayList<EquipmentType> equipmentTypeArrayList) {
        upgradableEquipmentArrayList = new ArrayList<>();
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            int currentEquipmentTypeId = equipmentArrayList.get(i).getEquipmentTypeId();
            for(int j = 0; j < equipmentTypeArrayList.size(); j++) {
                if(currentEquipmentTypeId == equipmentTypeArrayList.get(j).getEquipmentTypeId()){
                    //if(equipmentTypeArrayList.get(j).getEquipmentIsUpgradable() == 1) {
                    if(equipmentTypeArrayList.get(j).getEquipmentIsWeight() == 1) {
                        upgradableEquipmentArrayList.add(equipmentArrayList.get(i));
                    }
                    break;
                }
            }
        }
    }

    public ArrayList<Equipment> getDumbbellEquipmentArrayList() {
        if(dumbbellEquipmentArrayList == null) {
            insertToDumbbellEquipmentArrayList();
        }
        return dumbbellEquipmentArrayList;
    }

    private void insertToDumbbellEquipmentArrayList() {
        dumbbellEquipmentArrayList = new ArrayList<>();
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            if (equipmentArrayList.get(i).getEquipmentTypeId() == EquipmentType.DUMBELL_TYPE_ID) {
                dumbbellEquipmentArrayList.add((equipmentArrayList.get(i)));
            }
        }
    }

    private int isExist(Equipment equipment) {
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            Equipment current = equipmentArrayList.get(i);
            if(current.getEquipmentId() == equipment.getEquipmentId() && current.getEquipmentWeight() == equipment.getEquipmentWeight()) {
                return i;
            }
        }
        return -1;
    }

    private Boolean insert(Equipment equipment) {
        ContentValues contentValues = toContentValues(equipment);
        return databaseHelper.insert(contentValues);
    }


    public boolean updateEquipment(Equipment equipment) {
        ContentValues contentValues = toContentValues(equipment);
        if  (databaseHelper.update(contentValues, KEY_EQUIPMENT_ID + "=" + equipment.getEquipmentId())) {
            for (int i = 0; i < equipmentArrayList.size(); i++) {
                if (equipmentArrayList.get(i).getEquipmentId() == equipment.getEquipmentId()) {
                    equipmentArrayList.set(i, equipment);
                }
            }
            return  true;
        }
        return false;
    }


    private Boolean update(Equipment equipment) {
        ContentValues contentValues = toContentValues(equipment);
        return databaseHelper.update(contentValues,KEY_EQUIPMENT_ID + "=" + equipment.getEquipmentId());
    }

    public Date getLastCreationDate(Date userRegisterDate) {
        if(equipmentArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = equipmentArrayList.get(0).getCreatedAt();
        for(int i = 1; i < equipmentArrayList.size(); i++) {
            if(searchDate.before(equipmentArrayList.get(i).getCreatedAt())) {
                searchDate = equipmentArrayList.get(i).getCreatedAt();
            }
        }
        return searchDate;
    }

    public Date getLastUpdateDate(Date userRegisterDate) {
        if(equipmentArrayList.size() == 0 ) {
            return userRegisterDate;
        }
        Date searchDate = equipmentArrayList.get(0).getUpdatedAt();
        for(int i = 1; i < equipmentArrayList.size(); i++) {
            if(searchDate.before(equipmentArrayList.get(i).getUpdatedAt())) {
                searchDate = equipmentArrayList.get(i).getUpdatedAt();
            }
        }
        return searchDate;
    }


    private ContentValues toContentValues(Equipment equipment) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_EQUIPMENT_ID, equipment.getEquipmentId());
        contentValues.put(KEY_EQUIPMENT_TYPE_ID, equipment.getEquipmentTypeId());
        contentValues.put(KEY_EQUIPMENT_COUNT, equipment.getEquipmentCount());
        contentValues.put(KEY_EQUIPMENT_WEIGHT, equipment.getEquipmentWeight());
        contentValues.put(CREATED_AT, calendarService.dateToString(equipment.getCreatedAt()));
        contentValues.put(UPDATED_AT, calendarService.dateToString(equipment.getUpdatedAt()));
        contentValues.put(DELETED_AT, calendarService.dateOrNullToString(equipment.getDeletedAt()));
        return contentValues;
    }

    @Override
    protected void initiateArrayList(Cursor cursor) {
        CalendarService calendarService = AllServices.getInstance(activity).getCalendarService();
        equipmentArrayList = new ArrayList<>();
       // upgradableEquipmentArrayList = new ArrayList<>();
       // dumbbellEquipmentArrayList = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(isNotDeleted(cursor)) {
                Equipment equipment = new Equipment();
                equipment.setEquipmentId(cursor.getInt(cursor.getColumnIndex(KEY_EQUIPMENT_ID)));
                equipment.setEquipmentTypeId(cursor.getInt(cursor.getColumnIndex(KEY_EQUIPMENT_TYPE_ID)));
                equipment.setEquipmentCount(cursor.getInt(cursor.getColumnIndex(KEY_EQUIPMENT_COUNT)));
                equipment.setEquipmentWeight(cursor.getInt(cursor.getColumnIndex(KEY_EQUIPMENT_WEIGHT)));
                equipment.setCreatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(CREATED_AT))));
                equipment.setUpdatedAt(calendarService.stringToDate(cursor.getString(cursor.getColumnIndex(UPDATED_AT))));
                equipment.setDeletedAt(calendarService.stringToDateOrNull(cursor.getString(cursor.getColumnIndex(DELETED_AT))));
                equipmentArrayList.add(equipment);
            }
            cursor.moveToNext();
        }
    }

}
