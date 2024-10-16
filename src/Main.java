import java.io.*;
import java.util.Scanner;
import ArtAuctions.*;
import SystemExceptions.*;
import dataStructures.Iterator;
import enums.Feedback;

/**
 * @author Afonso Godinho 65153
 * @author Gonçalo Oliveira 65549
 */
public class Main {

    // Command Constants
    private static final String ADD_USER = "adduser";
    private static final String ADD_ARTIST = "addartist";
    private static final String REMOVE_USER = "removeuser";
    private static final String ADD_WORK = "addwork";
    private static final String INFO_USER = "infouser";
    private static final String INFO_ARTIST = "infoartist";
    private static final String INFO_WORK = "infowork";
    private static final String CREATE_AUCTION = "createauction";
    private static final String ADD_WORK_AUCTION = "addworkauction";
    private static final String BID = "bid";
    private static final String CLOSE_AUCTION = "closeauction";
    private static final String LIST_AUCTION_WORKS = "listauctionworks";
    private static final String LIST_ARTIST_WORKS = "listartistworks";
    private static final String LIST_BIDS_WORK = "listbidswork";
    private static final String LIST_WORKS_BY_VALUE = "listworksbyvalue";
    private static final String QUIT = "quit";

    private static final String SYSTEM_FILE = "storedArtSystem.dat";

