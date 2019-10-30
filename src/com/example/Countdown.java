package com.example;

import com.example.gui.GameForm;

import java.util.Timer;
import java.util.TimerTask;

abstract class Countdown {

    private final static int timeTotal = 30;
    private static int timeLeft;

    static void startCountdown(GameForm gameForm) {
        changeTimeLeftLabel(gameForm);
        timeLeft = timeTotal;
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
        return timeTotal;
    }
}