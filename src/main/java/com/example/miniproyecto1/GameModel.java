package com.example.miniproyecto1;

import java.util.Random;

/**
 * Representa la logica del juego "Escritura Rapida".
 * Maneja palabras aleatorias, niveles, tiempo y oportunidades del jugador.
 */

public class GameModel {
    private String[] words = {"java" , "programacion", "computador", "eventos", "ayuda","git", "interfaz","jdk", "maven", "rapido", "escritura","doc","pc","parangatirimicuaro"};
    private String currentWord;
    private int level = 1;
    private int time = 21;
    private int lives = 4;
    private Random random = new Random();

    /**
     * Constructor de GameModel.
     * Genera la primera palabra aleatoria.
     */
    public GameModel() {
        generateNewWord();
    }

    /**
     * Genera una nueva palabra aleatoria si el juego no termina aun.
     */
    public void generateNewWord() {
        currentWord = words[random.nextInt(words.length)];
    }

    /**
     * Obtiene la palabra actual que el jugador escribe.
     * @return la palabra actual.
     */
    public String getCurrentWord() {
        return currentWord;
    }

    /**
     * Obtiene el tiempo restante para escribir la palabra.
     * @return tiempo en segundos.
     */
    public int getTime() {
        return time;
    }

    /**
     * Obtiene el nivel actual del juego.
     * @return numero de nivel.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Obtiene las vidas restantes del jugador.
     * @return numero de vidas.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Valida si la palabra ingresada es correcta y actualiza el estado del juego.
     * @param input lña palabra ingresada por el usuario
     */
    public void validateWord(String input) {
        if (isGameOver()) return;

        if (input.equalsIgnoreCase(currentWord)) {
            level++;
            if (level % 5 == 0 && time > 2) {
                time = Math.max(time - 2, 3);
            }
            generateNewWord();
        } else {
            lives = Math.max(lives - 1, 0);
        }
    }
    /**
     * Verifica si el juego ha terminado.
     * @return true si el jugador no tiene vidas, false si aún puede jugar.
     */
    public boolean isGameOver() {
        return lives == 0;
    }
}