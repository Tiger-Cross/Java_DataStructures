package BSTs;

public class BSTRecursion<E extends Comparable<E>> implements BST<E> {

    private Node<E> root;


    public BSTRecursion() {
        this.root = null;
    }

    public BSTRecursion(Node<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(E element) {
        return false;
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean contains(E element) {
        return contains(root, element);
    }

    private boolean contains(Node<E> subtree, E element) {
        if (subtree == null) {
            return false;
        }
        int compareTo = subtree.getElement().compareTo(element);
        if (compareTo == 0) {
            return true;
        } else if (compareTo > 0) {
            return  contains(subtree.getLeft(), element);
        } else {
            return contains(subtree.getRight(), element);
        }
    }

    public int height() {
        return heightHelper(root);
    }

    private int heightHelper(Node<E> subtree) {
        if (subtree == null) {
            return 0;
        }
        return 1 + Math.max(heightHelper(subtree.getLeft()), heightHelper(subtree.getRight()));
    }
}
