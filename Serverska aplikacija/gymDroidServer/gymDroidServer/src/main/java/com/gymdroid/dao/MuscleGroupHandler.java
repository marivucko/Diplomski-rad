package com.gymdroid.dao;

import com.gymdroid.service.DataOperationService;
import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class MuscleGroupHandler {

    public static void insertMuscleGroup(String muscleGroupName) {
        MuscleGroup muscleGroup = new MuscleGroup();
        Date now = new Date();
        muscleGroup.setCreatedAt(now);
        muscleGroup.setUpdatedAt(now);
        muscleGroup.setMuscleGroupName(muscleGroupName);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.save(muscleGroup);
        session.getTransaction().commit();
    }

    public static ArrayList<MuscleGroup> getAllMuscleGroups(){
        ArrayList<MuscleGroup> muscleArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from MuscleGroup");

        for(int i = 0; i < query.list().size(); i++){
            muscleArrayList.add((MuscleGroup) query.list().get(i));
        }
        return muscleArrayList;
    }

    public static ArrayList<MuscleGroup> generateMissingListForInsert(Date lastUpdateDate) {
        ArrayList<MuscleGroup> muscleGroupArrayList = getAllMuscleGroups();
        ArrayList<MuscleGroup> missingArrayList = new ArrayList<>();
        System.out.println(lastUpdateDate + " ");
        for(int i = 0; i < muscleGroupArrayList.size(); i++) {
            MuscleGroup muscleGroup = muscleGroupArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, muscleGroup)) {
                System.out.println(muscleGroup.getCreatedAt() + " " + "!!!!!! " + muscleGroup.getMuscleGroupName());
                missingArrayList.add(muscleGroup);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<MuscleGroup> generateMissingListForUpdate(Date lastUpdateDate) {
        ArrayList<MuscleGroup> muscleGroupArrayList = getAllMuscleGroups();
        ArrayList<MuscleGroup> missingArrayList = new ArrayList<>();
        for(int i = 0; i < muscleGroupArrayList.size(); i++) {
            MuscleGroup muscleGroup = muscleGroupArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, muscleGroup)) {
                missingArrayList.add(muscleGroup);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate) {
        ArrayList<MuscleGroup> muscleGroupArrayList = getAllMuscleGroups();
        for(int i = 0; i < muscleGroupArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, muscleGroupArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate) {
        ArrayList<MuscleGroup> muscleGroupArrayList = getAllMuscleGroups();
        for(int i = 0; i < muscleGroupArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, muscleGroupArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

}
