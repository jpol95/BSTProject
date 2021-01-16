package sample;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Renderer {
    private static final Double VERTICAL_SPACING = 80.0;
    private static final Double VERTICAL_OFFSET = 150.0;
    private static final Double CIRCLE_RADIUS = 10.0;
    private final Map<Node, Circle> circMap;
    private final Map<Circle, Node> nodeMap;
    private final Group lines;
    private final Group circles;
    private final ArrayList<Group> onScreenChars;
    private final PlacementCalc shapePlacer;

    public Renderer(Node root){
        toDelete(root);
        lines = new Group();
        circles = new Group();
        onScreenChars = new ArrayList<>();
        shapePlacer = new PlacementCalc();
        circMap = new HashMap<>();
        nodeMap = new HashMap<>();
        shapePlacer.createDirections(root, "");
    }

    private void toDelete(Node root){
        if (root == null) return;
        if (root.left != null && root.left.toDelete){
            root.left = null;
        }
        if (root.right != null && root.right.toDelete) root.right = null;
        toDelete(root.left);
        toDelete(root.right);
    }


    public Map<Circle, Node> getNodeMap() {
        return nodeMap;
    }

    public ArrayList<Group> getOnScreenChars(){
        makeLines();
        makeCircles();
        onScreenChars.add(lines);
        onScreenChars.add(circles);
        return onScreenChars;
    }
    public Map<Node, Circle> getCircMap() {
        return circMap;
    }


    void makeLines(){
        for (String s: shapePlacer.getDirections().keySet()){
            for (String t: shapePlacer.getDirections().keySet()){
                int lowerLength = Math.min(s.length(), t.length());
                if (t.length() - s.length() == 1 && (t.substring(0,lowerLength).equals(s.substring(0,lowerLength)))) {
                    Double startX = shapePlacer.xPos(s)*Main.MAIN_WIDTH;
                    Double startY = s.length()*VERTICAL_SPACING + VERTICAL_OFFSET;
                    Double endX = shapePlacer.xPos(t)*Main.MAIN_WIDTH;
                    Double endY = t.length()*VERTICAL_SPACING + VERTICAL_OFFSET;
                    lines.getChildren().add(new Line(startX, startY, endX, endY));
                }
            }
        }
    }
     void makeCircles(){
        for (String s: shapePlacer.getDirections().keySet()){
            StackPane node = new StackPane();
            Circle circle = new Circle( CIRCLE_RADIUS);
            Node currentNode = shapePlacer.getDirections().get(s);
            Text input = new Text(Integer.toString(currentNode.value));
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            circle.setId(Integer.toString(currentNode.value));
            circMap.put(currentNode, circle);
            nodeMap.put(circle, currentNode);
            node.getChildren().addAll(circle, input);
            node.setLayoutX(shapePlacer.xPos(s)*Main.MAIN_WIDTH - circle.getRadius());
            node.setLayoutY(150 + s.length()*VERTICAL_SPACING - circle.getRadius());
            node.setId(node.toString());
            circles.getChildren().add(node);
        }


    }
}
