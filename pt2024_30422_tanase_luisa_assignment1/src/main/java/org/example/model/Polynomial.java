package org.example.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private HashMap<Integer, Number> coefficients;

    public Polynomial() {
        coefficients = new HashMap<>();
    }

    public HashMap<Integer, Number> getCoefficients() {
        return coefficients;
    }

    public void addTerm(int exponent, Number coefficient) {
        coefficients.put(exponent, coefficient);
    }

    public double getCoefficient(int exponent) {
        return coefficients.containsKey(exponent) ? coefficients.get(exponent).doubleValue() : 0;
    }

    public int degree() {
        int maxDegree = -1;
        for (int exp : this.coefficients.keySet()) {
            if (exp > maxDegree) {
                maxDegree = exp;
            }
        }
        return maxDegree;
    }

    public static Polynomial parsePolynomial(String input) throws Exception {
        Polynomial polynomial = new Polynomial();

        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            //System.out.println(matcher.group(1));

            String term = matcher.group(1);
            Number coefficient;
            int exponent;

            if (term.contains("x")) {
                String[] parts = term.split("x\\^?"); // Split each term into coefficient and exponent
                if (parts.length > 0) {
                    if (parts[0].isEmpty() || parts[0].equals("+")) {
                        coefficient = 1.0;
                    } else if (parts[0].equals("-")) {
                        coefficient = -1.0;
                    } else {
                        if (parts[0].contains(".")) {
                            coefficient = Double.parseDouble(parts[0]);
                        } else {
                            coefficient = Integer.parseInt(parts[0]);
                        }
                    }
                    if (parts.length > 1) {
                        if(term.contains("^")) {
                            exponent = Integer.parseInt(parts[1]);
                        } else { //something after x, without being its exponent
                            throw new Exception();
                        }
                    } else {
                        exponent = 1;
                    }
                } else {
                    coefficient = Integer.parseInt("1");
                    exponent = 1;
                }
                if (term.indexOf("x") != term.lastIndexOf("x")) { //multiple variables
                    throw new Exception();
                }
            } else {
                if (term.contains(".")) {
                    coefficient = Double.parseDouble(term);
                } else {
                    coefficient = Integer.parseInt(term);
                }
                exponent = 0;
            }
            polynomial.addTerm(exponent, coefficient);
        }

        return polynomial;
    }

    public String polynomialToString() {
        //sort the monomials in descending order by exponent
        TreeMap<Integer, Number> sortedMap = new TreeMap<>(Collections.reverseOrder());
        sortedMap.putAll(this.coefficients);

        StringBuilder result = new StringBuilder();
        for (int exponent : sortedMap.keySet()) {
            Number coefficient = sortedMap.get(exponent);
            if (result.length() != 0 && coefficient.doubleValue() > 0) {
                result.append("+");
            }
            if (coefficient.doubleValue() < 0) {
                result.append("-");
                coefficient = -coefficient.doubleValue();
            }
            if(coefficient.doubleValue() != 0) {
                if(coefficient.doubleValue() == coefficient.intValue()) {
                    result.append(coefficient.intValue()).append("x^").append(exponent);
                } else {
                    result.append(coefficient.doubleValue()).append("x^").append(exponent);
                }
            }
        }
        if(result.isEmpty()) {
            return "0";
        } else {
            return result.toString();
        }
    }

}