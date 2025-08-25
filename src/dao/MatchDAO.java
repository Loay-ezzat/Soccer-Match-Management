package dao;

import model.Match;
import util.DBConnection;

import java.sql.*;

/**
 * MatchDAO is a Data Access Object (DAO) class responsible for
 * performing CRUD operations (Create, Update, Delete) on the `matches` table
 * in the Soccer Management System database.
 *
 * <p>Each method connects to the database, executes the SQL query, and returns
 * a boolean indicating success or failure, except getMatchesCount() which returns an integer.</p>
 *
 * <p>Usage example:
 * <pre>
 * Match match = new Match("Team A", "Team B", "2025-08-25", "Stadium", 2, 1);
 * boolean added = MatchDAO.AddMatch(match);
 * match.setHomeScore(3);
 * boolean updated = MatchDAO.UpdateMatch(match);
 * boolean deleted = MatchDAO.DeleteMatch(match);
 * int totalMatches = MatchDAO.getMatchesCount();
 * </pre>
 * </p>
 *
 * Author: Loay
 * Version: 1.0
 */
public class MatchDAO {

    /**
     * Adds a new match to the database.
     *
     * @param m Match object containing home_team, away_team, match_date, venue, home_score, away_score
     * @return true if insertion succeeds, false otherwise
     */
    public static boolean AddMatch(Match m) {
        if (m.getHomeTeam().equals(m.getAwayTeam())) {
            System.out.println("⚠️ A team cannot play against itself!");
            return false;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO matches (home_team, away_team, match_date, venue, home_score, away_score) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, m.getHomeTeam());
            stmt.setString(2, m.getAwayTeam());
            stmt.setString(3, m.getMatchDate());
            stmt.setString(4, m.getVenue());
            stmt.setInt(5, m.getHomeScore());
            stmt.setInt(6, m.getAwayScore());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    /**
     * Updates an existing match in the database.
     *
     * @param m Match object containing updated home_team, away_team, match_date, venue, home_score, away_score, and match_id
     * @return true if update succeeds, false otherwise
     */
    public static boolean UpdateMatch(Match m) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE matches SET home_team=?, away_team=?, match_date=?, venue=?, home_score=?, away_score=? WHERE match_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, m.getHomeTeam());
            stmt.setString(2, m.getAwayTeam());
            stmt.setString(3, m.getMatchDate());
            stmt.setString(4, m.getVenue());
            stmt.setInt(5, m.getHomeScore());
            stmt.setInt(6, m.getAwayScore());
            stmt.setInt(7, m.getMatchId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Deletes a match from the database.
     *
     * @param m Match object containing the match_id of the match to delete
     * @return true if deletion succeeds, false otherwise
     */
    public static boolean DeleteMatch(Match m) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM matches WHERE match_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, m.getMatchId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the total number of matches in the database.
     *
     * @return total number of matches
     */
    public static int getMatchesCount() {
        int count = 0;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM matches")) {
            if (rs.next()) count = rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
