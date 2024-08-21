package org.example;

import org.example.gui.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new View("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}