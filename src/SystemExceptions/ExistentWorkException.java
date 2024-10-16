package SystemExceptions;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 *
 * This exception is thrown when the ID of the work already exists in the system
 */
public class ExistentWorkException extends Exception implements Serializable {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;
}
