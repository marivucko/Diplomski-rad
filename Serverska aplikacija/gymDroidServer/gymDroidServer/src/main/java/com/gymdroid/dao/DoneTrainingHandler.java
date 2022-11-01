package com.gymdroid.dao;

import com.gymdroid.domain.beans.*;
import com.gymdroid.service.DataOperationService;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class DoneTrainingHandler {

    public static DoneTraining insertDoneTraining(DoneTraining doneTraining){
        Date now = new Date();
        doneTraining.setCreatedAt(now);
        doneTraining.setUpdatedAt(now);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(doneTraining);
        session.getTransaction().commit();

        return doneTraining;
    }

    public static void deleteDoneTraining(int doneTrainingId) {
        DoneTraining doneTraining = getDoneTraining(doneTrainingId);
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(doneTraining);
        session.getTransaction().commit();
    }

    public static DoneTraining getDoneTraining(int doneTrainingId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from DoneTraining where doneTrainingId = '" +  doneTrainingId + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (DoneTraining) query.list().get(0);
    }

    public static ArrayList<DoneTraining> getDoneTrainings(int trainingId) {
        return executeQuery("from DoneTraining where trainingId = '" + trainingId + "'");
    }

    private static ArrayList<DoneTraining> executeQuery(String selectQuery) {
        ArrayList<DoneTraining> doneTrainingArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            doneTrainingArrayList.add((DoneTraining) query.list().get(i));
        }
        return doneTrainingArrayList;
    }

    public static ArrayList<DoneTraining> getAllUsersDoneTraining(User user) {
        ArrayList<Training> trainingArrayList = TrainingHandler.getAllUsersTraining(user);
        ArrayList<DoneTraining> doneTrainingArrayList = new ArrayList<>();
        for(int i = 0; i < trainingArrayList.size(); i++) {
            doneTrainingArrayList.addAll(getDoneTrainings(trainingArrayList.get(i).getTrainingId()));
        }
        return doneTrainingArrayList;
    }

    public static ArrayList<DoneTraining> generateMissingListForInsert(Date lastUpdatedAt, User user) {
        ArrayList<DoneTraining> doneTrainingArrayList = getAllUsersDoneTraining(user);
        ArrayList<DoneTraining> missingArrayList = new ArrayList<>();
        for(int i = 0; i < doneTrainingArrayList.size(); i++) {
            DoneTraining doneTraining = doneTrainingArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdatedAt, doneTraining)) {
                missingArrayList.add(doneTraining);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<DoneTraining> generateMissingListForUpdate(Date lastUpdatedAt, User user) {
        ArrayList<DoneTraining> doneTrainingArrayList = getAllUsersDoneTraining(user);
        ArrayList<DoneTraining> missingArrayList = new ArrayList<>();
        for(int i = 0; i < doneTrainingArrayList.size(); i++) {
            DoneTraining doneTraining = doneTrainingArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdatedAt, doneTraining)) {
                missingArrayList.add(doneTraining);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate,User user) {
        ArrayList<DoneTraining> doneTrainingArrayList = getAllUsersDoneTraining(user);
        for(int i = 0; i < doneTrainingArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, doneTrainingArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate,User user) {
        ArrayList<DoneTraining> doneTrainingArrayList = getAllUsersDoneTraining(user);
        for(int i = 0; i < doneTrainingArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, doneTrainingArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }
}
