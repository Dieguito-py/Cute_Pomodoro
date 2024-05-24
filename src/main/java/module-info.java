module com.example.cute_pomodoro {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.cute_pomodoro to javafx.fxml;
    exports com.example.cute_pomodoro;
}