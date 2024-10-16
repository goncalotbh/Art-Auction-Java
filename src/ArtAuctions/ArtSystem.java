package ArtAuctions;

import java.io.Serializable;

import SystemExceptions.*;
import dataStructures.*;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public interface ArtSystem extends Serializable {

    /**
     * Registers a new user in the system, identified by his login, name, age and email
     *
     * @param login the login of the user
     * @param name the user's name
     * @param age the user's age
     * @param email the user's email
     * @throws MinorUserException when the user's age is under 18
     * @throws ExistentUserException if there is already a registered user with the same login
     */
    void addUser(String login, String name, int age, String email) throws MinorUserException, ExistentUserException;

    /**
     * Registers a new artist in the system, identified by his login, name , artistic name, age and email
     *
     * @param login the login of the artist
     * @param name the artist's name
     * @param artisticName the artistic name of the artist
     * @param age the artist's age
     * @param email the artist's email
     * @throws MinorUserException when the user's age is under 18
     * @throws ExistentUserException if there is already a registered user with the same login
     */
    void addArtist(String login, String name, String artisticName, int age, String email) throws MinorUserException, ExistentUserException;

    /**
     * Removes a given user from the system. If the user is an artist, removes all his works as well
     *
     * @param login the login of the user to remove
     * @throws InexistentUserException when there is no registered user with the given login
     * @throws BidsActiveException if the user has active bids in an auction
     * @throws WorksAtAuctionException if, given that the user is an artist, still has works in auction
     */
    void removeUser(String login) throws InexistentUserException, BidsActiveException, WorksAtAuctionException;

    /**
     * Adds a new work to the system, identified by its ID, login of it's author, year and name
     *
     * @param workID the work's unique identifier
     * @param authLogin the login of the work's author
     * @param year the year in which the work was made
     * @param workName the work's name
     * @throws ExistentWorkException if there is already a work in the system with the given name
     * @throws InexistentUserException when there is no registered user with the given login
     * @throws InexistentArtistException when there is a user with the given login but isn't an artist
     */
    void addWork(String workID, String authLogin, int year, String workName) throws ExistentWorkException, InexistentUserException, InexistentArtistException;

    /**
     * Checks the basic information about the user with the given login
     *
     * @param login the user's login
     * @return the basic information of a user, that is, his login, name, age and email
     * @throws InexistentUserException when there is no registered user with the given login
     */
    String[] getUserInfo(String login) throws InexistentUserException;

    /**
     * Checks the basic information about the artist with the given login
     *
     * @param login the artist's login
     * @return the basic information of an artist, that is, his login, name, artistic name, age and email
     * @throws InexistentUserException when there is no registered user with the given login
     * @throws InexistentArtistException when there is a user with the given login but isn't an artist
     */
    String[] getArtistInfo(String login) throws InexistentUserException, InexistentArtistException;

    /**
     * Checks the basic information about the work with the given identifier
     *
     * @param workID the work's unique identifier
     * @return the basic information of an artist, that is, its ID, name, year, highest selling value,
     * author's login and author's name
     * @throws InexistentWorkException when there isn't a registered work with the given ID
     */
    String[] getWorkInfo(String workID) throws InexistentWorkException;

    /**
     * Creates a new auction in the system, recognized by the given identifier
     *
     * @param auctID the auction's unique identifier
     * @throws ExistentAuctionException if there is already an auction with the given identifier
     */
    void addAuction(String auctID) throws ExistentAuctionException;

    /**
     * Adds a work to an existent auction with the given identifier, given the work's ID and value
     *
     * @param auctionID the auction's unique identifier
     * @param workID the work's unique identifier
     * @param minValue the minimum acceptable value for the work's purchase
     * @throws InexistentAuctionException when there isn't a registered auction with the given ID
     * @throws InexistentWorkException when there isn't a registered work with the given ID
     */
    void addWorkAuction(String auctionID, String workID, int minValue) throws InexistentAuctionException, InexistentWorkException;

    /**
     * Makes a bid for the purchase of the work with the given ID in an auction with th
     *
     * @param auctionID the auction's unique identifier
     * @param workID the work's unique identifier
     * @param login the login of the user making the bid
     * @param value the value of the bid made
     * @throws InexistentUserException when there is no registered user with the given login
     * @throws InexistentAuctionException when there isn't a registered auction with the given ID
     * @throws InexistentWorkException when there isn't a registered work with the given ID
     * @throws InexistentWorkInAuctionException if there isn't any work with the given ID in the auction
     * @throws BidBelowMinException if the bid made is lower than the minimum value acceptable
     */
    void addBid(String auctionID, String workID, String login, int value) throws InexistentUserException, InexistentAuctionException, InexistentWorkException, InexistentWorkInAuctionException, BidBelowMinException;

    /**
     * Closes the auction, accepting the highest submitted offer for each work, updating each work's highest
     * purchase value if the bid is higher than the previous highest one. It also lists the values of the sales
     * associated with each work's buyer, and finally deletes the auction
     *
     * @param auctionID the auction's unique identifier
     * @return Array of strings to print related to the auction's entries.
     * @throws InexistentAuctionException when auction does not exist.
     */
    String[] closeAuction(String auctionID) throws InexistentAuctionException;

    dataStructures.Iterator<Artwork> listAuctionWorks(String auctionID) throws InexistentAuctionException,
            NoWorksInAuctionException;

    /**
     * Returns an iterator that goes through all the works made by an artist
     *
     * @param login the artist's login
     * @return an iterator that goes through all the works made by an artist
     * @throws InexistentUserException   when there is no registered user with the given login
     * @throws InexistentArtistException when there is a user with the given login but isn't an artist
     * @throws ArtistHasNoWorksException if the artist doesn't have any works
     */
    Iterator<Artwork> listArtistWorks(String login) throws InexistentUserException, InexistentArtistException,
            ArtistHasNoWorksException;

    /**
     * Returns an iterator that goes through all the bids made to the auction of a given work
     *
     * @param auctionID the auction's unique identifier
     * @param workID the work's unique identifier
     * @return an iterator that goes through all the bids made to the auction of a given work
     * @throws InexistentAuctionException when there isn't a registered auction with the given ID
     * @throws InexistentWorkException when there isn't a registered work with the given ID
     * @throws InexistentWorkInAuctionException if there isn't any work with the given ID in the auction
     * @throws NoBidsInEntryException if there aren't any bids for the work
     */
    Iterator<Bid> listBidsWork(String auctionID, String workID) throws InexistentAuctionException,
            InexistentWorkException, InexistentWorkInAuctionException, NoBidsInEntryException;

    /**
     *
     * @return an iterator that goes through all the sold works
     * @throws NoWorksSoldYetException when there haven´t been any art work sales made yet
     */
    Iterator<Artwork> listWorksByValue() throws NoWorksSoldYetException;
}
