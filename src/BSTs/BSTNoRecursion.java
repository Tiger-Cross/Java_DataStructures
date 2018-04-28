package BSTs;

import java.util.stream.IntStream;

@SuppressWarnings("unchecked")
public class BSTNoRecursion<E extends Comparable<E>> implements BST<E>{

    // Overcomplicated this massively. It's all wrong.

    private Node<E> root;

    public BSTNoRecursion() {
        this.root = null;
    }

    public BSTNoRecursion(E rootElem) {
        this.root = new Node<>(rootElem);
    }


    private ParentChild<E> find(E element) {
        Node<E> curr = root;
        Node<E> pred = null;

        while(curr != null) {
            pred = curr;
            int compareTo = curr.getElement().compareTo(element);
            if (compareTo == 0) {
                return new ParentChild(pred, curr);
            }
            if (compareTo > 0) {
                curr = curr.getLeft();
            } else {
                curr = curr.getRight();
            }

        }
        return new ParentChild<>(pred, curr);
    }

    @Override
    public boolean add(E element) {
        ParentChild pAndC = find(element);

        if (pAndC.parent == null) {
            root = new Node<>(element);
            return true;
        }
        if (pAndC.child != null) {
            return false;
        }

        if (pAndC.parent.getElement().compareTo(element) > 0) {
            pAndC.parent.setLeft(new Node(element));
            return true;
        } else {
            pAndC.parent.setRight(new Node(element));
            return true;
        }
    }

    @Override
    public boolean remove(E element) {
        ParentChild pAndC = find(element);
        if (pAndC.parent == null) {
            return false; // tree is empty;
        }
        if (pAndC.child == null) {
            return false; // element not in tree
        } else if (pAndC.child.isLeaf()) {
            replaceChild(pAndC, null);
        } else if (pAndC.child.getLeft() == null && pAndC.child.getRight() != null) {
            replaceChild(pAndC, pAndC.child.getRight());
        } else if (pAndC.child.getLeft() != null && pAndC.child.getRight() == null) {
            replaceChild(pAndC, pAndC.child.getLeft());
        } else {
            Node<E> replacementNode = pAndC.child.getRight();
            while (replacementNode.getLeft() != null) {
                replacementNode = replacementNode.getLeft();
            }
            remove(replacementNode.getElement());
            replacementNode.setLeft(pAndC.child.getLeft());
            replacementNode.setRight(pAndC.child.getRight());

            replaceChild(pAndC, replacementNode);
        }
        return true;
    }

    private void replaceChild(ParentChild<E> pAndC, Node<E> replacementChild) {
        if (pAndC.parent.getElement().compareTo(pAndC.child.getElement()) > 0) {
            pAndC.parent.setLeft(replacementChild);
        } else {
            pAndC.parent.setRight(replacementChild);
        }
    }

    @Override
    public boolean contains(E element) {
        return find(element).child != null;
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


    private class ParentChild<T extends Comparable<T>> {

        private Node<T> parent;
        private Node<T> child;

        public ParentChild(Node<T> parent, Node<T> child) {
            this.parent = parent;
            this.child = child;
        }
    }

}
