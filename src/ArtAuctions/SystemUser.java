package ArtAuctions;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 */
public interface SystemUser extends Serializable {

    /**
     * Returns the login of a user
     *
     * @return a user's login
     */
    String getLogin();

    /**
     * Returns the name of the user
     *
     * @return the user's name
     */
    String getName();

    /**
     * Returns the email of the user
     *
     * @return the user's email
     */
    String getEmail();

    /**
     * Returns the age of the user
     *
     * @return the user's age
     */
    int getAge();

    /**
     * Increases the number of active bids a user has
     */
    void incActiveBids();

    /**
     * Decreases the number of active bids a user has
     */
    void decActiveBids();

    /**
     * Checks if the user has active bids or not
     *
     * @return true, if it has any active bids
     */
    boolean hasActiveBids();

    /**
     * Checks if the user is an artist
     *
     * @return true, if the user is an artist
     */
    boolean isArtist();

}
