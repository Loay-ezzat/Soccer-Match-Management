package dao;

import util.DBConnection;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * AdminDAO is a Data Access Object (DAO) class for retrieving and managing
 * data related to players, teams, matches, events, and performance in the Soccer Management System.
 *
 * <p>This class provides methods to:
 * <ul>
 *     <li>Retrieve counts of players, teams, matches, and events.</li>
 *     <li>Retrieve table models for JTable display.</li>
 *     <li>Add new admin users.</li>
 * </ul>
 * </p>
 *
 * <p>Usage example:
 * <pre>
 * DefaultTableModel playersTable = AdminDAO.getPlayersTable();
 * int totalTeams = AdminDAO.getTeamsCount();
 * boolean added = AdminDAO.addAdmin("admin1", "admin@example.com", "password123");
 * </pre>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class AdminDAO {

    // ================= Players =================
    /**
     * Returns the total number of players in the database.
     * @return number of players
     */
    public static int getPlayersCount() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM players");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    /**
     * Returns a DefaultTableModel containing all players and their details.
     * @return DefaultTableModel of players
     */
    public static DefaultTableModel getPlayersTable() {
        String[] columns = {"ID", "Full Name", "Age", "Position", "Team", "Jersey Number"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = """
                SELECT p.player_id, p.full_name, p.age, p.position, t.team_name, p.jersey_number
                FROM players p
                LEFT JOIN teams t ON p.team_id = t.team_id
            """;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("player_id"),
                        rs.getString("full_name"),
                        rs.getInt("age"),
                        rs.getString("position"),
                        rs.getString("team_name"),
                        rs.getInt("jersey_number")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }

        return model;
    }

    // ================= Teams =================

    /** Returns the total number of teams in the database. */
    public static int getTeamsCount() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM teams");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    /** Returns a DefaultTableModel containing all teams and their details. */
    public static DefaultTableModel getTeamsTable() {
        String[] columns = {"Team ID", "Team Name", "Coach"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT team_id, team_name, coach_name FROM teams";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("team_id"),
                        rs.getString("team_name"),
                        rs.getString("coach_name")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }

        return model;
    }

    // ================= Matches =================

    /** Returns the total number of matches in the database. */
    public static int getMatchesCount() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM matches");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    /** Returns a DefaultTableModel containing all matches and their details. */
    public static DefaultTableModel getMatchesTable() {
        String[] columns = {"Match ID", "Home Team", "Away Team", "Date", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = """
                SELECT m.match_id, t1.team_name AS home_team, t2.team_name AS away_team,
                       m.match_date, m.home_score || ' - ' || m.away_score AS score
                FROM matches m
                LEFT JOIN teams t1 ON m.home_team = t1.team_id
                LEFT JOIN teams t2 ON m.away_team = t2.team_id
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("match_id"),
                        rs.getString("home_team"),
                        rs.getString("away_team"),
                        rs.getString("match_date"),
                        rs.getString("score")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }

        return model;
    }

    // ================= Events =================

    /** Returns the total number of game events in the database. */
    public static int getEventsCount() {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM game_event");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    /** Returns a DefaultTableModel containing all game events. */
    public static DefaultTableModel getEventsTable() {
        String[] columns = {"Event ID", "Match ID", "Player", "Team", "Event Type", "Time"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = """
                SELECT ge.event_id, ge.match_id, p.full_name AS player_name, t.team_name,
                       ge.event_type, ge.event_time
                FROM game_event ge
                LEFT JOIN players p ON ge.player_id = p.player_id
                LEFT JOIN teams t ON p.team_id = t.team_id
            """;
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("event_id"),
                        rs.getInt("match_id"),
                        rs.getString("player_name"),
                        rs.getString("team_name"),
                        rs.getString("event_type"),
                        rs.getString("event_time")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }

        return model;
    }

    // ================= Performance =================

    /** Returns a DefaultTableModel containing player performance ratings. */
    public static DefaultTableModel getPerformanceTable() {
        String[] columns = {"Player", "Team", "Goals", "Assists", "Minutes Played"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (Connection conn = DBConnection.getConnection()) {
            String sql = """
            SELECT p.full_name, t.team_name, pf.goals, pf.assists, pf.minutes_played
            FROM performance pf
            JOIN players p ON pf.player_id = p.player_id
            LEFT JOIN teams t ON p.team_id = t.team_id
        """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("full_name"),
                        rs.getString("team_name"),
                        rs.getInt("goals"),
                        rs.getInt("assists"),
                        rs.getInt("minutes_played")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }


    // ================= Admin =================

    /**
     * Adds a new admin user to the database.
     * @param username admin username
     * @param email admin email
     * @param password admin password
     * @return true if added successfully, false otherwise
     */
    public static boolean addAdmin(String username, String email, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, 'Admin')"
            );
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
