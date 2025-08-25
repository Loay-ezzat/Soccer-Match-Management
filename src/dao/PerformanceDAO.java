package dao;

import model.Performance;
import util.DBConnection;
import java.sql.*;

/**
 * PerformanceDAO
 *
 * <p>
 * Data Access Object (DAO) class for managing player performance records
 * in the Soccer Management System database.
 * </p>
 *
 * <p>
 * Provides methods to:
 * <ul>
 *     <li>Add a new performance record.</li>
 *     <li>Update an existing performance record.</li>
 *     <li>Delete a performance record by ID.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example Usage:
 * <pre>
 * Performance p = new Performance();
 * p.setPlayerId(1);
 * p.setMatchId(2);
 * p.setGoals(3);
 * p.setAssists(1);
 * p.setMinutesPlayed(90);
 * boolean added = PerformanceDAO.AddPerformance(p);
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */

public class PerformanceDAO {

    /**
     * AddPerformance
     * Inserts a new player performance record into the database.
     *
     * @param p Performance object containing player_id, match_id, goals, assists, minutes_played
     * @return true if insertion was successful, false otherwise
     */
    public static boolean AddPerformance(Performance p){
        try(Connection conn = DBConnection.getConnection()){
            String sql = "INSERT INTO performance (player_id, match_id, goals, assists, minutes_played) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getPlayerId());
            stmt.setInt(2, p.getMatchId());
            stmt.setInt(3, p.getGoals());
            stmt.setInt(4, p.getAssists());
            stmt.setInt(5, p.getMinutesPlayed());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){ e.printStackTrace(); return false; }
    }

    /**
     * UpdatePerformance
     * Updates an existing player performance record based on performance_id.
     *
     * @param p Performance object containing performance_id, player_id, goals, assists
     * @return true if update was successful, false otherwise
     */
    public static boolean UpdatePerformance(Performance p){
        try(Connection conn = DBConnection.getConnection()){
            String sql = "UPDATE performance SET player_id=?, goals=?, assists=? WHERE performance_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p.getPlayerId());
            stmt.setInt(2, p.getGoals());
            stmt.setInt(3, p.getAssists());
            stmt.setInt(4, p.getPerformanceId());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){ e.printStackTrace(); return false; }
    }

    /**
     * DeletePerformance
     * Deletes a performance record from the database by performance_id.
     *
     * @param id performance_id of the record to delete
     * @return true if deletion was successful, false otherwise
     */
    public static boolean DeletePerformance(int id){
        try(Connection conn = DBConnection.getConnection()){
            String sql = "DELETE FROM performance WHERE performance_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){ e.printStackTrace(); return false; }
    }
}
