package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    static final int MAIN_WIDTH = 812;
    static final int MAIN_HEIGHT = 573;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("homeview.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, MAIN_WIDTH, MAIN_HEIGHT));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
