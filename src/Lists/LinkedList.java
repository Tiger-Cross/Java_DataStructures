package Lists;


public class LinkedList<T> {

    private int length = 0;
    private Node<T> head;

    public LinkedList() {
        this.head = null;
    }

    public LinkedList(Node<T> head) {
        this.head = head;
    }

    public synchronized T get(int pos) {
        Node<T> pred = null;
        Node<T> curr = head;
        for (int i = 0; i < pos; i++) {
            pred = curr;
            curr = curr.getNext();
        }
        return curr.getElement();
    }

    public synchronized void add(T element) {
        // prepends the element at the end of the list;
        Node<T> pred = null;
        Node<T> curr = head;

        while(curr != null) {
            pred = curr;
            curr = curr.getNext();
        }
        Node<T> nodeToAdd = new Node<>(element);
        pred.setNext(nodeToAdd);
        length++;
    }

    public synchronized void add(int pos, T element) throws ListException {
        if (pos < 0 || pos > length) {
            throw new ListException("position out of range");
        }
        Node<T> nodeToAdd = new Node<>(element);
        if (head == null) {
            head = nodeToAdd;
        } else {
            Node<T> pred = null;
            Node<T> curr = head;
            for (int i = 0; i < pos; i++) {
                pred = curr;
                curr = curr.getNext();
            }
            pred.setNext(nodeToAdd);
            nodeToAdd.setNext(curr);
        }
        length++;
    }

    public synchronized void remove(int pos) throws ListException {
        if (pos < 0 || pos > length) {
            throw new ListException("position out of range");
        }
        if (head == null) {
            throw new ListException("list is empty");
        }
        if (pos == 0) {
            head = head.getNext();
        } else {
            Node<T> pred = null;
            Node<T> curr = head;
            for (int i = 0; i < pos; i++) {
                pred = curr;
                curr = curr.getNext();
            }
            pred.setNext(curr.getNext());
        }
        length--;
    }

    public synchronized boolean contains(T element) {
        Node<T> pred = null;
        Node<T> curr = head;

        while(curr != null) {
            pred = curr;
            if (curr.getElement().equals(element)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    public synchronized int getLength() {
        return length;
    }

}
