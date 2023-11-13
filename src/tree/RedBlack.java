package tree;

public class RedBlack {

    int key;
    RedBlack left_child;
    RedBlack right_child;
    RedBlack parent;
    boolean black;

    RedBlack(int k) {
        this.key = k;
        this.left_child = null;
        this.right_child = null;
        this.parent = null;
        this.black = true;
    }
}
