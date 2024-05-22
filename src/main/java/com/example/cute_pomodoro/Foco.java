package com.example.cute_pomodoro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Foco {
    @FXML
    private ImageView myImageView;

    @FXML
    private Button myButton;

    private Image initialImage;
    private Image newImage;

    public void initialize() {
        try {
            initialImage = new Image(getClass().getResource("/images/initialImage.png").toString());
            newImage = new Image(getClass().getResource("/images/newImage.png").toString());
            myImageView.setImage(initialImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleButtonClick() {
        // Alterne a imagem exibida
        if (myImageView.getImage() == initialImage) {
            myImageView.setImage(newImage);
        } else {
            myImageView.setImage(initialImage);
        }
    }
}