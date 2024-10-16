package ArtAuctions;

import java.io.Serializable;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public interface Bid extends Serializable {

    /**
     * Returns the user who made the bid
     *
     * @return the user who made the bid
     */
    SystemUser getBidder();

    /**
     * Returns the value of the bid
     *
     * @return the bid's value
     */
    int getValue();
}
