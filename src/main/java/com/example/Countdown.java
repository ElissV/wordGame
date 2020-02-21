package com.example;

import com.example.gui.GameForm;

import java.util.Timer;
import java.util.TimerTask;

class Countdown {

    private final static int TIMETOTAL = 20;
    private static int timeLeft;

    static void startCountdown(GameForm gameForm) {
        timeLeft = TIMETOTAL;
        changeTimeLeftLabel(gameForm);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                changeTimeLeftLabel(gameForm);
                if (timeLeft == 0) {
                    cancel();
                }
            }
        }, 0, 1000);
    }

    private static void changeTimeLeftLabel(GameForm gameForm) {
        String timeLeftStr = "Time: " + String.valueOf(timeLeft);
        gameForm.getTimeLeftLabel().setText(timeLeftStr);
    }

    static int getTimeTotal() {
        return TIMETOTAL;
    }

}