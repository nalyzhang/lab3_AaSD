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
        AVLFix(node);
    }

    public void AVLFix(AVL node) {
        if (node == null) return;
        while (node.parent != null && node.balance != 0) {
            node = node.parent;
            node.balance = AVLTreeHeight(node.left_child) - AVLTreeHeight(node.right_child);
            if (node.balance == 2) {
                if (node.right_child == null || node.left_child != null) {
                    if (node.left_child.balance == 1) AVLRightRotation(node);
                    else if (node.left_child.balance == -1) AVLBigRightRotation(node);
                } else {
                    if (node.right_child.balance == -1) AVLRightRotation(node);
                    else if (node.right_child.balance == 1) AVLBigRightRotation(node);
                }
            } else if (node.balance == -2) {
                if(node.right_child != null || node.left_child == null) {
                    if (node.right_child.balance == 1) AVLLeftRotation(node);
                    else if (node.right_child.balance == -1) AVLBigLeftRotation(node);
                } else {
                    if (node.left_child.balance == -1) AVLLeftRotation(node);
                    else if (node.left_child.balance == 1) AVLBigLeftRotation(node);

                }
            }
            if (node.left_child != null && node.balance == 2 && node.left_child.balance == 1) AVLRightRotation(node);
            else if (node.left_child != null && node.balance == 2 && node.left_child.balance == -1) AVLBigRightRotation(node);
            else if (node.left_child != null && node.balance == -2 && node.left_child.balance == 1) AVLLeftRotation(node);
            else if (node.left_child != null && node.balance == -2 && node.left_child.balance == -1) AVLBigLeftRotation(node);
        }
    }

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
    }


    //правый поворот
    public void AVLRightRotation(AVL node) {
        if (node == null || node.left_child == null) return;
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
        if (node == null || node.right_child == null) return;
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



    //обход в ширину
    public void AVLWidthTraversal() {
        int h = AVLTreeHeight(this.root);
        for (int i = 1; i <= h; i++) AVLCurrentLevel(this.root, i);
    }

    public void AVLCurrentLevel(AVL root, int level) {
        if (root == null) return;
        if (level == 1) {
            int i = root.key;
        }
        else if (level > 1) {
            AVLCurrentLevel(root.left_child, level - 1);
            AVLCurrentLevel(root.right_child, level - 1);
        }
    }

    //прямой обход в глубину
    //обход узлов в порядке: вершина, левое поддерево, правое поддерево
    public void AVLPreorder(AVL node) {
        if (node != null) {
            //System.out.println(node.key);
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
            //System.out.println(node.key);
        }
    }

    //симметричный обход в глубину
    // обход узлов в отсортированном порядке
    public void AVLInorder(AVL node) {
        if (node != null) {
            AVLInorder(node.left_child);
            //System.out.println(node.key);
            AVLInorder(node.right_child);
        }
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
            //System.out.println("Для элемента " + node.key + " следующий " + AVLSuccessor(node).key);
            AVLPrintSuccessor(node.right_child);
        }
    }

    public void AVLPrintPredecessor(AVL node) {
        if (node != null) {
            AVLPrintPredecessor(node.left_child);
            //System.out.println("Для элемента " + node.key + " предыдущий " + AVLPredecessor(node).key);
            AVLPrintPredecessor(node.right_child);
        }
    }


}



/*
 кладбище


    int getBalanceFactor(AVL N) {
        if (N == null)
            return 0;
        return AVLTreeHeight(N.left_child) - AVLTreeHeight(N.right_child);
    }

    // вставка
    public AVL AVLInsert(AVL node, int item) {
        // Find the position and insert the node
        if (node == null)
            return (new AVL(item));
        if (item < node.key)
            node.left_child = AVLInsert(node.left_child, item);
        else if (item > node.key)
            node.right_child = AVLInsert(node.right_child, item);
        else
            return node;

        // Update the balance factor of each node
        // And, balance the tree
        node.height = 1 + Math.max(AVLTreeHeight(node.left_child), AVLTreeHeight(node.right_child));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (item < node.left_child.key) {
                return rightRotate(node);
            } else if (item > node.left_child.key) {
                node.left_child = leftRotate(node.left_child);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (item > node.right_child.key) {
                return leftRotate(node);
            } else if (item < node.right_child.key) {
                node.right_child = rightRotate(node.right_child);
                return leftRotate(node);
            }
        }
        return node;
    }

    public AVL rightRotate(AVL y) {
        AVL x = y.left_child;
        AVL T2 = x.right_child;
        x.right_child = y;
        y.left_child = T2;
        y.height = Math.max(AVLTreeHeight(y.left_child), AVLTreeHeight(y.right_child)) + 1;
        x.height = Math.max(AVLTreeHeight(x.left_child), AVLTreeHeight(x.right_child)) + 1;
        return x;
    }

    AVL leftRotate(AVL x) {
        AVL y = x.right_child;
        AVL T2 = y.left_child;
        y.left_child = x;
        x.right_child = T2;
        x.height = Math.max(AVLTreeHeight(x.left_child), AVLTreeHeight(x.right_child)) + 1;
        y.height = Math.max(AVLTreeHeight(y.left_child), AVLTreeHeight(y.right_child)) + 1;
        return y;
    }

    public AVL AVLDelete(AVL Root, int item) {

        // Find the node to be deleted and remove it
        if (Root == null)
            return Root;
        if (item < Root.key)
            Root.left_child = AVLDelete(Root.left_child, item);
        else if (item > Root.key)
            Root.right_child = AVLDelete(Root.right_child, item);
        else {
            if ((Root.left_child == null) || (Root.right_child == null)) {
                AVL temp = null;
                if (temp == Root.left_child)
                    temp = Root.right_child;
                else
                    temp = Root.left_child;
                if (temp == null) {
                    temp = Root;
                    Root = null;
                } else
                    Root = temp;
            } else {
                AVL temp = AVLMinimum(Root.right_child);
                Root.key = temp.key;
                Root.right_child = AVLDelete(Root.right_child, temp.key);
            }
        }
        if (Root == null)
            return Root;

        // Update the balance factor of each node and balance the tree
        Root.height = Math.max(AVLTreeHeight(Root.left_child), AVLTreeHeight(Root.right_child)) + 1;
        int balanceFactor = getBalanceFactor(Root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(Root.left_child) >= 0) {
                return rightRotate(Root);
            } else {
                Root.left_child = leftRotate(Root.left_child);
                return rightRotate(Root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(Root.right_child) <= 0) {
                return leftRotate(Root);
            } else {
                Root.right_child = rightRotate(Root.right_child);
                return leftRotate(Root);
            }
        }
        return Root;
    }

 */