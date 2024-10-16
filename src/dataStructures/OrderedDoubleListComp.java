package dataStructures;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public class OrderedDoubleListComp<K extends Comparable<K>,V> extends OrderedDoubleList<K,V> {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
    private final Comparator<K> comp;

    public OrderedDoubleListComp(Comparator<K> comp) {
        super();
        this.comp = comp;
    }

    @Override
    protected DoubleListNode<Entry<K,V>> findNode (K key){
        DoubleListNode<Entry<K,V>> node = head;
        while (node != null && comp.compare(node.getElement().getKey(), key) < 0) {
            node = node.getNext();
        }
        return node;
    }

    @Override
    public V find(K key) {
        DoubleListNode<Entry<K,V>> node = findNode(key);
        if((node != null) && (comp.compare(node.getElement().getKey(), key) == 0))
            return node.getElement().getValue();
        else return null;
    }

    @Override
    public V insert(K key, V value) {
        DoubleListNode<Entry<K,V>> node = findNode(key);
        if ((node!=null) && (comp.compare(node.getElement().getKey(), key) == 0)){
            V nodeValue = node.getElement().getValue();
            node.getElement().setValue(value);
            return nodeValue;
        }
        else {
            Entry<K,V> newNode = new EntryClass<>(key, value);
            if (node == head) {
                addFirst(newNode);
            } else {
                if (node != null)
                    addBeforeNode(newNode, node);
                else
                    addLast(newNode);
            }
        }
        return null;
    }
}
