package com.gymdroid.services;



import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateTrainingService extends BaseService {

    private ArrayList<TrainingWorkout> trainingWorkoutArrayList;
    private HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap;
    private Training training;

    public CreateTrainingService() {
        trainingWorkoutArrayList = new ArrayList<>();
        trainingSetHashMap = new HashMap<>();
    }

    public void addData(TrainingWorkout trainingWorkout, ArrayList<TrainingSet> trainingSetArrayList) {
        trainingWorkoutArrayList.add(trainingWorkout);
        int lastIndex = trainingWorkoutArrayList.size() - 1;
        trainingSetHashMap.put(lastIndex,trainingSetArrayList);
    }

    public void  clearData() {
        trainingWorkoutArrayList.clear();
        trainingSetHashMap.clear();
        training = null;
    }

    public int generateTrainingWorkoutOrderNumber() {
        return trainingWorkoutArrayList.size();
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Training getTraining() {
        return training;
    }

    public ArrayList<TrainingWorkout> getTrainingWorkoutArrayList() {
        return trainingWorkoutArrayList;
    }

    public HashMap<Integer, ArrayList<TrainingSet>> getTrainingSetHashMap() {
        return trainingSetHashMap;
    }
}
