package sample;
import java.util.*;

public class Model {
    private String BSTstring;
    //do we store the root node in two places?
    private Tree BSTTree;

    public Node getRoot() {
        return BSTTree.getRoot();
    }
    public Tree getBSTTree() {
        return BSTTree;
    }
    public void setBSTTree(String s) {
        this.BSTTree = new Tree(s);
    }

    public String getBSTstring() {
        return BSTstring;
    }

}