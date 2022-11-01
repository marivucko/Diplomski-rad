package com.gymdroid.dao;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.service.DataOperationService;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class DoneWorkoutHandler {

    public static DoneWorkout insertDoneWorkout(DoneWorkout doneWorkout) {
        Date currentDate = new Date();
        doneWorkout.setCreatedAt(currentDate);
        doneWorkout.setUpdatedAt(currentDate);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(doneWorkout);
        session.getTransaction().commit();

        return doneWorkout;
    }

    public static DoneWorkout getDoneWorkout(int doneWorkoutId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from DoneWorkout where doneWorkoutId = '" + doneWorkoutId + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (DoneWorkout) query.list().get(0);
    }

    public static void deleteDoneWorkout(int doneWorkoutId) {
        DoneWorkout doneWorkout = getDoneWorkout(doneWorkoutId);
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(doneWorkout);
        session.getTransaction().commit();
    }

    public static ArrayList<DoneWorkout> getAllUsersDoneWorkouts(User user) {
        return executeQuery("from DoneWorkout where userId = '" + user.getUserId() + "'");
    }

    public static ArrayList<DoneWorkout> generateMissingListForInsert(Date lastUpdateDate, User user) {
        ArrayList<DoneWorkout> doneWorkoutArrayList = getAllUsersDoneWorkouts(user);
        ArrayList<DoneWorkout> missingArrayList = new ArrayList<>();
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            DoneWorkout doneWorkout = doneWorkoutArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, doneWorkout)) {
                missingArrayList.add(doneWorkout);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<DoneWorkout> generateMissingListForUpdate(Date lastUpdateDate, User user) {
        ArrayList<DoneWorkout> doneWorkoutArrayList = getAllUsersDoneWorkouts(user);
        ArrayList<DoneWorkout> missingArrayList = new ArrayList<>();
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            DoneWorkout doneWorkout = doneWorkoutArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, doneWorkout)) {
                missingArrayList.add(doneWorkout);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate,User user) {
        ArrayList<DoneWorkout> doneWorkoutArrayList = getAllUsersDoneWorkouts(user);
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, doneWorkoutArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate,User user) {
        ArrayList<DoneWorkout> doneWorkoutArrayList = getAllUsersDoneWorkouts(user);
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, doneWorkoutArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<DoneWorkout> executeQuery(String selectQuery) {
        ArrayList<DoneWorkout> doneWorkoutArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            doneWorkoutArrayList.add((DoneWorkout) query.list().get(i));
        }
        return doneWorkoutArrayList;
    }

    public static ArrayList<DoneWorkout> getDoneWorkout(Workout workout) {
        return executeQuery("from DoneWorkout where workoutId = '" + workout.getWorkoutId() + "'");
    }
}
