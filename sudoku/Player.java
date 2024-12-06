/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #1
 * 1 - 5026231030 - Jonathan Abimanyu Trisno
 * 2 - 5026231032 - Yokanan Prawira Nugroho
 * 3 - 5026231133 - Muhammad Rifqi Alfareza Santosa
 */

package sudoku;

public class Player {
    private String name;
    private int highScore;

    public Player(String name) {
        this.name = name;
        this.highScore = 0; // Initialize high score to 0
    }

    public String getName() {
        return name;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int score) {
        if (score > highScore) {
            highScore = score; // Update high score if the new score is higher
        }
    }
}
