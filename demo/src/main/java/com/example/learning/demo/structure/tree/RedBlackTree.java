package com.example.learning.demo.structure.tree;

import java.util.TreeMap;

/**
 * 红黑树，具体参考openjdk的 {@link TreeMap} 中的实现。
 *
 * <p>性质：一棵合法的红黑树必须遵循以下四条性质：
 *
 * <p>1、节点为红色或黑色
 *
 * <p>2、NIL 节点（空叶子节点）为黑色
 *
 * <p>3、红色节点的子节点为黑色
 *
 * <p>4、从根节点到 NIL 节点的每条路径上的黑色节点数量相同
 *
 * @author liyan
 */
public class RedBlackTree<K, V> {
    /* 红黑颜色 */
    private static final boolean RED = false;
    private static final boolean BLACK = false;
    /*  红黑树节点结构 */
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        boolean color = BLACK;
        Node(K key,V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
        public K getKey() {
            return key;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public V getValue() {
            return value;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
    /* 根节点 */
    private transient Node<K, V> root;

    /* 基本操作方法 */
    private static <K, V> boolean colorOf(Node<K, V> p) {
        return (p == null ? BLACK : p.color);
    }
    private static <K, V> void setColor(Node<K, V> p, boolean color) {
        if (p != null) {
            p.color = color;
        }
    }
    private static <K, V> Node<K, V> leftOf(Node<K, V> p) {
        return (p == null ? null : p.left);
    }
    private static <K, V> Node<K, V> rightOf(Node<K, V> p) {
        return (p == null ? null : p.right);
    }
    private static <K, V> Node<K, V> parentOf(Node<K, V> p) {
        return (p == null ? null : p.parent);
    }
    /* 节点获取 */
    /**获取有序的第一个节点*/
    final Node<K, V> getFirstNode() {
        Node<K, V> p = root;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
        }
        return p;
    }
    /**获取有序的最后一个节点*/
    final Node<K, V> getLastNode() {
        Node<K, V> p = root;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
        }
        return p;
    }
    /**获取后驱节点*/
    static <K, V> Node<K, V> successor(Node<K, V> t) {
        if (t == null) {
            return null;
        }
        /*如果右子树存在，找右子树的最左节点*/
        else if (t.right != null) {
            Node<K, V> p = t.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        /*如果右子树不存在，向上找第一个左拐的父节点*/
        else {
            Node<K, V> p = t.parent;
            Node<K, V> ch = t;
            /*沿着父链往上找，满足父节点不为空且ch不是父节点的右孩子时退出，得到的就是第一个左拐的p节点*/
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            // p 是第一个使得 ch 是 p 左子节点的父节点
            return p;
        }
    }
    /**获取前驱节点*/
    static <K, V> Node<K, V> predecessor(Node<K, V> t) {
        if (t == null) {
            return null;
        }
        /*如果左子树存在，找左子树的最右点*/
        else if (t.left != null) {
            Node<K, V> p = t.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        /*如果左子树不存在，向上找第一个右拐的父节点*/
        else {
            Node<K, V> p = t.parent;
            Node<K, V> ch = t;
            while (p != null && ch == p.left) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }
    /* 旋转操作 */
    /**
     * 对p节点左旋，p的右孩子会上移变成p的父节点，
     * p变成右孩子的左节点，p的右孩子的左孩子变成p的右孩子
     *
     */
    private void rotateLeft(Node<K, V> p) {
        if (p != null) {
            //p的右孩子r
            Node<K, V> r = p.right;
            //p的新右孩子是r的左孩子
            p.right = r.left;
            //更新r的左孩子的父亲
            if (r.left != null) {
                r.left.parent = p;
            }
            //r顶替了p
            r.parent = p.parent;
            if (p.parent == null) {
                //更新根节点
                root = r;
            } else if (p.parent.left == p) {
                //如果p的父亲的左孩子是p，那么换成r
                p.parent.left = r;
            } else {
                //如果p的父亲的右孩子是p，那么换成r
                p.parent.right = r;
            }
            //p变成了r的左孩子
            r.left = p;
            p.parent = r;
        }
    }
    /**
     * 右旋操作，左旋操作的镜像
     * 对p节点右旋，p的左孩子会上移变成p的父节点，
     * p变成左孩子的右孩子节点，p的左孩子的右孩子变成p的左孩子
     */
    private void rotateRight(Node<K, V> p) {
        if (p != null) {
            //p的左孩子l
            Node<K, V> l = p.left;
            //p的新左孩子是l的右孩子
            p.right = l.right;
            //更新l的右孩子的父亲
            if (l.right != null) {
                l.right.parent = p;
            }
            //l顶替了p
            l.parent = p.parent;
            if (p.parent == null) {
                //更新根节点
                root = l;
            } else if (p.parent.right == p) {
                //如果p的父亲的右孩子是p，那么换成l
                p.parent.right = l;
            } else {
                //如果p的父亲的左孩子是p，那么换成l
                p.parent.left = l;
            }
            //p变成了l的右孩子
            l.right = p;
            p.parent = l;
        }
    }

    /*
     * 红黑树的插入操作与普通的 BST 类似，对于红黑树来说，新插入的节点初始为红色，
     * 完成插入后需根据插入节点及相关节点的状态进行修正以满足红黑树的四条性质。
     *
     * 插入后的平衡维护：
     *
     * case1：该树原先为空，插入第一个节点后不需要进行修正。
     *
     * case2：当前的节点的父节点为黑色且为根节点，这时性质已经满足，不需要进行修正。
     *
     * case3：当前节点 N 的父节点 P 是为根节点且为红色，将其染为黑色即可，此时性质也已满足，不需要进一步修正。

     * case4：当前节点 N 的父节点 P 和叔节点 U 均为红色，此时 P 包含了一个红色子节点，违反了红黑树的性质，需要进行重新染色。
     * 由于在当前节点 N 之前该树是一棵合法的红黑树，根据性质 3 可以确定 N 的祖父节点 G 一定是黑色，
     * 这时只要后续操作可以保证以 G 为根节点的子树在不违反性质 4 的情况下再递归维护祖父节点 G 以保证性质 3 即可。
     * 因此，这种情况的维护需要：
     * 1、将 P，U 节点染黑，将 G 节点染红（可以保证每条路径上黑色节点个数不发生改变）。
     * 2、递归维护 G 节点（因为不确定 G 的父节点的状态，递归维护可以确保性质 3 成立）。
    // Case 4: Both parent and uncle are RED
    //   Paint parent and uncle to BLACK;
    //   Paint grandparent to RED.
    //        [GB]             <GR>
    //        / \             / \
    //      <PR> <UR>  ====>  [PB] [UB]
    //      /               /
    //    <NR>             <NR>
     *
     * 当前节点 N 与父节点 P 的方向相反（即 N 节点为右子节点且父节点为左子节点，或 N 节点为左子节点且父节点为右子节点。类似 AVL 树中 LR 和 RL 的情况）。
     * 根据性质 4，若 N 为新插入节点，U 则为 NIL 黑色节点，否则为普通黑色节点。
     * 该种情况无法直接进行维护，需要通过旋转操作将子树结构调整为 Case 6 的初始状态并进入 Case 6 进行后续维护。
     *
    // Case 5: Current node is the opposite direction as parent
    //   Step 1. If node is a LEFT child, perform l-rotate to parent; RL
    //           If node is a RIGHT child, perform r-rotate to parent. LR
    //   Step 2. Goto Case 6.
    //      [GB]                   [GB]
    //      / \    rotate(P)       / \
    //    <PR> [UB]  ========>  <NR> [UB]
    //      \                   /
    //      <NR>              <PR>
    *
    * 当前节点 N 与父节点 P 的方向相同（即 N 节点为右子节点且父节点为右子节点，或 N 节点为左子节点且父节点为左子节点。类似 AVL 树中 LL 和 RR 的情况）。
    * 根据性质 4，若 N 为新插入节点，U 则为 NIL 黑色节点，否则为普通黑色节点。
    * 因此，这种情况的维护需要：
        1、若 N 为左子节点则右旋祖父节点 G，否则左旋祖父节点 G.（该操作使得旋转过后 P - N 这条路径上的黑色节点个数比 P - G - U 这条路径上少 1，暂时打破性质 4）。
        2、重新染色，将 P 染黑，将 G 染红，同时满足了性质 3 和 4。
    *
    // Case 6: Current node is the same direction as parent
    //   Step 1. If node is a LEFT child, perform r-rotate to grandparent;
    //           If node is a RIGHT child, perform l-rotate to grandparent.
    //   Step 2. Paint parent (before rotate) to BLACK;
    //           Paint grandparent (before rotate) to RED.
    //        [GB]                   <PR>               [PB]
    //        / \    rotate(G)       / \    repaint     /   \
    //      <PR> [UB]  ========>  <NR> [GB]  ======>  <NR> <GR>
    //      /                            \                    \
    //    <NR>                           [UB]                  [UB]
     *
     */
    private void fixAfterInsertion(Node<K,V> x) {
        //新插入的节点初始为红色
        x.color = RED;
        //如果x不是根节点并且x的父节点的颜色不是黑色，那么就要修复树
        while (x != null && x != root && x.parent.color == RED) {
            //x的父节点是祖父节点的左孩子（平衡树中左小右大）
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                //获取y是祖父节点的右孩子，x的叔节点
                Node<K,V> y = rightOf(parentOf(parentOf(x)));
                /*
                    case 4:
                    Paint parent and uncle to BLACK;
                    Paint grandparent to RED.
                */
                //如果y是红色的
                if (colorOf(y) == RED) {
                    //设置x的父节点为黑色
                    setColor(parentOf(x), BLACK);
                    //设置y的颜色为黑色
                    setColor(y, BLACK);
                    //设置祖父节点是红色
                    setColor(parentOf(parentOf(x)), RED);
                    //修复祖父节点之上的节点颜色
                    x = parentOf(parentOf(x));
                }
                //y是黑色的
                else {
                    //如果x是右孩子节点
                    if (x == rightOf(parentOf(x))) {
                        //对x的父节点左旋
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    //x的父节点设为黑色
                    setColor(parentOf(x), BLACK);
                    //x的祖父节点设为红色
                    setColor(parentOf(parentOf(x)), RED);
                    //x的祖父节点进行右旋
                    rotateRight(parentOf(parentOf(x)));
                }
            }
            //x的父节点是祖父节点的右孩子，或者x的父节点是根节点
            else {
                //获取y是祖父节点的右孩子，x的叔节点
                Node<K,V> y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                }
                //考虑x的父节点是根节点的情况下，y是空
                else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    //x的父节点设为黑色
                    setColor(parentOf(x), BLACK);
                    //x的祖父节点设为红色
                    setColor(parentOf(parentOf(x)), RED);
                    //x的祖父节点进行右旋
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }
}
