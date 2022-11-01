package com.gymdroid.domain.message.request.enter;


import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.request.core.EnterOperationRequestMessage;

import java.util.Date;

public class EnterRequestMessage extends EnterOperationRequestMessage {

    private Operation operation;

    public EnterRequestMessage(User user, int startIndex, int increment, Date lastUpdatedAt, Operation operation) {
        super(user, startIndex, increment, lastUpdatedAt);
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
