package sample;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CircleHandler {
    Map<Circle, Node> nodeMap;
    BstViewController bstViewController;
    AnchorPane scene;

    public void addListeners(Group g, AnchorPane scene, BstViewController bstViewController) {
        this.bstViewController = bstViewController;
        this.nodeMap = bstViewController.getRenderer().getNodeMap();
        this.scene = scene;
        for (int i = 0; i < g.getChildren().size(); i++) {
            addEventListeners(((StackPane) g.getChildren().get(i)), scene);
        }
    }

    public void addEventListeners(StackPane c, AnchorPane scene) {
        dragDetected(c);
        dragOver(c);
        dragDrop(c);
    }

    private void dragDetected(StackPane c) {
        c.setOnDragDetected(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent mouseEvent) {
                                    Node gestureNode = nodeMap.get((Circle) ((StackPane) mouseEvent.getSource()).getChildren().get(0));
                                    if ( gestureNode.left != null || gestureNode.right != null) {
                                        try {
                                            new DraggedHasChild().display();
                                            return;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    Dragboard db = c.startDragAndDrop(TransferMode.ANY);
                                    ClipboardContent content = new ClipboardContent();
                                    content.putString(c.getId());
                                    db.setContent(content);

                                    mouseEvent.consume();
                                }

                            }
        );
    }

    private void dragOver(StackPane c) {
        c.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
//                System.out.print("HelloString");
                Dragboard db = dragEvent.getDragboard();
                if (dragEvent.getGestureSource() != dragEvent.getSource() && db.hasString()) {
                    dragEvent.acceptTransferModes(TransferMode.ANY);
                }
                dragEvent.consume();
            }
        });
    }

    private void dragDrop(StackPane c) {
        c.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                boolean dropSuccess = db.hasString();
                if (dropSuccess) {
                    Node gestureNode = nodeMap.get((Circle) ((StackPane) dragEvent.getGestureSource()).getChildren().get(0));
                    Node targetNode = nodeMap.get((Circle) ((StackPane) dragEvent.getSource()).getChildren().get(0));
                    String[] actionArr = new String[0];
                    try {
                        actionArr = (new NodePath().display(targetNode)).split(" ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        TreeEditor treeEditor = new TreeEditor();
                        treeEditor.chooseClass(gestureNode, targetNode, actionArr);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
                bstViewController.changeTree();
                dragEvent.setDropCompleted(dropSuccess);
                dragEvent.consume();

            }
        });
    }

}

//10 7 6 5 x x x 9 x x 15 13 x 14 x x 16 x x