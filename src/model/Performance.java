package model;
/**
 * Performance
 *
 * <p>
 * Represents a player's performance in a soccer match.
 * Tracks goals, assists, minutes played, and links to a specific match and player.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * Performance p = new Performance();
 * p.setPlayerId(101);
 * p.setMatchId(50);
 * p.setGoals(2);
 * p.setAssists(1);
 * p.setMinutesPlayed(90);
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class Performance {

    private int performanceId;
    private int playerId;
    private int goals;
    private int assists;
    private int matchId;
    private int minutesPlayed;

    // ---------- Getters & Setters ----------

    /** Returns the performance ID. */
    public int getPerformanceId() { return performanceId; }

    /** Sets the performance ID. */
    public void setPerformanceId(int performanceId) { this.performanceId = performanceId; }

    /** Returns the player ID. */
    public int getPlayerId() { return playerId; }

    /** Sets the player ID. */
    public void setPlayerId(int playerId) { this.playerId = playerId; }

    /** Returns the number of goals scored. */
    public int getGoals() { return goals; }

    /** Sets the number of goals scored. */
    public void setGoals(int goals) { this.goals = goals; }

    /** Returns the number of assists. */
    public int getAssists() { return assists; }

    /** Sets the number of assists. */
    public void setAssists(int assists) { this.assists = assists; }

    /** Returns the match ID. */
    public int getMatchId() { return matchId; }

    /** Sets the match ID. */
    public void setMatchId(int matchId) { this.matchId = matchId; }

    /** Returns the minutes played by the player in the match. */
    public int getMinutesPlayed() { return minutesPlayed; }

    /** Sets the minutes played by the player in the match. */
    public void setMinutesPlayed(int minutesPlayed) { this.minutesPlayed = minutesPlayed; }
}
