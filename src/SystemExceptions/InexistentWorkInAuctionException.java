package SystemExceptions;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 *
 * This exception is thrown when the work does not exist inside an auction
 */
public class InexistentWorkInAuctionException extends Exception implements Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
}
