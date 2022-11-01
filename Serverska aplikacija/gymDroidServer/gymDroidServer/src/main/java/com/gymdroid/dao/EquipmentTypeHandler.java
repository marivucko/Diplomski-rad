package com.gymdroid.dao;

import com.gymdroid.service.DataOperationService;
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class EquipmentTypeHandler {

    public static ArrayList<EquipmentType> getAllEquipmentType() {
        return executeQuery("from EquipmentType");
    }

    public static void insertEquipmentType(String equipmentTypeName, int equipmentIsUpgradable, int equipmentIWeight) {

        EquipmentType equipmentType = new EquipmentType();
        equipmentType.setEquipmentTypeName(equipmentTypeName);
        equipmentType.setEquipmentIsUpgradable(equipmentIsUpgradable);
        equipmentType.setEquipmentIsWeight(equipmentIWeight);

        Date date = new Date();
        equipmentType.setCreatedAt(date);
        equipmentType.setUpdatedAt(date);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.save(equipmentType);
        session.getTransaction().commit();

    }

    public static ArrayList<EquipmentType> generateMissingListForInsert(Date lastUpdateDate) {
        ArrayList<EquipmentType> equipmentTypeArrayList = getAllEquipmentType();
        ArrayList<EquipmentType> missingArrayList = new ArrayList<>();
        for(int i = 0; i < equipmentTypeArrayList.size(); i++) {
            EquipmentType currentEquipmentType = equipmentTypeArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, currentEquipmentType)) {
                missingArrayList.add(currentEquipmentType);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<EquipmentType> generateMissingListForUpdate(Date lastUpdateDate) {
        ArrayList<EquipmentType> equipmentTypeArrayList = getAllEquipmentType();
        ArrayList<EquipmentType> missingArrayList = new ArrayList<>();
        for(int i = 0; i < equipmentTypeArrayList.size(); i++) {
            EquipmentType currentEquipmentType = equipmentTypeArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, currentEquipmentType)) {
                missingArrayList.add(currentEquipmentType);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate) {
        ArrayList<EquipmentType> equipmentTypeArrayList = getAllEquipmentType();
        for(int i = 0; i < equipmentTypeArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, equipmentTypeArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate) {
        ArrayList<EquipmentType> equipmentTypeArrayList = getAllEquipmentType();
        for(int i = 0; i < equipmentTypeArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, equipmentTypeArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<EquipmentType> executeQuery(String selectQuery) {
        ArrayList<EquipmentType> equipmentTypeArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            equipmentTypeArrayList.add((EquipmentType) query.list().get(i));
        }
        return equipmentTypeArrayList;
    }

}
