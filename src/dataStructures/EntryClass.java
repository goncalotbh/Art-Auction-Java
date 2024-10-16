package dataStructures;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public class EntryClass<K,V> implements Entry<K,V> {

    /**
     * The key stored in the entry
     */
    private K entryK;

    /**
     * The value in the entry
     */
    private V entryV;

    /**
     *
     * @param keyK - the key associated with the entry
     * @param valueV - the value of the entry
     */
    public EntryClass( K keyK, V valueV) {
        entryK = keyK;
        entryV = valueV;
    }

    @Override
    public K getKey() {
        return entryK;
    }

    @Override
    public V getValue() {
        return entryV;
    }

    @Override
    public void setKey(K newKey) {
        entryK = newKey;
    }

    @Override
    public void setValue(V newValue) {
        entryV = newValue;
    }
}
