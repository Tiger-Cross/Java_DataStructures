package BSTs;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

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
        if (root == null) {
            root = new Node<>(element);
            return true;
        }
        return add(root, element);
    }

    private boolean add(Node<E> subtree, E element) {
        int compareTo = subtree.getElement().compareTo(element);
        if (compareTo == 0) {
            return false; // element already in tree
        } else if (compareTo > 0) {
            if (subtree.getLeft() == null) {
             subtree.setLeft(new Node<>(element));
            }
            return add(subtree.getLeft(), element);
        } else {
            if (subtree.getRight() == null) {
                subtree.setRight(new Node<>(element));
            }
            return add(subtree.getRight(), element);
        }
    }

    @Override
    public boolean remove(E element) {
        Set<Node<E>> deletedNode = new HashSet<>();
        root = remove(root, element, deletedNode);
        return !deletedNode.isEmpty();
    }

    private Node<E> remove(Node<E> subtree, E element, Set<Node<E>> deletedNode) {
        if (subtree == null) {
            return null; // node not in tree
        }
        int compareTo = subtree.getElement().compareTo(element);
        if (compareTo > 0) {
         subtree.setLeft(remove(subtree.getLeft(), element, deletedNode));
        } else if (compareTo < 0) {
            subtree.setRight(remove(subtree.getRight(), element, deletedNode));
        } else {
            deletedNode.add(subtree);
            subtree = deleteNode(subtree);
        }
        return subtree;
    }

    /* For the deletion there are 3 cases

    * the node to remove is a leaf in which we can set its parent's pointer to null
    * the node has one child in chich it's parents pointer inherits this child
    * the node has two children in whihc we have to find the miniMax or maxiMin node and replace the node with that.

     */
    private Node<E> deleteNode(Node<E> subtree) {
        if (subtree.getLeft() == null && subtree.getRight() == null) {
            return null;
        } else if (subtree.getLeft() == null && subtree.getRight() != null) {
            return subtree.getRight();
        } else if (subtree.getLeft() == null && subtree.getRight() != null) {
            return subtree.getLeft();
        } else {
            Node<E> replacementNode = findMin(subtree.getRight());
            remove(replacementNode.getElement());
            replacementNode.setLeft(subtree.getLeft());
            replacementNode.setRight(subtree.getRight());
            return replacementNode;
        }
    }

    private Node<E> findMin(Node<E> subtree) {
        if (subtree.getLeft() == null) {
            return subtree;
        }
        return findMin(subtree.getLeft());
    }

    private Node<E> findMax(Node<E> subtree) {
        if (subtree.getRight() == null) {
            return subtree;
        }
        return findMin(subtree.getRight());
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        toString(root, 0, stringBuilder);
        return stringBuilder.toString() + '\n';
    }

    private void toString(Node<E> node, int indentation, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }
        IntStream.range(0, indentation).forEach(i -> stringBuilder.append("  "));
        stringBuilder.append(node.getElement()+ "\n");
        toString(node.getLeft(), indentation + 1, stringBuilder);
        toString(node.getRight(), indentation + 1, stringBuilder);
    }


}
