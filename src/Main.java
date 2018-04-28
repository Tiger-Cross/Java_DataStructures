import BSTs.BST;
import BSTs.BSTNoRecursion;
import BSTs.BSTRecursion;

public class Main {

    public static void main(String[] args) {
        BST<Integer> tree = new BSTNoRecursion<>();

        tree.add(1);
        tree.add(4);
        tree.add(2);
        tree.add(3);
        tree.add(6);
        tree.add(5);
        System.out.println(tree);

        tree.remove(14);
        tree.remove(3);
        tree.remove(5);
        tree.remove(6);
        System.out.println(tree);

    }


}
