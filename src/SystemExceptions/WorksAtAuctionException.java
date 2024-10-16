package SystemExceptions;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 *
 * This exception is thrown when an artist is being removed from the system but still has works in auction
 */
public class WorksAtAuctionException extends Exception implements Serializable {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
}
