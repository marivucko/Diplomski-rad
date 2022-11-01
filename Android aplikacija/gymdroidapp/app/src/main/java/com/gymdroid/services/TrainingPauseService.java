package com.gymdroid.services;

import android.widget.TextView;

import com.gymdroid.activities.TrainingActivity;

public class TrainingPauseService extends BaseService {

    StartTrainingThread startTrainingThread;
    PauseTrainingThread pauseTrainingThread;

    public TrainingPauseService () {
      //  startTrainingThread = new StartTrainingThread();
      //  pauseTrainingClassThread = new PauseTrainingClassThread();
    }

    private class StartTrainingThread extends Thread {

        private boolean forceInterrupt;
        private TrainingActivity activity;
        private TextView textView;
        private int startFromCountDown;

        StartTrainingThread() {
            this.forceInterrupt = false;
        }

        public void setData(TrainingActivity activity, TextView textView, int startFromCountDown) {
            this.forceInterrupt = false;
            this.activity = activity;
            this.textView = textView;
            this.startFromCountDown = startFromCountDown;
        }

        @Override
        public void run() {
            int countDown = startFromCountDown;
            while (!forceInterrupt && countDown >= 0) {
                final int current = countDown;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(String.valueOf(current));
                    }
                });
                countDown--;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.changeToWorkoutFragment();
                }
            });
        }

        public void forceInterrupt() {
            forceInterrupt = true;
        }

    }

    private class PauseTrainingThread extends Thread {

        private boolean forceInterrupt;
        private TrainingActivity activity;
        private TextView textView;
        private long startFromCountDown;

        PauseTrainingThread() {
            this.forceInterrupt = false;
        }

        public void setData(TrainingActivity activity, TextView textView, long startFromCountDown) {
            this.forceInterrupt = false;
            this.activity = activity;
            this.textView = textView;
            this.startFromCountDown = startFromCountDown;
        }

        @Override
        public void run() {
            long countDown = startFromCountDown;
            while (!forceInterrupt && countDown >= 0) {
                final long current = countDown;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(INSTANCE.getCalendarService().millisecondsToMinuteSecondString((int) current));
                    }
                });
                countDown--;
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activity.changeToWorkoutFragment();
                }
            });
        }

        public void forceInterrupt() {
            forceInterrupt = true;
        }

    }

    public void startTrainingCountDown(TrainingActivity activity, TextView textView, int startFromCountDown) {
        startTrainingThread = new StartTrainingThread();
        startTrainingThread.setData(activity, textView, startFromCountDown);
        startTrainingThread.start();
    }

    public void stopTrainingCountDown(TrainingActivity activity){
        startTrainingThread.forceInterrupt();
    }

    public void startTrainingPause(TrainingActivity activity, TextView textView, long pauseDuration) {
        pauseTrainingThread = new PauseTrainingThread();
        pauseTrainingThread.setData(activity, textView, pauseDuration);
        pauseTrainingThread.start();
    }

    public void stopTrainingPause(TrainingActivity activity) {
        pauseTrainingThread.forceInterrupt();
    }

}
