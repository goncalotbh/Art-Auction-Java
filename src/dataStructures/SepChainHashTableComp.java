package dataStructures;

/**
 * @author Gonçalo Oliveira 65549
 */
public class SepChainHashTableComp<K extends Comparable<K>,V> extends SepChainHashTable<K,V> {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    public SepChainHashTableComp( int capacity , Comparator<K> comp) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            //TODO: Original comentado para nao dar erro de compilacao.
            table[i] = new OrderedDoubleListComp<K,V>(comp);
        maxSize = capacity;
        currentSize = 0;
    }


    public SepChainHashTableComp(Comparator<K> comp) {
        this(DEFAULT_CAPACITY, comp);
    }
}
