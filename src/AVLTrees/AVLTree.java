package AVLTrees;

import TreeStructures.BST;

public class AVLTree<T extends Comparable<T>> implements BST<T>{

  private AVLNode<T> root;


  public AVLTree() {
    this(null);
  }

  public AVLTree(AVLNode<T> root) {
    this.root = root;
  }


  @Override
  public boolean add(T element) {
    if (!contains(element)) {
      AVLNode<T> curr = root;
      AVLNode<T> pred = null;
      while (curr != null) {
        pred = curr;
        if (curr.getValue().compareTo(element) > 0) {
          curr = curr.getLeftChild();
        } else {
          curr = curr.getRightChild();
        }
      }
      curr = new AVLNode<T>(element);
      //rebalance();
      return true;
    }
    return false;
  }

  @Override
  public boolean remove(T element) {
    if (contains(element)) {
      AVLNode<T> curr = root;
      AVLNode<T> pred = null;
      if (curr.getValue().equals(element)) {
       AVLNode<T> leftChild = curr.getLeftChild();
       AVLNode<T> rightChild = curr.getRightChild();
       // do something with currs children
       curr = null;
      }
      while(curr != null) {
        pred = curr;
        // do some traversing to find curr (fairly trivial)
      }
      //rebalance();
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(T element) {
      AVLNode<T> curr = root;
      AVLNode<T> pred = null;
      while(curr != null) {
        if (curr.equals(element)) {
         return true;
        }
        pred = curr;
        if (curr.getValue().compareTo(element) > 0) {
          curr = curr.getLeftChild();
        } else {
          curr = curr.getRightChild();
        }
    }
    return false;
  }


  public int getHeight() {
    return root.getheight();
  }

}
