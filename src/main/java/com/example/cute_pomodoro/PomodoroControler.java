package com.example.cute_pomodoro;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

public class PomodoroControler {

    @FXML
    private TextField focusTimeField;

    @FXML
    private TextField breakTimeField;

    @FXML
    private Label timerLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button startButton;

    @FXML
    private Button pauseButton;

    private Timeline timeline;
    private int focusTime;
    private int breakTime;
    private int timeSeconds;
    private boolean isFocusTime = true;

    @FXML
    private void initialize() {
        focusTimeField.setText("25");
        breakTimeField.setText("5");

        startButton.setOnAction(e -> handleStartButtonAction());
        pauseButton.setOnAction(e -> handlePauseButtonAction());
    }

    private void handleStartButtonAction() {
        focusTime = Integer.parseInt(focusTimeField.getText()) * 60;
        breakTime = Integer.parseInt(breakTimeField.getText()) * 60;
        timeSeconds = focusTime;
        startTimer();
    }

    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeSeconds--;
            timerLabel.setText(formatTime(timeSeconds));
            progressBar.setProgress(1.0 - (double) timeSeconds / (isFocusTime ? focusTime : breakTime));
            if (timeSeconds <= 0) {
                timeline.stop();
                // Play alarm sound here
                isFocusTime = !isFocusTime;
                timeSeconds = isFocusTime ? focusTime : breakTime;
                startTimer();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void handlePauseButtonAction() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }
}
