package Lists;

public interface List<E> {

  void add(E item);

  void add(E item, int position);

  E get(int position);

  void remove(int Position);

  int size();

  boolean isEmpty();
}
