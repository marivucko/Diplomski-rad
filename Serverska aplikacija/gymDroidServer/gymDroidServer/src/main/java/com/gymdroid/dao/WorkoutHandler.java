package com.gymdroid.dao;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.service.DataOperationService;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class WorkoutHandler {

    public static int insertWorkout(Workout workout) {
        Date currentDate = new Date();
        workout.setCreatedAt(currentDate);
        workout.setUpdatedAt(currentDate);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(workout);
        session.getTransaction().commit();

        return workout.getWorkoutId();
    }

    public static int insertWorkout(String workoutName, String workoutDescription, int statusIsApproved, int statusWaitApproval, int needTime, int needWeight, int userCreatorId) {
        Workout workout = new Workout();
        workout.setWorkoutName(workoutName);
        workout.setWorkoutDescription(workoutDescription);
        workout.setWorkoutStatusIsApproved(statusIsApproved);
        workout.setWorkoutStatusWaitApproval(statusWaitApproval);
        workout.setWorkoutNeedTime(needTime);
        workout.setWorkoutNeedWeight(needWeight);
        workout.setUserCreatorId(userCreatorId);
        return insertWorkout(workout);
    }

    public static void deleteWorkout(int workoutId) {
        Workout workout = getWorkout(workoutId);
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(workout);
        session.getTransaction().commit();
    }

    public static Workout getWorkout(int workoutId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from Workout where workoutId = " + workoutId);
        if(query.list().size() == 0) {
            return null;
        }
        return (Workout) query.list().get(0);
    }

    public static ArrayList<Workout> getAllWorkouts() {
        return executeQuery("from Workout");
    }

    public static ArrayList<Workout> getAllApprovedWorkouts() {
        return executeQuery("from Workout where workoutStatusIsApproved = '1'");
    }

    public static ArrayList<Workout> getAllUsersWorkouts(User user) {
        int userId = user.getUserId();
        return executeQuery("from Workout where userCreatorId = '" + userId + "'");
    }

    public static ArrayList<Workout> getAllUsersOrActiveWorkouts(User user) {
        int userId = user.getUserId();
        return executeQuery("from Workout where workoutStatusIsApproved = '1' or userCreatorId = '" + userId + "'");
    }

    public static ArrayList<Workout> generateMissingListForInsert(Date lastUpdateDate, User user) {
        ArrayList<Workout> workoutArrayList = getAllUsersOrActiveWorkouts(user);
        ArrayList<Workout> missingArrayList = new ArrayList<>();
        for(int i = 0; i < workoutArrayList.size(); i++) {
            Workout workout = workoutArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, workout)) {
                missingArrayList.add(workout);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<Workout> generateMissingListForUpdate(Date lastUpdateDate, User user) {
        ArrayList<Workout> workoutArrayList = getAllUsersOrActiveWorkouts(user);
        ArrayList<Workout> missingArrayList = new ArrayList<>();
        for(int i = 0; i < workoutArrayList.size(); i++) {
            Workout workout = workoutArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, workout)) {
                missingArrayList.add(workout);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<Workout> workoutArrayList = getAllUsersOrActiveWorkouts(user);
        for(int i = 0; i < workoutArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, workoutArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<Workout> workoutArrayList = getAllUsersOrActiveWorkouts(user);
        for(int i = 0; i < workoutArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, workoutArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Workout> executeQuery(String selectQuery) {
        ArrayList<Workout> workoutArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            workoutArrayList.add((Workout) query.list().get(i));
        }
        return workoutArrayList;
    }

}
