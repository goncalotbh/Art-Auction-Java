package ArtAuctions;

/**
 * @author Gonçalo Oliveira 65549
 */
public class SystemUserC implements SystemUser {

    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * The user's login
     */
    private final String login;

    /**
     * The user's name
     */
    private final String name;

    /**
     * The email of the user
     */
    private final String email;

    /**
     * The user's age
     */
    private final int age;

    /**
     * The number of active bids the user has
     */
    private int activeBids;

    /**
     * System user object constructor, defined by its login, name, age and email
     * @param login the user's login
     * @param name the name of the user
     * @param age the user's age
     * @param email the email of the user
     */
    public SystemUserC(String login, String name, int age, String email) {
        this.login = login;
        this.name = name;
        this.age = age;
        this.email = email;
        activeBids = 0;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof SystemUser))
            return false;
        SystemUser other = (SystemUser) o;
        return this.getLogin().equalsIgnoreCase(other.getLogin());
    }

    @Override
    public String getLogin(){
        return login;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void incActiveBids() {
        activeBids++;
    }

    @Override
    public void decActiveBids() {
        activeBids--;
    }

    @Override
    public boolean hasActiveBids() {
        return activeBids != 0;
    }

    @Override
    public boolean isArtist() {
        return false;
    }
}
