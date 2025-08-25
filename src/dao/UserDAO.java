package dao;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserDAO
 *
 * <p>
 * Data Access Object (DAO) class for managing user records
 * in the Soccer Management System database.
 * </p>
 *
 * <p>
 * Provides methods for:
 * <ul>
 *     <li>User login verification.</li>
 *     <li>Creating a new user.</li>
 *     <li>Checking if an email exists.</li>
 *     <li>Fetching user details by email.</li>
 *     <li>Updating user password.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Example Usage:
 * <pre>
 * String role = UserDAO.UserLogin("loay", "1234", "Admin");
 * boolean created = UserDAO.CreateUser("loay", "1234", "loay@email.com", "Admin");
 * boolean exists = UserDAO.isEmailExists("loay@email.com");
 * User user = UserDAO.getUserByEmail("loay@email.com");
 * boolean updated = UserDAO.updatePassword("loay@email.com", "newpass123");
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class UserDAO {

    /**
     * UserLogin
     * Verifies user credentials and role.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param selectedRole the role selected by the user
     * @return role if login succeeds,
     *         "ROLE_MISMATCH" if role does not match,
     *         "INVALID" if username/password incorrect,
     *         "ERROR" if exception occurs
     */
    public static String UserLogin(String username , String password , String selectedRole){
        String sql = "SELECT role  FROM users WHERE username = ? AND password = ? ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,username);
            stmt.setString(2,password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                String role = rs.getString("role");

                if (role.equalsIgnoreCase(selectedRole)){
                    return role;
                }else {
                    return "ROLE_MISMATCH";
                }
            }else {
                return "INVALID";
            }

        }catch (Exception e ){
            e.printStackTrace();
            return "ERROR";
        }
    }

    /**
     * CreateUser
     * Inserts a new user into the database.
     *
     * @param username username of the new user
     * @param password password of the new user
     * @param email email of the new user
     * @param role role of the new user (Admin/Viewer/etc.)
     * @return true if creation succeeds, false otherwise
     */
    public static boolean CreateUser(String username, String password, String email, String role) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, role);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
/**
 * isEmailExists
 * Checks if an email is already registered in the database.
 *
 * @param email email to check
 * @return true if email exists, false otherwise
 */
    public static boolean isEmailExists(String email) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            return stmt.executeQuery().next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * getUserByEmail
     * Fetches a User object by email.
     *
     * @param email email of the user
     * @return User object if found, null otherwise
     */
    public static User getUserByEmail(String email) {
        User user = null;
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    /**
     * updatePassword
     * Updates the password of a user based on email.
     *
     * @param email email of the user
     * @param newPassword new password to set
     * @return true if update succeeds, false otherwise
     */
    public static boolean updatePassword(String email, String newPassword) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "UPDATE users SET password = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, email);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
