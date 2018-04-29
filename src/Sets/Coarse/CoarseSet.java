package Sets.Coarse;

import Sets.Node;
import Sets.Sequential.SequentialNode;

public class CoarseSet<E> implements Sets.Set<E> {

  private int size = 0;
  private Node<E> head, tail;

  public CoarseSet() {
    this.head = new SequentialNode<>(Integer.MIN_VALUE,null, null);
    this.tail = new SequentialNode<>(Integer.MAX_VALUE,null, null);
  }

  private Position<E> find(Node<E> start, int key) {
    Node<E> pred, curr;
    curr = start;
    do {
      pred = curr;
      curr = curr.getNext();
    } while (curr.getKey() < key);
    return new Position<>(pred, curr);
  }

  @Override
  public synchronized boolean add(E item) {
    SequentialNode<E> node = new SequentialNode<>(item);
    Position<E> where = find(head, node.getKey());
    if (where.curr.getKey() <= node.getKey()) {
      node.setNext(where.curr);
      where.pred.setNext(node);
      size++;
      return true;
    }
    return false;
  }

  @Override
  public synchronized boolean remove(E item) {
    SequentialNode<E> node = new SequentialNode<>(item);
    Position where = find(head, node.getKey());
    if (where.curr.getKey() <= node.getKey()) {
      where.pred.setNext(where.curr.getNext());
      size--;
      return true;
    }
    return false;
  }

  @Override
  public synchronized boolean contains(E item) {
    int itemKey = item.hashCode();
    Position<E> expectedPosition = find(head, itemKey);

    return expectedPosition.curr.getKey() == itemKey;
  }

  @Override
  public synchronized int size() {
    return this.size;
  }

  private static class Position<T> {

    final Node<T> pred, curr;

    public Position(Node<T> pred, Node<T> curr) {
      this.pred = pred;
      this.curr = curr;
    }
  }

}
