package com.gymdroid.dao;

import com.gymdroid.domain.beans.*;
import com.gymdroid.service.DataOperationService;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class TrainingHandler {

    public static Training insertTraining(Training training){
        Date now = new Date();
        training.setCreatedAt(now);
        training.setUpdatedAt(now);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(training);
        session.getTransaction().commit();

        return training;
    }

    public static Training getTraining(int trainingId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from Training where trainingId = '" +  trainingId + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (Training) query.list().get(0);
    }


    public static Training getTraining(Training training) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from Training where trainingName = '" +  training.getTrainingName() + "' and trainingDescription = '" + training.getTrainingDescription() + "' and trainingIntensityLevel = '" + training.getTrainingIntensityLevel() + "' and durationOfPauseBetweenWorkouts = '" + training.getDurationOfPauseBetweenWorkouts() + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (Training) query.list().get(0);
    }

    public static void deleteTraining(int trainingId) {
        Training training = getTraining(trainingId);
        if (training != null) {
            Session session = GymDroidHibernateUtil.getSession();
            session.beginTransaction();
            session.delete(training);
            session.getTransaction().commit();
        }
    }

    public static ArrayList<Training> getAllUsersTraining(User user) {
        return executeQuery("from Training where userCreatorId = '" + user.getUserId() + "'");
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate,User user) {
        ArrayList<Training> trainingArrayList = getAllUsersTraining(user);
        for(int i = 0; i < trainingArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, trainingArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Training> generateMissingListForInsert(Date lastUpdateDate, User user) {
        ArrayList<Training> trainingArrayList = getAllUsersTraining(user);
        ArrayList<Training> missingArrayList = new ArrayList<>();
        for(int i = 0; i < trainingArrayList.size(); i++) {
            Training training = trainingArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, training)) {
                missingArrayList.add(training);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<Training> generateMissingListForUpdate(Date lastUpdateDate, User user) {
        ArrayList<Training> trainingArrayList = getAllUsersTraining(user);
        ArrayList<Training> missingArrayList = new ArrayList<>();
        for(int i = 0; i < trainingArrayList.size(); i++) {
            Training training = trainingArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, training)) {
                missingArrayList.add(training);
            }
        }
        return missingArrayList;
    }


    public static boolean needUpdateOnMobileClient(Date lastUpdateDate,User user) {
        ArrayList<Training> trainingArrayList = getAllUsersTraining(user);
        for(int i = 0; i < trainingArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, trainingArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Training> executeQuery(String selectQuery) {
        ArrayList<Training> trainingArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            trainingArrayList.add((Training) query.list().get(i));
        }
        return trainingArrayList;
    }
}
