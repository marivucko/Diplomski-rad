
package com.gymdroid.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gymdroid.domain.message.Message;
import com.gymdroid.domain.message.request.*;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.*;
import com.gymdroid.domain.message.response.*;
import com.gymdroid.domain.message.response.enter.*;
import com.gymdroid.domain.message.response.login.*;
import com.gymdroid.service.*;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@EnableWebSocketMessageBroker
public class GymDroidController extends TextWebSocketHandler {


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        //Gson gson = new Gson();
        GsonBuilder gb = new GsonBuilder();
        gb.setDateFormat( "MMM dd, yyyy, HH:mm:ss a" );
        Gson gson = gb.create();
        Message requestMessage = gson.fromJson(message.getPayload(), Message.class);
        System.out.println("REQUEST -- " + requestMessage.getEventName());
        switch (requestMessage.getEventName()) {
            case REGISTER: {
                register(gson, requestMessage, session);
                break;
            }
            case LOGIN: {
                login(gson, requestMessage, session);
                break;
            }
            case ENTER: {
                enter(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_DONE_SET: {
                loginLoadDoneSets(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_DONE_TRAINING: {
                loginLoadDoneTrainings(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_DONE_WORKOUT: {
                loginLoadDoneWorkout(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_EQUIPMENT: {
                loginLoadEquipment(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_EQUIPMENT_TYPE: {
                loginLoadEquipmentTypes(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_MUSCLE: {
                loginLoadMuscle(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_RELATION_WORKOUT_MUSCLE: {
                loginLoadRelationWorkoutMuscle(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_TRAINING: {
                loginLoadTrainings(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_USER_WEIGHT: {
                loginLoadUserWeight(gson, requestMessage, session);
                break;
            }
            case LOGIN_LOAD_WORKOUT: {
                loginLoadWorkouts(gson, requestMessage, session);
                break;
            }
            case ENTER_DONE_SET: {
                enterDoneSet(gson, requestMessage, session);
                break;
            }
            case ENTER_DONE_TRAINING: {
                enterDoneTraining(gson, requestMessage, session);
                break;
            }
            case ENTER_DONE_WORKOUT: {
                enterDoneWorkout(gson, requestMessage, session);
                break;
            }
            case ENTER_EQUIPMENT: {
                enterEquipment(gson, requestMessage, session);
                break;
            }
            case ENTER_EQUIPMENT_TYPE: {
                enterEquipmentType(gson, requestMessage, session);
                break;
            }
            case ENTER_MUSCLE: {
                enterMuscle(gson, requestMessage, session);
                break;
            }
            case ENTER_MUSCLE_GROUP: {
                enterMuscleGroup(gson, requestMessage, session);
                break;
            }
            case ENTER_RELATION_WORKOUT_MUSCLE: {
                enterRelationWorkoutMuscle(gson, requestMessage, session);
                break;
            }
            case ENTER_TRAINING: {
                enterTraining(gson, requestMessage, session);
                break;
            }
            case ENTER_TRAINING_SET: {
                enterTrainingSet(gson, requestMessage, session);
                break;
            }
            case ENTER_TRAINING_WORKOUT: {
                enterTrainingWorkout(gson, requestMessage, session);
                break;
            }
            case ENTER_USER_WEIGHT: {
                enterUserWeight(gson, requestMessage, session);
                break;
            }
            case ENTER_WORKOUT: {
                enterWorkout(gson, requestMessage, session);
                break;
            }
            case OPERATION_CREATE_WORKOUT: {
                createWorkout(gson, requestMessage, session);
                break;
            }
            case OPERATION_DELETE_WORKOUT: {
                deleteWorkout(gson, requestMessage, session);
                break;
            }
            case OPERATION_CREATE_TRAINING: {
                createTraining(gson, requestMessage, session);
                break;
            }
            case OPERATION_DELETE_TRAINING: {
                deleteTraining(gson, requestMessage, session);
                break;
            }
            case OPERATION_ADD_EQUIPMENT: {
                addEquipment(gson, requestMessage, session);
                break;
            }
            case OPERATION_DELETE_EQUIPMENT: {
                deleteEquipment(gson, requestMessage, session);
                break;
            }
            case OPERATION_ADD_USER_WEIGHT: {
                addUserWeight(gson, requestMessage, session);
                break;
            }
            case OPERATION_DELETE_USER_WEIGHT: {
                deleteUserWeight(gson, requestMessage, session);
                break;
            }
            case OPERATION_ADD_PRACTICE: {
                addPractice(gson, requestMessage, session);
                break;
            }
            case OPERATION_ADD_MULTIPLE_PRACTICES: {
                addMultiplePractice(gson, requestMessage, session);
                break;
            }
        }
    }

    private void register(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        RegisterRequestMessage registerRequestMessage = gson.fromJson(requestMessage.getData(), RegisterRequestMessage.class);
        RegisterResponseMessage registerResponseMessage = UserService.register(registerRequestMessage);
        sentResponseMassage(requestMessage, session, registerResponseMessage);
    }

    private void login(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginRequestMessage loginRequestMessage = gson.fromJson(requestMessage.getData(), LoginRequestMessage.class);
        LoginResponseMessage loginResponseMessage = UserService.login(loginRequestMessage);
        sentResponseMassage(requestMessage, session, loginResponseMessage);
    }

    private void enter(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        com.gymdroid.domain.message.request.EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), com.gymdroid.domain.message.request.EnterRequestMessage.class);
        EnterResponseMessage enterResponseMessage = UserService.enter(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void loginLoadDoneSets(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadDoneSetResponseMessage loginLoadResponseMessage = DoneSetService.loginLoadDoneSets(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadResponseMessage);
    }

    private void loginLoadDoneWorkout(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadDoneWorkoutResponseMessage loginLoadDoneWorkoutResponseMessage = DoneWorkoutService.loginLoadDoneWorkouts(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadDoneWorkoutResponseMessage);
    }

    private void loginLoadWorkouts(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadWorkoutResponseMessage loginLoadWorkoutResponseMessage = WorkoutService.loginLoadWorkouts(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadWorkoutResponseMessage);
    }

    private void loginLoadRelationWorkoutMuscle(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadRelationWorkoutMuscleResponseMessage loginLoadRelationWorkoutMuscleResponseMessage = RelationWorkoutMuscleService.loginLoadRelations(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadRelationWorkoutMuscleResponseMessage);
    }

    private void loginLoadEquipmentTypes(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadEquipmentTypeResponseMessage loginLoadEquipmentTypeResponseMessage = EquipmentTypeService.loginLoadEquipmentTypes(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadEquipmentTypeResponseMessage);
    }

    private void loginLoadMuscle(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadMuscleResponseMessage loginLoadMuscleResponseMessage = MuscleService.loginLoadMuscle(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadMuscleResponseMessage);
    }

    private void loginLoadEquipment(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadEquipmentResponseMessage loginLoadEquipmentResponseMessage = EquipmentService.loginLoadEquipment(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadEquipmentResponseMessage);
    }

    private void loginLoadUserWeight(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadUserWeightResponseMessage loginLoadUserWeightResponseMessage = UserWeightService.loginLoadUserWeight(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadUserWeightResponseMessage);
    }

    private void loginLoadTrainings(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadTrainingResponseMessage loginLoadUserWeightResponseMessage = TrainingService.loginLoadTrainings(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadUserWeightResponseMessage);
    }

    private void loginLoadDoneTrainings(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        LoginLoadRequestMessage loginLoadRequestMessage = gson.fromJson(requestMessage.getData(), LoginLoadRequestMessage.class);
        LoginLoadDoneTrainingResponseMessage loginLoadDoneTrainingResponseMessage = DoneTrainingService.loginLoadDoneTraining(loginLoadRequestMessage);
        sentResponseMassage(requestMessage, session, loginLoadDoneTrainingResponseMessage);
    }

    private void createWorkout(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        WorkoutCreateRequestMessage workoutCreateRequestMessage = gson.fromJson(requestMessage.getData(), WorkoutCreateRequestMessage.class);
        WorkoutCreateResponseMessage workoutCreateResponseMessage = WorkoutService.createWorkout(workoutCreateRequestMessage);
        sentResponseMassage(requestMessage, session, workoutCreateResponseMessage);
    }

    private void deleteWorkout(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        WorkoutDeleteRequestMessage workoutDeleteRequestMessage = gson.fromJson(requestMessage.getData(), WorkoutDeleteRequestMessage.class);
        WorkoutDeleteResponseMessage workoutDeleteResponseMessage = WorkoutService.deleteWorkout(workoutDeleteRequestMessage);
        sentResponseMassage(requestMessage, session, workoutDeleteResponseMessage);
    }

    private void createTraining(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        TrainingCreateRequestMessage trainingCreateRequestMessage = gson.fromJson(requestMessage.getData(), TrainingCreateRequestMessage.class);
        TrainingCreateResponseMessage trainingCreateResponseMessage = TrainingService.createTraining(trainingCreateRequestMessage);
        sentResponseMassage(requestMessage, session, trainingCreateResponseMessage);
    }

    private void deleteTraining(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        TrainingDeleteRequestMessage trainingDeleteRequestMessage = gson.fromJson(requestMessage.getData(), TrainingDeleteRequestMessage.class);
        TrainingDeleteResponseMessage trainingDeleteResponseMessage = TrainingService.deleteTraining(trainingDeleteRequestMessage);
        sentResponseMassage(requestMessage, session, trainingDeleteResponseMessage);
    }

    private void enterDoneSet(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterDoneSetResponseMessage enterResponseMessage = DoneSetService.enterDoneSet(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterDoneTraining(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterDoneTrainingResponseMessage enterResponseMessage = DoneTrainingService.enterDoneTraining(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterDoneWorkout(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterDoneWorkoutResponseMessage enterResponseMessage = DoneWorkoutService.enterDoneWorkout(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterEquipment(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterEquipmentResponseMessage enterResponseMessage = EquipmentService.enterEquipment(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterEquipmentType(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterEquipmentTypeResponseMessage enterResponseMessage = EquipmentTypeService.enterEquipmentType(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterMuscle(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterMuscleResponseMessage enterResponseMessage = MuscleService.enterMuscle(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterMuscleGroup(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterMuscleGroupResponseMessage enterResponseMessage = MuscleGroupService.enterMuscleGroup(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterRelationWorkoutMuscle(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterRelationWorkoutMuscleResponseMessage enterResponseMessage = RelationWorkoutMuscleService.enterRelationWorkoutMuscle(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterTraining(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterTrainingResponseMessage enterResponseMessage = TrainingService.enterTraining(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterTrainingSet(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterTrainingSetResponseMessage enterResponseMessage = TrainingSetService.enterTrainingSet(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterTrainingWorkout(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterTrainingWorkoutResponseMessage enterResponseMessage = TrainingWorkoutService.enterTrainingWorkout(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterUserWeight(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterUserWeightResponseMessage enterResponseMessage = UserWeightService.enterUserWeight(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void enterWorkout(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EnterRequestMessage enterRequestMessage = gson.fromJson(requestMessage.getData(), EnterRequestMessage.class);
        EnterWorkoutResponseMessage enterResponseMessage = WorkoutService.enterWorkout(enterRequestMessage);
        sentResponseMassage(requestMessage, session, enterResponseMessage);
    }

    private void addEquipment(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EquipmentRequestMessage equipmentRequestMessage = gson.fromJson(requestMessage.getData(), EquipmentRequestMessage.class);
        EquipmentResponseMessage equipmentResponseMessage = EquipmentService.addEquipment(equipmentRequestMessage);
        sentResponseMassage(requestMessage, session, equipmentResponseMessage);
    }

    private void deleteEquipment(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        EquipmentRequestMessage equipmentRequestMessage = gson.fromJson(requestMessage.getData(), EquipmentRequestMessage.class);
        EquipmentResponseMessage equipmentResponseMessage = EquipmentService.deleteEquipment(equipmentRequestMessage);
        sentResponseMassage(requestMessage, session, equipmentResponseMessage);
    }

    private void addUserWeight(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        UserWeightRequestMessage userWeightRequestMessage = gson.fromJson(requestMessage.getData(), UserWeightRequestMessage.class);
        UserWeightResponseMessage userWeightResponseMessage = UserWeightService.addUserWeight(userWeightRequestMessage);
        sentResponseMassage(requestMessage, session, userWeightResponseMessage);
    }

    private void deleteUserWeight(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        UserWeightRequestMessage userWeightRequestMessage = gson.fromJson(requestMessage.getData(), UserWeightRequestMessage.class);
        UserWeightResponseMessage userWeightResponseMessage = UserWeightService.deleteUserWeight(userWeightRequestMessage);
        sentResponseMassage(requestMessage, session, userWeightResponseMessage);
    }

    private void addPractice(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        PracticeAddRequestMessage practiceAddRequestMessage = gson.fromJson(requestMessage.getData(), PracticeAddRequestMessage.class);
        PracticeAddResponseMessage practiceAddResponseMessage = PracticeService.addPractice(practiceAddRequestMessage);
        sentResponseMassage(requestMessage, session, practiceAddResponseMessage);
    }

    private void addMultiplePractice(Gson gson, Message requestMessage, WebSocketSession session) throws IOException {
        AddMultiplePracticesRequestMessage addMultiplePracticesRequestMessage = gson.fromJson(requestMessage.getData(), AddMultiplePracticesRequestMessage.class);
        AddMultiplePracticesResponseMessage addMultiplePracticesResponseMessage = PracticeService.addMultiplePractice(addMultiplePracticesRequestMessage);
        sentResponseMassage(requestMessage, session, addMultiplePracticesResponseMessage);
    }

    private void sentResponseMassage(Message requestMessage, WebSocketSession session, Object object) throws IOException {
        Message responseMessage = new Message(requestMessage.getEventName(), new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(object));
        session.sendMessage(new TextMessage(new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJson(responseMessage)));
        System.out.println("sent");
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message); //To change body of generated methods, choose Tools | Templates.
    }

}
