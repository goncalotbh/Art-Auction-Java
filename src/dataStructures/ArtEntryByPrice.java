package dataStructures;

import java.io.Serializable;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public class ArtEntryByPrice implements Comparable<ArtEntryByPrice>, Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The name of the art work
     */
    private final String name;

    /**
     * The highest price the art work has been previously sold for
     */
    private final int maxPrice;

    /**
     * An entry to be used in the AVL trees with the purpose of ordering the art works by their highest value of sale
     *
     * @param name the name of the art work
     * @param maxPrice the highest price the art work has been previously sold for
     */
    public ArtEntryByPrice(String name, int maxPrice) {
        this.name = name;
        this.maxPrice = maxPrice;
    }


    @Override
    public int compareTo(ArtEntryByPrice o) {
        if(maxPrice < o.getMaxPrice())
            return 1;
        if(maxPrice > o.getMaxPrice())
            return -1;
        return name.compareTo(o.getName());
    }

    /**
     * Returns the name of the art work
     * @return the name of the art work
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the art work's highest price of sale
     * @return the art work's highest price of sale
     */
    public int getMaxPrice() {
        return maxPrice;
    }
}
