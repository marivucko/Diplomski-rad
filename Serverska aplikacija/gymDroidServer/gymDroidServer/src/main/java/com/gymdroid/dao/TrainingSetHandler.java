package com.gymdroid.dao;

import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.service.DataOperationService;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class TrainingSetHandler {
    public static TrainingSet insertTrainingSet(TrainingSet trainingSet){
        Date now = new Date();
        trainingSet.setCreatedAt(now);
        trainingSet.setUpdatedAt(now);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(trainingSet);
        session.getTransaction().commit();

        return trainingSet;
    }

    public static TrainingSet getTrainingSet(int trainingSetId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from TrainingSet where trainingSetId = '" +  trainingSetId + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (TrainingSet) query.list().get(0);
    }

    public static void deleteTrainingSet(int trainingSetId) {
        TrainingSet trainingSet = getTrainingSet(trainingSetId);
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(trainingSet);
        session.getTransaction().commit();
    }

    public static ArrayList<TrainingSet> getTrainingSetByTrainingWorkoutId(int trainingWorkoutId) {
        return executeQuery("from TrainingSet where trainingWorkoutId = '" + trainingWorkoutId + "'");
    }

    public static ArrayList<TrainingSet> getAllUsersTrainingSet(User user) {
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = TrainingWorkoutHandler.getAllUsersTrainingWorkout(user);
        ArrayList<TrainingSet> trainingSetArrayList = new ArrayList<>();
        for(int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            trainingSetArrayList.addAll(getTrainingSetByTrainingWorkoutId(trainingWorkoutArrayList.get(i).getTrainingWorkoutId()));
        }
        return trainingSetArrayList;
    }

    public static ArrayList<TrainingSet> generateMissingListForInsert(Date lastUpdateDate, User user) {
        ArrayList<TrainingSet> trainingSetArrayList = getAllUsersTrainingSet(user);
        ArrayList<TrainingSet> missingArrayList = new ArrayList<>();
        for(int i = 0; i < trainingSetArrayList.size(); i++) {
            TrainingSet trainingSet = trainingSetArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, trainingSet)) {
                missingArrayList.add(trainingSet);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<TrainingSet> generateMissingListForUpdate(Date lastUpdateDate, User user) {
        ArrayList<TrainingSet> trainingSetArrayList = getAllUsersTrainingSet(user);
        ArrayList<TrainingSet> missingArrayList = new ArrayList<>();
        for(int i = 0; i < trainingSetArrayList.size(); i++) {
            TrainingSet trainingSet = trainingSetArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, trainingSet)) {
                missingArrayList.add(trainingSet);
            }
        }
        return missingArrayList;
    }


    public static boolean needInsertOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<TrainingSet> trainingSetArrayList = getAllUsersTrainingSet(user);
        for(int i = 0; i < trainingSetArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, trainingSetArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate,User user) {
        ArrayList<TrainingSet> trainingSetArrayList = getAllUsersTrainingSet(user);
        for(int i = 0; i < trainingSetArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, trainingSetArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }


    private static ArrayList<TrainingSet> executeQuery(String selectQuery) {
        ArrayList<TrainingSet> trainingSetArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            trainingSetArrayList.add((TrainingSet) query.list().get(i));
        }
        return trainingSetArrayList;
    }
}
