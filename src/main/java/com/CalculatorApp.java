import com.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class CalculatorApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/calculator.fxml"));
        Scene scene = new Scene(root, 300, 400);

        scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

