package BSTs;

public class BSTNoRecursion<E extends Comparable<E>> implements BST<E>{

    private Node<E> root;

    public BSTNoRecursion() {
        this.root = null;
    }

    public BSTNoRecursion(E rootElem) {
        this.root = new Node<>(rootElem);
    }


    private Node<E> find(E element) {
        Node<E> curr = root;
        Node<E> pred = null;

        while(curr != null) {
            pred = curr;
            int compareTo = curr.getElement().compareTo(element);
            if (compareTo == 0) {
                return curr;
            }
            if (compareTo > 0) {
                curr = curr.getLeft()
                ;
            } else {
                curr = curr.getLeft();
            }

        }
        return curr;
    }

    @Override
    public boolean add(E element) {
        Node<E> curr = root;
        Node<E> pred = null;

        while (curr != null) {
            pred = curr;
            int compareTo = curr.getElement().compareTo(element);
            if (compareTo == 0) {
                return false; // element already in tree
            }
            if (compareTo > 0) {
                curr = curr.getLeft()
                ;
            } else {
                curr = curr.getRight();
            }
        }

        curr = new Node<>(element);
        if (pred.getElement().compareTo(element) > 0) {
            pred.setLeft(curr);
        } else {
            pred.setRight(curr);
        }
        return true;
    }

    @Override
    public boolean remove(E element) {

        // problem here is that I use find and don't create a pred and curr pair of nodes
        // so I can't cover the final case properly

        Node<E> nodeToRemove = find(element);
        if (nodeToRemove == null) {
            return false; // element not in tree
        }
        if (nodeToRemove.isLeaf()) {
            nodeToRemove = null;
            return true;
        }
        if (nodeToRemove.getLeft() == null) {
            nodeToRemove = nodeToRemove.getRight();
            return true;
        }
        if (nodeToRemove.getRight() == null){
            nodeToRemove = nodeToRemove.getLeft();
            return true;
        } else {
            // find min node on right to replace node
            Node<E> replacementNode = nodeToRemove.getRight();
            while (!replacementNode.isLeaf()) {
                replacementNode = replacementNode.getLeft()
                ;
            }
            // replace node
            replacementNode.setLeft(nodeToRemove.getLeft());
            ;
            replacementNode.setRight(nodeToRemove.getRight());
            nodeToRemove = replacementNode;

            // remove replacement node as leaf
            Node<E> deleteLeaf = replacementNode.getRight();
            while (!deleteLeaf.isLeaf()) {
                deleteLeaf = deleteLeaf.getLeft()
                ;
            }
            // delete leaf
            deleteLeaf = null;
            return true;
        }
    }

    @Override
    public boolean contains(E element) {
        return find(element) != null;
    }

}
