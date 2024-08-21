package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {

    private JPanel contentPanel;
    private JTextField firstPolynomialTextField;
    private JLabel firstPolynomialLabel;
    private JTextField secondPolynomialTextField;
    private JLabel secondPolynomialLabel;
    private JComboBox operationsComboBox;
    private JButton computeButton;
    private JLabel resultValueLabel;

    Controller controller = new Controller(this);

    public View(String name) {
        super(name);
        this.prepareGui();
        this.setContentPane(this.contentPanel);
    }

    public void prepareGui(){
        this.setSize(500, 300);

        // Initialize components
        contentPanel = new JPanel(new GridLayout(5, 2));
        firstPolynomialLabel = new JLabel("First Polynomial:");
        firstPolynomialTextField = new JTextField();
        secondPolynomialLabel = new JLabel("Second Polynomial:");
        secondPolynomialTextField = new JTextField();
        operationsComboBox = new JComboBox<>(new String[]{"Add", "Subtract", "Multiply", "Divide", "Derivative", "Integration"}); // Add operation types as needed
        computeButton = new JButton("Compute");
        resultValueLabel = new JLabel();

        // Add components to the content pane
        contentPanel.add(firstPolynomialLabel);
        contentPanel.add(firstPolynomialTextField);
        contentPanel.add(secondPolynomialLabel);
        contentPanel.add(secondPolynomialTextField);
        contentPanel.add(new JLabel("Operation:")); // Adding label for spacing
        contentPanel.add(operationsComboBox);
        contentPanel.add(computeButton);
        computeButton.setActionCommand("COMPUTE");
        computeButton.addActionListener(this.controller);
        contentPanel.add(new JLabel(""));
        contentPanel.add(new JLabel("Result:"));
        contentPanel.add(resultValueLabel);
    }

    public JComboBox getOperationsComboBox() {
        return operationsComboBox;
    }

    public JLabel getResultValueLabel() {
        return resultValueLabel;
    }

    public String getFirstPolynomial() { return firstPolynomialTextField.getText(); }

    public String getSecondPolynomial() { return secondPolynomialTextField.getText(); }
}