package com.gymdroid.dao;

import com.gymdroid.service.DataOperationService;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.User;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class EquipmentHandler {

    public static Equipment insertEquipment(Equipment equipment) {
        Date currentDate = new Date();
        equipment.setCreatedAt(currentDate);
        equipment.setUpdatedAt(currentDate);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(equipment);
        session.getTransaction().commit();

        return equipment;
    }

    public static Equipment updateEquipment(Equipment equipment) {
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.update(equipment);
        session.getTransaction().commit();

        return equipment;
    }

    public static Equipment getEquipment(int equipmentId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from Equipment where equipmentId = " + equipmentId);
        if(query.list().size() == 0) {
            return null;
        }
        return (Equipment) query.list().get(0);
    }

    public static void deleteEquipment(int equipmentId) {
        Equipment equipment = getEquipment(equipmentId);
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(equipment);
        session.getTransaction().commit();
    }

    public static ArrayList<Equipment> getAllUsersEquipment(User user) {
        int userId = user.getUserId();
        return executeQuery("from Equipment where userOwnerId = '" + userId + "'");
    }

    public static ArrayList<Equipment> generateMissingListForInsert(Date lastUpdateDate, User user) {
        ArrayList<Equipment> equipmentArrayList = getAllUsersEquipment(user);
        ArrayList<Equipment> missingArrayList = new ArrayList<>();
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            Equipment currentEquipment = equipmentArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, currentEquipment)) {
                missingArrayList.add(currentEquipment);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<Equipment> generateMissingListForUpdate(Date lastUpdateDate, User user) {
        ArrayList<Equipment> equipmentArrayList = getAllUsersEquipment(user);
        ArrayList<Equipment> missingArrayList = new ArrayList<>();
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            Equipment currentEquipment = equipmentArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, currentEquipment)) {
                missingArrayList.add(currentEquipment);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<Equipment> equipmentArrayList = getAllUsersEquipment(user);
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, equipmentArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<Equipment> equipmentArrayList = getAllUsersEquipment(user);
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, equipmentArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Equipment> executeQuery(String selectQuery) {
        ArrayList<Equipment> workoutArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            workoutArrayList.add((Equipment) query.list().get(i));
        }
        return workoutArrayList;
    }

}
