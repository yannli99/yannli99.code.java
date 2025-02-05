package com.example.learning.demo.structure.tree;

import java.util.function.Consumer;

/**
 * 树的基本操作
 */
public class TreeOperation {
    public static void main(String[] args) {
        Integer[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        TreeNode<Integer> completeBinaryTree = createCompleteBinaryTree(arr, 0);
        preorder(completeBinaryTree);
        System.out.println();
        inorder(completeBinaryTree);
        System.out.println();
        postorder(completeBinaryTree);
        System.out.println();
    }

    /*------------------ 树的创建与构造方法 ------------------*/
    /*
     * 手动构造：直接创建 new TreeNode()，并分别赋值 left、right 指针。
     * 从数组/列表构造：对于完全二叉树或层序存储的树，常用的方式是按索引关系构造左右孩子。
     * 从前序/中序/后序遍历结果构造：比较经典的题型，比如给出前序、中序序列，重建二叉树。
     */

    /**
     * 从数组构造一颗完全二叉树
     * eg: arr:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
     * 输出：
     * 0
     * / \
     * 1 2
     * / \ / \
     * 3 4 5 6
     * / \ /
     * 7 8 9
     * 
     * @param <T>   树节点泛型参数
     * @param arr   数组
     * @param index 数组下标
     * @return
     */
    public static <T> TreeNode<T> createCompleteBinaryTree(T[] arr, int index) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        TreeNode<T> node = new TreeNode<T>(arr[index]);
        node.left = createCompleteBinaryTree(arr, 2 * index + 1);
        node.right = createCompleteBinaryTree(arr, 2 * index + 2);
        return node;
    }

    /*------------------ 遍历（Traversal） ------------------*/
    /*
     * (1) 深度优先遍历(DFS)
     * 
     * 前序遍历（Preorder：根 -> 左 -> 右）
     * 中序遍历（Inorder：左 -> 根 -> 右）
     * 后序遍历（Postorder：左 -> 右 -> 根）
     * 
     * (2) 广度优先遍历（BFS/层序遍历）
     * 需要一个队列逐层进行处理。
     */
    // 前序遍历 - 递归
    public static <T> void preorder(TreeNode<T> root) {
        if (root == null)
            return;
        visit(root.val); // 这里指打印或收集到列表
        preorder(root.left);
        preorder(root.right);
    }

    // 中序遍历 - 递归
    public static <T> void inorder(TreeNode<T> root) {
        if (root == null)
            return;
        inorder(root.left);
        visit(root.val);
        inorder(root.right);
    }

    // 后序遍历 - 递归
    public static <T> void postorder(TreeNode<T> root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        visit(root.val);
    }

    public static <T> void visit(T val) {
        visit(val + " ", System.out::print);
    }

    private static <T> void visit(T val, Consumer<T> action) {
        action.accept(val);
    }

    /*------------------ 其他常见操作 ------------------*/
    /*
     * 1、求树的高度：
     * 
     * 2、查找节点 / 判断值是否在树中：
     * 
     * 对于普通二叉树，只能遍历所有节点去查找；
     * 对于二叉搜索树（BST），可利用“左小右大”性质进行有序查找。
     * 
     * 3、统计节点数量 / 叶子数量：
     * 
     * 可类似求高度方式进行递归或层序遍历时计数。
     * 
     * 4、打印树的某些特定路径：如根到叶子路径、指定和的路径等。
     * 5、树的序列化与反序列化：
     * 
     * 前序/层序序列化 -> 字符串/数组；
     * 反序列化还原树结构。
     */
}
