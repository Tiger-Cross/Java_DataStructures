package LinearStructures.Sets.Coarse;

import LinearStructures.Sets.Sequential.SequentialSet;
import LinearStructures.Sets.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OtherCoarseSet<E> extends SequentialSet<E> implements Set<E> {

  Lock lock = new ReentrantLock();


  @Override
  public boolean add(E item) {
    try{
      lock.lock();
      return super.add(item);
  } finally {
    lock.unlock();
    }
  }

  @Override
  public boolean remove(E item) {
    try {
      lock.lock();
      return super.remove(item);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean contains(E item) {
    try {
      lock.lock();
      return super.contains(item);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int size() {
    try {
      lock.lock();
      return super.size();
    } finally {
      lock.unlock();
    }
  }
}