    /**
     * Main method, initializes new Scanner instance, tries to load program's saved status from file,
     * runs the command interpreter method runCommands and finally saves program's status in file.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArtSystem system = load();
        runCommands(in, system);
        save(system);
    }

    /**
     * Tries to load last saved program status from .dat file using Serializable properties,
     * if failed, returns a brand new ArtSystem object.
     *
     * @return ArtSystem object either with last saved status or empty if no status was saved.
     */
    private static ArtSystem load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SYSTEM_FILE));
            ArtSystem system = (ArtSystem) ois.readObject();
            ois.close();
            return system;
        }
        catch (IOException | ClassNotFoundException e) {
            return new ArtSystemC();
        }
    }

    /**
     * Saves program status at the end of execution so it can be loaded up again in next program run.
     *
     * @param system ArtSystem object to save in file.
     */
    private static void save(ArtSystem system) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SYSTEM_FILE));
            oos.writeObject(system);
            oos.flush();
            oos.close();
        }
        catch (IOException e) {
            System.out.println("Erro na serialização...");
        }
    }

    /**
     * Command interpreter method that processes user input while Quit command is not ran.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void runCommands(Scanner in, ArtSystem system) {
        String cmd;
        do {
            cmd = in.next().toLowerCase().trim();
            switch (cmd) {
                case ADD_USER -> addUser(in, system);
                case ADD_ARTIST -> addArtist(in, system);
                case REMOVE_USER -> removeUser(in, system);
                case ADD_WORK -> addWork(in, system);
                case INFO_USER -> infoUser(in, system);
                case INFO_ARTIST -> infoArtist(in, system);
                case INFO_WORK -> infoWork(in, system);
                case CREATE_AUCTION -> createAuction(in, system);
                case ADD_WORK_AUCTION -> addWorkAuction(in, system);
                case BID -> bid(in, system);
                case CLOSE_AUCTION -> closeAuction(in, system);
                case LIST_AUCTION_WORKS -> listAuctionWorks(in, system);
                case LIST_ARTIST_WORKS -> listArtistWorks(in, system);
                case LIST_BIDS_WORK -> listBidsWork(in, system);
                case LIST_WORKS_BY_VALUE -> listWorksByValue(system);
                case QUIT -> quit();
                default -> System.out.println(Feedback.UNKNOWN);
            }
            System.out.println();
        } while(!cmd.equals(QUIT));
        in.close();
    }

    /**
     * Processes user input related to user info and feedbacks whether user was successfully added to the system;
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void addUser(Scanner in, ArtSystem system) {
        String login = in.next().trim();
        String name = in.nextLine().trim();
        int age = in.nextInt();
        String email = in.nextLine().trim();
        System.out.println();

        try {
            system.addUser(login, name, age, email);
            System.out.println(Feedback.USER_REGISTERED);
        }
        catch (MinorUserException e) {
            System.out.println(Feedback.MINOR_USER);
        }
        catch (ExistentUserException e) {
            System.out.println(Feedback.EXISTENT_USER);
        }
    }

    /**
     * Processes user input related to an artist's info and feedbacks whether the artist was successfully added to the system.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void addArtist(Scanner in, ArtSystem system) {
        String login = in.next().trim();
        String name = in.nextLine().trim();
        String artisticName = in.nextLine().trim();
        int age = in.nextInt();
        String email = in.nextLine().trim();
        System.out.println();

        try {
            system.addArtist(login, name, artisticName, age, email);
            System.out.println(Feedback.ARTIST_REGISTERED);
        }
        catch (MinorUserException e) {
            System.out.println(Feedback.MINOR_USER);
        }
        catch(ExistentUserException e) {
            System.out.println(Feedback.EXISTENT_USER);
        }
    }

    /**
     * Tries to remove user from the system, gives feedback on the operation status.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void removeUser(Scanner in, ArtSystem system) {
        String login = in.nextLine().trim();
        System.out.println();

        try{
            system.removeUser(login);
            System.out.println(Feedback.USER_REMOVED);
        }
        catch(InexistentUserException e) {
            System.out.println(Feedback.INEXISTENT_USER);
        }
        catch(BidsActiveException e) {
            System.out.println(Feedback.ACTIVE_BIDS);
        }
        catch(WorksAtAuctionException e) {
            System.out.println(Feedback.WORKS_IN_AUCTION);
        }
    }

    /**
     * Processes user input related to an artwork's info and gives feedback on whether it was added successfully to the system.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void addWork(Scanner in, ArtSystem system) {
        String workID = in.next().trim();
        String authLogin = in.next().trim();
        int year = in.nextInt();
        String workName = in.nextLine().trim();
        System.out.println();

        try{
            system.addWork(workID, authLogin, year, workName);
            System.out.println(Feedback.WORK_REGISTERED);
        }
        catch(ExistentWorkException e) {
            System.out.println(Feedback.EXISTENT_WORK);
        }
        catch(InexistentUserException e) {
            System.out.println(Feedback.INEXISTENT_USER);
        }
        catch(InexistentArtistException e) {
            System.out.println(Feedback.INEXISTENT_ARTIST);
        }
    }

    /**
     * Print all the info related to a specified user, if possible.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void infoUser(Scanner in, ArtSystem system) {
        String login = in.nextLine().trim();
        System.out.println();

        try{
            String[] userInfo = system.getUserInfo(login);
            System.out.printf("%s %s %s %s\n", login, userInfo[0], userInfo[1], userInfo[2]);
        }
        catch (InexistentUserException e) {
            System.out.println(Feedback.INEXISTENT_USER);
        }
    }

    /**
     * Prints all the info related to a specified artist, if possible.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void infoArtist(Scanner in, ArtSystem system) {
        String login = in.nextLine().trim();
        System.out.println();

        try{
            String[] artistInfo = system.getArtistInfo(login);
            System.out.printf("%s %s %s %s %s\n", login, artistInfo[0], artistInfo[1], artistInfo[2], artistInfo[3]);
        }
        catch(InexistentUserException e) {
            System.out.println(Feedback.INEXISTENT_USER);
        }
        catch(InexistentArtistException e) {
            System.out.println(Feedback.INEXISTENT_ARTIST);
        }
    }

    /**
     * Prints all the info related to a specified artwork, if possible.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void infoWork(Scanner in, ArtSystem system) {
        String workID = in.next().trim();
        System.out.println();

        try{
            String[] workInfo = system.getWorkInfo(workID);
            System.out.printf("%s %s %s %s %s %s\n", workID, workInfo[0], workInfo[1], workInfo[2], workInfo[3],
                    workInfo[4]);
        }
        catch (InexistentWorkException e) {
            System.out.println(Feedback.INEXISTENT_WORK);
        }
    }

    /**
     * Creates a new empty auction in the system, if given ID isn't already taken.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void createAuction(Scanner in, ArtSystem system) {
        String auctID = in.nextLine().trim();
        System.out.println();

        try{
            system.addAuction(auctID);
            System.out.println(Feedback.AUCTION_REGISTERED);
        }
        catch(ExistentAuctionException e) {
            System.out.println(Feedback.EXISTENT_AUCTION);
        }
    }

    /**
     * Registers a known artwork into an existing auction.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void addWorkAuction(Scanner in, ArtSystem system) {
        String auctionID = in.next().trim();
        String workID = in.next().trim();
        int minValue = in.nextInt();
        in.nextLine();
        System.out.println();

        try{
            system.addWorkAuction(auctionID, workID, minValue);
            System.out.println(Feedback.WORK_ADDED_TO_AUCTION);
        }
        catch(InexistentAuctionException e) {
            System.out.println(Feedback.INEXISTENT_AUCTION);
        }
        catch (InexistentWorkException e) {
            System.out.println(Feedback.INEXISTENT_WORK);
        }
    }

    /**
     * Registers a new bid made by a system user directed towards an artwork that is at auction.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void bid(Scanner in, ArtSystem system) {
        String auctionID = in.next().trim();
        String workID = in.next().trim();
        String login = in.next().trim();
        int value = in.nextInt();
        in.nextLine();
        System.out.println();

        try{
            system.addBid(auctionID, workID, login, value);
            System.out.println(Feedback.BID_ACCEPTED);
        }
        catch(InexistentUserException e) {
            System.out.println(Feedback.INEXISTENT_USER);
        }
        catch(InexistentAuctionException e) {
            System.out.println(Feedback.INEXISTENT_AUCTION);
        }
        catch(InexistentWorkInAuctionException | InexistentWorkException e) {
            System.out.println(Feedback.INEXISTENT_WORK_IN_AUCTION);
        }
        catch(BidBelowMinException e) {
            System.out.println(Feedback.BELOW_MINIMUM_BID);
        }
     }

    /**
     * Closes an existing auction in the system.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
     private static void closeAuction(Scanner in, ArtSystem system) {
        String auctionID = in.nextLine().trim();
        System.out.println();

        try{
            String[] messages = system.closeAuction(auctionID);
            System.out.println(Feedback.AUCTION_CLOSED);
            for (String message : messages)
                System.out.println(message);
        }
        catch(InexistentAuctionException e) {
            System.out.println(Feedback.INEXISTENT_AUCTION);
        }
    }

    /**
     * Lists all the works registered in a specified auction, if possible.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void listAuctionWorks(Scanner in, ArtSystem system) {
        String auctionID = in.nextLine().trim();
        System.out.println();
        try{
            Iterator<Artwork> it = system.listAuctionWorks(auctionID);
            while(it.hasNext()) {
                Artwork work = it.next();
                System.out.println(work.getID() + " " + work.getName() + " " + work.getYear() + " " +
                        work.getHighestBid().getValue() +  " " + work.getAuthor().getLogin() + " " +
                        work.getAuthor().getName());
            }
        }
        catch(InexistentAuctionException e) {
            System.out.println(Feedback.INEXISTENT_AUCTION);
        }
        catch(NoWorksInAuctionException e) {
            System.out.println(Feedback.EMPTY_AUCTION);
        }
    }

    private static void listArtistWorks(Scanner in, ArtSystem system) {
        String login = in.nextLine().trim();
        System.out.println();

        try {
            Iterator<Artwork> it = system.listArtistWorks(login);
            while(it.hasNext()) {
                Artwork work = it.next();
                System.out.println(work.getID() + " " + work.getName() + " " + work.getYear() + " " +
                        work.getHighestPrice());
            }
        }
        catch (InexistentUserException e) {
            System.out.println(Feedback.INEXISTENT_USER);
        }
        catch (InexistentArtistException e) {
            System.out.println(Feedback.INEXISTENT_ARTIST);
        }
        catch (ArtistHasNoWorksException e) {
            System.out.println(Feedback.ARTIST_HAS_NO_WORKS);
        }
    }

    /**
     * Lists all the bids registered in a specified auction that are related to a specified artwork.
     *
     * @param in Scanner instance.
     * @param system ArtSystem instance.
     */
    private static void listBidsWork(Scanner in, ArtSystem system) {
        String auctionID = in.next().trim();
        String workID = in.nextLine().trim();
        System.out.println();

        try{
            Iterator<Bid> it = system.listBidsWork(auctionID, workID);
            while(it.hasNext()) {
                Bid bid = it.next();
                System.out.println(bid.getBidder().getLogin() + " " + bid.getBidder().getName().trim() + " " +
                        bid.getValue());
            }
        }
        catch(InexistentAuctionException e) {
            System.out.println(Feedback.INEXISTENT_AUCTION);
        }
        catch(InexistentWorkInAuctionException | InexistentWorkException e) {
            System.out.println(Feedback.INEXISTENT_WORK_IN_AUCTION);
        }
        catch(NoBidsInEntryException e) {
            System.out.println(Feedback.WORK_WITH_NO_BIDS);
        }
    }

    private static void listWorksByValue(ArtSystem system) {
        System.out.println();

        try{
            Iterator<Artwork> it = system.listWorksByValue();
            while(it.hasNext()) {
                Artwork work = it.next();
                System.out.println(work.getID() + " " + work.getName() + " " + work.getYear() + " " +
                        work.getHighestPrice() + " " + work.getAuthor().getLogin() + " " +
                        work.getAuthor().getName());
            }
        }
        catch(NoWorksSoldYetException e) {
            System.out.println(Feedback.NO_SOLD_WORKS);
        }
    }

    /**
     * Quit command, finishes execution.
     */
    private static void quit() {
        System.out.printf(Feedback.QUIT.toString());
    }
}