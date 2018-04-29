package Lists;

import java.util.stream.IntStream;

public class ArrayList<E> implements List<E>{

  private static final int DEFAULT_INITIAL_SIZE = 100;
  private int size;
  private E[] items;

  public ArrayList(int initialSize) {
    this.items = (E[]) new Object[initialSize];
    this.size = 0;
  }

  public ArrayList() {
    this(DEFAULT_INITIAL_SIZE);
  }

  @Override
  public void add(E item) {
    if (size < items.length) {
      items[size] = item;
    } else {
      E[] biggerList = (E[]) new Object[size * 2];
      for (int i = 0; i < size; i++) {
        biggerList[i] = items[i];
      }
      biggerList[size] = item;
      this.items = biggerList;
    }
    size++;
  }

  @Override
  public void add(E item, int position) throws ArrayIndexOutOfBoundsException{
    if (size < items.length) {
      if (position < 0 || position > size) {
        throw new ArrayIndexOutOfBoundsException();
      }
      shiftSubArrayRight(position);
      items[position] = item;
      size++;
    }
  }

  private void shiftSubArrayRight(int position) {
    for (int i = size + 1; i > position; i--) {
      items[i] = items[i-1];
    }
  }

  @Override
  public E get(int position) {
    if (position < 0 || position > size) {
      return null;
    }
    return items[position];
  }

  @Override
  public void remove(int position) throws ArrayIndexOutOfBoundsException{
    if (position < 0 || position > size) {
      throw new ArrayIndexOutOfBoundsException();
    }
    shiftSubArrayLeft(position);
    size--;
  }

  private void shiftSubArrayLeft(int position) {
    IntStream.range(position, size).forEach(i -> items[i] = items[i+1]);
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }
}
