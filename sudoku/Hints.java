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

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Hints {
    public static void revealCell(int[][] puzzle, JTextField[][] cells) {
        // Find a random cell that is not given
        for (int row = 0; row < SudokuConstants.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; col++) {
                if (puzzle[row][col] == 0) { // Cell is empty
                    cells[row][col].setText(String.valueOf(puzzle[row][col])); // Reveal the cell
                    cells[row][col].setEditable(false); // Make it non-editable
                    return; // Exit after revealing one cell
                }
            }
        }
        JOptionPane.showMessageDialog(null, "No hints available!");
    }

    public static void revealNumber(int[][] puzzle, JTextField[][] cells, int number) {
        for (int row = 0; row < SudokuConstants.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuConstants.GRID_SIZE; col++) {
                if (puzzle[row][col] == number) {
                    cells[row][col].setText(String.valueOf(number)); // Reveal the number
                    cells[row][col].setEditable(false); // Make it non-editable
                }
            }
        }
    }
}
