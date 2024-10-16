package ArtAuctions;

import dataStructures.*;
import enums.Feedback;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public class AuctionEntryC implements AuctionEntry {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The work to be auctioned
     */
    private final Artwork work;

    /**
     * The work's minimum value for a bid in the recurring auction
     */
    private final int minimumValue;

    /**
     * The value of a work's current highest bid in the recurring auction
     */
    private Bid firstHighestBid;

    /**
     * The list where the bids for a certain work are stored
     */
    private final List<Bid> bids;

    /**
     * Auction entry object constructor, defined by the work itself and its minimum value
     * @param work the work that will be auctioned
     * @param minValue the minimum value accepted in bids for the work
     */
    public AuctionEntryC(Artwork work, int minValue){
        this.work = work;
        this.minimumValue = minValue;
        firstHighestBid = null;
        bids = new DoubleList<>();
    }

    @Override
    public int getMinimumValue() {
        return minimumValue;
    }

    @Override
    public void addBid(Bid bid) {
        bids.addLast(bid);
        if(firstHighestBid == null)
            firstHighestBid = bid;
        else if(firstHighestBid.getValue() < bid.getValue())
            firstHighestBid = bid;
    }

    @Override
    public Artwork getArtwork() {
        return work;
    }

    @Override
    public void sell(Bid bid) {
        work.setLastPrice(bid.getValue());
        if(work.getHighestBid().getValue() < bid.getValue())
            work.updateHighestBid(bid);
    }

    @Override
    public String processBids() {
        String message;
        if(firstHighestBid == null){
            message = work.getID() + " " + work.getName() + Feedback.NO_BIDS;
        }
        else {
            sell(firstHighestBid);
            message = work.getID() + " " + work.getName() + " " + firstHighestBid.getBidder().getLogin() + " " +
                    firstHighestBid.getBidder().getName() + " " + firstHighestBid.getValue();
        }
        return message;
    }

    @Override
    public Iterator<Bid> getBids() {
        return bids.iterator();
    }

    @Override
    public boolean hasNoBids() {
        return bids.isEmpty();
    }

    @Override
    public void removeBids() {
        for (int i = 0; i < bids.size(); i++) {
            bids.get(i).getBidder().decActiveBids();
        }
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof AuctionEntry))
            return false;
        AuctionEntry other = (AuctionEntry) o;
        return this.getArtwork().equals(other.getArtwork());
    }
}
