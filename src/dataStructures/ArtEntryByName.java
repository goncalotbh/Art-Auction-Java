package dataStructures;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 */
public class ArtEntryByName implements Comparable<ArtEntryByName>, Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The art work's unique identifier
     */
    private final String id;

    /**
     * The name of the art work
     */
    private final String name;

    /**
     * An entry to be used in the AVL trees with the purpose of ordering the art works by name
     *
     * @param id the art work's unique identifier
     * @param name the name of the art work
     */
    public ArtEntryByName(String id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public int compareTo(ArtEntryByName o) {
        if(id.equals(o.getID()))
            return 0;
        return name.compareTo(o.getName());
    }

    /**
     * Returns the art work's ID
     * @return the art work's ID
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the name of the art work
     * @return the name of the art work
     */
    public String getName() {
        return name;
    }

}

