package tree;

import tree.Binary;

public class BinaryTree {

    private Binary root;

    BinaryTree(int x) {
        Binary tree = new Binary(x);
        this.root = tree;
    }
    public Binary getRoot() {
        return root;
    }

    public void setRoot(Binary root) {
        this.root = root;
    }

    public Binary BinarySearch(Binary bin, int numb) {
        if (bin == null || numb == bin.key) {
            return bin;
        }
        if (numb < bin.key) return BinarySearch(bin.left_child, numb);
        else return BinarySearch(bin.right_child, numb);
    }

    public Binary BinaryMinimum(Binary bin) {
        while (bin.left_child != null) bin = bin.left_child;
        return bin;
    }

    public Binary BinaryMaximum(Binary bin) {
        while (bin.right_child != null) bin = bin.right_child;
        return bin;
    }

    public void BinaryInsert(Binary node) {
        Binary x = this.root;
        while (true) {
            if (node.key > x.key) {
                if (x.right_child != null) x = x.right_child;
                else {
                    node.parent = x;
                    x.right_child = node;
                    return;
                }
            }
            else if (node.key < x.key) {
                if (x.left_child != null) x = x.left_child;
                else {
                    node.parent = x;
                    x.left_child = node;
                    return;
                }
            }
        }
    }



    public void BinaryPrint(String pr, Binary node, boolean left) {
        if (node == null) return;
        else {
            if (left) pr += "l ";
            else pr += "r ";
            System.out.print(pr);
            System.out.println(node.key);
            BinaryPrint(pr, node.right_child, false);
            BinaryPrint(pr, node.left_child, true);
        }
    }
}
