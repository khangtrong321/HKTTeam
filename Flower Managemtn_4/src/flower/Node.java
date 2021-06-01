/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flower;

/**
 * Class Node
 * @author ROG
 */
public class Node {

    private Flower key;
    private Node leftChild;
    private Node rightChild;

    //Constructor

    /**
     * Create new Node with flower
     * @param key
     */
    public Node(Flower key) {
        this.key = key;
        leftChild = rightChild = null;
    }

    /**
     * Create new Node without flower
     */
    public Node() {
        this.key = null;
        leftChild = rightChild= null;
    }
    
    /**
     * Create node with Flower, leftchild and rightchild
     * @param key
     * @param leftChild
     * @param rightChild
     */
    public Node(Flower key, Node leftChild, Node rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    //Getter & Setter

    /**
     * get Key flower
     * @return
     */
    public Flower getKey() {
        return key;
    }

    /**
     * Set Key
     * @param key
     */
    public void setKey(Flower key) {
        this.key = key;
    }

    /**
     * get LeftChild
     * @return
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * set Left Child
     * @param leftChild
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * get Right Child
     * @return
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * Get right child
     * @param rightChild
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

}
