package com.gymdroid.service;

import com.gymdroid.dao.*;
import com.gymdroid.domain.RegistrationType;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.EnterRequestMessage;
import com.gymdroid.domain.message.request.LoginRequestMessage;
import com.gymdroid.domain.message.request.RegisterRequestMessage;
import com.gymdroid.domain.message.response.EnterResponseMessage;
import com.gymdroid.domain.message.response.LoginResponseMessage;
import com.gymdroid.domain.message.response.RegisterResponseMessage;

public class UserService {

    public static RegisterResponseMessage register(RegisterRequestMessage registerRequestMessage){

        User user = registerRequestMessage.getUser();
        System.out.println("new user register with email : " + user.getUserEmail());

        if(RegistrationType.EMAIL.equals(user.getRegistrationType())) {
            if(UserHandler.userEmailExistButWithGoogle(user)) {
                return new RegisterResponseMessage(
                        StatusEnum.REGISTER_ERROR_WRONG_TYPE,
                        user
                );
            }
            if(UserHandler.userEmailExist(user)) {
                return new RegisterResponseMessage(
                        StatusEnum.REGISTER_ERROR_USER_EXIST,
                        user
                );
            }
            User registerUser = UserHandler.insertUser(user);
            if(registerUser != null) {
                return new RegisterResponseMessage(
                        StatusEnum.REGISTER_SUCCESS,
                        registerUser
                );
            }
        }

        if(RegistrationType.GOOGLE.equals(user.getRegistrationType())) {
            if(UserHandler.userGoogleExistButWithEmail(user)) {
                return new RegisterResponseMessage(
                        StatusEnum.REGISTER_ERROR_WRONG_TYPE,
                        user
                );
            }
            if(UserHandler.userGoogleExist(user)) {
                User loginGoogleUser = UserHandler.getGoogleUser(user.getUserEmail(),user.getFirebaseId());
                if(loginGoogleUser != null) {
                    return new RegisterResponseMessage(
                            StatusEnum.LOGIN_SUCCESS,
                            loginGoogleUser
                    );
                }
                else {
                    
                    return new RegisterResponseMessage(
                            StatusEnum.LOGIN_GOOGLE_WRONG_FIREBASE_ID,
                            user
                    );
                }
            }
            User registerUser = UserHandler.insertUser(user);
            if(registerUser != null) {
                return new RegisterResponseMessage(
                        StatusEnum.REGISTER_SUCCESS,
                        registerUser
                );
            }
        }

        return new RegisterResponseMessage(
                StatusEnum.SERVER_ERROR,
                user
        );

    }

    public static LoginResponseMessage login(LoginRequestMessage loginRequestMessage) {

        User user = loginRequestMessage.getUser();
        System.out.println("user login with email : " + user.getUserEmail() + ": " + user.getUserPassword());

        if(RegistrationType.EMAIL.equals(user.getRegistrationType())) {
            if (UserHandler.userEmailExist(user)) {
                User loginEmailUser = UserHandler.getEmailUser(user.getUserEmail(), user.getUserPassword());
                if (loginEmailUser != null) {
                    System.out.println(StatusEnum.LOGIN_SUCCESS);
                    return new LoginResponseMessage(
                            StatusEnum.LOGIN_SUCCESS,
                            loginEmailUser
                    );
                } else {
                    System.out.println(StatusEnum.LOGIN_EMAIL_WRONG_PASSWORD);
                    return new LoginResponseMessage(
                            StatusEnum.LOGIN_EMAIL_WRONG_PASSWORD,
                            user
                    );
                }
            }
            System.out.println(StatusEnum.USER_DOES_NOT_EXIST);
            return new LoginResponseMessage(
                    StatusEnum.USER_DOES_NOT_EXIST,
                    user
            );
        }
        else {
            System.out.println(StatusEnum.LOGIN_ERROR_WRONG_TYPE);
            return new LoginResponseMessage(
                    StatusEnum.LOGIN_ERROR_WRONG_TYPE,
                    user
            );
        }
    }

