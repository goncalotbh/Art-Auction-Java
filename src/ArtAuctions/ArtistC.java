package ArtAuctions;

import SystemExceptions.ArtistHasNoWorksException;
import SystemExceptions.ExistentWorkException;
import dataStructures.*;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public class ArtistC extends SystemUserC implements Artist {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The artistic name of the artist.
     */
    private final String artisticName;

    /**
     * The amount of works the artist has in auction.
     */
    private int worksAtAuction;

    /**
     * The hash table of works that the artist has made, defined by the work's name
     */
    private final BinarySearchTree<ArtEntryByName, Artwork> works;

    /**
     * An artist, represented by his login, name, artistic name, age and email.
     *
     * @param login the login username used by the artist
     * @param name the artist's name
     * @param artisticName the artistic name of the artist
     * @param age the artist's age
     * @param email the artist's email
     */
    public ArtistC(String login, String name, String artisticName, int age, String email) {
        super(login, name, age, email);
        this.artisticName = artisticName;
        works = new AVLTree<>();
        worksAtAuction = 0;
    }

    @Override
    public String getArtisticName() {
        return artisticName;
    }

    @Override
    public void incWorksAtAuction() {
        worksAtAuction++;
    }

    @Override
    public void decWorksAtAuction() {
        worksAtAuction--;
    }

    @Override
    public boolean hasWorksAtAuction() {
        return worksAtAuction != 0;
    }

    @Override
    public boolean isArtist() {
        return true;
    }

    @Override
    public boolean hasArtworks() {
        return !works.isEmpty();
    }

    @Override
    public void addWork(Artwork work) throws ExistentWorkException {
        if (works.find(new ArtEntryByName(work.getID().toLowerCase(), work.getName())) != null)
            throw new ExistentWorkException();

        works.insert(new ArtEntryByName(work.getID().toLowerCase(), work.getName()), work);
    }

    @Override
    public Iterator<Entry<ArtEntryByName, Artwork>> listArtistWorks() throws ArtistHasNoWorksException {
        if (works.isEmpty())
            throw new ArtistHasNoWorksException();
        return works.iterator();
    }

}
