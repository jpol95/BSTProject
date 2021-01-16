package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AddNodeAlert {
    private final Double RADIOBUTTON_YPOS = 290.0;
    private final Double RADIOBUTTONLEFT_XPOS = 240.0;
    private final Double RADIOBUTTONRIGHT_XPOS = 320.0;
    private final Double SUBMITX = 270.0;
    private final Double SUBMITY = 320.0;
    private final Double QUERYX = 120.0;
    private final Double QUERYY = 270.0;

    @FXML
    ComboBox<Node> choices;
    @FXML
    public Button makeLeft;
    @FXML
    public Button makeRight;
    @FXML
    public TextField newNodeEntry;

    String action;
    Stage addNode;
    Pane addNotePane;
    Integer newNode;
    Node selected;

    String display(Node root) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("add-node.fxml"));
        loader.setController(this);
        addNotePane = loader.load();
        addNode = new Stage();
        addNode.initModality(Modality.APPLICATION_MODAL);
        Scene nodePathScene = new Scene(addNotePane);
        addNode.setScene(nodePathScene);
        addNode.setTitle("Please choose how you would like to proceed");
        processComboBox(root);
        setButtonActions();
        addNode.showAndWait();
        return action;
    }

    private void processComboBox(Node root){
        loadComboBoxItems(root);
        choices.getSelectionModel().selectFirst();
        selected = choices.getSelectionModel().getSelectedItem();
        System.out.print("hello");
        choices.setOnAction(e -> {
            selected = choices.getSelectionModel().getSelectedItem();
        });
    }

    private void loadComboBoxItems(Node targetNode){
        if (targetNode == null) return;
        choices.getItems().add(targetNode);
        loadComboBoxItems(targetNode.left);
        loadComboBoxItems(targetNode.right);
    }

    void setButtonActions() {
        makeLeft.setOnAction(e -> {
            action = "makeLeft";
            if (selected.left != null) makeRadioButtons();
            else{
                action += " none";
                try {
                    new TreeEditor().chooseClass(new Node(newNode), selected, action.split(" ") );
                } catch (NoSuchMethodException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
                addNode.close();
            }
        });
        makeRight.setOnAction(e -> {
            action = "makeRight";
            if (selected.right != null) makeRadioButtons();
            else {
                action += " none";
                try {
                    new TreeEditor().chooseClass(new Node(newNode), selected, action.split(" ") );
                } catch (NoSuchMethodException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
                addNode.close();
            }
        });

        newNodeEntry.textProperty().addListener(( obs, oldText, newText) ->{
//            System.out.print(newText);
            newNode = Integer.parseInt(newText);
        }); ;
    }

    private void makeRadioButtons() {
        ToggleGroup replace = new ToggleGroup();
        RadioButton left = new RadioButton("Left");
        RadioButton right = new RadioButton("Right");
        Button submit = new Button("Submit");
        submit.setLayoutX(SUBMITX);
        submit.setLayoutY(SUBMITY);
        submit.setOnAction(e ->{
            try {
                new TreeEditor().chooseClass(new Node(newNode), selected, action.split(" ") );
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            } catch (InvocationTargetException invocationTargetException) {
                invocationTargetException.printStackTrace();
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }

            addNode.close();
        });
        left.setLayoutX(RADIOBUTTONLEFT_XPOS);
        left.setLayoutY(RADIOBUTTON_YPOS);
        left.setToggleGroup(replace);
        left.setOnAction(e -> {
            action = action.split(" ")[0] + " left";
        });
        right.setLayoutX(RADIOBUTTONRIGHT_XPOS);
        right.setLayoutY(RADIOBUTTON_YPOS);
        right.setToggleGroup(replace);
        right.setOnAction(e -> {
            action = action.split(" ")[0] + " right";
        });
        Text query = new Text("Do you want the subtree you're replacing to become a left or right child?");
        query.setLayoutX(QUERYX);
        query.setLayoutY(QUERYY);
        addNotePane.getChildren().addAll(left, right, query, submit);
    }

}

//10 7 6 5 x x x 9 x x 15 13 x 14 x x 16 x x

//8 6 4 x x 7 x x 10 9 x x 11 x x

