/*package com.example.miniproyecto1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}*/
package com.example.miniproyecto1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GameController {
    @FXML private Label wordLabel, timerLabel, levelLabel, livesLabel, messageLabel;
    @FXML private TextField inputField;
    @FXML private Button validateButton;
    @FXML private ImageView sunImage;

    private GameModel gameModel;
    private Timeline timeline;
    private int timeLeft;

    @FXML
    public void initialize() {
        gameModel = new GameModel();
        updateView();
        startTimer();
    }

    private void startTimer() {
        timeLeft = gameModel.getTime();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeLeft--;
            timerLabel.setText("Tiempo: " + timeLeft + "s");
            if (timeLeft == 0) {
                handleTimeOut();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void handleTimeOut() {
        gameModel.validateWord(""); // Penaliza si no se escribió nada
        updateView();
        if (gameModel.isGameOver()) {
            timeline.stop();
            wordLabel.setText("¡Juego Terminado!");
        } else {
            timeLeft = gameModel.getTime();
        }
    }

    @FXML
    private void validateWord(ActionEvent event) {
        String input = inputField.getText().trim();
        gameModel.validateWord(input);
        inputField.clear();
        updateView();
        if (gameModel.isGameOver()) {
            timeline.stop();
            wordLabel.setText("¡Juego Terminado!");
        } else {
            timeLeft = gameModel.getTime();
        }
    }

    private void updateView() {
        wordLabel.setText(gameModel.getCurrentWord());
        levelLabel.setText("Nivel: " + gameModel.getLevel());
        livesLabel.setText("Vidas: " + gameModel.getLives());
        updateSunImage();
    }

    /*private void updateSunImage() {
        String[] sunImages = {"/images/sun_100.png", "/images/sun_75.png", "/images/sun_50.png", "/images/sun_25.png", "/images/sun_0.png"};
        sunImage.setImage(new Image(sunImages[4 - gameModel.getLives()])); // Cambia la imagen según vidas
    }*/
    private void updateSunImage() {
        String[] sunImages = {
                "/com/example/miniproyecto1/images/sun_100.png",
                "/com/example/miniproyecto1/images/sun_75.png",
                "/com/example/miniproyecto1/images/sun_50.png",
                "/com/example/miniproyecto1/images/sun_25.png",
                "/com/example/miniproyecto1/images/sun_0.png"
        };

        int index = Math.max(0, 4 - gameModel.getLives()); // Evita errores si lives es menor a 0
        sunImage.setImage(new Image(getClass().getResourceAsStream(sunImages[index])));
    }

}
