package Sets.Fine;

import Sets.Node;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// should implement node instead of extending sequential
public class LockableNode<T> implements Sets.Node<T> {

  private Lock lock = new ReentrantLock();

  private int key;
  private T value;
  private LockableNode<T> next;

  public LockableNode(T value) {
    this(value, null);
  }

  public LockableNode(T value, LockableNode<T> next) {
    this(value.hashCode(), value, next);
  }

  public LockableNode(int key, T value, LockableNode<T> next) {
    this.key = key;
    this.value = value;
    this.next = next;
  }

  public void lock() {
    lock.lock();
  }

  public void unlock() {
    lock.unlock();
  }

  @Override
  public int getKey() {
    return key;
  }

  @Override
  public T getValue() {
    return value;
  }

  @Override
  public LockableNode<T> getNext() {
    return next;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public void setKey(int key) {
    this.key = key;
  }

  @Override
  public void setNext(Node<T> next) {
    this.next = (LockableNode<T>) next;
  }
}
