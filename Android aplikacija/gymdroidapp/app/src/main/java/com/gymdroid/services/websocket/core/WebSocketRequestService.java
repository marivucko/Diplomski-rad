package com.gymdroid.services.websocket.core;

import android.app.Activity;

import com.google.gson.Gson;
import com.gymdroid.domain.message.Message;
import com.gymdroid.services.BaseService;

import de.tavendo.autobahn.WebSocketConnection;

public class WebSocketRequestService extends BaseService {

    public static final int FIRST_START_INDEX = 0;
    protected static final int INCREMENT_FOR_LARGE_DATA = 100;

    protected boolean sendToServer(Activity activity, String loadingMessage, Message message) {
        WebSocketConnection connection = INSTANCE.getWebSocketConnectionService().getConnection();
        if (connection.isConnected()) {
            INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity, loadingMessage);
            connection.sendTextMessage(new Gson().toJson(message));
            return true;
        }
        return false;
    }

}
