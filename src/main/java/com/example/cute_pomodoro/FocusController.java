package com.example.cute_pomodoro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FocusController {
    @FXML
    private Label timerLabel;

    @FXML
    private ProgressBar progressBar;

    private Timeline timeline;
    private int focusTime;
    private int breakTime;
    private int timeRemaining;

    public void initTimes(int focusTime, int breakTime) {
        this.focusTime = focusTime;
        this.breakTime = breakTime;
        startFocus();
    }

    private void startFocus() {
        timeRemaining = focusTime * 60;
        startTimer();
    }

    private void startBreak() {
        timeRemaining = breakTime * 60;
        startTimer();
    }

    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeRemaining--;
            updateUI();
            if (timeRemaining <= 0) {
                timeline.stop();
                if (focusTime > 0) {
                    goToBreak();
                } else {
                    startFocus();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateUI() {
        int minutes = timeRemaining / 60;
        int seconds = timeRemaining % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        progressBar.setProgress((double) timeRemaining / (focusTime * 60));
    }

    private void goToBreak() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("descanso.fxml"));
            Scene scene = new Scene(loader.load());

            BreakController controller = loader.getController();
            controller.initBreak(breakTime);

            Stage stage = (Stage) timerLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void pauseTimer() {
        if (timeline != null) {
            timeline.pause();
        }
    }

    @FXML
    private void continueTimer() {
        if (timeline != null) {
            timeline.play();
        }
    }
}
