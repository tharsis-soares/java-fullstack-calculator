package com.calculator.controller;

import com.calculator.model.CalculatorModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalculatorController {
  @FXML
  private Label displayLabel;

  @FXML
  private GridPane buttonsGrid;

  private CalculatorModel calculatorModel;
  private boolean cientificMode;

  @FXML
  public void initialize() {
    calculatorModel = new CalculatorModel();
    updateDisplay();
  }

  private void configuratorButtons() {

  }