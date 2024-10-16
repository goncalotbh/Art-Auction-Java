package SystemExceptions;

import java.io.Serializable;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 *
 * This exception is thrown when the value of the bid made is lower than the minimum value accepted
 */
public class BidsActiveException extends Exception implements Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
}
