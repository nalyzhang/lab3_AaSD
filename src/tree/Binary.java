package tree;

public class Binary {
    int key;
    Binary right_child;
    Binary left_child;
    Binary parent;

    Binary(int key) {
        this.key = key;
        this.left_child = null;
        this.right_child = null;
        this.parent = null;
    }
}
