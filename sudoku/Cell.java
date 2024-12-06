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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;

public class Cell extends JTextField {
   private static final long serialVersionUID = 1L;

   public static final Color BG_GIVEN = new Color(240, 240, 240);
   public static final Color FG_GIVEN = Color.BLACK;
   public static final Color FG_NOT_GIVEN = Color.GRAY;
   public static final Color BG_TO_GUESS  = Color.YELLOW;
   public static final Color BG_CORRECT_GUESS = new Color(0, 216, 0);
   public static final Color BG_WRONG_GUESS   = new Color(216, 0, 0);
   public static final Font FONT_NUMBERS = new Font("OCR A Extended", Font.PLAIN, 28);

   int row, col;
   int number;
   CellStatus status;

   public Cell(int row, int col) {
      super();
      this.row = row;
      this.col = col;
      super.setHorizontalAlignment(JTextField.CENTER);
      super.setFont(FONT_NUMBERS);
   }

   public void newGame(int number, boolean isGiven) {
      this.number = number;
      status = isGiven ? CellStatus.GIVEN : CellStatus.TO_GUESS;
      paint();  // Kembalikan ke nama metode asli
   }

   public void paint() {
      if (status == CellStatus.GIVEN) {
         super.setText(number + "");
         super.setEditable(false);
         super.setBackground(BG_GIVEN);
         super.setForeground(FG_GIVEN);
      } else if (status == CellStatus.TO_GUESS) {
         super.setText("");
         super.setEditable(true);
         super.setBackground(ThemeManager.getCellBackground());
         super.setForeground(ThemeManager.getCellForeground());
      } else if (status == CellStatus.CORRECT_GUESS) {
         super.setBackground(BG_CORRECT_GUESS);
      } else if (status == CellStatus.WRONG_GUESS) {
         super.setBackground(BG_WRONG_GUESS);
      }
   }

   @Override
   protected void paintComponent(Graphics g) {
      // Perbarui warna latar belakang dan warna teks sesuai tema saat menggambar
      if (status == CellStatus.TO_GUESS) {
         setBackground(ThemeManager.getCellBackground());
         setForeground(ThemeManager.getCellForeground());
      }
      
      super.paintComponent(g);
   }
}