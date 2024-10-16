package ArtAuctions;

import SystemExceptions.NoBidsInEntryException;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 */
public interface Auction extends Serializable {

    /**
     * Returns the auction's unique identifier
     *
     * @return the auction's ID
     */
    String getID();

    /**
     * Adds an entry to an auction, that is, a new art work to be auctioned
     *
     * @param entry the art work to be auctioned
     */
    void addEntry(AuctionEntry entry);

    /**
     * Adds a bid for a given work, given the user who made it and its value
     *
     * @param work the work for which the bid is made
     * @param user the user that is making the bid
     * @param value the value of the bid made
     */
    void addBid(Artwork work, SystemUser user, int value);

    /**
     * Checks if the given work is registered in the auction
     *
     * @param work the work whose existence in the auction will be checked
     * @return true if the given work is registered in the auction
     */
    boolean hasWork(Artwork work);

    /**
     * Checks if the bid is valid or not, based on its value
     *
     * @param work the work for which the bid was made
     * @param value the value of the bid
     * @return true, if the bid is valid
     */
    boolean bidIsValid(Artwork work, int value);

    // FALTA ESTE
    String[] processEntries();

    /**
     * Checks if the auction has any works to be auctioned or not
     *
     * @return true, if the auction doesn't have any works
     */
    boolean hasNoWorks();

    /**
     * Returns an iterator that goes through all the works for sale in an auction
     *
     * @return an iterator that goes through all the works for sale in an auction
     */
    Iterator<Artwork> listWorks();

    /**
     * Returns an iterator that goes through all the bids made for a given art work
     *
     * @param artwork the art work we want to check the bids for
     * @return an iterator that goes through all the bids made for a given art work
     * @throws NoBidsInEntryException when the auction entry of the given work has no active bids
     */
    Iterator<Bid> getWorkBids(Artwork artwork) throws NoBidsInEntryException;

    /**
     * Upon closing an auction, removes all bids made for every auction entry
     */
    void removeAllBids();
}
