package com.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora;
            calculadora = new Calculadora();
            calculadora.pack();
            calculadora.setVisible(true);
            calculadora.setSize(450, 500);
            
        });
    }
}