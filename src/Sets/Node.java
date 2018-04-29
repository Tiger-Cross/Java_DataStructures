package Sets;

public interface Node<T> {

  int getKey();

  T getValue();

  Node<T> getNext();

  void setValue(T value);

  void setKey(int key);

  void setNext(Node<T> next);

}
