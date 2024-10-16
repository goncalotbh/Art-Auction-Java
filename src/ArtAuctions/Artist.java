package ArtAuctions;

import SystemExceptions.ArtistHasNoWorksException;
import SystemExceptions.ExistentWorkException;
import dataStructures.*;

import java.io.Serializable;

/**
 * @author Gonçalo Oliveira 65549
 */
public interface Artist extends SystemUser, Serializable {

    /**
     * Returns the artistic name of the artist.
     *
     * @return the name of the artist.
     */
    String getArtisticName();

    /**
     * Increases the amount of works in an auction
     */
    void incWorksAtAuction();

    /**
     * Decreases the amount of works in an auction
     */
    void decWorksAtAuction();

    /**
     * Checks if the artist has any work currently in an auction.
     */
    boolean hasWorksAtAuction();

    /**
     * Adds a work to the artist's collection
     *
     * @param work the new work the artist made
     */
    void addWork(Artwork work) throws ExistentWorkException;

    boolean hasArtworks();

    /**
     * Returns an iterator that goes through all the works the artist has made
     *
     * @return an iterator that goes through all the works the artist has made
     * @throws ArtistHasNoWorksException if the artist hasn't made any works
     */
    Iterator<Entry<ArtEntryByName, Artwork>> listArtistWorks() throws ArtistHasNoWorksException;
}
