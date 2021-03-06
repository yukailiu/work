package com.imooc.map;

/**
 * @author suyu
 * @version 1.0.0
 * @ClassName BST
 * @Description 基于二分搜索树创建的Map
 * @Date 2018/5/25 22:07
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    /**
     * 创建树节点
     */
    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    /**
     * 根节点
     */
    private Node root;
    /**
     * BSTMap大小
     */
    private int size;

    /**
     * 构造函数
     */
    public BSTMap() {
        root = null;
        size = 0;
    }


    @Override
    public void add(K key, V value) {
        //添加二分搜索树添加元素：根节点k-v值
        root = add(root, key, value);
    }

    /**
     * 二叉树递归添加K-V方法
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value) {
        //如果节点为空,创建新的树节点
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        //传入的值与树节点做对比,小于递归左子树,大于递归右子树
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else { //key.compareTo(node.key) == 0
            node.value = value;
        }
        return node;
    }


    /**
     * 返回以node为根的二分搜索树的最小值所在节点
     *
     * @param node node节点
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /**
     * 删除对应key的值
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除以node为根的二分搜索树中key的节点，递归算法
     *
     * @param node
     * @param key
     * @return 删除节点后的新节点
     */
    private Node remove(Node node, K key) {

        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { //key.compareTo(node.key)==0
            //删除左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //删除右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }


            //删除左右子树都不为空的情况
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }


    /**
     * 返回以node为根节点的二分搜索树中，key所在的节点
     * 递归遍历,根据传入的key值判断与节点的key值相比较,左右递归
     *
     * @param node 树节点
     * @param key  key值
     * @return node节点
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        //如果key等于节点中的key,返回node,否则遍历找下去
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    /**
     * 根据getNode方法判断是否包含节点
     *
     * @param key
     * @return
     */
    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    /**
     * 获取对应键的值
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 根据key,更新对应的value值
     *
     * @param key
     * @param newValue
     */
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        node.value = newValue;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
