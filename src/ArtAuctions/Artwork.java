package ArtAuctions;

import java.io.Serializable;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public interface Artwork extends Serializable {

    /**
     * Returns the work's unique identifier
     *
     * @return the work's ID
     */
    String getID();

    /**
     * Returns the artistic name of the artist.
     *
     * @return the name of the artist.
     */
    Artist getAuthor();

    /**
     * Returns the name of the work
     *
     * @return the work's name
     */
    String getName();

    /**
     * Returns the year in which the work was made
     *
     * @return the year in which the work was made
     */
    int getYear();

    /**
     * Returns the last price at which the work was sold
     *
     * @return the work's last sale price
     */
    int getLastPrice();

    /**
     * Returns the highest price at which the work has ever been sold
     *
     * @return the work's highest price
     */
    int getHighestPrice();

    /**
     * Returns the highest bid made for the work
     *
     * @return the work's highest bid
     */
    Bid getHighestBid();

    /**
     * Updates the highest bid made for the work
     *
     * @param bid the new highest bid for the work
     */
    void updateHighestBid(Bid bid);

    /**
     * Sets the work's last price of sale
     *
     * @param value the value of the sale
     */
    void setLastPrice(int value);
}
