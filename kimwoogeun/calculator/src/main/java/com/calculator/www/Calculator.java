package com.calculator.www;

public class Calculator {

    public long calculate(long num1, String operator, long num2) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                throw new InvalidOperatorException();
        }
    }
}
