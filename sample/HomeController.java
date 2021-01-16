package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    public  javafx.scene.control.TextField bstInput;


    public void submitButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BstView.fxml"));
        Parent bstViewParent = loader.load();
        Scene bstViewScene = new Scene(bstViewParent);
        BstViewController controller = loader.getController();
        controller.initData(bstInput.getText());
        Stage window = (Stage) ((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(bstViewScene);
        window.show();
    }


}

//make render class, render class would take in scene and the tree and make the circles


//lllrlrrlrllrlrllllllrrrrllllllll

// 10 7 6 5 x x x 9 x x 15 13 x 14 x x 16 x x