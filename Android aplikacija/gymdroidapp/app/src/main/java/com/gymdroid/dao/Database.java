package com.gymdroid.dao;

import android.app.Activity;

import com.gymdroid.dao.table.DoneSetTable;
import com.gymdroid.dao.table.DoneTrainingTable;
import com.gymdroid.dao.table.DoneWorkoutTable;
import com.gymdroid.dao.table.EquipmentTable;
import com.gymdroid.dao.table.EquipmentTypeTable;
import com.gymdroid.dao.table.MuscleGroupTable;
import com.gymdroid.dao.table.MuscleTable;
import com.gymdroid.dao.table.RelationWorkoutMuscleTable;
import com.gymdroid.dao.table.TrainingSetTable;
import com.gymdroid.dao.table.TrainingTable;
import com.gymdroid.dao.table.TrainingWorkoutTable;
import com.gymdroid.dao.table.UserWeightTable;
import com.gymdroid.dao.table.WorkoutTable;
import com.gymdroid.dao.virtual.Practices;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.TrainingMessage;
import com.gymdroid.domain.virtual.Practice;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Database extends Thread {

    private static final int SLEEP_TIME = 100;

    /// table
    private DoneSetTable doneSetTable;
    private DoneTrainingTable doneTrainingTable;
    private DoneWorkoutTable doneWorkoutTable;
    private EquipmentTable equipmentTable;
    private EquipmentTypeTable equipmentTypeTable;
    private MuscleGroupTable muscleGroupTable;
    private MuscleTable muscleTable;
    private RelationWorkoutMuscleTable relationWorkoutMuscleTable;
    private UserWeightTable userWeightTable;
    private WorkoutTable workoutTable;
    private Activity activity;

    /// trainingTable
    private TrainingTable trainingTable;
    private TrainingSetTable trainingSetTable;
    private TrainingWorkoutTable trainingWorkoutTable;

    /// virtual
    private Practices practices;

    private boolean isFinishedLoading;

    public Database(Activity activity) {
        this.activity = activity;
        this.isFinishedLoading = false;
        this.start();
    }

    @Override
    public void run() {
        doneSetTable = new DoneSetTable(activity); sleep();
        doneTrainingTable = new DoneTrainingTable(activity); sleep();
        doneWorkoutTable = new DoneWorkoutTable(activity); sleep();
        equipmentTable = new EquipmentTable(activity); sleep();
        equipmentTypeTable = new EquipmentTypeTable(activity); sleep();
        muscleGroupTable = new MuscleGroupTable(activity); sleep();
        muscleTable = new MuscleTable(activity); sleep();
        relationWorkoutMuscleTable = new RelationWorkoutMuscleTable(activity); sleep();
        userWeightTable = new UserWeightTable(activity); sleep();
        workoutTable = new WorkoutTable(activity); sleep();
        practices = new Practices(getDoneWorkoutArrayList(),getDoneSetArrayList()); sleep();
        trainingTable = new TrainingTable(activity); sleep();
        trainingSetTable = new TrainingSetTable(activity); sleep();
        trainingWorkoutTable = new TrainingWorkoutTable(activity); sleep();
        isFinishedLoading = true;
    }

    public boolean isFinishedLoading() {
        return isFinishedLoading;
    }

    private void sleep() {
        try {
            sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(Equipment equipment) {
        return equipmentTable.insertEquipment(equipment, getEquipmentTypeArrayList());
    }

    public boolean insert(Workout workout) {
        return workoutTable.insertWorkout(workout);
    }

    public boolean insert(UserWeight userWeight) {
        return userWeightTable.insertUserWeight(userWeight);
    }

    public boolean insert(Training training){
        return trainingTable.insertTraining(training);
    }

    public void delete(UserWeight userWeight) {
        userWeightTable.deleteUserWeight(userWeight);
    }

    public void delete(Equipment equipment) {
        equipmentTable.deleteEquipment(equipment);
    }

    public void deleteUpgradableEquipment(Equipment equipment) {
        equipmentTable.deleteUpgradableEquipment(equipment);
    }

    public boolean insertPractice(DoneWorkout doneWorkout, ArrayList<DoneSet> doneSetArrayList) {
        boolean resultDoneWorkout = this.doneWorkoutTable.insertDoneWorkout(doneWorkout);
        for(DoneSet doneSet: doneSetArrayList) {
            if (!this.doneSetTable.insertDoneSet(doneSet)) {
                return false;
            }
        }
        practices.addPractice(doneWorkout,doneSetArrayList);
        return resultDoneWorkout;
    }

    public boolean insertMultiplePractices(ArrayList<DoneWorkout> doneWorkoutArrayList, HashMap<Integer,ArrayList<DoneSet>> doneSetHashMap) {
        boolean result = true;
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            if(!insertPractice(doneWorkoutArrayList.get(i), doneSetHashMap.get(i))) {
                result = false;
            }
        }
        return result;
    }

    public boolean insertEquipment(ArrayList<Equipment> equipmentArrayList) {
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            boolean result = equipmentTable.insertEquipment(equipmentArrayList.get(i), getEquipmentTypeArrayList());
            if(!result) return false;
        }
        return true;
    }

    public boolean insertEquipmentTypes(ArrayList<EquipmentType> equipmentTypes) {
        for(int i = 0; i < equipmentTypes.size(); i++) {
            boolean result = equipmentTypeTable.insertEquipmentType(equipmentTypes.get(i));
            if(!result) return false;
        }
        return true;
    }

    public boolean insertMuscleGroups(ArrayList<MuscleGroup> muscles) {
        for(int i = 0; i < muscles.size(); i++) {
            boolean result = muscleGroupTable.insertMuscleGroup(muscles.get(i));
            System.out.println( "VAZNO " + muscles.get(i).getCreatedAt() );
            if(!result) return false;
        }
        return true;
    }

    public boolean insertMuscles(ArrayList<Muscle> muscles) {
        for(int i = 0; i < muscles.size(); i++) {
            boolean result = muscleTable.insertMuscle(muscles.get(i));
            if(!result) return false;
        }
        return true;
    }

    public boolean insertRelationWorkoutMuscles(ArrayList<RelationWorkoutMuscle> relationWorkoutMuscles) {
        for(int i = 0; i < relationWorkoutMuscles.size(); i++) {
            boolean result = relationWorkoutMuscleTable.insertRelationWorkoutMuscle(relationWorkoutMuscles.get(i));
            if(!result) return false;
        }
        return true;
    }

    public boolean enterInsertTraining(ArrayList<Training> trainingArrayList) {
        for (int i = 0; i < trainingArrayList.size(); i++) {
            boolean result = trainingTable.insertTraining(trainingArrayList.get(i));
            if (!result) return false;
        }
        return true;
    }

    public boolean insertTrainingSet(ArrayList<TrainingSet> trainingSetArrayList) {
        for (int i = 0; i < trainingSetArrayList.size(); i++) {
            boolean result = trainingSetTable.insertTrainingSet(trainingSetArrayList.get(i));
            if (!result) return false;
        }
        return true;
    }

    public boolean insertTrainingWorkout(ArrayList<TrainingWorkout> trainingWorkoutArrayList) {
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            boolean result = trainingWorkoutTable.insertTrainingWorkout(trainingWorkoutArrayList.get(i));
            if (!result) return false;
        }
        return true;
    }

    public boolean insertUserWeight(ArrayList<UserWeight> userWeightArrayList) {
        for(int i = 0; i < userWeightArrayList.size(); i++) {
            boolean result = userWeightTable.insertUserWeight(userWeightArrayList.get(i));
            if(!result) return false;
        }
        return true;
    }

    public boolean insertWorkouts(ArrayList<Workout> workouts) {
        for(int i = 0; i < workouts.size(); i++) {
            boolean result = workoutTable.insertWorkout(workouts.get(i));
            if(!result) return false;
        }
        return true;
    }

    public boolean insertDoneSet(ArrayList<DoneSet> doneSetArrayList) {
        for(int i = 0; i < doneSetArrayList.size(); i++) {
            boolean result = doneSetTable.insertDoneSet(doneSetArrayList.get(i));
            if(!result) return false;
        }
        return true;
    }

    public boolean insertDoneWorkout(ArrayList<DoneWorkout> doneWorkoutArrayList) {
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            boolean result = doneWorkoutTable.insertDoneWorkout(doneWorkoutArrayList.get(i));
            if(!result) return false;
        }
        return true;
    }

    public void updateDoneSet(ArrayList<DoneSet> doneSetArrayList) {
        for (int i = 0; i < doneSetArrayList.size(); i++) {
            doneSetTable.updateDoneSet(doneSetArrayList.get(i));
        }
    }

    public void updateDoneTraining(ArrayList<DoneTraining> doneTrainingArrayList) {
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            doneTrainingTable.updateDoneTraining(doneTrainingArrayList.get(i));
        }
    }

    public void updateDoneWorkout(ArrayList<DoneWorkout> doneWorkoutArrayList) {
        for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
            doneWorkoutTable.updateDoneWorkout(doneWorkoutArrayList.get(i));
        }
    }

    public void updateEquipment(ArrayList<Equipment> equipmentArrayList) {
        for (int i = 0; i < equipmentArrayList.size(); i++) {
            equipmentTable.updateEquipment(equipmentArrayList.get(i));
        }
    }

    public void updateEquipmentType(ArrayList<EquipmentType> equipmentTypeArrayList) {
        for (int i = 0; i < equipmentTypeArrayList.size(); i++) {
            equipmentTypeTable.updateEquipmentType(equipmentTypeArrayList.get(i));
        }
    }

    public void updateMuscleGroup(ArrayList<MuscleGroup> muscleGroupArrayList) {
        for (int i = 0; i < muscleGroupArrayList.size(); i++) {
            muscleGroupTable.updateMuscleGroup(muscleGroupArrayList.get(i));
        }
    }

    public void updateMuscle(ArrayList<Muscle> muscleArrayList) {
        for (int i = 0; i < muscleArrayList.size(); i++) {
            muscleTable.updateMuscle(muscleArrayList.get(i));
        }
    }

    public void updateRelationWorkoutMuscle(ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList) {
        for (int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            relationWorkoutMuscleTable.updateRelationWorkoutMuscle(relationWorkoutMuscleArrayList.get(i));
        }
    }

    public void updateTrainingSet(ArrayList<TrainingSet> trainingSetArrayList) {
        for (int i = 0; i < trainingSetArrayList.size(); i++) {
            trainingSetTable.updateTrainingSet(trainingSetArrayList.get(i));
        }
    }

    public void updateTraining(ArrayList<Training> trainingArrayList) {
        for (int i = 0; i < trainingArrayList.size(); i++) {
            trainingTable.updateTraining(trainingArrayList.get(i));
        }
    }

    public void updateTrainingWorkout(ArrayList<TrainingWorkout> trainingWorkoutArrayList) {
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            trainingWorkoutTable.updateTrainingWorkout(trainingWorkoutArrayList.get(i));
        }
    }

    public void updateUserWeight(ArrayList<UserWeight> userWeightArrayList) {
        for (int i = 0; i < userWeightArrayList.size(); i++) {
            userWeightTable.updateUserWeight(userWeightArrayList.get(i));
        }
    }

    public void updateWorkout(ArrayList<Workout> workoutArrayList) {
        for (int i = 0; i < workoutArrayList.size(); i++) {
            workoutTable.updateWorkout(workoutArrayList.get(i));
        }
    }

    public ArrayList<Equipment> getEquipmentArrayList() {
        return equipmentTable.getEquipmentArrayList();
    }

    public ArrayList<Equipment> getUpgradableEquipmentArrayList() {
        return equipmentTable.getUpgradableEquipmentArrayList(getEquipmentTypeArrayList());
    }

    public ArrayList<Equipment> getDumbbellEquipment() {
        return equipmentTable.getDumbbellEquipmentArrayList();
    }

    public ArrayList<EquipmentType> getEquipmentTypeArrayList() {
        return equipmentTypeTable.getEquipmentTypeArrayList();
    }

    public EquipmentType getEquipmentType(Equipment equipment) {
        ArrayList<EquipmentType> equipmentTypeArrayList = getEquipmentTypeArrayList();
        for(int i = 0; i < equipmentTypeArrayList.size(); i++) {
            if(equipment.getEquipmentTypeId() == equipmentTypeArrayList.get(i).getEquipmentTypeId()) {
                return equipmentTypeArrayList.get(i);
            }
        }
        return null;
    }

    public ArrayList<MuscleGroup> getMuscleGroupArrayList() {
        return muscleGroupTable.getMuscleGroupArrayList();
    }

    public ArrayList<Muscle> getMuscleArrayList() {
        return muscleTable.getMuscleArrayList();
    }

    public ArrayList<RelationWorkoutMuscle> getRelationWorkoutMuscleArrayList() {
        return relationWorkoutMuscleTable.getRelationWorkoutMuscleArrayList();
    }

    public ArrayList<UserWeight> getUserWeightArrayList() {
        return userWeightTable.getUserWeightArrayList();
    }

    public ArrayList<Workout> getWorkoutArrayList() {
        return workoutTable.getWorkoutArrayList();
    }

    public ArrayList<Workout> getWorkoutArrayList(int muscleGroupId) {
        ArrayList<Workout> workoutArrayList = new ArrayList<>();
        for (int i = 0; i < workoutTable.getWorkoutArrayList().size(); i++) {
            int workoutId = workoutTable.getWorkoutArrayList().get(i).getWorkoutId();
            for (int j = 0; j < relationWorkoutMuscleTable.getRelationWorkoutMuscleArrayList().size(); j++) {
                RelationWorkoutMuscle relation = relationWorkoutMuscleTable.getRelationWorkoutMuscleArrayList().get(j);
                if(workoutId == relation.getWorkoutId()) {
                    int muscleId = relation.getMuscleId();
                    for(int k = 0; k < muscleTable.getMuscleArrayList().size(); k++) {
                        Muscle muscle = muscleTable.getMuscleArrayList().get(k);
                        if(muscleId == muscle.getMuscleId() && muscleGroupId == muscle.getMuscleGroupId()) {
                            workoutArrayList.add(workoutTable.getWorkoutArrayList().get(i));
                            break;
                        }
                    }
                }
            }
        }
        return workoutArrayList;
    }

    public ArrayList<Workout> getUniqueWorkoutArrayList(int muscleGroupId) {
        ArrayList<Workout> workoutArrayList = new ArrayList<>();
        for (int i = 0; i < workoutTable.getWorkoutArrayList().size(); i++) {
            int workoutId = workoutTable.getWorkoutArrayList().get(i).getWorkoutId();
            for (int j = 0; j < relationWorkoutMuscleTable.getRelationWorkoutMuscleArrayList().size(); j++) {
                RelationWorkoutMuscle relation = relationWorkoutMuscleTable.getRelationWorkoutMuscleArrayList().get(j);
                if(workoutId == relation.getWorkoutId()) {
                    int muscleId = relation.getMuscleId();
                    for(int k = 0; k < muscleTable.getMuscleArrayList().size(); k++) {
                        Muscle muscle = muscleTable.getMuscleArrayList().get(k);
                        if(muscleId == muscle.getMuscleId() && muscleGroupId == muscle.getMuscleGroupId() && !workoutInWorkoutArrayList(workoutArrayList, workoutTable.getWorkoutArrayList().get(i))) {
                            workoutArrayList.add(workoutTable.getWorkoutArrayList().get(i));
                            break;
                        }
                    }
                }
            }
        }
        return workoutArrayList;
    }

    public boolean workoutInWorkoutArrayList(ArrayList<Workout> workouts, Workout workout) {
        return workoutTable.workoutInWorkoutArrayList(workouts, workout);
    }

    public ArrayList<DoneWorkout> getDoneWorkoutArrayList() {
        return doneWorkoutTable.getDoneWorkoutArrayList();
    }

    public ArrayList<DoneWorkout> getDoneWorkoutArrayList(int idWorkout) {
        ArrayList <DoneWorkout> doneWorkoutArrayList = new ArrayList();
        for(int i = 0; i < doneWorkoutTable.getDoneWorkoutArrayList().size(); i++) {
            if(idWorkout == doneWorkoutTable.getDoneWorkoutArrayList().get(i).getWorkoutId()) {
                doneWorkoutArrayList.add(doneWorkoutTable.getDoneWorkoutArrayList().get(i));
            }
        }
        return doneWorkoutArrayList;
    }

    public ArrayList<DoneSet> getDoneSetArrayList() {
        return doneSetTable.getDoneSetArrayList();
    }

    public ArrayList<DoneSet> getDoneSetArrayList(int doneWorkoutId) {
        ArrayList<DoneSet> doneSetArrayList = new ArrayList<>();
        for(int i = 0; i < doneSetTable.getDoneSetArrayList().size(); i++) {
            if(doneWorkoutId == doneSetTable.getDoneSetArrayList().get(i).getDoneWorkoutId()) {
                doneSetArrayList.add(doneSetTable.getDoneSetArrayList().get(i));
            }
        }
        return doneSetArrayList;
    }

    public ArrayList<Practice> getPracticeArrayList() {
        return practices.getPracticeArrayList();
    }

    public Date getDoneSetLastUpdateDate(Date userRegisterDate) {
        return doneSetTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getDoneTrainingLastUpdateDate(Date userRegisterDate) {
        return doneTrainingTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getDoneWorkoutLastUpdateDate(Date userRegisterDate) {
        return doneWorkoutTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getEquipmentLastUpdateDate(Date userRegisterDate) {
        return equipmentTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getEquipmentTypeLastUpdateDate(Date userRegisterDate) {
        return equipmentTypeTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getMuscleLastUpdateDate(Date userRegisterDate) {
        return muscleTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getMuscleGroupLastUpdateDate(Date userRegisterDate) {
        return muscleGroupTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getRelationWorkoutMuscleLastUpdateDate(Date userRegisterDate) {
        return relationWorkoutMuscleTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getTrainingLastUpdateDate(Date userRegisterDate) {
        return trainingTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getTrainingSetLastUpdateDate(Date userRegisterDate) {
        return trainingSetTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getTrainingWorkoutLastUpdateDate(Date userRegisterDate) {
        return trainingWorkoutTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getUserWeightLastUpdateDate(Date userRegisterDate) {
        return userWeightTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getWorkoutLastUpdateDate(Date userRegisterDate) {
        return workoutTable.getLastUpdateDate(userRegisterDate);
    }

    public Date getDoneSetLastCreationDate(Date userRegisterDate) {
        return doneSetTable.getLastCreationDate(userRegisterDate);
    }

    public Date getDoneTrainingLastCreationDate(Date userRegisterDate) {
        return doneTrainingTable.getLastCreationDate(userRegisterDate);
    }

    public Date getDoneWorkoutLastCreationDate(Date userRegisterDate) {
        return doneWorkoutTable.getLastCreationDate(userRegisterDate);
    }

    public Date getEquipmentLastCreationDate(Date userRegisterDate) {
        return equipmentTable.getLastCreationDate(userRegisterDate);
    }

    public Date getEquipmentTypeLastCreationDate(Date userRegisterDate) {
        return equipmentTypeTable.getLastCreationDate(userRegisterDate);
    }

    public Date getMuscleLastCreationDate(Date userRegisterDate) {
        return muscleTable.getLastCreationDate(userRegisterDate);
    }

    public Date getMuscleGroupLastCreationDate(Date userRegisterDate) {
        return muscleGroupTable.getLastCreationDate(userRegisterDate);
    }

    public Date getRelationWorkoutMuscleLastCreationDate(Date userRegisterDate) {
        return relationWorkoutMuscleTable.getLastCreationDate(userRegisterDate);
    }

    public Date getTrainingLastCreationDate(Date userRegisterDate) {
        return trainingTable.getLastCreationDate(userRegisterDate);
    }

    public Date getTrainingSetLastCreationDate(Date userRegisterDate) {
        return trainingSetTable.getLastCreationDate(userRegisterDate);
    }

    public Date getTrainingWorkoutLastCreationDate(Date userRegisterDate) {
        return trainingWorkoutTable.getLastCreationDate(userRegisterDate);
    }

    public Date getUserWeightLastCreationDate(Date userRegisterDate) {
        return userWeightTable.getLastCreationDate(userRegisterDate);
    }

    public Date getWorkoutLastCreationDate(Date userRegisterDate) {
        return workoutTable.getLastCreationDate(userRegisterDate);
    }

    public int countDoneWorkouts(int idWorkout) {
        int count = 0;
        for(int i = 0; i < doneWorkoutTable.getDoneWorkoutArrayList().size(); i++) {
            if(idWorkout == doneWorkoutTable.getDoneWorkoutArrayList().get(i).getWorkoutId()) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<DoneSet> countNumberOfSetsInLastDoneWorkout(int workoutId) {
        ArrayList<DoneSet> doneSetArrayList = new ArrayList<>();
        for(int i = 0; i < doneWorkoutTable.getDoneWorkoutArrayList().size(); i++) {
            if(workoutId == doneWorkoutTable.getDoneWorkoutArrayList().get(i).getWorkoutId()) {
                int doneWorkoutId = doneWorkoutTable.getDoneWorkoutArrayList().get(i).getDoneWorkoutId();
                for(int j = 0; j < doneSetTable.getDoneSetArrayList().size(); j++) {
                    if(doneWorkoutId == doneSetTable.getDoneSetArrayList().get(j).getDoneWorkoutId()) {
                        doneSetArrayList.add(doneSetTable.getDoneSetArrayList().get(j));
                    }
                }
                return doneSetArrayList;
            }
        }
        return doneSetArrayList;
    }

    public Workout getWorkout(int workoutId) {
        for(int i = 0; i < workoutTable.getWorkoutArrayList().size(); i++) {
            if(workoutId == workoutTable.getWorkoutArrayList().get(i).getWorkoutId()) {
                return workoutTable.getWorkoutArrayList().get(i);
            }
        }
        return null;
    }

    public int getNumberOfWorkouts(int muscleGroupId) {
        int count = 0;
        for (int i = 0; i < workoutTable.getWorkoutArrayList().size(); i++) {
            int workoutId = workoutTable.getWorkoutArrayList().get(i).getWorkoutId();
            for (int j = 0; j < relationWorkoutMuscleTable.getRelationWorkoutMuscleArrayList().size(); j++) {
                RelationWorkoutMuscle relation = relationWorkoutMuscleTable.getRelationWorkoutMuscleArrayList().get(j);
                if(workoutId == relation.getWorkoutId()) {
                    int muscleId = relation.getMuscleId();
                    for(int k = 0; k < muscleTable.getMuscleArrayList().size(); k++) {
                        Muscle muscle = muscleTable.getMuscleArrayList().get(k);
                        if(muscleId == muscle.getMuscleId() && muscleGroupId == muscle.getMuscleGroupId()) {
                            count++;
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }

    ///////////////////////////////////////
    /////////// T R A I N I N G //////////
    /////////////////////////////////////

    public boolean insertNewTraining(Training training, ArrayList<TrainingWorkout> trainingWorkoutArrayList, HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap) {

        if(!trainingTable.insertTraining(training)) return false;

        for(int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(i);
            trainingWorkout.setTrainingId(training.getTrainingId());
            if(!trainingWorkoutTable.insertTrainingWorkout(trainingWorkout)) {
                return false;
            }
        }

        for(int i = 0; i < trainingSetHashMap.size(); i++) {
            ArrayList<TrainingSet> trainingSetArrayList = trainingSetHashMap.get(i);
            for(int j = 0; j < trainingSetArrayList.size(); j++) {
                TrainingSet trainingSet = trainingSetArrayList.get(j);
                trainingSet.setTrainingWorkoutId(trainingWorkoutArrayList.get(i).getTrainingWorkoutId());
                if(!trainingSetTable.insertTrainingSet(trainingSet)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void deleteTraining(int trainingId) {
        trainingTable.deleteTraining(trainingId);
        ArrayList<DoneTraining> doneTrainingArrayList = doneTrainingTable.getDoneTrainingByTrainingId(trainingId);
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            doneTrainingTable.deleteDoneTraining(doneTrainingArrayList.get(i).getDoneTrainingId());
        }
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = trainingWorkoutTable.getTrainingWorkoutArrayList(trainingId);
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            deleteTrainingWorkout(trainingWorkoutArrayList.get(i).getTrainingWorkoutId());
        }
    }

    public void deleteTrainingWorkout(int trainingWorkoutId) {
        ArrayList<TrainingSet> trainingSetArrayList = trainingSetTable.getTrainingSetArrayList(trainingWorkoutId);
        printTrainingSetArrayList(trainingSetArrayList);
        System.out.println("OVDE trainingWorkoutId "  + trainingWorkoutId);
        System.out.println("OVDE SIZE " + trainingSetArrayList.size());
;        for (int i = 0; i < trainingSetArrayList.size(); i++) {
            deleteTrainingSet(trainingSetArrayList.get(i).getTrainingSetId());
            System.out.println("OVDE SIZE " + trainingSetArrayList.get(i).getTrainingSetId());
        }
        trainingWorkoutTable.deleteTrainingWorkout(trainingWorkoutId);
    }


    public void deleteTrainingWorkoutTrainingSet(Workout workout) {
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = trainingWorkoutTable.getTrainingWorkoutArrayList(workout);
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            deleteTrainingWorkout(trainingWorkoutArrayList.get(i).getTrainingWorkoutId());
            Training training = trainingTable.getTrainingArrayList(trainingWorkoutArrayList.get(i));
            if (training != null) {
                deleteTraining(training.getTrainingId());
            }
        }
    }

    public void deleteTrainingSet(int trainingSetId) {
        trainingSetTable.deleteTrainingSet(trainingSetId);
    }

    public void deleteWorkout(int workoutId) {
        workoutTable.deleteWorkout(workoutId);
    }

    public void deleteDoneWorkoutAndDoneSet(Workout workout) {
        ArrayList<DoneWorkout> doneWorkoutArrayList = getDoneWorkoutArrayList(workout.getWorkoutId());
        for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
            ArrayList<DoneSet> doneSetArrayList = doneSetTable.getDoneSetArrayList(doneWorkoutArrayList.get(i));
            for (int j = 0; j < doneSetArrayList.size(); j++) {
                doneSetTable.deleteDoneSet(doneSetArrayList.get(j));
            }
            doneWorkoutTable.deleteDoneWorkout(doneWorkoutArrayList.get(i));
        }
    }

    public ArrayList<Training> getTrainingArrayList() {
        return trainingTable.getTrainingArrayList();
    }

    public Training getTraining(int trainingId) {
        for(int i = 0; i < trainingTable.getTrainingArrayList().size(); i++) {
            if(trainingId == trainingTable.getTrainingArrayList().get(i).getTrainingId()) {
                return trainingTable.getTrainingArrayList().get(i);
            }
        }
        return null;
    }

    public ArrayList<TrainingWorkout> getTrainingWorkoutArrayList(int trainingId) {
        ArrayList<TrainingWorkout> allTrainingArrayList = getTrainingWorkoutArrayList();
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = new ArrayList<>();
        for(int i = 0; i < allTrainingArrayList.size(); i++) {
            if(allTrainingArrayList.get(i).getTrainingId() == trainingId) {
                trainingWorkoutArrayList.add(allTrainingArrayList.get(i));
            }
        }
        return trainingWorkoutArrayList;
    }

    public String getTrainingInfo(int trainingId) {
        String trainingInfo = "";
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = getTrainingWorkoutArrayList(trainingId);
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(i);
            String workoutName = getWorkoutName(trainingWorkout.getWorkoutId());
            int numberOfSets = trainingSetTable.getTrainingSetArrayList(trainingWorkout.getTrainingWorkoutId()).size();
            trainingInfo += (i+1) + ". " + workoutName + " - " + numberOfSets + " sets \n";
        }
        return trainingInfo;
    }

    public String getWorkoutName(int workoutId) {
        ArrayList<Workout> workoutArrayList = workoutTable.getWorkoutArrayList();
        for (int i = 0; i < workoutArrayList.size(); i++) {
            if (workoutArrayList.get(i).getWorkoutId() == workoutId)
                return workoutArrayList.get(i).getWorkoutName();
        }
        return "";
    }

    public ArrayList<TrainingWorkout> getTrainingWorkoutArrayList() {
        return trainingWorkoutTable.getTrainingWorkoutArrayList();
    }

    public void printTrainingWorkoutArrayList(ArrayList<TrainingWorkout> trainingWorkouts) {
        System.out.println("============= TrainingWorkuts ");
        for (int i = 0; i < trainingWorkouts.size(); i++) {
            System.out.println(trainingWorkouts.get(i).getTrainingWorkoutId() + " ");
        }
        System.out.println("\n");
    }

    public void printTrainingSetArrayList(ArrayList<TrainingSet> trainingSets) {
        System.out.println("============= TrainingSets ");
        for (int i = 0; i < trainingSets.size(); i++) {
            System.out.print("{"+trainingSets.get(i).getTrainingSetId()+" "+trainingSets.get(i).getTrainingWorkoutId()+"}" + " ");
        }
        System.out.println("\n");
    }

//    public void printTrainingSetHashMap(HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap) {
//        System.out.println("============= TrainingSetHashMap ");
//        for (int i = 0; i < trainingWorkouts.size(); i++) {
//            System.out.println(trainingWorkouts.get(i).getTrainingWorkoutId() + " ");
//        }
//        System.out.println("\n");
//    }

    public HashMap<Integer,ArrayList<TrainingSet>> getTrainingSetHashMap(ArrayList<TrainingWorkout> trainingWorkoutArrayList) {
        HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap = new HashMap<>();
        ArrayList<TrainingSet> trainingSetArrayList = getTrainingSetArrayList();
        for(int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            ArrayList<TrainingSet> currentTrainingSetArrayList = new ArrayList<>();
            for(int j = 0; j < trainingSetArrayList.size(); j++) {
                System.out.print(trainingSetArrayList.get( j ).getTrainingSetId()+"-"+trainingSetArrayList.get( j ).getTrainingWorkoutId()+" ");
                if(trainingWorkoutArrayList.get(i).getTrainingWorkoutId() == trainingSetArrayList.get(j).getTrainingWorkoutId()) {
                    currentTrainingSetArrayList.add(trainingSetArrayList.get(j));
                }
            }
            trainingSetHashMap.put(i, currentTrainingSetArrayList);
        }

        return trainingSetHashMap;
    }

    public ArrayList<TrainingSet> getTrainingSetArrayList() {
        return trainingSetTable.getTrainingSetArrayList();
    }

    // clear

    public void clearDatabase() {
        doneSetTable.clearDoneSets();
        doneTrainingTable.clearDoneTrainings();//
        doneWorkoutTable.clearDoneWorkouts();
        equipmentTable.clearEquipments();
        equipmentTypeTable.clearEquipmentTypes();
        muscleGroupTable.clearMuscleGroups();
        muscleTable.clearMuscles();//
        relationWorkoutMuscleTable.clearRelationsWorkoutMuscle();
        trainingSetTable.clearTrainingSets();//
        trainingTable.clearTrainingTable();//
        trainingWorkoutTable.clearTrainingTable();//
        userWeightTable.clearUserWeights();
        workoutTable.clearWorkouts();
    }

    public void insertDoneTrainingArrayList(ArrayList<DoneTraining> doneTrainingArrayList) {
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            insertDoneTraining(doneTrainingArrayList.get(i));
        }
    }

    public void insertDoneTraining(DoneTraining doneTraining) {
        doneTrainingTable.insertDoneTraining(doneTraining);
    }

    public ArrayList<DoneTraining> getDoneTrainingByTrainingId(int trainingId) {
        return doneTrainingTable.getDoneTrainingByTrainingId(trainingId);
    }

    public void insertTrainingMessageArrayList(ArrayList<TrainingMessage> trainingMessageArrayList) {
        for (int i = 0; i < trainingMessageArrayList.size(); i++) {
            TrainingMessage trainingMessage = trainingMessageArrayList.get(i);
            insertNewTraining(trainingMessage.getTraining(), trainingMessage.getTrainingWorkoutArrayList(), trainingMessage.getTrainingSetHashMap());
        }
    }

    public TrainingWorkout getTrainingWorkout(int trainingWorkoutId) {
        return trainingWorkoutTable.getTrainingWorkout(trainingWorkoutId);
    }

    public void deleteRelationWorkoutMusclesByWorkoutId(int workoutId) {
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = relationWorkoutMuscleTable.getAllRelationWorkoutMuscleArrayList();
        for (int i = 0; i < relationWorkoutMuscleArrayList.size(); i++) {
            if (relationWorkoutMuscleArrayList.get(i).getWorkoutId() == workoutId) {
                System.out.println("~~~~~~~~~~~~~~~ " + relationWorkoutMuscleArrayList.get(i).getRelationId());
                relationWorkoutMuscleTable.deleteRelationWorkoutMuscle(relationWorkoutMuscleArrayList.get(i).getRelationId());
            }
        }
    }

}
