package SystemExceptions;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 *
 * This exception is thrown when there are no bids for the given work inside the auction
 */
public class NoBidsInEntryException extends Exception implements Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
}
