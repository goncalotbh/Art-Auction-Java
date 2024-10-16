package SystemExceptions;

import java.io.Serializable;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 *
 * This exception is thrown when there is already a user registered with the given login
 */
public class ExistentUserException extends Exception implements Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
}
