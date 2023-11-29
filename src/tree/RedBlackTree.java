package tree;

import tree.RedBlack;

public class RedBlackTree {

    private RedBlack root;

    RedBlackTree(int x) {
        this.root = new RedBlack(x);
    }
    public RedBlack getRoot() {
        return root;
    }

    public RedBlack RedBlackSearch(RedBlack bin, int numb) {
        if (bin == null || numb == bin.key) {
            return bin;
        }
        if (numb < bin.key) return RedBlackSearch(bin.left_child, numb);
        else return RedBlackSearch(bin.right_child, numb);
    }

    public RedBlack RedBlackMinimum(RedBlack bin) {
        while (bin.left_child != null) bin = bin.left_child;
        return bin;
    }

    public RedBlack RedBlackMaximum(RedBlack bin) {
        while (bin.right_child != null) bin = bin.right_child;
        return bin;
    }


    // вставка
    public void RedBlackInsert(RedBlack node) {
        RedBlack x = this.root;
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
        node.black = false;
        RedBlackFixInsert(node);
    }

    public void RedBlackFixInsert(RedBlack node) {
        while (node.parent != null && node.parent.parent != null && !node.parent.black) {
            if (node.parent.parent.left_child == node.parent) {
                if (node.parent.parent.right_child != null && !node.parent.parent.right_child.black) {
                    node.parent.black = true;
                    node.parent.parent.right_child.black = true;
                    node.parent.parent.black = false;
                    node = node.parent.parent;
                } else {
                    if (node.parent.right_child == node) {
                        node = node.parent;
                        RedBlackLeftRotation(node);
                    }
                    node.parent.parent.black = false;
                    node.parent.black = true;
                    RedBlackRightRotation(node.parent.parent);
                }
            } else {
                if (node.parent.parent.left_child != null && !node.parent.parent.left_child.black) {
                    node.parent.parent.black = false;
                    node.parent.black = true;
                    node.parent.parent.left_child.black = true;
                    node = node.parent.parent;
                } else {
                    if (node.parent.left_child == node) {
                        node = node.parent;
                        RedBlackRightRotation(node);
                    }
                    node.parent.black = true;
                    node.parent.parent.black = false;
                    RedBlackLeftRotation(node.parent.parent);
                }
            }
        }
        this.root.black = true;
    }

