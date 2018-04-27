package Lists;
@SuppressWarnings("unchecked")
public class ArrayList<T> {

    private static final int MAX_ARRAY_SIZE = 512;
    private int size;
    private T[] items;

    public ArrayList() {
        this.items = (T[]) new Object[MAX_ARRAY_SIZE];
    }

    public ArrayList(int size) {
        this.items = (T[]) new Object[size];
    }
}
