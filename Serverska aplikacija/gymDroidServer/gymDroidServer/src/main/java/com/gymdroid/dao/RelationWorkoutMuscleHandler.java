package com.gymdroid.dao;

import com.gymdroid.domain.beans.*;
import com.gymdroid.utils.GymDroidHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import com.gymdroid.service.DataOperationService;

import java.util.ArrayList;
import java.util.Date;

public class RelationWorkoutMuscleHandler {

    public static int insertRelation(RelationWorkoutMuscle relationWorkoutMuscle) {
        Date currentDate = new Date();
        relationWorkoutMuscle.setCreatedAt(currentDate);
        relationWorkoutMuscle.setUpdatedAt(currentDate);

        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();

        session.save(relationWorkoutMuscle);
        session.getTransaction().commit();

        return relationWorkoutMuscle.getRelationId();
    }

    public static int insertRelation(int workoutId, int muscleId, int targetPriority) {
        RelationWorkoutMuscle relation = new RelationWorkoutMuscle();
        relation.setWorkoutId(workoutId);
        relation.setMuscleId(muscleId);
        relation.setMuscleTargetPriority(targetPriority);
        return insertRelation(relation);
    }

    public static void deleteRelationWorkoutMuscle(RelationWorkoutMuscle relationWorkoutMuscle) {
        Session session = GymDroidHibernateUtil.getSession();
        session.beginTransaction();
        session.delete(relationWorkoutMuscle);
        session.getTransaction().commit();
    }

    public static ArrayList<RelationWorkoutMuscle> getAllWorkoutsRelations(Workout workout) {
        int workoutId = workout.getWorkoutId();
        return executeQuery("from RelationWorkoutMuscle where workoutId = '" + workoutId + "'");
    }

    public static ArrayList<RelationWorkoutMuscle> getAllWorkoutsRelations() {
        return executeQuery("from RelationWorkoutMuscle");
    }

    public static ArrayList<RelationWorkoutMuscle> generateMissingListForInsert(Date lastUpdateDate) {
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = getAllWorkoutsRelations();
        ArrayList<RelationWorkoutMuscle> missingArrayList = new ArrayList<>();
        for(int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            RelationWorkoutMuscle relationWorkoutMuscle = relationWorkoutMuscleArrayList.get(i);
            if(DataOperationService.isReadyForInsert(lastUpdateDate, relationWorkoutMuscle)) {
                missingArrayList.add(relationWorkoutMuscle);
            }
        }
        return missingArrayList;
    }

    public static ArrayList<RelationWorkoutMuscle> generateMissingListForUpdate(Date lastUpdateDate) {
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = getAllWorkoutsRelations();
        ArrayList<RelationWorkoutMuscle> missingArrayList = new ArrayList<>();
        for(int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            RelationWorkoutMuscle relationWorkoutMuscle = relationWorkoutMuscleArrayList.get(i);
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, relationWorkoutMuscle)) {
                missingArrayList.add(relationWorkoutMuscle);
            }
        }
        return missingArrayList;
    }

    public static boolean needInsertOnMobileClient(Date lastUpdateDate) {
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = getAllWorkoutsRelations();
        for(int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            if(DataOperationService.isReadyForInsert(lastUpdateDate, relationWorkoutMuscleArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean needUpdateOnMobileClient(Date lastUpdateDate) {
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = getAllWorkoutsRelations();
        for(int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            if(DataOperationService.isReadyForUpdate(lastUpdateDate, relationWorkoutMuscleArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    private static ArrayList<RelationWorkoutMuscle> executeQuery(String selectQuery) {
        ArrayList<RelationWorkoutMuscle> workoutArrayList = new ArrayList<>();
        Session session = GymDroidHibernateUtil.getSession();
        Query query = session.createQuery(selectQuery);
        for(int i = 0; i < query.list().size(); i++){
            workoutArrayList.add((RelationWorkoutMuscle) query.list().get(i));
        }
        return workoutArrayList;
    }

}
