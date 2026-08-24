package com.calculator.controller;

import com.calculator.model.CalculatorModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CalculatorController {
  @FXML
  private Label display;

  @FXML
  private GridPane buttonsGrid;

  @FXML
private void handleTeste() {
    System.out.println("Botão clicado!");
}

  private CalculatorModel calculatorModel;
  private boolean scientificMode;

  @FXML
  public void initialize() {
    calculatorModel = new CalculatorModel();
    configuratorButtons();
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
                calculatorModel.changeSign();
                break;
              case "√":
                calculatorModel.squareRoot();
                break;
              case "%":
                calculatorModel.percent();
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

  private void toggleScientificMode() {
     scientificMode = !scientificMode;
     updateDisplay();
  }

  private void updateDisplay() {
    if(calculatorModel.isError()) {
      display.setText("Error");
    } else {
      display.setText(calculatorModel.getActualValue().toString());
    }
  }


}