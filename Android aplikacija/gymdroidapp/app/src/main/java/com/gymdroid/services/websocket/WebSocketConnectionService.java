package com.gymdroid.services.websocket;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gymdroid.domain.message.Message;
import com.gymdroid.services.BaseService;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class WebSocketConnectionService extends BaseService {

    private static final String TAG = " * GymDroidConnection";
    private static final String HOME_URL = "ws://192.168.1.25:8080/GymDroid";
    private static final int STATUS_LOST_CONNECTION = 2;
    private static final int STATUS_SERVER_EXCEPTION = 4;

    private WebSocketConnection connection = new WebSocketConnection();

    public WebSocketConnection getConnection() {
        return connection;
    }

    public void initWebSocketConnection(final Activity activity) {
        if(!connection.isConnected()) {
            try {
                connection.connect(HOME_URL, new WebSocketHandler() {

                    @Override
                    public void onOpen() {
                        System.out.println( "------------------------------" );
                        Log.i(TAG, "Status: Connected to " + HOME_URL);
                    }

                    @Override
                    public void onTextMessage(String payload) {
                        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create();
                        Message responseMessage = gson.fromJson(payload, Message.class);
                        switch (responseMessage.getEventName()) {
                            case REGISTER: {
                                INSTANCE.getWebSocketResponseLoginLoadService().register(responseMessage, activity);
                                break;
                            }
                            case LOGIN: {
                                INSTANCE.getWebSocketResponseLoginLoadService().login(responseMessage, activity);
                                break;
                            }
                            case ENTER: {
                                INSTANCE.getWebSocketResponseEnterService().enter(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_DONE_SET: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadDoneSet(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_DONE_WORKOUT: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadDoneWorkout(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_WORKOUT: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadWorkout(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_RELATION_WORKOUT_MUSCLE:{
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadRelationWorkoutMuscle(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_EQUIPMENT_TYPE: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadEquipmentType(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_EQUIPMENT: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadEquipment(responseMessage,activity);
                                break;
                            }
                            case LOGIN_LOAD_MUSCLE: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadMuscle(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_USER_WEIGHT: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadUserWeight(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_TRAINING: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadTraining(responseMessage, activity);
                                break;
                            }
                            case LOGIN_LOAD_DONE_TRAINING: {
                                INSTANCE.getWebSocketResponseLoginLoadService().loginLoadDoneTraining(responseMessage, activity);
                                break;
                            }
                            case ENTER_DONE_SET: {
                                INSTANCE.getWebSocketResponseEnterService().enterDoneSet(responseMessage,activity);
                                break;
                            }
                            case ENTER_DONE_WORKOUT: {
                                INSTANCE.getWebSocketResponseEnterService().enterDoneWorkout(responseMessage,activity);
                                break;
                            }
                            case ENTER_EQUIPMENT: {
                                INSTANCE.getWebSocketResponseEnterService().enterEquipment(responseMessage,activity);
                                break;
                            }
                            case ENTER_EQUIPMENT_TYPE: {
                                INSTANCE.getWebSocketResponseEnterService().enterEquipmentType(responseMessage,activity);
                                break;
                            }
                            case ENTER_MUSCLE: {
                                INSTANCE.getWebSocketResponseEnterService().enterMuscle(responseMessage,activity);
                                break;
                            }
                            case ENTER_MUSCLE_GROUP: {
                                INSTANCE.getWebSocketResponseEnterService().enterMuscleGroup(responseMessage,activity);
                                break;
                            }
                            case ENTER_RELATION_WORKOUT_MUSCLE: {
                                INSTANCE.getWebSocketResponseEnterService().enterRelationWorkoutMuscle(responseMessage,activity);
                                break;
                            }
                            case ENTER_USER_WEIGHT: {
                                INSTANCE.getWebSocketResponseEnterService().enterUserWeight(responseMessage,activity);
                                break;
                            }
                            case ENTER_WORKOUT: {
                                INSTANCE.getWebSocketResponseEnterService().enterWorkout(responseMessage,activity);
                                break;
                            }
                            case ENTER_TRAINING: {
                                INSTANCE.getWebSocketResponseEnterService().enterTraining(responseMessage,activity);
                                break;
                            }
                            case ENTER_TRAINING_SET: {
                                INSTANCE.getWebSocketResponseEnterService().enterTrainingSet(responseMessage,activity);
                                break;
                            }
                            case ENTER_TRAINING_WORKOUT: {
                                INSTANCE.getWebSocketResponseEnterService().enterTrainingWorkout(responseMessage,activity);
                                break;
                            }
                            case ENTER_DONE_TRAINING: {
                                INSTANCE.getWebSocketResponseEnterService().enterDoneTraining(responseMessage,activity);
                                break;
                            }
                            case OPERATION_CREATE_WORKOUT: {
                                INSTANCE.getWebSocketResponseOperationService().createWorkout(responseMessage, activity);
                                break;
                            }
                            case OPERATION_DELETE_WORKOUT: {
                                INSTANCE.getWebSocketResponseOperationService().deleteWorkout(responseMessage, activity);
                                break;
                            }
                            case OPERATION_CREATE_TRAINING: {
                                INSTANCE.getWebSocketResponseOperationService().createTraining(responseMessage, activity);
                                break;
                            }
                            case OPERATION_DELETE_TRAINING: {
                                INSTANCE.getWebSocketResponseOperationService().deleteTraining(responseMessage, activity);
                                break;
                            }
                            case OPERATION_ADD_EQUIPMENT: {
                                INSTANCE.getWebSocketResponseOperationService().addEquipment(responseMessage, activity);
                                break;
                            }
                            case OPERATION_DELETE_EQUIPMENT: {
                                INSTANCE.getWebSocketResponseOperationService().deleteEquipment(responseMessage, activity);
                                break;
                            }
                            case OPERATION_ADD_USER_WEIGHT: {
                                INSTANCE.getWebSocketResponseOperationService().addUserWeight(responseMessage, activity);
                                break;
                            }
                            case OPERATION_DELETE_USER_WEIGHT: {
                                INSTANCE.getWebSocketResponseOperationService().deleteUserWeight(responseMessage, activity);
                                break;
                            }
                            case OPERATION_ADD_PRACTICE: {
                                INSTANCE.getWebSocketResponseOperationService().addPractice(responseMessage, activity);
                                break;
                            }
                            case OPERATION_ADD_MULTIPLE_PRACTICES: {
                                INSTANCE.getWebSocketResponseOperationService().addMultiplePractices(responseMessage, activity);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onClose(int code, String reason) {
                        Log.e(TAG, "code : " + code);
                        Log.e(TAG, "reason : " + reason);
                        INSTANCE.getDialogResponseService().signalizeReceived();
                        boolean isNetworkConnected = INSTANCE.getDialogInternetService().openInternetConnectionDialog(activity);
                        if (isNetworkConnected) {
                            if(code == STATUS_LOST_CONNECTION || code == STATUS_SERVER_EXCEPTION) {
                                INSTANCE.getStartActivityService().startServerIsDownActivity(activity);
                            }
                            else {
                                INSTANCE.getStartActivityService().startSplashScreenActivity(activity);
                            }
                        } else {
                            initWebSocketConnection(activity);
                        }
                    }

                });
            } catch (WebSocketException ex) {
                Log.e(TAG, "WebSocketException");
                ex.printStackTrace();
            }
        }
    }

}
