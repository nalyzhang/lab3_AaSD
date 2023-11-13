package tree;

import tree.Binary;
import tree.BinaryTree;
import tree.AVL;
import tree.RedBlack;


public class Main {

    public static void ForBinaryTree() {
        int[] array = {20, 10, 30, 5, 15, 25, 35, 3, 7, 8, 17, 23, 27, 6, 19};
        BinaryTree tree = new BinaryTree(array[0]);

        for (int i = 1; i < 15; i++) {
            Binary node = new Binary(array[i]);
            tree.BinaryInsert(node);
        }
        tree.BinaryPrint("",tree.getRoot(),false);

        System.out.println();

        System.out.println("Максимальное значение: " + tree.BinaryMaximum(tree.getRoot()).key);
        System.out.println("Минимальное значение: " + tree.BinaryMinimum(tree.getRoot()).key);
        //System.out.println(tree.BinarySearch(tree.getRoot(), 15).key);

        System.out.println();
        System.out.println();

        System.out.println("Прямой обход в глубину");
        tree.BinaryPreorder(tree.getRoot());
        System.out.println();
        System.out.println("Обратный обход в глубину");
        tree.BinaryPostorder(tree.getRoot());
        System.out.println();
        System.out.println("Симметричный обход в глубину");
        tree.BinaryInorder(tree.getRoot());
        System.out.println();
        System.out.println("Обход в ширину: ");
        tree.BinaryWidthTraversal();


        System.out.println();
        tree.BinaryPrintSuccessor(tree.getRoot());
        System.out.println();
        tree.BinaryPrintPredecessor(tree.getRoot());

        System.out.println();
        tree.BinaryDelete(tree.BinarySearch(tree.getRoot(), 20));
        tree.BinaryPrint(" ", tree.getRoot(), false);

        System.out.println();
        System.out.println("Высота дерева: " + tree.BinaryTreeHeight(tree.getRoot()));

        System.out.println();
        tree.BinaryLeftRotation(tree.BinarySearch(tree.getRoot(), 15));
        tree.BinaryWidthTraversal();

        System.out.println();
        tree.BinaryRightRotation(tree.BinarySearch(tree.getRoot(), 10));
        tree.BinaryWidthTraversal();
    }

    public static void ForRedBlackTree() {
        int[] array = {1,2,3,4,5,6};
        RedBlackTree tree = new RedBlackTree(array[0]);

        for (int i = 1; i < 6; i++) {
            RedBlack node = new RedBlack(array[i]);
            tree.RedBlackInsert(node);
        }
        tree.RedBlackDelete(tree.RedBlackSearch(tree.getRoot(), 4));
        tree.RedBlackPrint("", tree.getRoot(), false);
        //tree.RedBlackInorder(tree.getRoot());
    }

    public static void main(String[] args) {
        ForRedBlackTree();
//        int[] array = {20, 10, 30, 5, 15, 25, 35, 3, 7, 8, 17, 23, 27, 6, 19};
//        RedBlackTree tree = new RedBlackTree(array[0]);
//
//        for (int i = 1; i < 15; i++) {
//            Binary node = new Binary(array[i]);
//            tree.BinaryInsert(node);
//        }
//        tree.BinaryPrint("",tree.getRoot(),false);

//        System.out.println();
//
//        System.out.println("Максимальное значение: " + tree.BinaryMaximum(tree.getRoot()).key);
//        System.out.println("Минимальное значение: " + tree.BinaryMinimum(tree.getRoot()).key);
//        //System.out.println(tree.BinarySearch(tree.getRoot(), 15).key);
//
//        System.out.println();
//        System.out.println();
//
//        System.out.println("Прямой обход в глубину");
//        tree.BinaryPreorder(tree.getRoot());
//        System.out.println();
//        System.out.println("Обратный обход в глубину");
//        tree.BinaryPostorder(tree.getRoot());
//        System.out.println();
//        System.out.println("Симметричный обход в глубину");
//        tree.BinaryInorder(tree.getRoot());
//        System.out.println();
//        System.out.println("Обход в ширину: ");
//        tree.BinaryWidthTraversal();
//
//
//        System.out.println();
//        tree.BinaryPrintSuccessor(tree.getRoot());
//        System.out.println();
//        tree.BinaryPrintPredecessor(tree.getRoot());
//
//        System.out.println();
//        tree.BinaryDelete(tree.BinarySearch(tree.getRoot(), 20));
//        tree.BinaryPrint(" ", tree.getRoot(), false);
//
//        System.out.println();
//        System.out.println("Высота дерева: " + tree.BinaryTreeHeight(tree.getRoot()));
//
//        System.out.println();
//        tree.BinaryLeftRotation(tree.BinarySearch(tree.getRoot(), 15));
//        tree.BinaryWidthTraversal();
//
//        System.out.println();
//        tree.BinaryRightRotation(tree.BinarySearch(tree.getRoot(), 10));
//        tree.BinaryWidthTraversal();
    }
}