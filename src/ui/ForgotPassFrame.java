package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * ForgotPassFrame provides a GUI interface for users to reset their password.
 *
 * <p>Features:
 * <ul>
 *     <li>User enters their registered email.</li>
 *     <li>Generates a 4-digit verification code (for demo purposes, shown in a dialog; normally sent via email).</li>
 *     <li>User enters the code and a new password.</li>
 *     <li>Password is updated in the database if code matches.</li>
 *     <li>Displays success/failure messages using JOptionPane.</li>
 *     <li>Redirects user to LoginFrame upon successful password reset.</li>
 * </ul>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class ForgotPassFrame extends JFrame implements ActionListener {

    // ---------- GUI Components ----------
    JLabel emailLabel, codeLabel, newPassLabel, titleLabel;
    JTextField emailField, codeField;
    JPasswordField newPassField;
    JButton sendCodeBtn, confirmBtn;

    /**
     * Stores the generated 4-digit verification code.
     * Initialized to -1 to indicate that no code has been generated yet.
     */
    int generatedCode = -1;

    /**
     * Constructor: Initializes the Forgot Password GUI window and its components.
     *
     * <p>Sets window size, layout, labels, text fields, buttons, and action listeners.</p>
     */
    public ForgotPassFrame() {
        setTitle("Forgot Password");
        setSize(500, 400);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Forgot Password");
        titleLabel.setBounds(150, 20, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel);

        emailLabel = new JLabel("Enter Email:");
        emailLabel.setBounds(50, 80, 120, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(180, 80, 220, 25);
        add(emailField);

        sendCodeBtn = new JButton("Send Code");
        sendCodeBtn.setBounds(180, 120, 120, 30);
        add(sendCodeBtn);

        codeLabel = new JLabel("Enter Code:");
        codeLabel.setBounds(50, 170, 120, 25);
        add(codeLabel);

        codeField = new JTextField();
        codeField.setBounds(180, 170, 220, 25);
        add(codeField);

        newPassLabel = new JLabel("New Password:");
        newPassLabel.setBounds(50, 220, 120, 25);
        add(newPassLabel);

        newPassField = new JPasswordField();
        newPassField.setBounds(180, 220, 220, 25);
        add(newPassField);

        confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(180, 270, 120, 30);
        add(confirmBtn);

        sendCodeBtn.addActionListener(this);
        confirmBtn.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendCodeBtn) {
            String email = emailField.getText().trim();
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your email!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // تحقق من وجود المستخدم في قاعدة البيانات
            User user = UserDAO.getUserByEmail(email);
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Email not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // توليد كود 4 أرقام
            Random rand = new Random();
            generatedCode = 1000 + rand.nextInt(9000);
            JOptionPane.showMessageDialog(this,
                    "Your code is: " + generatedCode + "\n(For demo purposes, normally it would be emailed)",
                    "Code Sent", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == confirmBtn) {
            if (generatedCode == -1) {
                JOptionPane.showMessageDialog(this, "Please generate a code first!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String enteredCode = codeField.getText().trim();
            String newPassword = new String(newPassField.getPassword()).trim();

            if (enteredCode.isEmpty() || newPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!enteredCode.equals(String.valueOf(generatedCode))) {
                JOptionPane.showMessageDialog(this, "Incorrect code!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // تحديث كلمة المرور في قاعدة البيانات
            String email = emailField.getText().trim();
            boolean success = UserDAO.updatePassword(email, newPassword);
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Password updated successfully!");
                dispose();
                new LoginFrame().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
