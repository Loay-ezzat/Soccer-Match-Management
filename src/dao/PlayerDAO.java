package dao;

import model.Player;
import ui.EditPlayerFrame;
import util.DBConnection;

import java.sql.*;

/**
 * PlayerDAO
 *
 * <p>
 * Data Access Object (DAO) class for managing player records
 * in the Soccer Management System database.
 * </p>
 *
 * <p>
 * Provides methods to:
 * <ul>
 *     <li>Add a new player.</li>
 *     <li>Update an existing player.</li>
 *     <li>Delete a player by ID, name, and jersey number.</li>
 *     <li>Get total count of players.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example Usage:
 * <pre>
 * Player player = new Player("Lionel Messi", 36, "Argentina", "Forward", "PSG", 30);
 * boolean added = PlayerDAO.AddPlayer(player);
 * player.setAge(37);
 * boolean updated = PlayerDAO.UpdatePlayer(player);
 * boolean deleted = PlayerDAO.DeletePlayer(player);
 * int totalPlayers = PlayerDAO.getPlayersCount();
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class PlayerDAO {

    /**
     * AddPlayer
     * Inserts a new player record into the database.
     *
     * @param p Player object containing full_name, age, nationality, position, team_id, jersey_number
     * @return true if insertion succeeds, false otherwise
     */
    public static boolean AddPlayer(Player p){

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO players (full_name, age, nationality, position, team_id, jersey_number) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,p.getUsername());
            stmt.setInt(2,p.getAge());
            stmt.setString(3,p.getNationality());
            stmt.setString(4, p.getPosition());
            stmt.setString(5, p.getTeamName());
            stmt.setInt(6,p.getJerseyNumber());
            int rows = stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * UpdatePlayer
     * Updates an existing player record based on player_id.
     *
     * @param p Player object containing updated full_name, age, nationality, position, team_id, jersey_number, and player_id
     * @return true if update succeeds, false otherwise
     */
    public static boolean UpdatePlayer(Player p){

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE players SET full_name=?, age=?, nationality=?, position=?, team_id=?, jersey_number=? WHERE player_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,p.getUsername());
            stmt.setInt(2,p.getAge());
            stmt.setString(3,p.getNationality());
            stmt.setString(4, p.getPosition());
            stmt.setString(5, p.getTeamName());
            stmt.setInt(6,p.getJerseyNumber());
            stmt.setInt(7, p.getId());
            int rows = stmt.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * DeletePlayer
     * Deletes a player record from the database by player_id, full_name, and jersey_number.
     *
     * @param player Player object containing player_id, full_name, and jersey_number
     * @return true if deletion succeeds, false otherwise
     */
    public static boolean DeletePlayer( Player player){
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM players WHERE player_id=? AND full_name=? AND jersey_number=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, player.getId());
            stmt.setString(2,player.getUsername());
            stmt.setInt(3,player.getJerseyNumber());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * getPlayersCount
     * Returns the total number of players stored in the database.
     *
     * @return total number of players as integer
     */
    public static int getPlayersCount() {
        int count = 0;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM players")) {
            if (rs.next()) count = rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
