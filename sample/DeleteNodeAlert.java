package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteNodeAlert {
    @FXML
    ComboBox<Node> choices;
    @FXML
    public Button delete;

    String action;
    Stage deleteNode;
    Pane addNotePane;
    Integer newNode;
    Node selected;

    String display(Node root) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("delete-node.fxml"));
        loader.setController(this);
        addNotePane = loader.load();
        deleteNode = new Stage();
        deleteNode.initModality(Modality.APPLICATION_MODAL);
        Scene nodePathScene = new Scene(addNotePane);
        deleteNode.setScene(nodePathScene);
        deleteNode.setTitle("Please choose how you would like to proceed");
        processComboBox(root);
        setButtonActions();
        deleteNode.showAndWait();
        return action;
    }

    void setButtonActions(){
        delete.setOnAction(e -> {
            selected.toDelete = true;
            deleteNode.close();
        });
    }
    private void processComboBox(Node root){
        loadComboBoxItems(root);
        choices.getSelectionModel().selectFirst();
        selected = choices.getSelectionModel().getSelectedItem();
        choices.setOnAction(e -> {
            selected = choices.getSelectionModel().getSelectedItem();
        });
    }

    private void loadComboBoxItems(Node targetNode){
        if (targetNode == null) return;
        if (targetNode.left == null && targetNode.right == null){
            choices.getItems().add(targetNode);
            return;
        }
        loadComboBoxItems(targetNode.left);
        loadComboBoxItems(targetNode.right);
    }


}
