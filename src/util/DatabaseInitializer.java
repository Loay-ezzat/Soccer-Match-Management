package util;

import java.sql.Connection;
import java.sql.Statement;

/**
 * DatabaseInitializer
 *
 * <p>
 * This class is responsible for initializing the SQLite database for the Soccer Management System.
 * It creates all required tables if they do not already exist, including users, teams, players, matches,
 * game events, and performance data.
 * </p>
 *
 * <p>Main Features:
 * <ul>
 *   <li>Creates users table with roles (Admin/Viewer) and unique username</li>
 *   <li>Creates teams table with team name, coach, and founded year</li>
 *   <li>Creates players table linked to teams</li>
 *   <li>Creates matches table with team references, date, location, and result</li>
 *   <li>Creates game_event table to track in-match events like goals, cards, and substitutions</li>
 *   <li>Creates performance table linking player performance stats to matches</li>
 *   <li>All foreign keys are defined with proper cascading where needed</li>
 * </ul>
 * </p>
 *
 * <p>Example usage:
 * <pre>
 * DatabaseInitializer.initialize();
 * </pre>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class DatabaseInitializer {

    /**
     * Initializes the database by creating all necessary tables.
     *
     * <p>Tables created:
     * <ul>
     *   <li><b>users:</b> id, username, password, role (Admin/Viewer), email</li>
     *   <li><b>teams:</b> id, name, coach, founded_year</li>
     *   <li><b>players:</b> id, name, age, position, team_id (FK)</li>
     *   <li><b>matches:</b> id, team1_id (FK), team2_id (FK), match_date, location, result</li>
     *   <li><b>game_event:</b> id, match_id (FK), player_id (FK), event_type, event_time, description</li>
     *   <li><b>performance:</b> id, player_id (FK), match_id (FK), goals, assists, yellow_cards, red_cards, minutes_played, rating</li>
     * </ul>
     * </p>
     */
    public static void initialize() {
        String[] queries = {
                "CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username TEXT NOT NULL UNIQUE," +
                        "password TEXT NOT NULL," +
                        "role TEXT CHECK(role IN ('Admin','Viewer')) NOT NULL," +
                        "email TEXT);",

                "CREATE TABLE IF NOT EXISTS teams (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL UNIQUE," +
                        "coach TEXT," +
                        "founded_year INTEGER);",

                "CREATE TABLE IF NOT EXISTS players (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "age INTEGER," +
                        "position TEXT," +
                        "team_id INTEGER," +
                        "FOREIGN KEY(team_id) REFERENCES teams(id) ON DELETE CASCADE);",

                "CREATE TABLE IF NOT EXISTS matches (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "team1_id INTEGER," +
                        "team2_id INTEGER," +
                        "match_date TEXT," +
                        "location TEXT," +
                        "result TEXT," +
                        "FOREIGN KEY(team1_id) REFERENCES teams(id)," +
                        "FOREIGN KEY(team2_id) REFERENCES teams(id));",

                "CREATE TABLE IF NOT EXISTS game_event (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "match_id INTEGER NOT NULL," +
                        "player_id INTEGER," +
                        "event_type TEXT CHECK(event_type IN ('Goal','Assist','Yellow Card','Red Card','Substitution','Foul'))," +
                        "event_time INTEGER," +
                        "description TEXT," +
                        "FOREIGN KEY(match_id) REFERENCES matches(id) ON DELETE CASCADE," +
                        "FOREIGN KEY(player_id) REFERENCES players(id));",

                "CREATE TABLE IF NOT EXISTS performance (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "player_id INTEGER NOT NULL," +
                        "match_id INTEGER NOT NULL," +
                        "goals INTEGER DEFAULT 0," +
                        "assists INTEGER DEFAULT 0," +
                        "yellow_cards INTEGER DEFAULT 0," +
                        "red_cards INTEGER DEFAULT 0," +
                        "minutes_played INTEGER DEFAULT 0," +
                        "rating REAL DEFAULT 0.0," +
                        "FOREIGN KEY(player_id) REFERENCES players(id) ON DELETE CASCADE," +
                        "FOREIGN KEY(match_id) REFERENCES matches(id) ON DELETE CASCADE);"
        };

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String query : queries) {
                stmt.execute(query);
            }
            System.out.println("✅ All tables created successfully.");

        } catch (Exception e) {
            System.out.println("❌ Error creating tables: " + e.getMessage());
        }
    }
}
