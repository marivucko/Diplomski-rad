package com.gymdroid.domain.message.response.core;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;

public class EnterResponseLargeDateMessage extends ResponseLargeDataMessage {

    private Operation operation;

    public EnterResponseLargeDateMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, Operation operation) {
        super(status, user, totalArraySize, finishIndex);
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }
}
