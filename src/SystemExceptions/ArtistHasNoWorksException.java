package SystemExceptions;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 *
 * This exception is thrown when an artist hasn't elaborated any works
 */
public class ArtistHasNoWorksException extends Exception implements Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
}

