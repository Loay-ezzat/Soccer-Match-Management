package dao;

import model.Team;
import util.DBConnection;

import java.sql.*;

/**
 * TeamDAO
 *
 * <p>
 * Data Access Object (DAO) class for managing team records
 * in the Soccer Management System database.
 * </p>
 *
 * <p>
 * Provides methods to:
 * <ul>
 *     <li>Add a new team.</li>
 *     <li>Update an existing team.</li>
 *     <li>Delete a team by ID and name.</li>
 *     <li>Get total count of teams.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example Usage:
 * <pre>
 * Team team = new Team("FC Barcelona", "Xavi Hernandez", 1899);
 * boolean added = TeamDAO.AddTeam(team);
 * team.setCoachName("Ronald Koeman");
 * boolean updated = TeamDAO.UpdateTeam(team);
 * boolean deleted = TeamDAO.DeleteTeam(team);
 * int totalTeams = TeamDAO.getTeamsCount();
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class TeamDAO {

    /**
     * AddTeam
     * Inserts a new team record into the database.
     *
     * @param t Team object containing team_name, coach_name, founded_year
     * @return true if insertion succeeds, false otherwise
     */
    public static boolean AddTeam(Team t) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO teams (team_name, coach_name, founded_year) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getTeamName());
            stmt.setString(2, t.getCoachName());
            stmt.setInt(3, t.getFoundationYear());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * UpdateTeam
     * Updates an existing team record based on team_id.
     *
     * @param t Team object containing updated team_name, coach_name, founded_year, and team_id
     * @return true if update succeeds, false otherwise
     */
    public static boolean UpdateTeam(Team t) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE teams SET team_name=?, coach_name=?, founded_year=? WHERE team_id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, t.getTeamName());
            stmt.setString(2, t.getCoachName());
            stmt.setInt(3, t.getFoundationYear());
            stmt.setInt(4, t.getTeamId());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * DeleteTeam
     * Deletes a team record from the database by team_id and team_name.
     *
     * @param t Team object containing team_id and team_name
     * @return true if deletion succeeds, false otherwise
     */
    public static boolean DeleteTeam(Team t) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "DELETE FROM teams WHERE team_id=? AND team_name=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, t.getTeamId());
            stmt.setString(2, t.getTeamName());
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * getTeamsCount
     * Returns the total number of teams stored in the database.
     *
     * @return total number of teams as integer
     */
    public static int getTeamsCount() {
        int count = 0;
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM teams")) {
            if (rs.next()) count = rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
