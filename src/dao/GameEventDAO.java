package dao;

import model.GameEvent;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * GameEventDAO is a Data Access Object (DAO) class responsible for
 * performing CRUD operations (Create, Update, Delete) on the `game_event` table
 * in the Soccer Management System database.
 *
 * <p>Each method connects to the database, executes the SQL query, and returns
 * a boolean indicating success or failure.</p>
 *
 * <p>Usage example:
 * <pre>
 * GameEvent event = new GameEvent(1, 5, "Goal", 23);
 * boolean added = GameEventDAO.AddEvent(event);
 * event.setEventTime(25);
 * boolean updated = GameEventDAO.UpdateEvent(event);
 * boolean deleted = GameEventDAO.DeleteEvent(event);
 * </pre>
 * </p>
 *
 * Author: Loay
 * Version: 1.0
 */
public class GameEventDAO {

    /**
     * Adds a new game event to the database.
     *
     * @param e GameEvent object containing match_id, player_id, event_type, event_time
     * @return true if insertion succeeds, false otherwise
     */
    public static boolean AddEvent(GameEvent e) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO game_event (match_id, player_id, event_type, event_time) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getMatchId());
            stmt.setInt(2, e.getPlayerId());
            stmt.setString(3, e.getEventType());
            stmt.setInt(4, e.getEventTime());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Updates an existing game event in the database.
     *
     * @param e GameEvent object containing updated match_id, player_id, event_type, event_time, and event_id
     * @return true if update succeeds, false otherwise
     */
    public static boolean UpdateEvent(GameEvent e) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE game_event SET match_id=?, player_id=?, event_type=?, event_time=? WHERE event_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getMatchId());
            stmt.setInt(2, e.getPlayerId());
            stmt.setString(3, e.getEventType());
            stmt.setInt(4, e.getEventTime());
            stmt.setInt(5, e.getEventId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a game event from the database.
     *
     * @param e GameEvent object containing the event_id of the event to delete
     * @return true if deletion succeeds, false otherwise
     */
    public static boolean DeleteEvent(GameEvent e) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM game_event WHERE event_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, e.getEventId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
