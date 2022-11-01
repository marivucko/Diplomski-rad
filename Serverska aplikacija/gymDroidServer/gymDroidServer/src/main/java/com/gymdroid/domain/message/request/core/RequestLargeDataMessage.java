package com.gymdroid.domain.message.request.core;

import com.gymdroid.domain.beans.User;

public class RequestLargeDataMessage extends RequestMessage {

    private int startIndex;
    private int increment;

    public RequestLargeDataMessage(User user, int startIndex, int increment) {
        super(user);
        this.startIndex = startIndex;
        this.increment = increment;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

}
