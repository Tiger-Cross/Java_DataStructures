package Sets.Sequential;

import Sets.Node;

public class SequentialNode<T> implements Node<T> {

  private int key;
  private T value;
  private Node<T> next;

  public SequentialNode(T value) {
    this(value, null);
  }

  public SequentialNode(T value, Node<T> next) {
    this(value.hashCode(), value, next);
  }

  public SequentialNode(int key, T value, Node<T> next) {
    this.key = key;
    this.value = value;
    this.next = next;
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
  public Node<T> getNext() {
    return next;
  }

  @Override
  public void setKey(int key) {
    this.key = key;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public void setNext(Node<T> next) {
    this.next = next;
  }
}
