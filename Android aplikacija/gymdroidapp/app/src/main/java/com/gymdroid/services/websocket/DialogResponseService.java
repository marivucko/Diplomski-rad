package com.gymdroid.services.websocket;

import android.app.Activity;
import android.content.Intent;

import com.gymdroid.activities.loading.WaitForServerResponseActivity;
import com.gymdroid.services.BaseService;

public class DialogResponseService extends BaseService {

    private DialogThread dialogThread;

    public DialogResponseService() {
        this.dialogThread = new DialogThread();
    }

    public String openWaitForServerResponseDialog(Activity activity, String loadingMessage){
        if(this.dialogThread.isNotLoading()) {
            Intent intent = new Intent(activity, WaitForServerResponseActivity.class);
            intent.putExtra( WaitForServerResponseActivity.LOADING_MESSAGE, loadingMessage);
            activity.startActivity(intent);
            return WaitForServerResponseActivity.class.getSimpleName();
        }
        return null;
    }

    public void signalizeReceived() {
        this.dialogThread.signalizeToStop();
    }

    public boolean waitForResponse(final WaitForServerResponseActivity activity){
        return dialogThread.setActivityAndStart(activity);
    }

}
