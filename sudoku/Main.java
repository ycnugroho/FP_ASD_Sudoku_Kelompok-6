package sudoku;

import javax.swing.SwingUtilities;

/** The entry main() entry method */
public class Main{
    public static void main(String[] args) {
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Sudoku frame = new Sudoku();
                frame.setVisible(true);
            }
        });
    }
}
        
