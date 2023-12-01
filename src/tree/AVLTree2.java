package tree;

public class AVLTree2 {

    private AVL2 root;

    AVLTree2(int x) {
        this.root = new AVL2(x);
    }
    public AVL2 getRoot() {
        return root;
    }

    public AVL2 AVLSearch(AVL2 bin, int numb) {
        if (bin == null || numb == bin.key) {
            return bin;
        }
        if (numb < bin.key) return AVLSearch(bin.left_child, numb);
        else return AVLSearch(bin.right_child, numb);
    }

    public AVL2 AVLMinimum(AVL2 bin) {
        while (bin.left_child != null) bin = bin.left_child;
        return bin;
    }

    public AVL2 AVLMaximum(AVL2 bin) {
        while (bin.right_child != null) bin = bin.right_child;
        return bin;
    }

    public void AVLInsert(AVL2 node) {
        AVL2 x = this.root;
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
        AVLFixInsert(node);
    }

    public int getHeight(AVL2 node) {
        if (node == null) return 0;
        else return node.height;
    }

    public int balance(AVL2 node) {
        return (getHeight(node.left_child) - getHeight(node.right_child));
    }

    public void AVLFixInsert(AVL2 node) {
//        System.out.println("\nbefore:");
//        AVLPrint("", getRoot(), false);
//        System.out.println("\nafter:");
        if (node == null) return;
        while (node.parent != null) {
            node.height = Math.max(getHeight(node.left_child), getHeight(node.right_child)) + 1;
            node = node.parent;
            if (balance(node) == 2 && balance(node.left_child) == 1) AVLRightRotation(node);
            else if (balance(node) == 2 && balance(node.left_child) == -1) AVLBigRightRotation(node);
            else if (balance(node) == -2 && balance(node.right_child) == -1) AVLLeftRotation(node);
            else if (balance(node) == -2 && balance(node.right_child) == 1) AVLBigLeftRotation(node);
            if (balance(node) == 0) break;
        }
    }

    public void AVLDelete(AVL2 node) {
        AVL2 parent = node.parent;
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
            AVL2 successor = AVLSuccessor(node);
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

    public void AVLFixDelete(AVL2 node) {
        if (node == null) return;
        while (node.parent != null) {
            node.height = Math.max(getHeight(node.left_child), getHeight(node.right_child)) + 1;
            node = node.parent;
            if (balance(node) == 2 && balance(node.left_child) == 1) AVLRightRotation(node);
            else if (balance(node) == 2 && balance(node.left_child) == -1) AVLBigRightRotation(node);
            else if (balance(node) == -2 && balance(node.right_child) == -1) AVLLeftRotation(node);
            else if (balance(node) == -2 && balance(node.right_child) == 1) AVLBigLeftRotation(node);
        }
    }

    //правый поворот
    public void AVLRightRotation(AVL2 node) {
        AVL2 b = node.left_child;
        node.left_child = b.right_child;
        if (b.right_child != null) b.right_child.parent = node;
        b.parent = node.parent;
        if (node == this.root) this.root = b;
        else if (node == node.parent.right_child) node.parent.right_child = b;
        else node.parent.left_child = b;
        b.right_child = node;
        node.parent = b;
        node.height = Math.max(getHeight(node.left_child), getHeight(node.right_child)) + 1;
        b.height = Math.max(getHeight(b.left_child), getHeight(b.right_child)) + 1;
    }

    //левый поворот
    public void AVLLeftRotation(AVL2 node) {
        AVL2 b = node.right_child;
        node.right_child = b.left_child;
        if (b.left_child != null) b.left_child.parent = node;
        b.parent = node.parent;
        if (node == this.root) this.root = b;
        else if (node == node.parent.left_child) node.parent.left_child = b;
        else node.parent.right_child = b;
        b.left_child = node;
        node.parent = b;
        node.height = Math.max(getHeight(node.left_child), getHeight(node.right_child)) + 1;
        b.height = Math.max(getHeight(b.left_child), getHeight(b.right_child)) + 1;
    }

    //большой правый поворот
    public void AVLBigRightRotation(AVL2 node) {
        AVLLeftRotation(node.left_child);
        AVLRightRotation(node);
    }

    //большой левый
    public void AVLBigLeftRotation(AVL2 node) {
        AVLRightRotation(node.right_child);
        AVLLeftRotation(node);
    }


    //последующий эл-т
    public AVL2 AVLSuccessor(AVL2 node) {
        if (node.key == AVLMaximum(this.root).key) return node;
        if (node.right_child != null)
            return AVLMinimum(node.right_child);
        AVL2 parent = node.parent;
        while (parent != null && node == parent.right_child) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //предыдущий эл-т
    public AVL2 AVLPredecessor(AVL2 node) {
        if (node.key == AVLMinimum(this.root).key) return node;
        if (node.left_child != null) return AVLMaximum(node.left_child);
        AVL2 parent = node.parent;
        while (parent != null && node == parent.left_child) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //обход в ширину
    public void AVLWidthTraversal() {
        int h = AVLTreeHeight(this.root);
        for (int i = 1; i <= h; i++) {
            AVLCurrentLevel(this.root, i);
            System.out.println();
        }
    }

    public void AVLCurrentLevel(AVL2 root, int level) {
        if (root == null) return;
        if (level == 1) {
            System.out.print(root.key + " ");
        }
        else if (level > 1) {
            AVLCurrentLevel(root.left_child, level - 1);
            AVLCurrentLevel(root.right_child, level - 1);
        }
    }

    //прямой обход в глубину
    //обход узлов в порядке: вершина, левое поддерево, правое поддерево
    public void AVLPreorder(AVL2 node) {
        if (node != null) {
            //System.out.println(node.key);
            AVLPreorder(node.left_child);
            AVLPreorder(node.right_child);
        }
    }

    // обратный обход в глубину
    // обход узлов в порядке: левое поддерево, правое поддерево, вершина
    public void AVLPostorder(AVL2 node) {
        if (node != null){
            AVLPostorder(node.left_child);
            AVLPostorder(node.right_child);
            //System.out.println(node.key);
        }
    }

    //симметричный обход в глубину
    // обход узлов в отсортированном порядке
    public void AVLInorder(AVL2 node) {
        if (node != null) {
            AVLInorder(node.left_child);
            //System.out.println(node.key);
            AVLInorder(node.right_child);
        }
    }


    //высота дерева
    public int AVLTreeHeight(AVL2 node) {
        if (node == null) {
            return 0;
        }
        return (1 + Math.max(AVLTreeHeight(node.left_child), AVLTreeHeight(node.right_child)));
    }

    public void AVLPrint(String pr, AVL2 node, boolean left) {
        if (node == null) return;
        else {
            if (left) pr += "l ";
            else pr += "r ";
            System.out.print(pr);
            System.out.println(node.key + " " + balance(node));
            AVLPrint(pr, node.right_child, false);
            AVLPrint(pr, node.left_child, true);
        }
    }

    public void AVLPrintSuccessor(AVL2 node) {
        if (node != null) {
            AVLPrintSuccessor(node.left_child);
            //System.out.println("Для элемента " + node.key + " следующий " + AVLSuccessor(node).key);
            AVLPrintSuccessor(node.right_child);
        }
    }

    public void AVLPrintPredecessor(AVL2 node) {
        if (node != null) {
            AVLPrintPredecessor(node.left_child);
            //System.out.println("Для элемента " + node.key + " предыдущий " + AVLPredecessor(node).key);
            AVLPrintPredecessor(node.right_child);
        }
    }


}