    //последующий эл-т
    public RedBlack RedBlackSuccessor(RedBlack node) {
        if (node.key == RedBlackMaximum(this.root).key) return node;
        if (node.right_child != null)
            return RedBlackMinimum(node.right_child);
        RedBlack parent = node.parent;
        while (parent != null && node == parent.right_child) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //предыдущий эл-т
    public RedBlack RedBlackPredecessor(RedBlack node) {
        if (node.key == RedBlackMinimum(this.root).key) return node;
        if (node.left_child != null) return RedBlackMaximum(node.left_child);
        RedBlack parent = node.parent;
        while (parent != null && node == parent.left_child) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    //удаление
    public void RedBlackDelete(RedBlack node) {
        RedBlack parent = node.parent;
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
            RedBlack successor = RedBlackSuccessor(node);
            node.key = successor.key;
            if (successor.parent.left_child == successor) {
                successor.parent.left_child = successor.right_child;
                if (successor.right_child != null) successor.right_child.parent = successor.parent;
            } else {
                successor.parent.right_child = successor.right_child;
                if (successor.right_child != null) successor.right_child.parent = successor.parent;
            }
        }

        RedBlackFixDelete(node);
    }

    //todo
    public void RedBlackFixDelete(RedBlack node) {
        {
            RedBlack othernode;
            RedBlack parent = node.parent;
            while (node.black && node != this.root)
            {
                if (parent.left_child == node)
                {
                    othernode = parent.right_child;
                    if (!othernode.black)
                    {
                        othernode.black = true;
                        parent.black = false;
                        RedBlackLeftRotation(parent);
                        othernode = parent.right_child;
                    }
                    if (othernode.left_child.black && othernode.right_child.black) {
                        othernode.black = false;
                        node = parent;
                    }
                    else {
                        if (othernode.right_child.black) {
                            othernode.left_child.black = true;
                            othernode.black = false;
                            RedBlackRightRotation(othernode);
                            othernode = parent.right_child;
                        }
                        othernode.black = parent.black;
                        parent.black = true;
                        othernode.right_child.black = true;
                        RedBlackLeftRotation(parent);
                        node = this.root;
                    }
                }
                else
                {
                    othernode = parent.left_child;
                    if (!othernode.black)
                    {
                        othernode.black = true;
                        parent.black = false;
                        RedBlackRightRotation(parent);
                        othernode = parent.left_child;
                    }
                    if (othernode.right_child.black && othernode.left_child.black) {
                        othernode.black = false;
                        node = parent;
                    }
                    else {
                        if (othernode.left_child.black) {
                            othernode.right_child.black = true;
                            othernode.black = false;
                            RedBlackLeftRotation(othernode);
                            othernode = parent.left_child;
                        }
                        othernode.black = parent.black;
                        parent.black = true;
                        othernode.left_child.black = true;
                        RedBlackRightRotation(parent);
                        node = this.root;
                    }
                }
            }
        }
        node.black = true;
    }

    //обход в ширину
    public void RedBlackWidthTraversal() {
        int h = RedBlackTreeHeight(this.root);
        for (int i = 1; i <= h; i++) RedBlackCurrentLevel(this.root, i);
    }

    public void RedBlackCurrentLevel(RedBlack root, int level) {
        if (root == null) return;
        if (level == 1) {
            System.out.println(root.key);
        }
        else if (level > 1) {
            RedBlackCurrentLevel(root.left_child, level - 1);
            RedBlackCurrentLevel(root.right_child, level - 1);
        }
    }

    //прямой обход в глубину
    //обход узлов в порядке: вершина, левое поддерево, правое поддерево
    public void RedBlackPreorder(RedBlack node) {
        if (node != null) {
            System.out.println(node.key);
            RedBlackPreorder(node.left_child);
            RedBlackPreorder(node.right_child);
        }
    }

    // обратный обход в глубину
    // обход узлов в порядке: левое поддерево, правое поддерево, вершина
    public void RedBlackPostorder(RedBlack node) {
        if (node != null){
            RedBlackPostorder(node.left_child);
            RedBlackPostorder(node.right_child);
            System.out.println(node.key);
        }
    }

    //симметричный обход в глубину
    // обход узлов в отсортированном порядке
    public void RedBlackInorder(RedBlack node) {
        if (node != null) {
            RedBlackInorder(node.left_child);
            if (node.black) System.out.print(" black ");
            else System.out.print(" red ");
            System.out.println(node.key);
            RedBlackInorder(node.right_child);
        }
    }

    //правый поворот
    public void RedBlackRightRotation(RedBlack node) {
        RedBlack b = node.left_child;
        node.left_child = b.right_child;
        if (b.right_child != null) b.right_child.parent = node;
        b.parent = node.parent;
        if (node == this.root) this.root = b;
        else {
            if (node == node.parent.right_child) node.parent.right_child = b;
            else node.parent.left_child = b;
        }
        b.right_child = node;
        node.parent = b;
    }

    //левый поворот
    public void RedBlackLeftRotation(RedBlack node) {
        RedBlack b = node.right_child;
        node.right_child = b.left_child;
        if (b.left_child != null) b.left_child.parent = node;
        b.parent = node.parent;
        if (node.parent == null) this.root = b;
        else {
            if (node == node.parent.left_child) node.parent.left_child = b;
            else node.parent.right_child = b;
        }
        b.left_child = node;
        node.parent = b;
    }

    //большой правый поворот
    public void RedBlackBigRightRotation(RedBlack node) {
        RedBlackLeftRotation(node.right_child);
        RedBlackRightRotation(node);
    }

    //большой левый
    public void RedBlackBigLeftRotation(RedBlack node) {
        RedBlackRightRotation(node.left_child);
        RedBlackLeftRotation(node);
    }

    //высота дерева
    public int RedBlackTreeHeight(RedBlack node) {
        if (node == null) {
            return 0;
        }
        return (1 + Math.max(RedBlackTreeHeight(node.left_child), RedBlackTreeHeight(node.right_child)));
    }

    public void RedBlackPrint(String pr, RedBlack node, boolean left) {
        if (node == null) return;
        else {
            if (left) pr += "l ";
            else pr += "r ";
            System.out.print(pr);
            if (node.black) System.out.print(" black ");
            else System.out.print(" red ");
            System.out.println(node.key);
            RedBlackPrint(pr, node.right_child, false);
            RedBlackPrint(pr, node.left_child, true);
        }
    }

    public void RedBlackPrintSuccessor(RedBlack node) {
        if (node != null) {
            RedBlackPrintSuccessor(node.left_child);
            //System.out.println("Для элемента " + node.key + " следующий " + RedBlackSuccessor(node).key);
            RedBlackPrintSuccessor(node.right_child);
        }
    }

    public void RedBlackPrintPredecessor(RedBlack node) {
        if (node != null) {
            RedBlackPrintPredecessor(node.left_child);
            //System.out.println("Для элемента " + node.key + " предыдущий " + RedBlackPredecessor(node).key);
            RedBlackPrintPredecessor(node.right_child);
        }
    }

}
