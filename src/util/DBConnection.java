package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection is a utility class for managing a single SQLite database connection.
 *
 * <p>Features:
 * <ul>
 *     <li>Provides a singleton connection instance to the SQLite database "soccer_project.db".</li>
 *     <li>Handles automatic connection creation if not already connected.</li>
 *     <li>Provides a method to safely close the connection.</li>
 * </ul>
 * </p>
 *
 * <p>Usage:
 * <pre>
 * Connection conn = DBConnection.getConnection();
 * // Use the connection for queries...
 * DBConnection.closeConnection();
 * </pre>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class DBConnection {
    /** SQLite database URL */
    private static final String URL = "jdbc:sqlite:soccer_project.db";

    /** Singleton connection instance */
    private static Connection connection = null;

    /**
     * Returns a Connection object to the SQLite database.
     * If the connection is closed or null, it creates a new one.
     *
     * @return Connection object or null if connection failed
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("✅ Connected to SQLite database.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Connection Failed: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Closes the database connection safely and sets the instance to null.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("🔒 Connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDatabasePath() {
        return "soccer_project.db"; // نفس المسار اللي مستخدم في الاتصال
    }

}
