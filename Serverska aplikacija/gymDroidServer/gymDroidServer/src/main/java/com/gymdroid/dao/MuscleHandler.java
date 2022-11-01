package com.gymdroid.dao;

import com.gymdroid.service.DataOperationService;
import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class MuscleHandler {

    public static void insertMuscle(String muscleName, double muscleSize, int muscleGroupId) {
        Muscle muscle = new Muscle();
        muscle.setMuscleName(muscleName);
        muscle.setMuscleSize(muscleSize);
        muscle.setMuscleGroupId(muscleGroupId);
        Date date = new Date();
        muscle.setCreatedAt(date);
        muscle.setUpdatedAt(date);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.save(muscle);
        session.getTransaction().commit();
    }

    public static ArrayList<Muscle> getAllMuscles(){
        ArrayList<Muscle> muscleArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from Muscle");

        for(int i = 0; i < query.list().size(); i++){
            muscleArrayList.add((Muscle) query.list().get(i));
        }
        return muscleArrayList;
    }

    public static ArrayList<Muscle> generateMissingListForInsert(Date lastUpdateDate) {
        ArrayList<Muscle> muscleArrayList = getAllMuscles();
        ArrayList<Muscle> missingArrayList = new ArrayList<>();
        for(int i = 0; i < muscleArrayList.size(); i++) {
            Muscle muscle = muscleArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, muscle)) {
                missingArrayList.add(muscle);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<Muscle> generateMissingListForUpdate(Date lastUpdateDate) {
        ArrayList<Muscle> muscleArrayList = getAllMuscles();
        ArrayList<Muscle> missingArrayList = new ArrayList<>();
        for(int i = 0; i < muscleArrayList.size(); i++) {
            Muscle muscle = muscleArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, muscle)) {
                missingArrayList.add(muscle);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate) {
        ArrayList<Muscle> muscleArrayList = getAllMuscles();
        for(int i = 0; i < muscleArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, muscleArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate) {
        ArrayList<Muscle> muscleArrayList = getAllMuscles();
        for(int i = 0; i < muscleArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, muscleArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

}
