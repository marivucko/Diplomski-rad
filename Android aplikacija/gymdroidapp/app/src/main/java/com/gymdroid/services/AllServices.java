package com.gymdroid.services;

import android.app.Activity;

import com.gymdroid.dao.Database;
import com.gymdroid.services.authentication.EmailAuthService;
import com.gymdroid.services.authentication.GoogleAuthService;
import com.gymdroid.services.equipment.EquipmentService;
import com.gymdroid.services.navigation.DecideNextActivityService;
import com.gymdroid.services.navigation.StartActivityService;
import com.gymdroid.services.practice.PracticeAddService;
import com.gymdroid.services.training.TrainingService;
import com.gymdroid.services.websocket.DialogInternetService;
import com.gymdroid.services.websocket.DialogResponseService;
import com.gymdroid.services.websocket.WebSocketConnectionService;
import com.gymdroid.services.websocket.WebSocketRequestEnterService;
import com.gymdroid.services.websocket.WebSocketRequestLoginLoadService;
import com.gymdroid.services.websocket.WebSocketRequestOperationService;
import com.gymdroid.services.websocket.WebSocketResponseEnterService;
import com.gymdroid.services.websocket.WebSocketResponseLoginLoadService;
import com.gymdroid.services.websocket.WebSocketResponseOperationService;
import com.gymdroid.services.weight.UserWeightService;
import com.gymdroid.services.workout.WorkoutService;

public class AllServices extends BaseService {

    private CalendarService calendarService;
    private ConfigurationService configurationService;
    private CreateTrainingService createTrainingService;
    private Database database;
    private DecideNextActivityService decideNextActivityService;
    private DialogInternetService dialogInternetService;
    private DialogResponseService dialogResponseService;
    private EmailAuthService emailAuthService;
    private EquipmentService equipmentService;
    private FasterInputServiceDatePicker fasterInputServiceDatePicker;
    private FasterInputServiceNewWorkout fasterInputServiceNewWorkout;
    private FasterInputServiceTimePicker fasterInputServiceTimePicker;
    private FasterInputServiceWorkoutDetails fasterInputServiceWorkoutDetails;
    private GoogleAuthService googleAuthService;
    private PracticeAddService practiceAddService;
    private SimpleDesignService simpleDesignService;
    private StartActivityService startActivityService;
    private TrainingPauseService trainingPauseService;
    private TrainingService trainingService;
    private StringService stringService;
    private UserWeightService userWeightService;
    private WebSocketConnectionService webSocketConnectionService;
    private WebSocketRequestEnterService webSocketRequestEnterService;
    private WebSocketRequestLoginLoadService webSocketRequestLoginLoadService;
    private WebSocketRequestOperationService webSocketRequestOperationService;
    private com.gymdroid.services.websocket.WebSocketResponseEnterService WebSocketResponseEnterService;
    private WebSocketResponseLoginLoadService webSocketResponseLoginLoadService;
    private WebSocketResponseOperationService webSocketResponseOperationService;
    private WorkoutService workoutService;

    private AllServices(Activity activity) {
        this.calendarService = new CalendarService();
        this.configurationService = new ConfigurationService(activity);
        this.createTrainingService = new CreateTrainingService();
        this.decideNextActivityService = new DecideNextActivityService();
        this.dialogInternetService = new DialogInternetService();
        this.dialogResponseService = new DialogResponseService();
        this.emailAuthService = new EmailAuthService(activity);
        this.equipmentService = new EquipmentService();
        this.fasterInputServiceDatePicker = new FasterInputServiceDatePicker();
        this.fasterInputServiceNewWorkout = new FasterInputServiceNewWorkout();
        this.fasterInputServiceTimePicker = new FasterInputServiceTimePicker();
        this.fasterInputServiceWorkoutDetails = new FasterInputServiceWorkoutDetails();
        this.googleAuthService = new GoogleAuthService(activity);
        this.practiceAddService = new PracticeAddService();
        this.simpleDesignService = new SimpleDesignService();
        this.startActivityService = new StartActivityService();
        this.trainingService = new TrainingService();
        this.stringService = new StringService();
        this.trainingPauseService = new TrainingPauseService();
        this.userWeightService = new UserWeightService();
        this.webSocketConnectionService = new WebSocketConnectionService();
        this.webSocketRequestEnterService = new WebSocketRequestEnterService();
        this.webSocketRequestLoginLoadService = new WebSocketRequestLoginLoadService();
        this.webSocketRequestOperationService = new WebSocketRequestOperationService();
        this.WebSocketResponseEnterService = new WebSocketResponseEnterService();
        this.webSocketResponseLoginLoadService = new WebSocketResponseLoginLoadService();
        this.webSocketResponseOperationService = new WebSocketResponseOperationService();
        this.workoutService = new WorkoutService();
        this.database = new Database(activity);
    }

