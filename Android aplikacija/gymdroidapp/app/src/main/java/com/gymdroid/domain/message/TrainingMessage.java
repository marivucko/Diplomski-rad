package com.gymdroid.domain.message;

import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainingMessage {

    private Training training;
    private ArrayList<TrainingWorkout> trainingWorkoutArrayList;
    private HashMap<Integer, ArrayList<TrainingSet>> trainingSetHashMap;

    public TrainingMessage(Training training, ArrayList<TrainingWorkout> trainingWorkoutArrayList, HashMap<Integer, ArrayList<TrainingSet>> trainingSetHashMap) {
        this.training = training;
        this.trainingWorkoutArrayList = trainingWorkoutArrayList;
        this.trainingSetHashMap = trainingSetHashMap;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public ArrayList<TrainingWorkout> getTrainingWorkoutArrayList() {
        return trainingWorkoutArrayList;
    }

    public void setTrainingWorkoutArrayList(ArrayList<TrainingWorkout> trainingWorkoutArrayList) {
        this.trainingWorkoutArrayList = trainingWorkoutArrayList;
    }

    public HashMap<Integer, ArrayList<TrainingSet>> getTrainingSetHashMap() {
        return trainingSetHashMap;
    }

    public void setTrainingSetHashMap(HashMap<Integer, ArrayList<TrainingSet>> trainingSetHashMap) {
        this.trainingSetHashMap = trainingSetHashMap;
    }
}
