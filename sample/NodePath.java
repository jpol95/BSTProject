package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NodePath {
    @FXML
    public Button replace;
    @FXML
    public Button makeLeft;
    @FXML
    public Button makeRight;
    String action;
    Stage nodePath;
    Pane nodePathPane;

    String display(Node targetNode) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("choicebox.fxml"));
        loader.setController(this);
        nodePathPane = loader.load();
        nodePath = new Stage();
        nodePath.initModality(Modality.APPLICATION_MODAL);
        Scene nodePathScene = new Scene(nodePathPane);
        nodePath.setScene(nodePathScene);
        nodePath.setTitle("Please choose how you would like to proceed");
        setButtonActions(targetNode);
        nodePath.showAndWait();
        return action;
    }

    void setButtonActions(Node targetNode) {
        replace.setOnAction(e -> {
            action = "replace";
            makeRadioButtons();
//            nodePath.close();
        });
        makeLeft.setOnAction(e -> {
            action = "makeLeft";
            if (targetNode.left != null) makeRadioButtons();
            else{
                action += " none";
                nodePath.close();
            }
        });
        makeRight.setOnAction(e -> {
            action = "makeRight";
            if (targetNode.right != null) makeRadioButtons();
            else {
                action += " none";
                nodePath.close();
            }
        });
    }

    private void makeRadioButtons() {
        ToggleGroup replace = new ToggleGroup();
        RadioButton left = new RadioButton("Left");
        RadioButton right = new RadioButton("Right");
        Button submit = new Button("Submit");
        submit.setLayoutX(270);
        submit.setLayoutY(250);
        submit.setOnAction(e -> nodePath.close());
        left.setLayoutX(240.0);
        left.setLayoutY(220);
        left.setToggleGroup(replace);
        left.setOnAction(e -> {
            action = action.split(" ")[0] + " left";
        });
        right.setLayoutX(320);
        right.setLayoutY(220);
        right.setToggleGroup(replace);
        right.setOnAction(e -> {
            action = action.split(" ")[0] + " right";
        });
        Text query = new Text("Do you want the subtree you're replacing to become a left or right child?");
        query.setLayoutX(140.0);
        query.setLayoutY(200.0);
        nodePathPane.getChildren().addAll(left, right, query, submit);
    }

}
//10 7 6 5 x x x 9 x x 15 13 x 14 x x 16 x x