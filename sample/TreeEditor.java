package sample;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class TreeEditor {

    void chooseClass(Node gesture, Node target, String[] directArr) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.print("helgjneeg");
        System.out.println(Arrays.asList(this.getClass().getDeclaredMethods()).toString());
        this.getClass().getDeclaredMethod(directArr[0], Node.class, Node.class, String.class).invoke(this, gesture, target, directArr[1]);
    }

    public void makeLeft(Node gesture, Node target, String direct) {
        if (target.left == null){
            gesture.toDelete = true;
            target.left = new Node(gesture.value);
            return;
        }
        replace(gesture, target.left, direct);
    }

    public void makeRight(Node gesture, Node target, String direct) {
        if (target.right == null) {
            gesture.toDelete = true;
            target.right = new Node(gesture.value);
            System.out.print("called");
            return;
        }
        replace(gesture, target.right, direct);
    }

    public void replace(Node gSource, Node tSource, String direct) {
        int incomingVal = gSource.value;
        gSource.toDelete = true;
        gSource = tSource;
        Node tLeft = tSource.left;
        Node tRight = tSource.right;
        tSource = new Node(tSource.value);
        if (direct.equals("none")){
            tSource.left = tLeft;
            tSource.right = tRight;
        }
        if (direct.equals("left")) {
            tSource.left = tLeft;
            if (tRight != null) {
                tSource.right = new Node(tRight.value);
                tSource.right.right = tRight.right;
                tSource.right.left = tRight.left;
            }
            gSource.left = tSource;
            if (gSource.right != null) gSource.right.toDelete = true;
        }
        //DID YOU COMMIT
        //DID YOU COMMIT
        //DID YOU COMMIT
        //DID YOU COMMIT
        //DID YOU COMMIT
        if (direct.equals("right")) {
            tSource.right = tRight;
            if (tLeft != null) {
                tSource.left = new Node(tLeft.value);
                tSource.left.right = tLeft.right;
                tSource.left.left = tLeft.left;
            }
            gSource.right = tSource;
            if (gSource.left != null) gSource.left.toDelete = true;
        }
        gSource.value = incomingVal;

    }
}
