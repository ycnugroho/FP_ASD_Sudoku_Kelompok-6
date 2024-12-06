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
