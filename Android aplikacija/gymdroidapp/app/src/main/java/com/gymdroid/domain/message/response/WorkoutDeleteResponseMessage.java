package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

public class WorkoutDeleteResponseMessage extends ResponseMessage {

    private Workout workout;

    public WorkoutDeleteResponseMessage(StatusEnum status, User user, Workout workout) {
        super(status, user);
        this.workout = workout;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
