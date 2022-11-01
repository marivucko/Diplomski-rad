package com.gymdroid.dao;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.service.DataOperationService;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UserWeightHandler {

    public static UserWeight insertUserWeight(UserWeight userWeight) {
        Date currentDate = new Date();
        userWeight.setCreatedAt(currentDate);
        userWeight.setUpdatedAt(currentDate);

        UserWeight alreadyExist = getUserWeightOnDate(userWeight);
        if(alreadyExist != null) {
            Session session = GymDroidHibernateUtil.getSession();
            session.beginTransaction();
            alreadyExist.setWeight(userWeight.getWeight());
            alreadyExist.setWeightNote(userWeight.getWeightNote());
            session.update(alreadyExist);
            session.getTransaction().commit();
            return alreadyExist;
        }
        else {
            Session session = GymDroidHibernateUtil.getSession();
            session.beginTransaction();
            session.save(userWeight);
            session.getTransaction().commit();
            return userWeight;
        }
    }

    public static UserWeight getUserWeight(int weightId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from UserWeight where weightId = " + weightId);
        if(query.list().size() == 0) {
            return null;
        }
        return (UserWeight) query.list().get(0);
    }

    public static void deleteUserWeight(int weightId) {
        UserWeight userWeight = getUserWeight(weightId);
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(userWeight);
        session.getTransaction().commit();
    }

    private static UserWeight getUserWeightOnDate(UserWeight userWeight) {
        int userId = userWeight.getUserId();
        ArrayList<UserWeight> userWeightArrayList = executeQuery("from UserWeight where userId = '" + userId + "'");
        for (UserWeight userWeight1 : userWeightArrayList) {
            if (isInTheSameDay(userWeight1.getWeightDate(), userWeight.getWeightDate())) {
                return userWeight1;
            }
        }
        return null;
    }

    private static boolean isInTheSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

    public static ArrayList<UserWeight> getAllUsersWeight(User user) {
        int userId = user.getUserId();
        return executeQuery("from UserWeight where userId = '" + userId + "'");
    }

    public static ArrayList<UserWeight> generateMissingListForInsert(Date lastUpdateDate, User user) {
        ArrayList<UserWeight> equipmentArrayList = getAllUsersWeight(user);
        ArrayList<UserWeight> missingArrayList = new ArrayList<>();
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            UserWeight currentUserWeight = equipmentArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, currentUserWeight)) {
                missingArrayList.add(currentUserWeight);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<UserWeight> generateMissingListForUpdate(Date lastUpdateDate, User user) {
        ArrayList<UserWeight> equipmentArrayList = getAllUsersWeight(user);
        ArrayList<UserWeight> missingArrayList = new ArrayList<>();
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            UserWeight currentUserWeight = equipmentArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, currentUserWeight)) {
                missingArrayList.add(currentUserWeight);
            }
        }
        return missingArrayList;
    }



    public static boolean needInsertOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<UserWeight> workoutArrayList = getAllUsersWeight(user);
        for(int i = 0; i < workoutArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, workoutArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate, User user) {
        ArrayList<UserWeight> workoutArrayList = getAllUsersWeight(user);
        for(int i = 0; i < workoutArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, workoutArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<UserWeight> executeQuery(String selectQuery) {
        ArrayList<UserWeight> userWeightArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            userWeightArrayList.add((UserWeight) query.list().get(i));
        }
        return userWeightArrayList;
    }

}
