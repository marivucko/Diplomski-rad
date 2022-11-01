package com.gymdroid.domain.message.response.core;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;

public class ResponseLargeDataMessage {

    private StatusEnum status;
    private User user;
    private int totalArraySize;
    private int finishIndex;

    public ResponseLargeDataMessage(StatusEnum status, User user, int totalArraySize, int finishIndex) {
        this.status = status;
        this.user = user;
        this.totalArraySize = totalArraySize;
        this.finishIndex = finishIndex;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalArraySize() {
        return totalArraySize;
    }

    public void setTotalArraySize(int totalArraySize) {
        this.totalArraySize = totalArraySize;
    }

    public int getFinishIndex() {
        return finishIndex;
    }

    public void setFinishIndex(int finishIndex) {
        this.finishIndex = finishIndex;
    }
}
