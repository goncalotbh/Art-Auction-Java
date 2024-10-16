package dataStructures;

/**
 * BinarySearchTreeIterator implementation
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 * @version 1.0
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
public class BinarySearchTreeIterator<K, V> implements Iterator<Entry<K,V>>{

    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;

    /**
     * The stack where the binary tree will be inserted
     */
    private Stack<BSTNode<K,V>> stack;

    /**
     * The root of the tree.
     */
    private final BSTNode<K,V> root;


    public BinarySearchTreeIterator(BSTNode<K,V> root) {
        stack = new StackInList<>();
        this.root = root;
        pushPath(root);
    }

    private void pushPath(BSTNode<K,V> root) {
        stack.push(root);
        BSTNode<K,V> currNode = root;
        while(currNode.getLeft() != null) {
            stack.push(currNode.getLeft());
            currNode = currNode.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Entry<K,V> next() throws NoSuchElementException {
        if(stack.isEmpty())
            throw new NoSuchElementException();

        BSTNode<K,V> retNode = stack.pop();
        if(retNode.getRight() != null)
            pushPath(retNode.getRight());

        return retNode.getEntry();
    }

    @Override
    public void rewind() {
        stack = new StackInList<>();
        pushPath(root);
    }
}
