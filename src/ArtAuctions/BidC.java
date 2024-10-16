package ArtAuctions;

/**
 * @author Gonçalo Oliveira 65549
 */
public class BidC implements Bid {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The user who made the bid
     */
    private final SystemUser bidder;

    /**
     * The value of the bid
     */
    private final int value;

    /**
     * Bid object constructor, defined by the user who makes it and its value
     * @param buyer the user that makes the bid
     * @param value the monetary value of the bid
     */
    public BidC(SystemUser buyer, int value) {
        this.bidder = buyer;
        this.value = value;
    }

    @Override
    public SystemUser getBidder() {
        return bidder;
    }

    @Override
    public int getValue() {
        return value;
    }
}
