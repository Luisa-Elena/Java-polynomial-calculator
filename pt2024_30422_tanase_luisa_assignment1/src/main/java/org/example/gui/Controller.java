package org.example.gui;

import org.example.logic.Operations;
import org.example.model.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private View view;

    private Operations operations;

    public Controller(View v){
        this.view = v;
        operations = new Operations();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if(command == "COMPUTE"){

            try {
                String operation = String.valueOf(view.getOperationsComboBox().getSelectedItem());

                Polynomial p1 = Polynomial.parsePolynomial(view.getFirstPolynomial());
                Polynomial p2 = new Polynomial();
                if(!operation.equals("Derivative") && !operation.equals("Integration")) {
                    p2 = Polynomial.parsePolynomial(view.getSecondPolynomial());
                }

                String result = null;

                switch (operation) {
                    case "Add":
                        result = operations.add(p1, p2).polynomialToString();
                        break;
                    case "Subtract":
                        result = operations.subtract(p1, p2).polynomialToString();
                        break;
                    case "Multiply":
                        result = operations.multiply(p1, p2).polynomialToString();
                        break;
                    case "Divide":
                        result = operations.divide(p1, p2);
                        break;
                    case "Derivative":
                        result = operations.differentiation(p1);
                        break;
                    case "Integration":
                        result = operations.integrate(p1);
                        break;
                }
                view.getResultValueLabel().setText(String.valueOf(result));
            } catch (Exception err) {
                view.getResultValueLabel().setText("Invalid input");
            }
        }
    }
}
