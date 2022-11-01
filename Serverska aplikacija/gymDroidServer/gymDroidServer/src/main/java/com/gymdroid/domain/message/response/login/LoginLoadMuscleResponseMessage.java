package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

import java.util.ArrayList;

public class LoginLoadMuscleResponseMessage extends ResponseMessage {

    private ArrayList<Muscle> muscleArrayList;
    private ArrayList<MuscleGroup> muscleGroupArrayList;

    public LoginLoadMuscleResponseMessage(StatusEnum status, User user, ArrayList<Muscle> muscleArrayList, ArrayList<MuscleGroup> muscleGroupArrayList) {
        super(status, user);
        this.muscleArrayList = muscleArrayList;
        this.muscleGroupArrayList = muscleGroupArrayList;
    }

    public ArrayList<Muscle> getMuscleArrayList() {
        return muscleArrayList;
    }

    public void setMuscleArrayList(ArrayList<Muscle> muscleArrayList) {
        this.muscleArrayList = muscleArrayList;
    }

    public ArrayList<MuscleGroup> getMuscleGroupArrayList() {
        return muscleGroupArrayList;
    }

    public void setMuscleGroupArrayList(ArrayList<MuscleGroup> muscleGroupArrayList) {
        this.muscleGroupArrayList = muscleGroupArrayList;
    }
}
