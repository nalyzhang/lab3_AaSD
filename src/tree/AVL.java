package tree;

public class AVL {

    int key;
    AVL right_child;
    AVL left_child;
    AVL parent;
    int balance;

    AVL(int k) {
        this.key = k;
        this.left_child = null;
        this.right_child = null;
        this.parent = null;
        this.balance = 1;
    }
}
