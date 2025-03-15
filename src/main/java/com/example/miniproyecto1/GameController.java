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

/**
 * Controlador de la interfaz gráfica del juego .
 * Maneja la interacción entre el usuario y la logica del juego.
 */
public class GameController {
    @FXML private Label wordLabel, timerLabel, levelLabel, livesLabel, messageLabel;
    @FXML private TextField inputField;
    @FXML private Button validateButton;
    @FXML private ImageView sunImage;

    private GameModel gameModel;
    private Timeline timeline;
    private int timeLeft;

    /**
     * Inicia el juego la interfaz y el temporizador.
     */
    @FXML
    public void initialize() {
        gameModel = new GameModel();
        updateView();
        startTimer();


        inputField.setOnAction(event -> validateWord(new ActionEvent()));
    }

    /**
     * Inicia el temporizador del juego, reduciendo el tiempo cada segundo.
     */
    private void startTimer() {
        timeLeft = gameModel.getTime();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (gameModel.isGameOver()) {
                timeline.stop();
                return;
            }

            timeLeft--;
            timerLabel.setText("Tiempo: " + timeLeft + "s");
            if (timeLeft == 0) {
                handleTimeOut();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Maneja el evento cuando el tiempo se acaba.
     */
    private void handleTimeOut() {
        if (gameModel.isGameOver()) return;

        gameModel.validateWord("");
        updateView();
        if (gameModel.isGameOver()) {
            endGame();
        } else {
            timeLeft = gameModel.getTime();
        }
    }

    /**
     * Valida la palabra ingresada por el usuario al presionar "validar" o "enter".
     * @param event evento de acción del botón o la tecla "Enter".
     */
    @FXML
    private void validateWord(ActionEvent event) {
        if (gameModel.isGameOver()) return;

        String input = inputField.getText().trim();
        if (input.equalsIgnoreCase(gameModel.getCurrentWord())) {
            messageLabel.setText("Palabra correcta.");
        } else {
            messageLabel.setText("Palabra incorrecta.");
        }

        gameModel.validateWord(input);
        inputField.clear();
        updateView();

        if (gameModel.isGameOver()) {
            endGame();
        } else {
            timeLeft = gameModel.getTime();
        }
    }

    /**
     * Actualiza la interfaz con la información más reciente.
     */
    private void updateView() {
        wordLabel.setText(gameModel.getCurrentWord());
        levelLabel.setText("Nivel: " + gameModel.getLevel());
        livesLabel.setText("Vidas: " + gameModel.getLives());
        updateSunImage();
    }

    /**
     * Actualiza la imagen del sol eclipsado según las vidas .
     */
    private void updateSunImage() {
        String[] sunImages = {
                "/com/example/miniproyecto1/images/sun_100.png",
                "/com/example/miniproyecto1/images/sun_75.png",
                "/com/example/miniproyecto1/images/sun_50.png",
                "/com/example/miniproyecto1/images/sun_25.png",
                "/com/example/miniproyecto1/images/sun_0.png"
        };

        int index = Math.max(0, 4 - gameModel.getLives());
        sunImage.setImage(new Image(getClass().getResourceAsStream(sunImages[index])));
    }

    /**
     * Finaliza el juego cuando el jugador pierde todas sus vidas.
     */
    private void endGame() {
        timeline.stop();
        wordLabel.setText("...Juego Terminado...");
        messageLabel.setText("Perdiste todas tus vidas.");
        inputField.setDisable(true);
        validateButton.setDisable(true);
    }
}
