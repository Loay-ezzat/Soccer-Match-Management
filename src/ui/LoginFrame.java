package ui;

import dao.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LoginFrame provides a graphical interface for users to log in to the Soccer Matches System.
 *
 * <p>Features:
 * <ul>
 *     <li>Username and password input fields.</li>
 *     <li>Role selection (Admin or Viewer/User) using checkboxes.</li>
 *     <li>Login button with authentication against database.</li>
 *     <li>Sign Up button to redirect to SignupFrame.</li>
 *     <li>Forgot Password link to redirect to ForgotPassFrame.</li>
 *     <li>Menu bar with Help, About, and Settings sections.</li>
 * </ul>
 * </p>
 *
 * <p>Upon successful login:
 * <ul>
 *     <li>Admin users are redirected to AdminFrame.</li>
 *     <li>Viewer users are redirected to ViewerFrame.</li>
 * </ul>
 * </p>
 *
 * <p>Displays error messages if login fails or role mismatches.</p>
 *
 * @author Loay
 * @version 1.0
 */
public class LoginFrame extends JFrame implements ActionListener {


    // -------------Variables--------------

    JButton loginBtn ,  signUpBtn;
    JCheckBox adminCheck , userCheck;
    JTextField userNameField ;
    JPasswordField passwordField;
    JLabel userNameLabel , passwordLabel , loginLabel , poweredLabel  ,forgotPassLabel ;
    ButtonGroup groupBtn;
    JMenuBar menuBar;
    JMenu helpMenu;
    JMenuItem aboutItem , contactItem , howtoItem;


