package com.gymdroid.dao;

import com.gymdroid.domain.beans.User;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.Date;

public class UserHandler {

    public static User insertUser(User user){
        user.setIsActive(1);

        Date now = new Date();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(user);
        session.getTransaction().commit();

        return getUser(user);
    }

    public static User updateUser(User user){
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.update(user);
        session.getTransaction().commit();

        return getUser(user);
    }

    public static User getEmailUser(String userEmail, String userPassword){
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from User where userEmail = '"+  userEmail + "' and userPassword = '" + userPassword + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (User) query.list().get(0);
    }

    public static User getGoogleUser(String userEmail, String firebaseId) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from User where userEmail = '" +  userEmail + "' and registrationType = 'GOOGLE' and firebaseId = '" + firebaseId + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (User) query.list().get(0);
    }

    public static boolean userGoogleExist(User user){
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where userEmail = '" +  user.getUserEmail() + "' and registrationType = 'GOOGLE'");
        if (query.list().iterator().hasNext()) {
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    public static boolean userEmailExist(User user){
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where userEmail = '" +  user.getUserEmail() + "' and registrationType = 'EMAIL'" );
        if (query.list().iterator().hasNext()) {
            session.getTransaction().commit();
            return true;
        }
        return false;
    }

    public static User getUser(User user) {
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery("from User where userEmail = '" +  user.getUserEmail() + "' and registrationType = '" + user.getRegistrationType() + "' and firebaseId = '" + user.getFirebaseId() + "'");
        if(query.list().size() == 0) {
            return null;
        }
        return (User) query.list().get(0);
    }

    public static int getUserId(User user) {
        User fetchedUser = getUser(user);
        if(fetchedUser == null) {
            return -1;
        }
        else {
            return fetchedUser.getUserId();
        }
    }

    public static boolean userGoogleExistButWithEmail(User user){
        return userEmailExist(user);
    }

    public static boolean userEmailExistButWithGoogle(User user){
        return userGoogleExist(user);
    }

}
