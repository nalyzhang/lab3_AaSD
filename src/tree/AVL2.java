package tree;

public class AVL2 {

    int key;
    AVL2 right_child;
    AVL2 left_child;
    AVL2 parent;
    int height;

    AVL2(int k) {
        this.key = k;
        this.left_child = null;
        this.right_child = null;
        this.parent = null;
        this.height = 0;
    }
}