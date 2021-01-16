package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DraggedHasChild {

    @FXML
    Pane notifPane;

    @FXML
    Button okButton;

    void display() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dragged-has-child.fxml"));
        loader.setController(this);
        notifPane = loader.load();
        Stage dhc = new Stage();
        dhc.initModality(Modality.APPLICATION_MODAL);
        Scene nodePathScene = new Scene(notifPane);
        dhc.setScene(nodePathScene);
        dhc.setTitle("Please move leaf nodes");
        okButton.setOnAction(e -> dhc.close());
        dhc.showAndWait();
    }
}
