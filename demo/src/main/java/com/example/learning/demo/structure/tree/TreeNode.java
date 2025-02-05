package com.example.learning.demo.structure.tree;

/**
 * 树的基本结构：树节点
 */
public class TreeNode<T> {

    /**
     * 存储节点数据
     */
    T val;
    /**
     * 左孩子
     */
    TreeNode<T> left;
    /**
     * 右孩子
     */
    TreeNode<T> right;

    public TreeNode() {
    }

    public TreeNode(T val) {
        this.val = val;
    }

    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
