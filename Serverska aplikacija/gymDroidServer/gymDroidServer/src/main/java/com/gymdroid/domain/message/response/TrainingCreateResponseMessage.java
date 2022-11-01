package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

import java.util.ArrayList;
import java.util.HashMap;


public class TrainingCreateResponseMessage extends ResponseMessage {

    private Training training;
    private ArrayList<TrainingWorkout> trainingWorkoutArrayList;
    private HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap;

    public TrainingCreateResponseMessage(StatusEnum statusEnum, User user, Training training, ArrayList<TrainingWorkout> trainingWorkoutArrayList, HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap) {
        super(statusEnum, user);
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
