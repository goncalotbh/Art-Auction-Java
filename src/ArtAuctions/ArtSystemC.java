package ArtAuctions;
import dataStructures.*;
import SystemExceptions.*;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public class ArtSystemC implements ArtSystem {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The minimum age required for a user to be registered
     */
    static final int MIN_AGE = 18;

    /**
     * A dictionary containing all users registered in the system
     */
    private final Dictionary<String, SystemUser> users;

    /**
     * A dictionary containing all artists registered in the system
     */
    private final Dictionary<String, Artist> artists;

    /**
     * A dictionary containing all art works
     */
    private final Dictionary<String, Artwork> allArtworks;

    /**
     * A dictionary containing all art works ordered by their highest price
     */
    private final Dictionary<ArtEntryByPrice, Artwork> artworksByMaxPrice;

    /**
     * A dictionary containing all auctions
     */
    private final Dictionary<String, Auction> auctions;

    public ArtSystemC() {
        users = new SepChainHashTable<>();
        artists = new SepChainHashTable<>();
        allArtworks = new SepChainHashTable<>();
        auctions = new SepChainHashTable<>();
        artworksByMaxPrice = new AVLTree<>();
    }

    @Override
    public void addUser(String login, String name, int age, String email) throws MinorUserException, ExistentUserException {
        if(age < MIN_AGE)
            throw new MinorUserException();
        if(users.find(login.toLowerCase()) != null)
            throw new ExistentUserException();

        users.insert(login.toLowerCase(), new SystemUserC(login, name, age, email));
    }

    @Override
    public void addArtist(String login, String name, String artisticName, int age, String email) throws MinorUserException, ExistentUserException {
        if(age < MIN_AGE)
            throw new MinorUserException();
        if(users.find(login.toLowerCase()) != null)
            throw new ExistentUserException();

        Artist artist = new ArtistC(login, name, artisticName, age, email);
        users.insert(login.toLowerCase(), artist);
        artists.insert(login.toLowerCase(), artist);
    }

    @Override
    public void removeUser(String login) throws InexistentUserException, BidsActiveException, WorksAtAuctionException {
        SystemUser user = getUserByLogin(login.toLowerCase());

        if(user.hasActiveBids())
            throw new BidsActiveException();

        if(user instanceof Artist) {
            if(((Artist) user).hasWorksAtAuction())
                throw new WorksAtAuctionException();
            if(((Artist)user).hasArtworks())
                removeWorksFromArtist((Artist) user);
            artists.remove(login.toLowerCase());
        }
        users.remove(login.toLowerCase());
    }

    @Override
    public void addWork(String workID, String authLogin, int year, String workName)
            throws ExistentWorkException, InexistentUserException, InexistentArtistException {
        if (allArtworks.find(workID) != null)
            throw new ExistentWorkException();

        SystemUser author = getUserByLogin(authLogin.toLowerCase());
        if (!author.isArtist())
            throw new InexistentArtistException();

        Artwork work = new ArtworkC(workID, (Artist) author, year, workName);
        allArtworks.insert(workID, work);
        artworksByMaxPrice.insert(new ArtEntryByPrice(workName, 0), work);
        ((Artist) author).addWork(work);
    }

    @Override
    public String[] getUserInfo(String login) throws InexistentUserException {
        SystemUser user = getUserByLogin(login.toLowerCase());
        return new String[] {user.getName(), Integer.toString(user.getAge()), user.getEmail()};
    }

    @Override
    public String[] getArtistInfo(String login) throws InexistentUserException, InexistentArtistException {
        SystemUser user = getUserByLogin(login.toLowerCase());
        if(!user.isArtist())
            throw new InexistentArtistException();

        Artist artist = (Artist) user;
        return new String[] {artist.getName(), artist.getArtisticName(), Integer.toString(artist.getAge()), artist.getEmail()};
    }

    @Override
    public String[] getWorkInfo(String workID) throws InexistentWorkException {
        Artwork work = getWorkByID(workID.toLowerCase());
        return new String[] {work.getName(), Integer.toString(work.getYear()), Integer.toString(work.getHighestPrice()),
                work.getAuthor().getLogin(), work.getAuthor().getName()};
    }

    @Override
    public void addAuction(String auctID) throws ExistentAuctionException {
        Auction auct = new AuctionC(auctID);
        if(auctions.find(auctID.toLowerCase()) != null)
            throw new ExistentAuctionException();

        auctions.insert(auctID.toLowerCase(), auct);
    }

    @Override
    public void addWorkAuction(String auctionID, String workID, int minValue) throws InexistentAuctionException, InexistentWorkException {
        Auction auction = getAuctionByID(auctionID.toLowerCase());
        Artwork work = getWorkByID(workID.toLowerCase());
        auction.addEntry(new AuctionEntryC(work, minValue));
        work.getAuthor().incWorksAtAuction();
    }

    @Override
    public void addBid(String auctionID, String workID, String login, int value) throws InexistentUserException,
            InexistentAuctionException, InexistentWorkException, InexistentWorkInAuctionException, BidBelowMinException {
        SystemUser user = getUserByLogin(login.toLowerCase());
        Auction auction = getAuctionByID(auctionID.toLowerCase());
        Artwork work = getWorkByID(workID.toLowerCase());
        if(!auction.hasWork(work))
            throw new InexistentWorkInAuctionException();
        if(!auction.bidIsValid(work, value))
            throw new BidBelowMinException();

        auction.addBid(work, user, value);
    }

    @Override
    public String[] closeAuction(String auctionID) throws InexistentAuctionException {
        Auction auction = getAuctionByID(auctionID.toLowerCase());
        List<Entry<Artwork, Integer>> ogPrices = new DoubleList<>();
        Iterator<Artwork> it = auction.listWorks();
        while(it.hasNext()) {
            Artwork work = it.next();
            ogPrices.addLast(new EntryClass<>(work, work.getHighestPrice()));
        }
        String[] messages = auction.processEntries();
        updateWorks(ogPrices.iterator());
        auction.removeAllBids();
        auctions.remove(auctionID.toLowerCase());
        return messages;
    }

    @Override
    public Iterator<Artwork> listAuctionWorks(String auctionID) throws InexistentAuctionException, NoWorksInAuctionException {
        Auction auction = getAuctionByID(auctionID.toLowerCase());
        if(auction.hasNoWorks())
            throw new NoWorksInAuctionException();

        return auction.listWorks();
    }

    @Override
    public Iterator<Artwork> listArtistWorks(String login)
            throws InexistentUserException, InexistentArtistException, ArtistHasNoWorksException {
        SystemUser user = getUserByLogin(login.toLowerCase());
        if (!user.isArtist())
            throw new InexistentArtistException();

        Iterator<Entry<ArtEntryByName, Artwork>> it = ((Artist) user).listArtistWorks();
        List<Artwork> list = new DoubleList<>();

        while(it.hasNext()) {
            list.addLast(it.next().getValue());
        }
        return list.iterator();
    }

    @Override
    public Iterator<Bid> listBidsWork(String auctionID, String workID) throws InexistentAuctionException,
            InexistentWorkException, InexistentWorkInAuctionException, NoBidsInEntryException {
        Auction auction = getAuctionByID(auctionID.toLowerCase());
        Artwork artwork = getWorkByID(workID.toLowerCase());
        if(!auction.hasWork(artwork))
            throw new InexistentWorkInAuctionException();
        return auction.getWorkBids(artwork);
    }

    @Override
    public Iterator<Artwork> listWorksByValue() throws NoWorksSoldYetException {
        Iterator<Entry<ArtEntryByPrice, Artwork>> it = artworksByMaxPrice.iterator();
        List<Artwork> list = new DoubleList<>();
        while(it.hasNext()) {
            Entry<ArtEntryByPrice, Artwork> entry = it.next();
            if (entry.getValue().getLastPrice() != 0)
                list.addLast(entry.getValue());
        }
        if(list.isEmpty())
            throw new NoWorksSoldYetException();
        return list.iterator();
    }

    /**
     * Updates the new list by removing the art work and then inserting it again to include its new highest price
     *
     * @param it the iterator that goes through the list
     */
    private void updateWorks(Iterator<Entry<Artwork, Integer>> it) {
        while(it.hasNext()) {
            Entry<Artwork, Integer> entry = it.next();
            Artwork work = entry.getKey();
            int ogPrice = entry.getValue();

            artworksByMaxPrice.remove(new ArtEntryByPrice(work.getName(), ogPrice));
            artworksByMaxPrice.insert(new ArtEntryByPrice(work.getName(), work.getHighestPrice()), work);
        }
    }

    /**
     * Removes all the works made by the given artist from the system
     *
     * @param artist the artist whose works are being removed
     */
    private void removeWorksFromArtist(Artist artist) {
        Iterator<Entry<String,Artwork>> it = allArtworks.iterator();
        while(it.hasNext()) {
            Entry<String, Artwork> entry = it.next();
            if(entry.getValue().getAuthor().equals(artist)) {
                allArtworks.remove(entry.getKey());
                artworksByMaxPrice.remove(new ArtEntryByPrice(entry.getValue().getName(), entry.getValue().getHighestPrice()));
            }
        }
    }

    /**
     * Searches and returns the auction that has the given identifier
     *
     * @param auctionID the work's unique identifier
     * @return the auction identified by the given ID
     * @throws InexistentAuctionException when there isn't any auction with the given ID
     */
    private Auction getAuctionByID(String auctionID) throws InexistentAuctionException {
        Auction auction = auctions.find(auctionID.toLowerCase());
        if(auction == null)
            throw new InexistentAuctionException();
        return auction;
    }

    /**
     * Searches and returns the work that has the given identifier
     *
     * @param workID the work's unique identifier
     * @return the work matching the given ID
     * @throws InexistentWorkException when there isn't a registered work with the given ID
     */
    private Artwork getWorkByID(String workID) throws InexistentWorkException {
        Artwork work = allArtworks.find(workID);
        if(work == null)
            throw new InexistentWorkException();
        return work;
    }

    /**
     * Searches and returns the user identified by the given login
     *
     * @param login the user's login in the system
     * @return the user identified by the given login
     * @throws InexistentUserException when there is no registered user with the given login
     */
    private SystemUser getUserByLogin(String login) throws InexistentUserException {
        SystemUser user;
        user = users.find(login.toLowerCase());
        if(user == null)
            throw new InexistentUserException();
        return user;
    }
}
