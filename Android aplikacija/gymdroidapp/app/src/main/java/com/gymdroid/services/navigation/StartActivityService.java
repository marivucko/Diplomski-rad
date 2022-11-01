package com.gymdroid.services.navigation;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.activities.AddEquipmentActivity;
import com.gymdroid.activities.AddPracticeActivity;
import com.gymdroid.activities.AddUserWeightActivity;
import com.gymdroid.activities.CreateNewWorkoutActivity;
import com.gymdroid.activities.DetailsDoneWorkoutActivity;
import com.gymdroid.activities.MainActivity;
import com.gymdroid.activities.SplashScreenActivity;
import com.gymdroid.activities.TrainingActivity;
import com.gymdroid.activities.dialog.InfoDoneTrainingActivity;
import com.gymdroid.activities.dialog.InfoDoneWorkoutActivity;
import com.gymdroid.activities.dialog.InfoTrainingWorkoutActivity;
import com.gymdroid.activities.dialog.UserWeightDetailsActivity;
import com.gymdroid.activities.dialog.WorkoutHelpActivity;
import com.gymdroid.activities.error.ErrorActivity;
import com.gymdroid.activities.error.ServerIsDownActivity;
import com.gymdroid.activities.registration.LoginActivity;
import com.gymdroid.activities.training.TrainingAddFinishActivity;
import com.gymdroid.activities.training.TrainingAddInformationActivity;
import com.gymdroid.activities.training.TrainingAddWorkoutActivity;
import com.gymdroid.services.BaseService;

public class StartActivityService extends BaseService {

    public String startSplashScreenActivity(Activity activity) {
        Intent intent = new Intent(activity, SplashScreenActivity.class);
        activity.startActivity(intent);
        activity.finishAffinity();
        return SplashScreenActivity.class.getSimpleName();
    }

