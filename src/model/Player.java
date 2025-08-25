package model;
/**
 * Player
 *
 * <p>
 * Represents a soccer player in the system. Inherits from User.
 * Contains personal and team-related details such as age, nationality, position, team name, and jersey number.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * Player p = new Player();
 * p.setUsername("Lionel Messi");
 * p.setAge(36);
 * p.setNationality("Argentina");
 * p.setPosition("Forward");
 * p.setTeamName("PSG");
 * p.setJerseyNumber(30);
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class Player extends User {

    private int age;
    private String nationality;
    private String position;
    private String teamName;
    private int jerseyNumber;

    // ---------- Constructors ----------

    /** Default constructor. */
    public Player() { }

    /**
     * Parameterized constructor.
     *
     * @param id user ID
     * @param username player's name
     * @param role role (inherited from User)
     * @param passwword user's password
     * @param age player's age
     * @param nationality player's nationality
     * @param position player's position
     * @param teamName player's team
     * @param jerseyNumber player's jersey number
     */
    public Player(int id, String username, String role, String passwword,
                  int age, String nationality, String position, String teamName, int jerseyNumber) {
        super(id, username, role, passwword);
        this.age = age;
        this.nationality = nationality;
        this.position = position;
        this.teamName = teamName;
        this.jerseyNumber = jerseyNumber;
    }

    // ---------- Getters & Setters ----------

    /** Returns the player's age. */
    public int getAge() { return age; }

    /** Sets the player's age. */
    public void setAge(int age) { this.age = age; }

    /** Returns the player's nationality. */
    public String getNationality() { return nationality; }

    /** Sets the player's nationality. */
    public void setNationality(String nationality) { this.nationality = nationality; }

    /** Returns the player's position. */
    public String getPosition() { return position; }

    /** Sets the player's position. */
    public void setPosition(String position) { this.position = position; }

    /** Returns the player's team name. */
    public String getTeamName() { return teamName; }

    /** Sets the player's team name. */
    public void setTeamName(String teamName) { this.teamName = teamName; }

    /** Returns the player's jersey number. */
    public int getJerseyNumber() { return jerseyNumber; }

    /** Sets the player's jersey number. */
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }
}