    public static AllServices getInstance(Activity activity){
        if(INSTANCE == null){
            synchronized (BaseService.class) {
                if(INSTANCE == null){
                    INSTANCE = new AllServices(activity);
                }
            }
        }
        return INSTANCE;
    }

    public CalendarService getCalendarService() {
        return calendarService;
    }

    public ConfigurationService getConfigurationService() {
        return configurationService;
    }

    public Database getDatabase() {
        return database;
    }

    public DecideNextActivityService getDecideNextActivityService() {
        return decideNextActivityService;
    }

    public DialogInternetService getDialogInternetService() {
        return dialogInternetService;
    }

    public DialogResponseService getDialogResponseService() {
        return dialogResponseService;
    }

    public EmailAuthService getEmailAuthService() {
        return emailAuthService;
    }

    public EquipmentService getEquipmentService() {
        return equipmentService;
    }

    public FasterInputServiceDatePicker getFasterInputServiceDatePicker() {
        return fasterInputServiceDatePicker;
    }

    public FasterInputServiceNewWorkout getFasterInputServiceNewWorkout() {
        return fasterInputServiceNewWorkout;
    }

    public CreateTrainingService getCreateTrainingService() {
        return createTrainingService;
    }

    public FasterInputServiceTimePicker getFasterInputServiceTimePicker() {
        return fasterInputServiceTimePicker;
    }

    public FasterInputServiceWorkoutDetails getFasterInputServiceWorkoutDetails() {
        return fasterInputServiceWorkoutDetails;
    }

    public TrainingService getTrainingService() {
        return trainingService;
    }

    public GoogleAuthService getGoogleAuthService() {
        return googleAuthService;
    }

    public PracticeAddService getPracticeAddService() {
        return practiceAddService;
    }

    public SimpleDesignService getSimpleDesignService() {
        return simpleDesignService;
    }

    public StartActivityService getStartActivityService() {
        return startActivityService;
    }

    public StringService getStringService() {
        return stringService;
    }

    public TrainingPauseService getTrainingPauseService() {
        return trainingPauseService;
    }

    public UserWeightService getUserWeightService() {
        return userWeightService;
    }

    public WebSocketConnectionService getWebSocketConnectionService() {
        return webSocketConnectionService;
    }

    public WebSocketRequestEnterService getWebSocketRequestEnterService() {
        return webSocketRequestEnterService;
    }

    public WebSocketRequestLoginLoadService getWebSocketRequestLoginLoadService() {
        return webSocketRequestLoginLoadService;
    }

    public WebSocketRequestOperationService getWebSocketRequestOperationService() {
        return webSocketRequestOperationService;
    }

    public com.gymdroid.services.websocket.WebSocketResponseEnterService getWebSocketResponseEnterService() {
        return WebSocketResponseEnterService;
    }

    public WebSocketResponseLoginLoadService getWebSocketResponseLoginLoadService() {
        return webSocketResponseLoginLoadService;
    }

    public WebSocketResponseOperationService getWebSocketResponseOperationService() {
        return webSocketResponseOperationService;
    }

    public WorkoutService getWorkoutService() {
        return workoutService;
    }

}
