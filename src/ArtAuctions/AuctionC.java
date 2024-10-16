package ArtAuctions;

import SystemExceptions.NoBidsInEntryException;
import dataStructures.*;

/**
 * @author Gonçalo Oliveira 65549
 */
public class AuctionC implements Auction {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The auction's unique identifier
     */
    private final String id;

    /**
     * The list of entries in the auction, that is, the works associated to it
     */
    private final List<AuctionEntry> entries;

    /**
     * Auction object constructor, defined by its ID
     * @param id the auction's unique identifier
     */
    public AuctionC(String id) {
        this.id = id;
        entries = new DoubleList<>();
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void addEntry(AuctionEntry entry) {
        if (!hasWork(entry.getArtwork()))
            entries.addLast(entry);
    }

    @Override
    public void addBid(Artwork work, SystemUser user, int value) {
        AuctionEntry entry = findEntryByWork(work);
        entry.addBid(new BidC(user, value));
        user.incActiveBids();
    }

    @Override
    public boolean hasWork(Artwork work) {
        return entries.find(new AuctionEntryC(work, 0)) != -1;
    }

    @Override
    public boolean bidIsValid(Artwork work, int value) {
        AuctionEntry entry = findEntryByWork(work);
        return value >= entry.getMinimumValue();
    }

    @Override
    public String[] processEntries() {
        Iterator<AuctionEntry> it = entries.iterator();
        String[] messages = new String[entries.size()];

        int i = 0;
        while(it.hasNext()) {
            AuctionEntry entry = it.next();
            messages[i] = entry.processBids();
            entry.getArtwork().getAuthor().decWorksAtAuction();
            i++;
        }
        return messages;
    }

    @Override
    public boolean hasNoWorks() {
        return entries.isEmpty();
    }

    @Override
    public Iterator<Artwork> listWorks() {
        List<Artwork> list = new DoubleList<>();
        Iterator<AuctionEntry> it = entries.iterator();
        while(it.hasNext())
            list.addLast(it.next().getArtwork());

        return list.iterator();
    }

    @Override
    public Iterator<Bid> getWorkBids(Artwork artwork) throws NoBidsInEntryException {
        AuctionEntry entry = findEntryByWork(artwork);
        if(entry.hasNoBids())
            throw new NoBidsInEntryException();
        return entry.getBids();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Auction))
            return false;
        Auction other = (Auction) o;
        return this.getID().equalsIgnoreCase(other.getID());
    }

    @Override
    public void removeAllBids() {
        for (int i = 0; i < entries.size(); i++)
            entries.get(i).removeBids();
    }

    /**
     * Searches and returns the auction entry corresponding to the given art work
     *
     * @param artwork the art work to be auctioned
     * @return the auction entry corresponding to the given art work
     */
    private AuctionEntry findEntryByWork(Artwork artwork) {
        return entries.get(entries.find(new AuctionEntryC(artwork, 0)));
    }
}
