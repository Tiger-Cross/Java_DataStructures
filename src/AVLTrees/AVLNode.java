package AVLTrees;

public class AVLNode<T> {

  private T value;
  private AVLNode<T> leftChild;
  private AVLNode<T> rightChild;

  public AVLNode(T value) {
    this(value, null, null);
  }

  public AVLNode(T value, AVLNode<T> leftChild, AVLNode<T> rightChild) {
    this.value = value;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

  public T getValue() {
    return value;
  }

  public AVLNode<T> getLeftChild() {
    return leftChild;
  }

  public void setLeftChild(AVLNode<T> leftChild) {
    this.leftChild = leftChild;
  }

  public AVLNode<T> getRightChild() {
    return rightChild;
  }

  public void setRightChild(AVLNode<T> rightChild) {
    this.rightChild = rightChild;
  }

  public int getheight() {
    return (leftChild == null && rightChild == null) ? 0 :
        1 + Math.max(leftChild.getheight(), rightChild.getheight());
  }
}
