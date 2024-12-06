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

public enum CellStatus {
    GIVEN,         // clue, no need to guess
   TO_GUESS,      // need to guess - not attempted yet
   CORRECT_GUESS, // need to guess - correct guess
   WRONG_GUESS    // need to guess - wrong guess
      // The puzzle is solved if none of the cells have 
      //  status of TO_GUESS or WRONG_GUESS
}
