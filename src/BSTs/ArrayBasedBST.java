package BSTs;

public class ArrayBasedBST<T extends Comparable<T>> implements BST<T> {

  private final static int DEFAULT_SIZE = 1024;
  private int size;
  private T[] items;

  public ArrayBasedBST() {
    this(DEFAULT_SIZE);
  }

  public ArrayBasedBST(int capacity) {
    this.size = 0;
    this.items = (T[]) new Comparable[capacity];
  }

  @Override
  public boolean add(T element) {
    // same kind of thing for add as contains but instead of returning if the
    // element is contained add the element at a position;
    return false;
  }

  @Override
  public boolean remove(T element) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean contains(T element) {
    return containsHelper(0, element);
  }

  private boolean containsHelper(int pos, T elem) {
    if (pos < 0 || pos > size) {
      return false;
    }
    if (items[pos] == null) {
      return false;
    } else {
      int compareTo = items[pos].compareTo(elem);
      if (compareTo == 0) {
        return true;
      }
      if (compareTo > 0) {
        int leftChild = getLeftChildIndex(pos);
        return containsHelper(leftChild, elem);
      } else {
        int rightChild = getRightChildIndex(pos);
        return containsHelper(rightChild, elem);
      }
    }
  }

  private int getLeftChildIndex(int parent) {
    return parent * 2 + 1;
  }

  private int getRightChildIndex(int parent) {
    return parent * 2 + 2;
  }

}
