package sample;

import java.io.Serializable;

public class Node {
    boolean toDelete;
    int value;
    Node left;
    Node right;

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString(){
        return Integer.toString(this.value);
    }
}
