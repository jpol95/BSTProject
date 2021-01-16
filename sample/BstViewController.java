package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class BstViewController {

    public AnchorPane onScreen;
    public Text isBST;
    private Model m;
    private Renderer renderer;
    private ArrayList<Node> selectedTraversal;
    private int stepIndex;
    private CircleHandler cHandler;


    public void initData(String bstInput){
        stepIndex = -1;
        m.setBSTTree(bstInput);
        cHandler = new CircleHandler();
        renderer = new Renderer(m.getRoot());
        putOnScreen();
    }
    public BstViewController(){
        m = new Model();
    }

    private void updateIsBST(){
       ArrayList<Node> inOrderList = m.getBSTTree().inOrder(m.getRoot());
       int tracker = Integer.MIN_VALUE;
       boolean isBst = true;
       for (Node n: inOrderList){
           if (n.value < tracker){
               isBst = false;
               break;
           }
           tracker = n.value;
       }
        if (isBst) {
            isBST.setFill(Color.GREEN);
            isBST.setText("This is a BST");
        }
        else {
            isBST.setFill(Color.RED);
            isBST.setText("This is not a BST");
        }
        //
    }

    public void changeTree(){
        onScreen.getChildren().removeIf(n -> n instanceof Group);
        renderer = new Renderer(m.getRoot());
        putOnScreen();
        updateIsBST();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    private void putOnScreen(){
        for (Group g: renderer.getOnScreenChars()){
           if (g.getChildren().get(0) instanceof StackPane) cHandler.addListeners(g, onScreen, this);
            onScreen.getChildren().addAll(g);
        }
        updateIsBST();
    }

    public void toggleButton(ActionEvent actionEvent) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String selectedTraversalString = processRadioButton(((RadioButton) actionEvent.getSource()).getText());
        for (Circle c: renderer.getCircMap().values()) c.setFill(Color.WHITE);
        stepIndex = -1;
        selectedTraversal = (ArrayList<Node>) m.getBSTTree().getClass().getDeclaredMethod(selectedTraversalString, Node.class).invoke(m.getBSTTree(), m.getRoot());
    }

    private String processRadioButton(String s){
        String result = "";
        result += s.substring(0,1).toLowerCase();
        result += s.substring(1).replace("-", "");
        return result;
    }


    public void stepBackward(ActionEvent actionEvent) {
        if (stepIndex > 0) stepIndex--;
        else return;
        for (Circle c: renderer.getCircMap().values()) c.setFill(Color.WHITE);
        renderer.getCircMap().get(selectedTraversal.get(stepIndex)).setFill(Color.YELLOW);

    }

    public void stepForward(ActionEvent actionEvent) {
        if (stepIndex < selectedTraversal.size() - 1) stepIndex++;
        for (Circle c: renderer.getCircMap().values()) c.setFill(Color.WHITE);
        renderer.getCircMap().get(selectedTraversal.get(stepIndex)).setFill(Color.YELLOW);

    }


    public void createNewTree(ActionEvent actionEvent) throws IOException {
        Parent homeScreen = FXMLLoader.load(getClass().getResource("homeview.fxml"));
        Stage window = (Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
        Scene homeScene = new Scene(homeScreen);
        window.setScene(homeScene);
        window.show();
    }

    public void addNode(ActionEvent actionEvent) throws IOException {
        new AddNodeAlert().display(m.getRoot());
        changeTree();
    }

    public void deleteNode(ActionEvent actionEvent) throws IOException {
        new DeleteNodeAlert().display(m.getRoot());
        changeTree();
    }
}

//10 7 6 5 x x x 9 x x 15 13 x 14 x x 16 x x

//8 6 4 x x 7 x x 10 9 x x 11 x x