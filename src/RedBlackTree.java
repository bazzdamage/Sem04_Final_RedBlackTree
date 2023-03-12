import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RedBlackTree {
    private Node root;
    private int depth;

    private class Node {
        private int value;
        private Color color;
        private Node leftChild;
        private Node rightChild;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color=" + color +
                    '}';
        }
    }

    private enum Color {
        RED, BLACK
    }

    /**
     * Print tree with dfs method
     */
    public void printTree() {
        Node node = root;
        System.out.print("root -- ");
        printTree(node);
    }

    /**
     * Recursive print with start Node parameter
     * @param node Start node
     */
    private void printTree(Node node) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.print(depth + ". ");
        System.out.println(node);
        if (node.leftChild != null) {
            depth++;
            printTree(node.leftChild);
        }
        if (node.rightChild != null) {
            depth++;
            printTree(node.rightChild);
        }
        depth--;
    }

    /**
     * Add value in tree and balancing them if needed. Result is RedBlack Tree.
     * @param value integer value to add
     * @return true if create new node
     */
    public boolean add(int value) {
        if (root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    /**
     * Adding node to the existing one and rebalance tree as RedBlackTree
     * @param node Node, create in Add method
     * @param value value of Node
     * @return true if Node are created
     */
    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.leftChild != null) {
                    boolean result = addNode(node.leftChild, value);
                    node.leftChild = rebalance(node.leftChild);
                    return result;
                } else {
                    node.leftChild = new Node();
                    node.leftChild.color = Color.RED;
                    node.leftChild.value = value;
                    return true;
                }
            } else {
                if (node.rightChild != null) {
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                } else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }

            }
        }
    }

    /**
     * rebalance while needed. Method choose from rightSwap, leftSwap or colorSwap according conditions.
     * @param node node for rebalancing
     * @return Current balanced node
     */
    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.rightChild != null &&
                    result.rightChild.color == Color.RED &&
                    (result.leftChild == null ||
                            result.leftChild.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.leftChild != null &&
                    result.leftChild.color == Color.RED &&
                    result.leftChild.leftChild != null &&
                    result.leftChild.leftChild.color == Color.RED) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.leftChild != null &&
                    result.leftChild.color == Color.RED &&
                    result.rightChild != null &&
                    result.rightChild.color == Color.RED) {
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }

    private Node rightSwap(Node node) {
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    private Node leftSwap(Node node) {
        Node leftChild = node.leftChild;
        Node betweenChild = leftChild.rightChild;
        leftChild.rightChild = node;
        node.leftChild = betweenChild;
        leftChild.color = node.color;
        node.color = Color.RED;
        return leftChild;
    }

    private void colorSwap(Node node) {
        node.rightChild.color = Color.BLACK;
        node.leftChild.color = Color.BLACK;
        node.color = Color.RED;
    }
}
