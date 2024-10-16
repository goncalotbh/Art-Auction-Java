package ArtAuctions;

/**
 * @author Gonçalo Oliveira 65549
 */
public class ArtworkC implements Artwork {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The work's unique identifier
     */
    private final String id;

    /**
     * The author of the work
     */
    private final Artist author;

    /**
     * The year in which the work was made
     */
    private final int year;

    /**
     * The work's name
     */
    private final String name;

    /**
     * The last price at which the work was sold
     */
    private int lastPrice;

    /**
     * The highest price at which the work has ever been sold
     */
    private int highestPrice;

    /**
     * The highest bid made for the work
     */
    private Bid highestBid;


    /**
     * Art work object constructor, defined by its ID, author, year of elaboration and name
     * @param id the work's unique identifier
     * @param author the author of the work
     * @param year the work's year of elaboration
     * @param name the name of the work
     */
    public ArtworkC(String id, Artist author, int year, String name) {
        this.id = id;
        this.author = author;
        this.year = year;
        this.name = name;
        lastPrice = 0;
        highestPrice = 0;
        highestBid = new BidC(null, 0);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Artwork))
            return false;
        Artwork other = (Artwork) o;
        return this.getID().equalsIgnoreCase(other.getID());
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Artist getAuthor() {
        return author;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public int getLastPrice() {
        return lastPrice;
    }

    @Override
    public int getHighestPrice() {
        return highestPrice;
    }

    @Override
    public Bid getHighestBid() {
        return highestBid;
    }

    @Override
    public void updateHighestBid(Bid bid) {
        highestBid = bid;
    }

    @Override
    public void setLastPrice(int value) {
        lastPrice = value;
        if (lastPrice > highestPrice)
            highestPrice = lastPrice;
    }


}
