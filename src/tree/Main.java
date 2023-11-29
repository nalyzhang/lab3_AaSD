package tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import tree.Binary;
import tree.BinaryTree;
import tree.AVL;
import tree.RedBlack;


public class Main {

    public static int[] Array(int n) {
        int[] arr = new int[n];
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            a.add(i+1);
        }
        Collections.shuffle(a);
        for (int i = 0; i < n; i++) {
            arr[i] = a.get(i);
        }
        return arr;
    }

    public static void ForBinaryTree(int[] array, int number) throws IOException {
        BinaryTree tree = new BinaryTree(array[0]);

        for (int i = 1; i < array.length; i++) {
            Binary node = new Binary(array[i]);
            tree.BinaryInsert(node);
        }
        tree.BinaryPrint("",tree.getRoot(),false);
        System.out.println();

        long startTime, endTime, timeElapsed;
        Binary node;

//        node = new Binary(number);
//        startTime = System.nanoTime();
//        tree.BinaryInsert(node);
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        startTime = System.nanoTime();
//        tree.BinaryDelete(tree.getRoot());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;

//        System.out.println(timeElapsed);
//        System.out.println(timeElapsed);

        //System.out.println("Максимальное значение: " + tree.BinaryMaximum(tree.getRoot()).key);
        //System.out.println("Минимальное значение: " + tree.BinaryMinimum(tree.getRoot()).key);
        //System.out.println(tree.BinarySearch(tree.getRoot(), 15).key);

//        System.out.println();
//
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

//        System.out.println();
//        tree.BinaryPrintSuccessor(tree.getRoot());
//        System.out.println();
//        tree.BinaryPrintPredecessor(tree.getRoot());
//        System.out.println();
//
//        System.out.println(tree.BinaryTreeHeight(tree.getRoot()));
//        startTime = System.nanoTime();
//        int h = tree.BinaryTreeHeight(tree.getRoot());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.println(timeElapsed);
//        System.out.println();
    }

    public static void ForRedBlackTree(int[] array, int number) throws IOException {
        RedBlackTree tree = new RedBlackTree(array[0]);

        for (int i = 1; i < array.length; i++) {
            RedBlack node = new RedBlack(array[i]);
            tree.RedBlackInsert(node);
//            System.out.println();
        }
        tree.RedBlackPrint("", tree.getRoot(), false);
        System.out.println();
        //tree.RedBlackDelete(tree.RedBlackSearch(tree.getRoot(), 4));

        long startTime, endTime, timeElapsed = 0;
        long startTime1, endTime1, timeElapsed1 = 0;
        RedBlack node;

//        node = new RedBlack(number);
//        startTime = System.nanoTime();
//        tree.RedBlackInsert(node);
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        startTime1 = System.nanoTime();
//        tree.RedBlackDelete(tree.getRoot());
//        endTime1 = System.nanoTime();
//        timeElapsed1 = endTime1 - startTime1;

//        System.out.println(timeElapsed);
//        System.out.println(timeElapsed1);

//        System.out.println();

        System.out.println("Прямой обход в глубину");
        tree.RedBlackPreorder(tree.getRoot());
        System.out.println();

        System.out.println("Обратный обход в глубину");
        tree.RedBlackPostorder(tree.getRoot());
        System.out.println();

        System.out.println("Симметричный обход в глубину");
        tree.RedBlackInorder(tree.getRoot());
        System.out.println();

        System.out.println("Обход в ширину: ");
        tree.RedBlackWidthTraversal();

        //System.out.println(tree.RedBlackTreeHeight(tree.getRoot()));
//        startTime = System.nanoTime();
//        int h = tree.RedBlackTreeHeight(tree.getRoot());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.println(timeElapsed);
//        System.out.println();
    }

    public static void ForAVLTree(int[] array, int number) {
        AVLTree tree = new AVLTree(array[0]);

        for (int i = 1; i < array.length; i++) {
            AVL node = new AVL(array[i]);
            tree.AVLInsert(node);
//            tree.AVLPrint("", tree.getRoot(), false);
//            System.out.println();
        }
        //tree.AVLDelete(tree.AVLSearch(tree.getRoot(), 3));
//        tree.AVLPrint("", tree.getRoot(), false);
//        System.out.println();

//        tree.AVLDelete(tree.AVLSearch(tree.getRoot(), 60));
//        tree.AVLPrint("", tree.getRoot(), false);
//        System.out.println();

        long startTime, endTime, timeElapsed = 0;
        long startTime1, endTime1, timeElapsed1 = 0;

        AVL node;


//        System.out.println();
//        System.out.println(array.length);
//        System.out.println();

//        node = new AVL(number);
//        startTime = System.nanoTime();
//        tree.AVLInsert(node);
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        startTime1 = System.nanoTime();
//        tree.AVLDelete(tree.AVLSearch(tree.getRoot(), number));
//        endTime1 = System.nanoTime();
//        timeElapsed1 = endTime1 - startTime1;

//        System.out.println(timeElapsed);
//        System.out.println("Удаление \n" + (timeElapsed1));
//
//
//        System.out.println();
//
//        startTime = System.nanoTime();
//        tree.AVLPreorder(tree.getRoot());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//
//        System.out.println("Прямой обход в глубину " + timeElapsed);
//        System.out.println();
//
//        startTime = System.nanoTime();
//        tree.AVLPostorder(tree.getRoot());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//
//        System.out.println("Обратный обход в глубину " + timeElapsed);
//        System.out.println();
//
//        startTime = System.nanoTime();
//        tree.AVLInorder(tree.getRoot());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//
//        System.out.println("Симметричный обход в глубину " + timeElapsed);
//        System.out.println();
//
//        startTime = System.nanoTime();
//        tree.AVLWidthTraversal();
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//
//        System.out.println("Обход в ширину " + timeElapsed);
//        System.out.println();
//
//        startTime = System.nanoTime();
//        int h = tree.AVLTreeHeight(tree.getRoot());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
        System.out.println(tree.AVLTreeHeight(tree.getRoot()));
//        System.out.println();
    }

    public static void ForAVLTree3(int[] array, int number) {
        AVLTree3 tree = new AVLTree3();

        for (int i = 0; i < array.length; i++) {
            tree.root = tree.insertNode(tree.root, array[i]);
        }
        tree.printTree(tree.root, "", true);
        System.out.println();

        long startTime, endTime, timeElapsed = 0;
        long startTime1, endTime1, timeElapsed1 = 0;

//        startTime = System.nanoTime();
//        tree.insertNode(tree.root,number);
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        startTime1 = System.nanoTime();
//        tree.deleteNode(tree.root, number);
//        endTime1 = System.nanoTime();
//        timeElapsed1 = endTime1 - startTime1;

//        System.out.println(timeElapsed);
//        System.out.println(timeElapsed1);
//        System.out.println(tree.height(tree.root));

        System.out.println("Прямой обход в глубину");
        tree.AVLPreorder(tree.root);
        System.out.println();

        System.out.println("Обратный обход в глубину");
        tree.AVLPostorder(tree.root);
        System.out.println();

        System.out.println("Симметричный обход в глубину");
        tree.AVLInorder(tree.root);
        System.out.println();

        System.out.println("Обход в ширину: ");
        tree.AVLWidthTraversal();
    }



    public static void main(String[] args) throws IOException {
        List<int[]> a = new ArrayList<int[]>();
//        int[] array = {7, 8, 1, 10, 6, 5, 2, 4,3,9};
        //7, 8, 1, 10, 6, 5, 2, 4, 3, 9
//        int[] array = Array(10);
//        for (int n = 0; n < 10; n++) System.out.print(array[n] + ", ");
//        System.out.println();
//        a.add(array);
        for (int n = 10000; n < 1000001; n += 10000) {
            int[] array = Array(n);
            a.add(array);
        }

//        System.out.println();
//        System.out.println("Бинарное");
//        System.out.println();
//        for (int i = 0; i < a.size(); i++) {
//            int c = a.get(i).length+1;
//            ForBinaryTree(a.get(i), c);
//        }
//
//        System.out.println();
//        System.out.println("КЧ");
//        System.out.println();
//        for (int i = 0; i < a.size(); i++) {
//            int c = a.get(i).length+1;
//            ForRedBlackTree(a.get(i), c);
//        }

        System.out.println();
        System.out.println("АВЛ");
        System.out.println();
        for (int i = 0; i < a.size(); i++) {
            int c = a.get(i).length+1;
            ForAVLTree(a.get(i), c);
        }
    }
}