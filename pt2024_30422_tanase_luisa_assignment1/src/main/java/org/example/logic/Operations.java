package org.example.logic;

import org.example.model.Polynomial;

public class Operations {
    private static boolean isZeroPolynomial(Polynomial p) {
        for (Number coefficient : p.getCoefficients().values()) {
            if (coefficient.doubleValue() != 0.0) {
                return false;
            }
        }
        return true;
    }
    public static Polynomial add (Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();

        // Add terms from p1
        for (int exponent : p1.getCoefficients().keySet()) {
            double coefficient = p1.getCoefficient(exponent);
            res.addTerm(exponent, coefficient);
        }

        // Add terms from p2
        for (int exponent : p2.getCoefficients().keySet()) {
            double coefficient = p2.getCoefficient(exponent);
            double existingCoefficient = res.getCoefficient(exponent);
            if(existingCoefficient + coefficient != 0) {
                res.addTerm(exponent, existingCoefficient + coefficient);
            } else {
                res.getCoefficients().remove(exponent);
            }
        }

        return res;
    }

    public static Polynomial subtract (Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();

        // Add terms from p1
        for (int exponent : p1.getCoefficients().keySet()) {
            double coefficient = p1.getCoefficient(exponent);
            res.addTerm(exponent, coefficient);
        }

        // Substract terms from p2
        for (int exponent : p2.getCoefficients().keySet()) {
            double coefficient = p2.getCoefficient(exponent);
            double existingCoefficient = res.getCoefficient(exponent);
            if(existingCoefficient - coefficient != 0) {
                res.addTerm(exponent, existingCoefficient - coefficient);
            } else {
                res.getCoefficients().remove(exponent);
            }
        }

        return res;
    }

    public static Polynomial multiply (Polynomial p1, Polynomial p2) {
        Polynomial res = new Polynomial();

        for (int exp1 : p1.getCoefficients().keySet()) {
            double coef1 = p1.getCoefficient(exp1);
            for (int exp2 : p2.getCoefficients().keySet()) {
                double coef2 = p2.getCoefficient(exp2);
                int newExp = exp1 + exp2;
                double newCoef = coef1 * coef2;
                double existingCoef = res.getCoefficient(newExp);
                res.addTerm(newExp, existingCoef + newCoef);
            }
        }

        return res;
    }

    public static String divide (Polynomial p1, Polynomial p2) {
        if(isZeroPolynomial(p2)) {
            return "Cannot divide by zero";
        } else {
            Polynomial quotient = new Polynomial();
            Polynomial remainder = p1;

            while (!remainder.getCoefficients().isEmpty() && remainder.degree() >= p2.degree()) {
                int expDifference = remainder.degree() - p2.degree();
                double leadingCoefficient = remainder.getCoefficient(remainder.degree()) / p2.getCoefficient(p2.degree());

                //System.out.println("expDiff=" + expDifference + "  leadCoeff=" + leadingCoefficient);

                Polynomial term = new Polynomial();
                term.addTerm(expDifference, leadingCoefficient);
                quotient.addTerm(expDifference, leadingCoefficient);
                //System.out.println("r1=" + remainder.polynomialToString());
                remainder = subtract(remainder, multiply(term, p2));
                //System.out.println("r2=" + remainder.polynomialToString());
            }

            //System.out.println("done");
            return "Q = " + quotient.polynomialToString() + " and R = " + remainder.polynomialToString();
        }
    }

    public static String differentiation (Polynomial p) {
        Polynomial res = new Polynomial();
        for (int exponent : p.getCoefficients().keySet()) {
            if (exponent != 0) {
                double derivativeCoefficient = p.getCoefficient(exponent) * exponent;
                int derivativeExponent = exponent - 1;
                res.addTerm(derivativeExponent, derivativeCoefficient);
            }
        }

        return res.polynomialToString();
    }

    public static String integrate (Polynomial p) {
        Polynomial res = new Polynomial();

        for (int exponent : p.getCoefficients().keySet()) {
            double integralCoefficient = p.getCoefficient(exponent) / (exponent + 1);
            int integralExponent = exponent + 1;
            res.addTerm(integralExponent, integralCoefficient);
        }

        return res.polynomialToString() + " + C";
    }
}
