package model;
/**
 * Viewer
 *
 * <p>
 * Represents a viewer user in the Soccer Management System.
 * هذا الكلاس يرث من User ولا يحتوي على أي خصائص إضافية.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * Viewer v = new Viewer();
 * v.setUsername("fan123");
 * v.setEmail("fan@example.com");
 * v.setRole("Viewer");
 * </pre>
 * </p>
 *
 * @version 1.0
 * @author Loay
 */
public class Viewer extends User {

    /** Default constructor. */
    public Viewer() {
        super();
    }
}
