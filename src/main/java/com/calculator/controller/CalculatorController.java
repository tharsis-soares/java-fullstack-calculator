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
  private boolean scientificMode;

  @FXML
  public void initialize() {
    calculatorModel = new CalculatorModel();
    updateDisplay();
  }

  private void configuratorButtons() {
    for(var node : buttonsGrid.getChildren()) {
      if(node instanceof Button) {
        Button button = (Button) node;
        String text = button.getText();

        button.setOnAction(event -> {
          if(text.matches("[0-9]")) {
            calculatorModel.addDigit(text);
          } else {
            switch (text) {
              case ".":
                calculatorModel.addPoint();
                break;
              case "C":
                calculatorModel.clear();
                break;
              case "CE":
                calculatorModel.clearActual();
                break;
              case "+":
              case "-":
              case "*":
              case "/":
                calculatorModel.setOperator(text);
                break;
              case "=":
                calculatorModel.calculate();
                break;
              case "±":
                calculatorModel.toggleSign();
                break;
              case "√":
                calculatorModel.calculateSquareRoot();
                break;
              case "%":
                calculatorModel.calculatePercentage();
                break;
              case "Scientific":
                toggleScientificMode();
                break;
              default:
                return;
            }
          }
          updateDisplay();
        });
      }
    }
  }

  private void scientificModeAlternation() {
     scientificMode = !scientificMode;
     updateDisplay();
  }

  private void updateDisplay() {
    if(calculatorModel.isError()) {
      displayLabel.setText("Error");
    } else {
      displayLabel.setText(calculatorModel.getActualValue().toString());
    }
  }


}