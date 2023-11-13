package tree;

import tree.AVL;

public class AVLTree {

    private AVL root;

    AVLTree(int x) {
        this.root = new AVL(x);
    }
        public AVL getRoot() {
        return root;
    }

    public AVL AVLSearch(AVL bin, int numb) {
        if (bin == null || numb == bin.key) {
            return bin;
        }
        if (numb < bin.key) return AVLSearch(bin.left_child, numb);
        else return AVLSearch(bin.right_child, numb);
    }

    public AVL AVLMinimum(AVL bin) {
        while (bin.left_child != null) bin = bin.left_child;
        return bin;
    }

    public AVL AVLMaximum(AVL bin) {
        while (bin.right_child != null) bin = bin.right_child;
        return bin;
    }


    // вставка
    public void AVLInsert(AVL node) {
        AVL x = this.root;
        while (true) {
            if (node.key > x.key) {
                if (x.right_child != null) x = x.right_child;
                else {
                    node.parent = x;
                    x.right_child = node;
                    break;
                }
            }
            else if (node.key < x.key) {
                if (x.left_child != null) x = x.left_child;
                else {
                    node.parent = x;
                    x.left_child = node;
                    break;
                }
            }
        }
        AVLFixDelete(node);
    }

    // todo
    public void AVLFixInsert(AVL node) {

    }

    //последующий эл-т
    public AVL AVLSuccessor(AVL node) {
        if (node.key == AVLMaximum(this.root).key) return node;
        if (node.right_child != null)
            return AVLMinimum(node.right_child);
        AVL parent = node.parent;
        while (parent != null && node == parent.right_child) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //предыдущий эл-т
    public AVL AVLPredecessor(AVL node) {
        if (node.key == AVLMinimum(this.root).key) return node;
        if (node.left_child != null) return AVLMaximum(node.left_child);
        AVL parent = node.parent;
        while (parent != null && node == parent.left_child) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //удаление
    public void AVLDelete(AVL node) {
        AVL parent = node.parent;
        // 1 - удаляемый эл-т - лист
        if (node.left_child == null && node.right_child == null) {
            if (parent.left_child == node) parent.left_child = null;
            if (parent.right_child == node) parent.right_child = null;
        }
        // 2 - удаляемый эл-т имеет одного потомка
        else if (node.left_child == null || node.right_child == null) {
            if (node.left_child == null) {
                if (parent.left_child == node) parent.left_child = node.right_child;
                else parent.right_child = node.right_child;
                node.right_child.parent = parent;
            } else {
                if (parent.left_child == node) parent.left_child = node.left_child;
                else parent.right_child = node.left_child;
                node.left_child.parent = parent;
            }
        }
        // 3 - удаляемый эл-т имеет двух потомков
        else {
            AVL successor = AVLSuccessor(node);
            node.key = successor.key;
            if (successor.parent.left_child == successor) {
                successor.parent.left_child = successor.right_child;
                if (successor.right_child != null) successor.right_child.parent = successor.parent;
            } else {
                successor.parent.right_child = successor.right_child;
                if (successor.right_child != null) successor.right_child.parent = successor.parent;
            }
        }
        AVLFixDelete(node);
    }

    //todo
    public void AVLFixDelete(AVL node) {

    }

    //обход в ширину
    public void AVLWidthTraversal() {
        int h = AVLTreeHeight(this.root);
        for (int i = 1; i <= h; i++) AVLCurrentLevel(this.root, i);
    }

    public void AVLCurrentLevel(AVL root, int level) {
        if (root == null) return;
        if (level == 1) System.out.println(root.key);
        else if (level > 1) {
            AVLCurrentLevel(root.left_child, level - 1);
            AVLCurrentLevel(root.right_child, level - 1);
        }
    }

    //прямой обход в глубину
    //обход узлов в порядке: вершина, левое поддерево, правое поддерево
    public void AVLPreorder(AVL node) {
        if (node != null) {
            System.out.println(node.key);
            AVLPreorder(node.left_child);
            AVLPreorder(node.right_child);
        }
    }

    // обратный обход в глубину
    // обход узлов в порядке: левое поддерево, правое поддерево, вершина
    public void AVLPostorder(AVL node) {
        if (node != null){
            AVLPostorder(node.left_child);
            AVLPostorder(node.right_child);
            System.out.println(node.key);
        }
    }

    //симметричный обход в глубину
    // обход узлов в отсортированном порядке
    public void AVLInorder(AVL node) {
        if (node != null) {
            AVLInorder(node.left_child);
            System.out.println(node.key);
            AVLInorder(node.right_child);
        }
    }

    //правый поворот
    public void AVLRightRotation(AVL node) {
        AVL b = node.left_child;
        node.left_child = b.right_child;
        if (b.right_child != null) b.right_child.parent = node;
        b.parent = node.parent;
        if (node == this.root) this.root = b;
        else if (node == node.parent.right_child) node.parent.right_child = b;
        else node.parent.left_child = b;
        b.right_child = node;
        node.parent = b;
    }

    //левый поворот
    public void AVLLeftRotation(AVL node) {
        AVL b = node.right_child;
        node.right_child = b.left_child;
        if (b.left_child != null) b.left_child.parent = node;
        b.parent = node.parent;
        if (node == this.root) this.root = b;
        else if (node == node.parent.left_child) node.parent.left_child = b;
        else node.parent.right_child = b;
        b.left_child = node;
        node.parent = b;
    }

    //большой правый поворот
    public void AVLBigRightRotation(AVL node) {
        AVLLeftRotation(node.right_child);
        AVLRightRotation(node);
    }

    //большой левый
    public void AVLBigLeftRotation(AVL node) {
        AVLRightRotation(node.left_child);
        AVLLeftRotation(node);
    }

    //высота дерева
    public int AVLTreeHeight(AVL node) {
        if (node == null) {
            return 0;
        }
        return (1 + Math.max(AVLTreeHeight(node.left_child), AVLTreeHeight(node.right_child)));
    }

    public void AVLPrint(String pr, AVL node, boolean left) {
        if (node == null) return;
        else {
            if (left) pr += "l ";
            else pr += "r ";
            System.out.print(pr);
            System.out.println(node.key);
            AVLPrint(pr, node.right_child, false);
            AVLPrint(pr, node.left_child, true);
        }
    }

    public void AVLPrintSuccessor(AVL node) {
        if (node != null) {
            AVLPrintSuccessor(node.left_child);
            System.out.println("Для элемента " + node.key + " следующий " + AVLSuccessor(node).key);
            AVLPrintSuccessor(node.right_child);
        }
    }

    public void AVLPrintPredecessor(AVL node) {
        if (node != null) {
            AVLPrintPredecessor(node.left_child);
            System.out.println("Для элемента " + node.key + " предыдущий " + AVLPredecessor(node).key);
            AVLPrintPredecessor(node.right_child);
        }
    }


}