    /**
     * Constructor: Initializes the login GUI window with all components and event listeners.
     *
     * <p>Sets up:
     * <ul>
     *     <li>Background image panel</li>
     *     <li>Menu bar with Help, About, and Settings menus</li>
     *     <li>Username, password fields and labels</li>
     *     <li>Role selection checkboxes</li>
     *     <li>Login and Sign Up buttons</li>
     *     <li>Forgot Password link</li>
     * </ul>
     * </p>
     */
    public LoginFrame(){

        setTitle("Soccor Matches ");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // ---------- Background Image ------------
        ImageIcon backgroundIcon = new ImageIcon("Login.jpg");
        final Image BACKGROUNDIMAGE = backgroundIcon.getImage();

        JPanel background = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(BACKGROUNDIMAGE, 0, 0, getWidth(), getHeight(), this);
        }
        };
        background.setLayout(null);
        add(background);

        /**
         * Configures the menu bar, menus, and menu items with their respective actions.
         */

        // ---------- Menu Bar -----------
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(40, 40, 40));
        menuBar.setBorderPainted(false);

        // ----- Help :
        helpMenu = new JMenu("❓ Help");
        helpMenu.setForeground(Color.WHITE);

        howtoItem = new JMenuItem("How to Use");

        JMenuItem shortcutsItem = new JMenuItem("Keyboard Shortcuts");

        helpMenu.add(howtoItem);
        helpMenu.add(shortcutsItem);

        // ------ About :
        JMenu aboutMenu = new JMenu("ℹ️ About");
        aboutMenu.setForeground(Color.WHITE);

        aboutItem = new JMenuItem("About Application");
        contactItem = new JMenuItem("Contact Support");
        aboutMenu.add(aboutItem);
        aboutMenu.add(contactItem);

        // ------ Setting :
        JMenu settingsMenu = new JMenu("⚙️ Settings");
        settingsMenu.setForeground(Color.WHITE);

        JMenuItem themeItem = new JMenuItem("Change Theme");
        JMenuItem langItem = new JMenuItem("Language Settings");
        settingsMenu.add(themeItem);
        settingsMenu.add(langItem);

        // ------ Add Menus :
        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);
        menuBar.add(settingsMenu);

        setJMenuBar(menuBar);

        // ------ Action for Menu ----------
        howtoItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                🏟️ How to Use the Program:
                1. Enter Username & Password.
                2. Select your role (Admin / User).
                3. Click Login to access the system.
                """,
                "How to Use", JOptionPane.INFORMATION_MESSAGE));

        shortcutsItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                ⌨️ Keyboard Shortcuts:
                - Ctrl + S : Save changes
                - Ctrl + L : Logout
                - Esc      : Close current window
                """,
                "Shortcuts", JOptionPane.INFORMATION_MESSAGE));

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                ⚽ Soccer Management System
                Version: 1.0
                Developed by: Loay Ezzat Mohamed
                Faculty of Computers & Informatics
                """,
                "About", JOptionPane.INFORMATION_MESSAGE));

        contactItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                📧 Contact Us:
                - Email: loayezzat20@gmail.com
                - LinkedIn: linkedin.com/in/loayezzat
                - GitHub: github.com/loayezzat
                """,
                "Contact Support", JOptionPane.INFORMATION_MESSAGE));

        themeItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "✨ Theme switching feature coming soon!",
                "Change Theme", JOptionPane.INFORMATION_MESSAGE));

        langItem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "🌍 Multilanguage support coming soon (Arabic/English).",
                "Language Settings", JOptionPane.INFORMATION_MESSAGE));
        // ---------- Components -----------

        loginLabel = new JLabel("Login Page ");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font(null , Font.BOLD , 25));
        loginLabel.setBounds(320,80,150,35);
        background.add(loginLabel);

        poweredLabel = new JLabel("Powered by Loay");
        poweredLabel.setForeground(Color.white);
        poweredLabel.setBounds(350,450,1700,25);
        background.add(poweredLabel);

        userNameLabel = new JLabel("Username :");
        userNameLabel.setForeground(Color.WHITE);
        userNameLabel.setBounds(250,180,100,25);
        background.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setBounds(350,180,180,25);
        background.add(userNameField);

        passwordLabel = new JLabel("Password :");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(250,220,100,25);
        background.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(350,220,180,25);
        background.add(passwordField);

        // --------- Check Box -------------
        groupBtn = new ButtonGroup();

        adminCheck = new JCheckBox("Admin");
        adminCheck.setBounds(350,270,120,35);
        adminCheck.setForeground(Color.white);
        adminCheck.setOpaque(false);
        groupBtn.add(adminCheck);
        background.add(adminCheck);

        userCheck = new JCheckBox("User");
        userCheck.setBounds(480,270,140,35);
        userCheck.setForeground(Color.white);
        userCheck.setOpaque(false);
        groupBtn.add(userCheck);
        background.add(userCheck);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(250,340,120,35);
        loginBtn.setBackground(Color.WHITE);
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(null);
        background.add(loginBtn);

        signUpBtn = new JButton("Sign up");
        signUpBtn.setBounds(450,340,120,35);
        signUpBtn.setForeground(Color.BLACK);
        signUpBtn.setBackground(Color.WHITE);
        signUpBtn.setFocusPainted(false);
        signUpBtn.setBorder(null);
        background.add(signUpBtn);

        forgotPassLabel = new JLabel("Forgot Password ? ");
        forgotPassLabel.setForeground(Color.GREEN);
        forgotPassLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPassLabel.setBounds(250,380,120,35);
        background.add(forgotPassLabel);

        forgotPassLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new ForgotPassFrame().setVisible(true);
            }
        });

        setVisible(true);

        loginBtn.addActionListener(this);
        signUpBtn.addActionListener(this);
        adminCheck.addActionListener(this);
        userCheck.addActionListener(this);
        userNameField.addActionListener(this);
        passwordField.addActionListener(this);

    }

    /**
     * Handles login, sign up, and role selection actions.
     *
     * @param e ActionEvent triggered by buttons or fields
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginBtn){
            String username = userNameField.getText();
            String password = new String(passwordField.getPassword());
            String selectedRole = adminCheck.isSelected() ? "Admin" : "Viewer";

            String result = UserDAO.UserLogin(username,password,selectedRole);

            switch (result)
            {
                case "Admin":
                    JOptionPane.showMessageDialog(this, "Welcome Admin!");
                    new AdminFrame().setVisible(true);
                    dispose();
                    break;
                case "Viewer":
                    JOptionPane.showMessageDialog(this, "Welcome Viewer!");
                    new ViewerFrame().setVisible(true);
                    dispose();
                    break;
                case "ROLE_MISMATCH":
                    JOptionPane.showMessageDialog(this, "Role does not match selection!", "Login Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case "INVALID":
                    JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
                    break;

                case "ERROR":
                    JOptionPane.showMessageDialog(this, "Database error occurred!", "Login Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } else if (e.getSource() == signUpBtn) {
            new SignupFrame().setVisible(true);
            dispose();
        } else if (e.getSource() == forgotPassLabel) {
            this.dispose();
        }
    }
}
