package BSTs;

public class Node<E extends Comparable<E>> {

    private E getElement;
    private Node<E> right = null;
    private Node<E> left = null;


    public Node(E element) {
        this.getElement = element;
    }


    public E getElement() {
        return getElement;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }


}
