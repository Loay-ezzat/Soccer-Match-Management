package model;
/**
 * User
 *
 * <p>
 * Represents a general user in the Soccer Management System.
 * Contains information about the user's ID, username, password, role, and email.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * User u = new User();
 * u.setUsername("loay123");
 * u.setPassword("securePass");
 * u.setRole("Admin");
 * u.setEmail("loay@example.com");
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String role;
    private String email;

    // ---------- Constructors ----------

    /** Constructs a User with specified id, username, role, and password. */
    public User(int id, String username, String role, String password) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
    }

    /** Default constructor. */
    public User() { }

    // ---------- Getters & Setters ----------

    /** Returns the user's ID. */
    public int getId() { return id; }

    /** Sets the user's ID. */
    public void setId(int id) { this.id = id; }

    /** Returns the username. */
    public String getUsername() { return username; }

    /** Sets the username. */
    public void setUsername(String username) { this.username = username; }

    /** Returns the password. */
    public String getPassword() { return password; }

    /** Sets the password. */
    public void setPassword(String password) { this.password = password; }

    /** Returns the role (e.g., Admin, Player). */
    public String getRole() { return role; }

    /** Sets the role. */
    public void setRole(String role) { this.role = role; }

    /** Returns the email. */
    public String getEmail() { return email; }

    /** Sets the email. */
    public void setEmail(String email) { this.email = email; }
}
