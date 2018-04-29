package Sets.Fine;

import java.util.concurrent.atomic.AtomicInteger;

public class FineSet<T> implements Sets.Set<T>{

  AtomicInteger size = new AtomicInteger(0);
  private LockableNode<T> head, tail;

  private Position<T> find(LockableNode<T> start, int key) {
    LockableNode<T> pred, curr;
    // lock pred and curr hand in hand
    pred = start;
    pred.lock();
    curr = start.getNext();
    curr.lock();
    while (curr.getKey() < key) {
      pred.unlock();
      pred = curr;
      curr = curr.getNext();
      curr.lock();
    }
    return new Position<>(pred, curr);
  }

  public boolean add(T item) {
    LockableNode<T> node = new LockableNode<T>(item);
    LockableNode<T> pred = null, curr = null;
    try {
      Position<T> where = find(head, node.getKey());
      pred = where.pred;
      curr = where.curr;

      if (node.getKey() == where.curr.getKey()) {
        return false;
      } else {
        node.setNext(where.curr);
        where.pred.setNext(node);
        size.incrementAndGet();
        return true;
      }

    } finally {
      pred.unlock();
      curr.unlock();
    }
  }

  public boolean remove(T item) {
    LockableNode<T> node = new LockableNode<T>(item);
    LockableNode<T> pred = null, curr = null;
    try {
      Position<T> where = find(head, node.getKey());
      pred = where.pred;
      curr = where.curr;

      if (node.getKey() < where.curr.getKey()) {
        return false;
      } else {
        where.pred.setNext(where.curr.getNext());
        size.decrementAndGet();
        return true;
      }

    } finally {
      pred.unlock();
      curr.unlock();
    }
  }

  public boolean contains(T item) {
    LockableNode<T> node = new LockableNode<T>(item);
    LockableNode<T> pred = null, curr = null;
    try {
      Position<T> where = find(head, node.getKey());
      pred = where.pred;
      curr = where.curr;
      return where.curr.getKey() == node.getKey();
    } finally {
      pred.unlock();
      curr.unlock();
    }
  }

  public int size() {
    return size.get();
  }

  private class Position<T> {

    private LockableNode<T> pred, curr;

    public Position(LockableNode<T> pred, LockableNode<T> curr) {
      this.pred = pred;
      this.curr = curr;
    }
  }
}
