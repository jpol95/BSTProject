package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree {
//    private ArrayList<Node> preOrder;
//    private String postOrder;
//    private String inOrder;
//    private String bfs;
    private Node root;
    private int depth;

    public void reset(){
        root = null;
    }

    Tree(String s){
        setTree(s);
        this.depth = setDepth(root);
    }
    public Node getRoot(){
        return this.root;
    }
    public int setDepth(Node root){
        if (root == null) return 0;
        int result = 1;
        result += Integer.max(setDepth(root.left), setDepth(root.right));
        return result;
    }
    public void setTree(String s){
        String[] BSTarr = s.split(" ");
        Queue<String> BSTq = new LinkedList<String>();
        for (String node: BSTarr) BSTq.add(node);
        this.root = makeTree(BSTq);
    }
    private Node makeTree(Queue<String> s){
        String input = s.poll();
        if (input.equals("x")) return null;
        Node root = new Node(Integer.parseInt(input));
        root.left = makeTree(s);
        root.right = makeTree(s);
        return root;
    }
//10 7 6 5 x x x 9 x x 15 13 x 14 x x 16 x x
    public ArrayList<Node> preOrder(Node root){
        ArrayList<Node> result = new ArrayList<>();
        if (root == null) return result;
        result.add(root);
        result.addAll(preOrder(root.left));
        result.addAll(preOrder(root.right));
        return result;
    }
    public ArrayList<Node> inOrder(Node root){
        ArrayList<Node> result = new ArrayList<>();
        if (root == null) return result;
        result.addAll(inOrder(root.left));
        result.add(root);
        result.addAll(inOrder(root.right));
        return result;
    }

    public ArrayList<Node> postOrder(Node root){
        ArrayList<Node> result = new ArrayList<>();
        if (root == null) return result;
        result.addAll(postOrder(root.left));
        result.addAll(postOrder(root.right));
        result.add(root);
        return result;
    }
    public String bfs(Node root){
        String result = "";
        for (int i = 0; i < this.depth; i++){
            result += bfsHelper(root, i);
        }
        return result;
    }

    public String bfsHelper(Node root, int level){
        if (root == null) return "x ";
        String result = "";
        if (level > 0) {
            result += bfsHelper(root.left, level - 1);
            result += bfsHelper(root.right, level - 1);
        }
        if (level == 0) result += root.value + " ";
        return result;
    }
    //could make this have the addNode/deleteNode functionality here.
    //template--> extract components for popups
    //file structure --> put different types of classes in different folders/packages
    //OOP guiding principles --> encapsulation
    //interfaces are more composable: prevent carrying a bunch of data and baggage, prevent exposing unnecessary details
    //
}
