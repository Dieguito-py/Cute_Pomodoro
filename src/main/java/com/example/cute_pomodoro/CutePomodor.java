package com.example.cute_pomodoro;

import com.example.cute_pomodoro.FocusController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class CutePomodor {
    @FXML
    private TextField focusTimeField;

    @FXML
    private TextField breakTimeField;

    @FXML
    private void startPomodoro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("foco.fxml"));
            Scene scene = new Scene(loader.load());

            FocusController controller = loader.getController();
            int focusTime = Integer.parseInt(focusTimeField.getText());
            int breakTime = Integer.parseInt(breakTimeField.getText());
            controller.initTimes(focusTime, breakTime);

            Stage stage = (Stage) focusTimeField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
