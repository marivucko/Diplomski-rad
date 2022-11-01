package com.gymdroid.domain.message.request.core;

import com.gymdroid.domain.beans.User;

import java.util.Date;

public class EnterOperationRequestMessage extends RequestLargeDataMessage {

    private Date lastUpdatedAt;

    public EnterOperationRequestMessage(User user, int startIndex, int increment, Date lastUpdatedAt) {
        super(user, startIndex, increment);
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
