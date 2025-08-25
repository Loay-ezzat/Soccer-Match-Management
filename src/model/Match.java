package model;
/**
 * Match
 *
 * <p>
 * Represents a soccer match between two teams.
 * Contains information such as teams, match date, venue, and scores.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * Match match = new Match();
 * Match match1 = new Match(101, "Team A", "Team B", "2025-08-25", "Stadium X", 2, 1);
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class Match {

    private int matchId;
    private String homeTeam;
    private String awayTeam;
    private String matchDate;
    private String venue;
    private int homeScore;
    private int awayScore;

    // ---------- Constructors ----------

    /**
     * Default constructor.
     * Creates an empty Match object.
     */
    public Match() {}

    /**
     * Parameterized constructor.
     *
     * @param matchId   the unique ID of the match
     * @param homeTeam  the name of the home team
     * @param awayTeam  the name of the away team
     * @param matchDate the date of the match
     * @param venue     the venue of the match
     * @param homeScore the score of the home team
     * @param awayScore the score of the away team
     */
    public Match(int matchId, String homeTeam, String awayTeam, String matchDate,
                 String venue, int homeScore, int awayScore) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.venue = venue;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    // ---------- Getters & Setters ----------

    /** Returns the match ID. */
    public int getMatchId() {
        return matchId;
    }

    /** Sets the match ID. */
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    /** Returns the home team name. */
    public String getHomeTeam() {
        return homeTeam;
    }

    /** Sets the home team name. */
    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    /** Returns the away team name. */
    public String getAwayTeam() {
        return awayTeam;
    }

    /** Sets the away team name. */
    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    /** Returns the match date. */
    public String getMatchDate() {
        return matchDate;
    }

    /** Sets the match date. */
    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    /** Returns the venue of the match. */
    public String getVenue() {
        return venue;
    }

    /** Sets the venue of the match. */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /** Returns the home team's score. */
    public int getHomeScore() {
        return homeScore;
    }

    /** Sets the home team's score. */
    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    /** Returns the away team's score. */
    public int getAwayScore() {
        return awayScore;
    }

    /** Sets the away team's score. */
    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}
