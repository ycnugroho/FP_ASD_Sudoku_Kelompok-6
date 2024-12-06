package sudoku;

public class Validation {
    // Metode untuk memeriksa apakah input valid
    public static boolean isValidInput(int[][] puzzle, int number, int row, int col) {
        int GRID_SIZE = puzzle.length;
        int SUBGRID_SIZE = (int) Math.sqrt(GRID_SIZE);

        // Cek duplikat di baris
        for (int c = 0; c < GRID_SIZE; c++) {
            if (c != col && puzzle[row][c] == number) {
                return false;
            }
        }
        // Cek duplikat di kolom
        for (int r = 0; r < GRID_SIZE; r++) {
            if (r != row && puzzle[r][col] == number) {
                return false;
            }
        }
        // Cek duplikat di sub-grid
        int boxRow = row / SUBGRID_SIZE * SUBGRID_SIZE;
        int boxCol = col / SUBGRID_SIZE * SUBGRID_SIZE;
        for (int r = boxRow; r < boxRow + SUBGRID_SIZE; r++) {
            for (int c = boxCol; c < boxCol + SUBGRID_SIZE; c++) {
                if ((r != row || c != col) && puzzle[r][c] == number) {
                    return false;
                }
            }
        }
        return true;
    }
}