    public String startLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finishAffinity();
        return LoginActivity.class.getSimpleName();
    }

    public String startMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
        return MainActivity.class.getSimpleName();
    }

    public String startErrorActivity(Activity activity, String errorMessage, boolean logout) {
        Intent intent = new Intent(activity, ErrorActivity.class);
        intent.putExtra( ErrorActivity.ERROR_MESSAGE,errorMessage);
        intent.putExtra( ErrorActivity.LOGOUT,logout);
        activity.startActivity(intent);
        return ErrorActivity.class.getSimpleName();
    }

    public String startServerIsDownActivity(Activity activity) {
        Intent intent = new Intent(activity, ServerIsDownActivity.class);
        activity.startActivity(intent);
        activity.finishAffinity();
        return ServerIsDownActivity.class.getSimpleName();
    }

    public String startCreateNewWorkoutActivity(Activity activity, View view) {
        if(INSTANCE.getDatabase().getMuscleArrayList().size() > 0) {
            Intent intent = new Intent(activity, CreateNewWorkoutActivity.class);
            activity.startActivity(intent);
            return CreateNewWorkoutActivity.class.getSimpleName();
        }
        else {
            Snackbar.make(view, activity.getResources().getString( R.string.server_is_empty_no_muscle),Snackbar.LENGTH_LONG).show();
            return MainActivity.class.getSimpleName();
        }
    }

    public String startAddUserWeightActivity(Activity activity) {
        Intent intent = new Intent(activity, AddUserWeightActivity.class);
        activity.startActivity(intent);
        return AddUserWeightActivity.class.getSimpleName();
    }

    public String startAddEquipmentActivity(Activity activity, View view) {
        if(INSTANCE.getDatabase().getEquipmentTypeArrayList().size() > 0) {
            Intent intent = new Intent(activity, AddEquipmentActivity.class);
            activity.startActivity(intent);
            return AddEquipmentActivity.class.getSimpleName();
        }
        else {
            Snackbar.make(view, activity.getResources().getString(R.string.server_is_empty_no_equipment_type),Snackbar.LENGTH_LONG).show();
            return MainActivity.class.getSimpleName();
        }
    }

    public String startAddPracticeActivity(Activity activity, View view, int muscleGroupId) {
        if(INSTANCE.getDatabase().getWorkoutArrayList().size() > 0) {
            Intent intent = new Intent(activity, AddPracticeActivity.class);
            intent.putExtra( AddPracticeActivity.MUSCLE_GROUP_ID,muscleGroupId);
            activity.startActivity(intent);
            return AddPracticeActivity.class.getSimpleName();
        }
        else {
            Snackbar.make(view, activity.getResources().getString(R.string.server_is_empty_no_workout),Snackbar.LENGTH_LONG).show();
            return MainActivity.class.getSimpleName();
        }
    }

    public String startUserWeightDetailsActivity(Activity activity, int index) {
        Intent intent = new Intent(activity, UserWeightDetailsActivity.class);
        intent.putExtra( UserWeightDetailsActivity.INDEX, index);
        activity.startActivity(intent);
        return UserWeightDetailsActivity.class.getSimpleName();
    }

    public String startWorkoutHelpActivity(Activity activity) {
        Intent intent = new Intent(activity, WorkoutHelpActivity.class);
        activity.startActivity(intent);
        return WorkoutHelpActivity.class.getSimpleName();
    }

    public String startInfoDoneTrainingActivity(Activity activity, int trainingId) {
        Intent intent = new Intent(activity, InfoDoneTrainingActivity.class);
        intent.putExtra("TRAINING_ID", trainingId);
        activity.startActivity(intent);
        return InfoDoneTrainingActivity.class.getSimpleName();
    }

    public String startInfoTrainingWorkoutActivity(Activity activity, int trainingWorkoutId) {
        Intent intent = new Intent(activity, InfoTrainingWorkoutActivity.class);
        intent.putExtra("TRAINING_WORKOUT_ID", trainingWorkoutId);
        activity.startActivity(intent);
        return InfoTrainingWorkoutActivity.class.getSimpleName();
    }

    public String startDetailsDoneWorkoutActivity(Activity activity, int workoutId) {
        Intent intent = new Intent(activity, DetailsDoneWorkoutActivity.class);
        intent.putExtra( DetailsDoneWorkoutActivity.WORKOUT_ID, workoutId);
        activity.startActivity(intent);
        return DetailsDoneWorkoutActivity.class.getSimpleName();
    }

    public String startInfoDoneWorkoutActivity(Activity activity, int workoutId) {
        Intent intent = new Intent(activity, InfoDoneWorkoutActivity.class);
        intent.putExtra( InfoDoneWorkoutActivity.WORKOUT_ID, workoutId);
        activity.startActivity(intent);
        return InfoDoneWorkoutActivity.class.getSimpleName();
    }

    public String startTrainingInformationActivity(Activity activity) {
        Intent intent = new Intent(activity, TrainingAddInformationActivity.class);
        activity.startActivity(intent);
        return TrainingAddInformationActivity.class.getSimpleName();
    }

    public String startTrainingAddWorkoutActivity(Activity activity) {
        Intent intent = new Intent(activity, TrainingAddWorkoutActivity.class);
        activity.startActivity(intent);
        return TrainingAddWorkoutActivity.class.getSimpleName();
    }

    public String startTrainingAddFinishActivity(Activity activity) {
        Intent intent = new Intent(activity, TrainingAddFinishActivity.class);
        activity.startActivity(intent);
        return TrainingAddFinishActivity.class.getSimpleName();
    }

    public String startTrainingActivity(Activity activity, int trainingId) {
        Intent intent = new Intent(activity, TrainingActivity.class);
        intent.putExtra( TrainingActivity.TRAINING_ID, trainingId);
        activity.startActivity(intent);
        return TrainingActivity.class.getSimpleName();
    }

    public String startMainActivityWorkoutFragment(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("STARTER_FRAGMENT", 0);
        activity.startActivity(intent);
        activity.finish();
        return MainActivity.class.getSimpleName();
    }

    public String startMainActivityWeightFragment(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("STARTER_FRAGMENT", 2);
        activity.startActivity(intent);
        activity.finish();
        return MainActivity.class.getSimpleName();
    }

    public String startMainActivityEquipmentFragment(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("STARTER_FRAGMENT", 3);
        activity.startActivity(intent);
        activity.finish();
        return MainActivity.class.getSimpleName();
    }

}
