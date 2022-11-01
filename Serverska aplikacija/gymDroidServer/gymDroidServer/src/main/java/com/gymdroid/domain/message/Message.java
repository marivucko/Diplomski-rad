
package com.gymdroid.domain.message;

import com.google.gson.JsonElement;

public class Message {
    private MessageEnum eventName;
    private JsonElement data;

    public Message() { }

    public Message(MessageEnum eventName, JsonElement data) {
        this.eventName = eventName;
        this.data = data;
    }

    public MessageEnum getEventName() {
        return eventName;
    }

    public void setEventName(MessageEnum eventName) {
        this.eventName = eventName;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
