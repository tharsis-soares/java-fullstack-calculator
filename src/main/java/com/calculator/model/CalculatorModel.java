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

        
    }


}