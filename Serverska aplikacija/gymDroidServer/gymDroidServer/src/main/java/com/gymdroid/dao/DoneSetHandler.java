package com.gymdroid.dao;

import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.service.DataOperationService;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class DoneSetHandler {

    public static DoneSet insertDoneSet(DoneSet doneSet) {
        Date currentDate = new Date();
        doneSet.setCreatedAt(currentDate);
        doneSet.setUpdatedAt(currentDate);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(doneSet);
        session.getTransaction().commit();

        return doneSet;
    }

    public static DoneSet getDoneSet(int doneSetId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from DoneSet where doneSetId = '" + doneSetId + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (DoneSet) query.list().get(0);
    }

    public static void deleteDoneSet(int doneSetId) {
        DoneSet doneSet = getDoneSet(doneSetId);
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(doneSet);
        session.getTransaction().commit();
    }

    public static ArrayList<DoneSet> getAllUsersDoneSets(User user) {
        ArrayList<DoneWorkout> doneWorkoutArrayList = DoneWorkoutHandler.getAllUsersDoneWorkouts(user);
        ArrayList<DoneSet> doneSetArrayList = new ArrayList<>();
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            doneSetArrayList.addAll(getAllDoneWorkoutsDoneSets(doneWorkoutArrayList.get(i)));
        }
        return doneSetArrayList;
    }

    public static ArrayList<DoneSet> getAllDoneWorkoutsDoneSets(DoneWorkout doneWorkout) {
        return executeQuery("from DoneSet where doneWorkoutId = '" + doneWorkout.getDoneWorkoutId() + "'");
    }

    public static ArrayList<DoneSet> generateMissingListForInsert(Date lastUpdateDate, User user) {
        ArrayList<DoneSet> doneSetArrayList = getAllUsersDoneSets(user);
        ArrayList<DoneSet> missingArrayList = new ArrayList<>();
        for(int i = 0; i < doneSetArrayList.size(); i++) {
            DoneSet doneSet = doneSetArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, doneSet)) {
                missingArrayList.add(doneSet);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<DoneSet> generateMissingListForUpdate(Date lastUpdateDate, User user) {
        ArrayList<DoneSet> doneSetArrayList = getAllUsersDoneSets(user);
        ArrayList<DoneSet> missingArrayList = new ArrayList<>();
        for(int i = 0; i < doneSetArrayList.size(); i++) {
            DoneSet doneSet = doneSetArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, doneSet)) {
                missingArrayList.add(doneSet);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<DoneSet> doneSetArrayList = getAllUsersDoneSets(user);
        for(int i = 0; i < doneSetArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, doneSetArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<DoneSet> doneSetArrayList = getAllUsersDoneSets(user);
        for(int i = 0; i < doneSetArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, doneSetArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<DoneSet> executeQuery(String selectQuery) {
        ArrayList<DoneSet> doneSetArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            doneSetArrayList.add((DoneSet) query.list().get(i));
        }
        return doneSetArrayList;
    }
}
