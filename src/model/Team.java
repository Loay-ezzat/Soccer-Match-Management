package model;
/**
 * Team
 *
 * <p>
 * Represents a soccer team in the system. Contains team details such as
 * team ID, team name, coach name, and the year the team was founded.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * Team t = new Team();
 * t.setTeamName("FC Barcelona");
 * t.setCoachName("Xavi Hernandez");
 * t.setFoundationYear(1899);
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class Team {

    private int teamId;
    private String teamName;
    private String coachName;
    private int foundationYear;

    // ---------- Constructors ----------

    /** Default constructor. */
    public Team() { }

    // ---------- Getters & Setters ----------

    /** Returns the team's ID. */
    public int getTeamId() { return teamId; }

    /** Sets the team's ID. */
    public void setTeamId(int teamId) { this.teamId = teamId; }

    /** Returns the team's name. */
    public String getTeamName() { return teamName; }

    /** Sets the team's name. */
    public void setTeamName(String teamName) { this.teamName = teamName; }

    /** Returns the coach's name. */
    public String getCoachName() { return coachName; }

    /** Sets the coach's name. */
    public void setCoachName(String coachName) { this.coachName = coachName; }

    /** Returns the year the team was founded. */
    public int getFoundationYear() { return foundationYear; }

    /** Sets the year the team was founded. */
    public void setFoundationYear(int foundationYear) { this.foundationYear = foundationYear; }
}
