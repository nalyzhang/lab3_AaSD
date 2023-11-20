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

    //последующий эл-т
    public Binary BinarySuccessor(Binary node) {
        if (node.key == BinaryMaximum(this.root).key) return node;
        if (node.right_child != null)
            return BinaryMinimum(node.right_child);
        Binary parent = node.parent;
        while (parent != null && node == parent.right_child) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //предыдущий эл-т
    public Binary BinaryPredecessor(Binary node) {
        if (node.key == BinaryMinimum(this.root).key) return node;
        if (node.left_child != null) return BinaryMaximum(node.left_child);
        Binary parent = node.parent;
        while (parent != null && node == parent.left_child) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //удаление
    public void BinaryDelete(Binary node) {
        Binary parent = node.parent;
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
            Binary successor = BinarySuccessor(node);
            node.key = successor.key;
            if (successor.parent.left_child == successor) {
                successor.parent.left_child = successor.right_child;
                if (successor.right_child != null) successor.right_child.parent = successor.parent;
            } else {
                successor.parent.right_child = successor.right_child;
                if (successor.right_child != null) successor.right_child.parent = successor.parent;
            }
        }
    }

    //обход в ширину
    public void BinaryWidthTraversal() {
        int h = BinaryTreeHeight(this.root);
        for (int i = 1; i <= h; i++) BinaryCurrentLevel(this.root, i);
    }

    public void BinaryCurrentLevel(Binary root, int level) {
        if (root == null) return;
        if (level == 1) {
            int i = root.key;
        }
        else if (level > 1) {
            BinaryCurrentLevel(root.left_child, level - 1);
            BinaryCurrentLevel(root.right_child, level - 1);
        }
    }

    //прямой обход в глубину
    //обход узлов в порядке: вершина, левое поддерево, правое поддерево
    public void BinaryPreorder(Binary node) {
        if (node != null) {
            //System.out.println(node.key);
            BinaryPreorder(node.left_child);
            BinaryPreorder(node.right_child);
        }
    }

    // обратный обход в глубину
    // обход узлов в порядке: левое поддерево, правое поддерево, вершина
    public void BinaryPostorder(Binary node) {
        if (node != null){
            BinaryPostorder(node.left_child);
            BinaryPostorder(node.right_child);
            //System.out.println(node.key);
        }
    }

    //симметричный обход в глубину
    // обход узлов в отсортированном порядке
    public void BinaryInorder(Binary node) {
        if (node != null) {
            BinaryInorder(node.left_child);
            //System.out.println(node.key);
            BinaryInorder(node.right_child);
        }
    }

    //правый поворот
    public void BinaryRightRotation(Binary node) {
        Binary b = node.left_child;
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
    public void BinaryLeftRotation(Binary node) {
        Binary b = node.right_child;
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
    public void BinaryBigRightRotation(Binary node) {
        BinaryLeftRotation(node.right_child);
        BinaryRightRotation(node);
    }

    //большой левый поворот
    public void BinaryBigLeftRotation(Binary node) {
        BinaryRightRotation(node.left_child);
        BinaryLeftRotation(node);
    }

    //высота дерева
    public int BinaryTreeHeight(Binary node) {
        if (node == null) {
            return 0;
        }
        return (1 + Math.max(BinaryTreeHeight(node.left_child), BinaryTreeHeight(node.right_child)));
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

    public void BinaryPrintSuccessor(Binary node) {
        if (node != null) {
            BinaryPrintSuccessor(node.left_child);
            //System.out.println("Для элемента " + node.key + " следующий " + BinarySuccessor(node).key);
            BinaryPrintSuccessor(node.right_child);
        }
    }

    public void BinaryPrintPredecessor(Binary node) {
        if (node != null) {
            BinaryPrintPredecessor(node.left_child);
            //System.out.println("Для элемента " + node.key + " предыдущий " + BinaryPredecessor(node).key);
            BinaryPrintPredecessor(node.right_child);
        }
    }
}
