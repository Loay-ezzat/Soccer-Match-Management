package ui;

import dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SignupFrame provides a graphical interface for creating a new user account in the Soccer Matches System.
 *
 * <p>Features:
 * <ul>
 *     <li>Username, password, confirm password, and email input fields.</li>
 *     <li>Create Account button to register a new user.</li>
 *     <li>Back button to return to the LoginFrame.</li>
 *     <li>Validation checks for empty fields and password confirmation.</li>
 *     <li>Uses UserDAO to insert new users into the database.</li>
 * </ul>
 * </p>
 *
 * <p>Upon successful account creation:
 * <ul>
 *     <li>User is redirected to LoginFrame to log in with the new credentials.</li>
 * </ul>
 * </p>
 *
 * <p>Displays error messages if validation fails or database insertion fails.</p>
 *
 * @author Loay
 * @version 1.0
 */
public class SignupFrame extends JFrame implements ActionListener {

    JTextField usernameField, emailField;
    JPasswordField passwordField, confirmPassField;
    JButton createBtn, backBtn;
    JLabel usernameLabel, passwordLabel, confirmPassLabel, emailLabel, titleLabel;

    /**
     * Constructor: Initializes the signup GUI window with all components and action listeners.
     *
     * <p>Sets up:
     * <ul>
     *     <li>Background image panel</li>
     *     <li>Labels and input fields for username, password, confirm password, and email</li>
     *     <li>Create Account and Back buttons</li>
     * </ul>
     * </p>
     */
    public SignupFrame() {
        setTitle("Create New User Account");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ---------- Background Image ------------
        ImageIcon backgroundIcon = new ImageIcon("Login.jpg");
        final Image BACKGROUNDIMAGE = backgroundIcon.getImage();

        JPanel background = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(BACKGROUNDIMAGE, 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(null);
        add(background);

        // ---------- Labels ----------
        titleLabel = new JLabel("- Create User Account -");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font(null, Font.BOLD, 25));
        titleLabel.setBounds(250, 30, 400, 50);
        background.add(titleLabel);

        usernameLabel = new JLabel("Username :");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(200, 120, 100, 25);
        background.add(usernameLabel);

        passwordLabel = new JLabel("Password :");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(200, 160, 100, 25);
        background.add(passwordLabel);

        confirmPassLabel = new JLabel("Confirm Password :");
        confirmPassLabel.setForeground(Color.WHITE);
        confirmPassLabel.setBounds(200, 200, 150, 25);
        background.add(confirmPassLabel);

        emailLabel = new JLabel("Email :");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(200, 240, 100, 25);
        background.add(emailLabel);

        // ---------- Fields ----------
        usernameField = new JTextField();
        usernameField.setBounds(360, 120, 200, 25);
        background.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(360, 160, 200, 25);
        background.add(passwordField);

        confirmPassField = new JPasswordField();
        confirmPassField.setBounds(360, 200, 200, 25);
        background.add(confirmPassField);

        emailField = new JTextField();
        emailField.setBounds(360, 240, 200, 25);
        background.add(emailField);

        // ---------- Buttons ----------
        createBtn = new JButton("Create Account");
        createBtn.setBounds(250, 320, 150, 30);
        createBtn.setBackground(Color.WHITE);
        createBtn.setForeground(Color.BLACK);
        createBtn.setFocusPainted(false);
        createBtn.setBorder(null);
        background.add(createBtn);

        backBtn = new JButton("Back to Login");
        backBtn.setBounds(420, 320, 150, 30);
        backBtn.setBackground(Color.WHITE);
        backBtn.setForeground(Color.BLACK);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(null);
        background.add(backBtn);

        // ---------- Action Listeners ----------
        createBtn.addActionListener(this);
        backBtn.addActionListener(this);

        setVisible(true);
    }

    /**
     * Handles actions for Create Account and Back buttons.
     *
     * @param e ActionEvent triggered by buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createBtn) {
            String username = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPass = new String(confirmPassField.getPassword());

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean success = UserDAO.CreateUser(username, password, email, "Viewer");

            if (success) {
                JOptionPane.showMessageDialog(this, "Account created successfully! ✅");
                new LoginFrame().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create account! ❌", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == backBtn) {
            new LoginFrame().setVisible(true);
            dispose();
        }
    }
}
