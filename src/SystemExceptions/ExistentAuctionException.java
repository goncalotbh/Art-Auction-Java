package SystemExceptions;

import java.io.Serializable;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 *
 * This exception is thrown when there is already an existent auction with the given identifier
 */
public class ExistentAuctionException extends Exception implements Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
}
