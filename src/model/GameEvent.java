package model;

/**
 * GameEvent
 *
 * <p>
 * Represents an event that occurs during a soccer match.
 * Examples of events include goals, assists, fouls, cards, etc.
 * </p>
 *
 * <p>
 * Contains information about the event, including the match, player, type, and time.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * GameEvent event = new GameEvent();
 * GameEvent goalEvent = new GameEvent(1, 101, 5, "Goal", 23);
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class GameEvent {

    private int eventId;
    private int matchId;
    private int playerId;
    private String eventType;
    private int eventTime;

    // ---------- Constructors ----------

    /**
     * Default constructor.
     * Creates an empty GameEvent object.
     */
    public GameEvent() {}

    /**
     * Parameterized constructor.
     *
     * @param eventId   the unique ID of the event
     * @param matchId   the ID of the match where the event occurred
     * @param playerId  the ID of the player involved in the event
     * @param eventType the type of event (e.g., "Goal", "Assist", "Yellow Card")
     * @param eventTime the minute in the match when the event occurred
     */
    public GameEvent(int eventId, int matchId, int playerId, String eventType, int eventTime) {
        this.eventId = eventId;
        this.matchId = matchId;
        this.playerId = playerId;
        this.eventType = eventType;
        this.eventTime = eventTime;
    }

    // ---------- Getters & Setters ----------

    /** Returns the event ID. */
    public int getEventId() {
        return eventId;
    }

    /** Sets the event ID. */
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    /** Returns the match ID. */
    public int getMatchId() {
        return matchId;
    }

    /** Sets the match ID. */
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    /** Returns the player ID involved in the event. */
    public int getPlayerId() {
        return playerId;
    }

    /** Sets the player ID involved in the event. */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /** Returns the type of the event. */
    public String getEventType() {
        return eventType;
    }

    /** Sets the type of the event. */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /** Returns the time (minute) when the event occurred. */
    public int getEventTime() {
        return eventTime;
    }

    /** Sets the time (minute) when the event occurred. */
    public void setEventTime(int eventTime) {
        this.eventTime = eventTime;
    }
}

