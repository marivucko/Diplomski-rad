package com.gymdroid.domain.message;

public enum StatusEnum {
    LOGIN_SUCCESS,
    LOGIN_GOOGLE_WRONG_FIREBASE_ID,
    LOGIN_EMAIL_WRONG_PASSWORD,
    LOGIN_ERROR_WRONG_TYPE,
    REGISTER_SUCCESS,
    REGISTER_ERROR_WRONG_TYPE,
    REGISTER_ERROR_USER_EXIST,
    USER_DOES_NOT_EXIST,
    SERVER_ERROR,
    WORKOUT_CREATE_SUCCESS,
    DELETE_WORKOUT_SUCCESS,
    TRAINING_CREATE_SUCCESS,
    DELETE_TRAINING_SUCCESS,
    ADD_EQUIPMENT_SUCCESS,
    DELETE_EQUIPMENT_SUCCESS,
    ADD_PRACTICE_SUCCESS,
    ADD_MULTIPLE_PRACTICES_SUCCESS,
    ADD_USER_WEIGHT_SUCCESS,
    DELETE_USER_WEIGHT_SUCCESS,
    LOGIN_LOAD_SUCCESS,
    ENTER_SUCCESS
}
