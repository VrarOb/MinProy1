package com.example.miniproyecto1;

import java.util.Random;

public class GameModel {
    private String[] words = {"java", "programacion", "desafio", "evento", "intellij"};
    private String currentWord;
    private int level = 1;
    private int time = 20; // Segundos iniciales
    private int lives = 4; // Oportunidades (Sol eclipsado)
    private Random random = new Random();

    public GameModel() {
        generateNewWord();
    }

    public void generateNewWord() {
        currentWord = words[random.nextInt(words.length)];
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public int getTime() {
        return time;
    }

    public int getLevel() {
        return level;
    }

    public int getLives() {
        return lives;
    }

    public void validateWord(String input) {
        if (input.equalsIgnoreCase(currentWord)) { // Palabra correcta
            level++;
            if (level % 5 == 0 && time > 2) {
                time -= 2; // Reducir tiempo cada 5 niveles
            }
            generateNewWord();
        } else { // Palabra incorrecta
            lives--;
        }
    }

    public boolean isGameOver() {
        return lives == 0;
    }
}