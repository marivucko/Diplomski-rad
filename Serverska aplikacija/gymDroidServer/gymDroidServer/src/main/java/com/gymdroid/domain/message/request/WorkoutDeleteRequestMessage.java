package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.request.core.RequestMessage;

public class WorkoutDeleteRequestMessage extends RequestMessage {

    private Workout workout;

    public WorkoutDeleteRequestMessage(User user, Workout workout) {
        super(user);
        this.workout = workout;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}