    public static EnterResponseMessage enter(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        User userInDatabase = UserHandler.getUser(user);

        if(userInDatabase == null) {
            return new EnterResponseMessage(StatusEnum.USER_DOES_NOT_EXIST, user);
        }

        return new EnterResponseMessage(
                StatusEnum.LOGIN_SUCCESS,
                user,

                DoneSetHandler.needInsertOnMobileClient(enterRequestMessage.getDoneSetLastCreationDate(), user),
                DoneTrainingHandler.needInsertOnMobileClient(enterRequestMessage.getDoneTrainingLastCreationDate(), user),
                DoneWorkoutHandler.needInsertOnMobileClient(enterRequestMessage.getDoneWorkoutLastCreationDate(),user),
                EquipmentHandler.needInsertOnMobileClient(enterRequestMessage.getEquipmentLastCreationDate(), user),
                EquipmentTypeHandler.needInsertOnMobileClient(enterRequestMessage.getEquipmentTypeLastCreationDate()),
                MuscleHandler.needInsertOnMobileClient(enterRequestMessage.getMuscleLastCreationDate()),
                MuscleGroupHandler.needInsertOnMobileClient(enterRequestMessage.getMuscleGroupLastCreationDate()),
                RelationWorkoutMuscleHandler.needInsertOnMobileClient(enterRequestMessage.getRelationWorkoutMuscleLastCreationDate()),
                TrainingHandler.needInsertOnMobileClient(enterRequestMessage.getTrainingLastCreationDate(), user),
                TrainingSetHandler.needInsertOnMobileClient(enterRequestMessage.getTrainingSetLastCreationDate(), user),
                TrainingWorkoutHandler.needInsertOnMobileClient(enterRequestMessage.getTrainingWorkoutLastCreationDate(), user),
                UserWeightHandler.needInsertOnMobileClient(enterRequestMessage.getUserWeightLastCreationDate(), user),
                WorkoutHandler.needInsertOnMobileClient(enterRequestMessage.getWorkoutLastCreationDate(), user),

                DoneSetHandler.needUpdateOnMobileClient(enterRequestMessage.getDoneSetLastUpdateDate(), user),
                DoneTrainingHandler.needUpdateOnMobileClient(enterRequestMessage.getDoneTrainingLastUpdateDate(), user),
                DoneWorkoutHandler.needUpdateOnMobileClient(enterRequestMessage.getDoneWorkoutLastUpdateDate(),user),
                EquipmentHandler.needUpdateOnMobileClient(enterRequestMessage.getEquipmentLastUpdateDate(), user),
                EquipmentTypeHandler.needUpdateOnMobileClient(enterRequestMessage.getEquipmentTypeLastUpdateDate()),
                MuscleHandler.needUpdateOnMobileClient(enterRequestMessage.getMuscleLastUpdateDate()),
                MuscleGroupHandler.needUpdateOnMobileClient(enterRequestMessage.getMuscleGroupLastUpdateDate()),
                RelationWorkoutMuscleHandler.needUpdateOnMobileClient(enterRequestMessage.getRelationWorkoutMuscleLastUpdateDate()),
                TrainingHandler.needUpdateOnMobileClient(enterRequestMessage.getTrainingLastUpdateDate(), user),
                TrainingSetHandler.needUpdateOnMobileClient(enterRequestMessage.getTrainingSetLastUpdateDate(), user),
                TrainingWorkoutHandler.needUpdateOnMobileClient(enterRequestMessage.getTrainingWorkoutLastUpdateDate(), user),
                UserWeightHandler.needUpdateOnMobileClient(enterRequestMessage.getUserWeightLastUpdateDate(), user),
                WorkoutHandler.needUpdateOnMobileClient(enterRequestMessage.getWorkoutLastUpdateDate(), user),

                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false
        );
    }

    public static boolean isUserNotExist(User user) {
        return UserHandler.getUserId(user) == -1;
    }

}
