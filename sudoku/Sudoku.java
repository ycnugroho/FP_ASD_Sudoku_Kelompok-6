package sudoku;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sudoku extends JFrame {
    private static final int GRID_SIZE = 9; // Ukuran grid Sudoku
    private static final int SUBGRID_SIZE = 3; // Ukuran sub-grid
    private static final int MAX_ERRORS = 2; // Maksimal kesalahan sebelum Game Over

    private Timer gameTimer; // Timer untuk permainan
    private JLabel timerLabel; // Label untuk menampilkan waktu
    private int secondsElapsed = 0; // Waktu yang telah berlalu (detik)
    private boolean isPaused = false; // Status pause

    private JButton pauseButton; // Tombol Pause
    private JButton resumeButton; // Tombol Resume

    private int[][] puzzle; // Puzzle saat ini
    private int[][] originalPuzzle; // Puzzle asli untuk reset
    private JTextField[][] cells; // Grid tampilan
    private StatusBar statusBar; // Status bar
    private int errorCount; // Counter kesalahan

    // Puzzle untuk masing-masing level kesulitan
    private int[][] easyPuzzle = {
            { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
            { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
            { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
            { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
            { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
            { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
            { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
            { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
            { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
    };

    private int[][] intermediatePuzzle = {
            { 0, 2, 0, 6, 0, 8, 0, 0, 0 },
            { 5, 8, 0, 0, 0, 9, 7, 0, 0 },
            { 0, 0, 0, 0, 4, 0, 0, 0, 0 },
            { 3, 7, 0, 0, 0, 0, 5, 0, 0 },
            { 6, 0, 0, 0, 0, 0, 0, 0, 4 },
            { 0, 0, 8, 0, 0, 0, 0, 1, 3 },
            { 0, 0, 0, 0, 2, 0, 0, 0, 0 },
            { 0, 0, 9, 8, 0, 0, 0, 3, 6 },
            { 0, 0, 0, 3, 0, 6, 0, 9, 0 }
    };

    private int[][] difficultPuzzle = {
            { 0, 0, 0, 0, 0, 0, 2, 0, 0 },
            { 0, 0, 0, 6, 0, 0, 0, 0, 3 },
            { 0, 7, 4, 0, 8, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 3, 0, 0, 2 },
            { 0, 8, 0, 0, 4, 0, 0, 1, 0 },
            { 6, 0, 0, 5, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 1, 0, 7, 8, 0 },
            { 5, 0, 0, 0, 0, 9, 0, 0, 0 },
            { 0, 0, 3, 0, 0, 0, 0, 0, 0 }
    };

    public Sudoku() {
        puzzle = easyPuzzle; // Default puzzle
        originalPuzzle = Utils.copyMatrix(puzzle); // Salin untuk reset
        cells = new JTextField[GRID_SIZE][GRID_SIZE];
        errorCount = 0;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        cp.add(panel, BorderLayout.CENTER);

        initGrid(panel); // Inisialisasi grid

        setJMenuBar(new SudokuMenuBar(this)); // Tambah menu bar
        statusBar = new StatusBar();
        add(statusBar, BorderLayout.SOUTH); // Tambah status bar

        // Panel untuk timer dan kontrol
        JPanel topPanel = new JPanel(new BorderLayout());
        timerLabel = new JLabel("Time: 00:00", JLabel.CENTER);
        topPanel.add(timerLabel, BorderLayout.CENTER);

        pauseButton = new JButton("Pause");
        resumeButton = new JButton("Resume");
        resumeButton.setEnabled(false); // Default, Resume dinonaktifkan

        pauseButton.addActionListener(e -> pauseGame());
        resumeButton.addActionListener(e -> resumeGame());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(pauseButton);
        buttonPanel.add(resumeButton);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        JMenu themeMenu = new JMenu("Themes");

        JMenuItem medievalTheme = new JMenuItem("Medieval");
        medievalTheme.addActionListener(e -> {
            ThemeManager.setTheme(ThemeManager.Theme.MEDIEVAL);
            applyTheme();
        });
        themeMenu.add(medievalTheme);

        JMenuItem japaneseTheme = new JMenuItem("Japanese");
        japaneseTheme.addActionListener(e -> {
            ThemeManager.setTheme(ThemeManager.Theme.JAPANESE);
            applyTheme();
        });
        themeMenu.add(japaneseTheme);

        JMenuItem modernTheme = new JMenuItem("Modern");
        modernTheme.addActionListener(e -> {
            ThemeManager.setTheme(ThemeManager.Theme.MODERN);
            applyTheme();
        });
        themeMenu.add(modernTheme);

        getJMenuBar().add(themeMenu);

        // Timer
        gameTimer = new Timer(1000, (ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++;
                updateTimerLabel();
            }
        });
        gameTimer.start();

        setTitle("Sudoku");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisikan di tengah layar
    }

    private void updateTimerLabel() {
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;
        timerLabel.setText(String.format("Time: %02d:%02d", minutes, seconds));
    }

    private void pauseGame() {
        if (!isPaused) {
            gameTimer.stop();
            isPaused = true;
            pauseButton.setEnabled(false);
            resumeButton.setEnabled(true);
        }
    }

    private void resumeGame() {
        if (isPaused) {
            gameTimer.start();
            isPaused = false;
            pauseButton.setEnabled(true);
            resumeButton.setEnabled(false);
        }
    }

    private void applyTheme() {
        // Update individual cell themes
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (cells[row][col] instanceof Cell) {
                    ((Cell) cells[row][col]).paint(); // Use the existing paint method
                } else {
                    // Fallback for standard JTextField
                    cells[row][col].setBackground(ThemeManager.getCellBackground());
                    cells[row][col].setForeground(ThemeManager.getCellForeground());
                }
            }
        }

        // Update status bar
        statusBar.setBackground(ThemeManager.getStatusBarBackground());
        statusBar.setForeground(ThemeManager.getStatusBarForeground());

        // Update fonts if needed
        Font themeFont = ThemeManager.getThemeFont(); // Assuming you've added this method to ThemeManager
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setFont(themeFont);
            }
        }

        // Trigger UI update
        revalidate();
        repaint();
    }

    private void initGrid(JPanel panel) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col] = new JTextField();
                panel.add(cells[row][col]);
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setEditable(false); // Default tidak bisa diedit
                if (puzzle[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(puzzle[row][col]));
                } else {
                    cells[row][col].setEditable(true);
                    cells[row][col].setForeground(Color.BLUE);
                    ((AbstractDocument) cells[row][col].getDocument()).setDocumentFilter(new SudokuInputFilter());
                    cells[row][col].addActionListener(this::handleInput);
                }
            }
        }
    }

    private void handleInput(ActionEvent e) {
        JTextField source = (JTextField) e.getSource();
        int value;
        try {
            value = Integer.parseInt(source.getText());
            source.setBackground(Color.WHITE);
        } catch (NumberFormatException ex) {
            source.setText("");
            source.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "Invalid input! Enter an integer between 1 and 9.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        Point point = Utils.getCellPosition(cells, source);
        if (Validation.isValidInput(puzzle, value, point.x, point.y)) {
            puzzle[point.x][point.y] = value;
            highlightSameValue(value);
            statusBar.updateStatusBar(puzzle);
        } else {
            source.setText("");
            source.setBackground(Color.RED);
            errorCount++;
            if (errorCount > MAX_ERRORS) {
                JOptionPane.showMessageDialog(this, "Game Over! Too many mistakes.", "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            }
        }
    }

    public void setDifficulty(String level) {
        switch (level) {
            case "Easy":
                originalPuzzle = easyPuzzle;
                break;
            case "Intermediate":
                originalPuzzle = intermediatePuzzle;
                break;
            case "Difficult":
                originalPuzzle = difficultPuzzle;
                break;
        }
        resetGame();
    }

    public void resetGame() {
        puzzle = Utils.copyMatrix(originalPuzzle);
        errorCount = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setText("");
                cells[row][col].setBackground(Color.WHITE);
                if (puzzle[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(puzzle[row][col]));
                    cells[row][col].setEditable(false);
                } else {
                    cells[row][col].setEditable(true);
                }
            }
        }
        statusBar.updateStatusBar(puzzle);
    }

    private void highlightSameValue(int number) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                cells[row][col].setBackground(puzzle[row][col] == number ? Color.YELLOW : Color.WHITE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Sudoku().setVisible(true));
    }

}
