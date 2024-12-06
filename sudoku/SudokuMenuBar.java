package sudoku;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuMenuBar extends JMenuBar {
    private Sudoku sudoku;

    public SudokuMenuBar(Sudoku sudoku) {
        this.sudoku = sudoku;

        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudoku.resetGame();
            }
        });
        fileMenu.add(newGameItem);

        JMenuItem resetGameItem = new JMenuItem("Reset Game");
        resetGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudoku.resetGame();
            }
        });
        fileMenu.add(resetGameItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);

        add(fileMenu);

        JMenu optionsMenu = new JMenu("Options");
        JMenuItem easyItem = new JMenuItem("Easy");
        easyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudoku.setDifficulty("Easy");
            }
        });
        optionsMenu.add(easyItem);

        JMenuItem intermediateItem = new JMenuItem("Intermediate");
        intermediateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudoku.setDifficulty("Intermediate");
            }
        });
        optionsMenu.add(intermediateItem);

        JMenuItem difficultItem = new JMenuItem("Difficult");
        difficultItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sudoku.setDifficulty("Difficult");
            }
        });
        optionsMenu.add(difficultItem);

        add(optionsMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(sudoku, "Sudoku Game\nVersion 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutItem);

        add(helpMenu);
    }
}
