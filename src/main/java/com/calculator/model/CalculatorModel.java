package com.calculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorModel {
    private BigDecimal actualValue;
    private BigDecimal memoryValue;
    private String operator;
    private boolean newNumber;
    private boolean error;

    public CalculatorModel() {
        clear();
    }

    public void clear() {
        this.actualValue = BigDecimal.ZERO;
        this.operator = null;
        this.newNumber = true;
        this.error = false;
        this.memoryValue = BigDecimal.ZERO;
    }

    public void clearActual() {
        this.actualValue = BigDecimal.ZERO;
        this.newNumber = true;
    }

    public void addDigit(String digit) {
        if(error) {
            clear();
        }

        if(newNumber) {
            actualValue = new BigDecimal(digit);
            newNumber = false;
        } else {
          String actual = actualValue.toString();
          if(!actual.contains(".") || !digit.equals(".")) {
              actualValue = new BigDecimal(actual + digit);
          }
        }
    }

    public void addPoint() {
        if(error) {
            clear();
        }

        if(newNumber) {
            actualValue = BigDecimal.ZERO;
            newNumber = false;
        }

        String actual = actualValue.toString();
        if(!actual.contains(".")) {
            actualValue = new BigDecimal(actual + ".");
        }
    }

    public void setOperator(String operator) {

        if(this.operator != null && !newNumber) {
            calculate();
        }

        this.operator = operator;
        newNumber = true;
    }

    public void calculate() {
        if(operator == null || memoryValue == null || errnor) {
            return;
        }

        try {
          BigDecimal result;
            switch (operator) {
                case "+":
                    result = memoryValue.add(actualValue);
                    break;
                case "-":
                    result = memoryValue.subtract(actualValue);
                    break;
                case "*":
                    result = actualValue.multiply(memoryValue);
                    break;
                case "/":
                    if (actualValue.compareTo(BigDecimal.ZERO) == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    result = memoryValue.divide(actualValue, 10, RoundingMode.HALF_UP);
                    break;
                default:
                    return;
            }

            actualValue = result.stripTrailingZeros();
            memoryValue = BigDecimal.ZERO;
            operator = null;
            newNumber = true;

        } catch (ArithmeticException e) {
            error = true;
        }
    }

    public void changeSign() {
        if(!error && actualValue != null) {
            actualValue = actualValue.negate();
        }
    }

    public void percent() {
        if(!error && actualValue != null) {
            actualValue = actualValue.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        }
    }

    public void squareRoot() {
        if(!error && actualValue != null && actualValue.compareTo(BigDecimal.ZERO) >= 0) {
            double sqrtValue = Math.sqrt(actualValue.doubleValue());
            actualValue = new BigDecimal(Double.toString(sqrtValue)).stripTrailingZeros();
        }
    }

    public void square() {
        if(!error && actualValue != null) {
            actualValue = actualValue.multiply(actualValue).stripTrailingZeros();
        }
    }

    public String getActualValue() {
        if(error) {
            return "Error";
        }
        return actualValue.stripTrailingZeros().toPlainString();
    }

    public BigDecimal getActualValueAsBigDecimal() {
        return actualValue;
    }

    public boolean isError() {
        return error;
    }

}