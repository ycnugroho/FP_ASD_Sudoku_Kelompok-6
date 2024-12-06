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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JDialog {
    public Welcome(JFrame parent) {
        super(parent, "Welcome to Sudoku", true);
        
        // Create a JLabel for the welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the Sudoku Game!", SwingConstants.CENTER);
        
        // Create an "Ok" button
        JButton okButton = new JButton("Ok");
        
        // Add an ActionListener to the button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog when the button is clicked
            }
        });
        
        setLayout(new java.awt.BorderLayout());
        add(welcomeLabel, java.awt.BorderLayout.CENTER);
        add(okButton, java.awt.BorderLayout.SOUTH); 
        

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}