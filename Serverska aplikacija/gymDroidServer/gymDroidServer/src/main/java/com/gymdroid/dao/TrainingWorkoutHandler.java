package com.gymdroid.dao;

import com.gymdroid.domain.beans.*;
import com.gymdroid.service.DataOperationService;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class TrainingWorkoutHandler {

    public static TrainingWorkout insertTrainingWorkout(TrainingWorkout trainingWorkout){
        Date now = new Date();
        trainingWorkout.setCreatedAt(now);
        trainingWorkout.setUpdatedAt(now);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(trainingWorkout);
        session.getTransaction().commit();

        return trainingWorkout;
    }

    public static TrainingWorkout getTrainingWorkout(int trainingWorkoutId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from TrainingWorkout where trainingWorkoutId = '" +  trainingWorkoutId + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (TrainingWorkout) query.list().get(0);
    }

    public static void deleteTrainingWorkout(int trainingWorkoutId) {
        TrainingWorkout trainingWorkout = getTrainingWorkout(trainingWorkoutId);
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(trainingWorkout);
        session.getTransaction().commit();
    }

    public static ArrayList<TrainingWorkout> getTrainingWorkoutByTrainingId(int trainingId) {
        return executeQuery("from TrainingWorkout where trainingId = '" + trainingId + "'");
    }

    public static ArrayList<TrainingWorkout> getTrainingWorkoutByWorkoutId(int workoutId) {
        return executeQuery("from TrainingWorkout where workoutId = '" + workoutId + "'");
    }

    public static ArrayList<TrainingWorkout> getAllUsersTrainingWorkout(User user) {
        ArrayList<Training> trainingArrayList = TrainingHandler.getAllUsersTraining(user);
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = new ArrayList<>();
        for(int i = 0; i < trainingArrayList.size(); i++) {
            trainingWorkoutArrayList.addAll(getTrainingWorkoutByTrainingId(trainingArrayList.get(i).getTrainingId()));
        }
        return trainingWorkoutArrayList;
    }

    public static ArrayList<TrainingWorkout> generateMissingListForInsert(Date lastUpdateDate, User user) {
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = getAllUsersTrainingWorkout(user);
        ArrayList<TrainingWorkout> missingArrayList = new ArrayList<>();
        for(int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, trainingWorkout)) {
                missingArrayList.add(trainingWorkout);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<TrainingWorkout> generateMissingListForUpdate(Date lastUpdateDate, User user) {
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = getAllUsersTrainingWorkout(user);
        ArrayList<TrainingWorkout> missingArrayList = new ArrayList<>();
        for(int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, trainingWorkout)) {
                missingArrayList.add(trainingWorkout);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = getAllUsersTrainingWorkout(user);
        for(int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, trainingWorkoutArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate,User user) {
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = getAllUsersTrainingWorkout(user);
        for(int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, trainingWorkoutArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }


    private static ArrayList<TrainingWorkout> executeQuery(String selectQuery) {
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            trainingWorkoutArrayList.add((TrainingWorkout) query.list().get(i));
        }
        return trainingWorkoutArrayList;
    }
}
