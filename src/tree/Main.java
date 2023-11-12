package tree;

import tree.Binary;
import tree.BinaryTree;
import tree.AVL;
import tree.RedBlack;


public class Main {

    public static void main(String[] args) {
        int n = (int)Math.floor(Math.random() * 100);
        BinaryTree tree = new BinaryTree(n);

        for (int i = 1; i < 15; i++) {
            n = (int)Math.floor(Math.random() * 100);
            Binary node = new Binary(n);
            tree.BinaryInsert(node);
        }
        tree.BinaryPrint(" ", tree.getRoot(), false);



    }
}