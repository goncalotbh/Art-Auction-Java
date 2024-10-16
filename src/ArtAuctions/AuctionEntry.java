package ArtAuctions;

import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 */
public interface AuctionEntry extends Serializable {

    /**
     * Returns the minimum value allowed for a bid
     *
     * @return the minimum value for a bid
     */
    int getMinimumValue();

    /**
     * Adds a bid for the art work corresponding to the auction entry
     *
     * @param bid the bid made for the art work
     */
    void addBid(Bid bid);

    /**
     * Returns the artwork that will be auctioned
     *
     * @return the artwork to be auctioned
     */
    Artwork getArtwork();

    /**
     * Sells the bid for the highest bid, closing the auction for an artwork
     *
     * @param bid the winning bid
     */
    void sell(Bid bid);

    /**
     * Analyses all the entry's bids and checks if its artwork was sold.
     * The entry stores the first and highest bid that was registered, if there is a winner bid registered,
     * the artwork was sold and the buyer is the bid's author, else, the artwork was not sold.
     *
     * @return a string to be printed in closeAuction static method which gives info on whether the artwork was/wasn't sold.
     */
    String processBids();

    /**
     * Returns an iterator that goes through all the bids made for an art work
     *
     * @return an iterator that goes through all the bids made for an art work
     */
    Iterator<Bid> getBids();

    /**
     * Checks if the work from the entry has bids or not
     *
     * @return true if the work doesn't have any bids
     */
    boolean hasNoBids();

    /**
     * Removes all existing bids related to an auction entry when it closes
     */
    void removeBids();
}
