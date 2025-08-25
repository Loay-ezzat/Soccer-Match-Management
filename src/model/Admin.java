package model;

/**
 * Admin
 *
 * <p>
 * Represents an Admin user in the Soccer Management System.
 * Inherits from the User class and does not add any additional fields.
 * </p>
 *
 * <p>
 * Provides constructors to create an Admin object:
 * <ul>
 *     <li>Default constructor</li>
 *     <li>Parameterized constructor with id, username, role, and password</li>
 * </ul>
 * </p>
 *
 * Example usage:
 * <pre>
 * Admin admin1 = new Admin();
 * Admin admin2 = new Admin(1, "loay", "Admin", "1234");
 * </pre>
 *
 * @version 1.0
 * @author Loay
 */
public class Admin extends User {

    /**
     * Default constructor.
     * Creates an Admin object with default values.
     */
    public Admin() {
    }

    /**
     * Parameterized constructor.
     *
     * @param id       the unique ID of the admin
     * @param username the username of the admin
     * @param role     the role of the user, should be "Admin"
     * @param password the password of the admin
     */
    public Admin(int id, String username, String role, String password) {
        super(id, username, role, password);
    }
}