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

import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class StatusBar extends JPanel {
    private JTextField statusBar;

    public StatusBar() {
        setLayout(new BorderLayout());
        statusBar = new JTextField("Selamat datang digame sudoku, selamat bermain!!!");
        statusBar.setEditable(false);
        add(statusBar, BorderLayout.CENTER);
    }

    public void updateStatusBar(int[][] puzzle) {
        int remainingCells = 0;
        for (int row = 0; row < puzzle.length; row++) {
            for (int col = 0; col < puzzle[row].length; col++) {
                if (puzzle[row][col] == 0) {
                    remainingCells++;
                }
            }
        }
        statusBar.setText("Cells remaining: " + remainingCells);
    }
}

