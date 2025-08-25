package ui;

import dao.AdminDAO;
import util.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AdminFrame
 *
 * <p>
 * This class represents the main GUI window for the Admin page in the Soccer Management System.
 * It provides the interface for managing players, teams, matches, events, performance data,
 * and admin settings.
 * </p>
 *
 * <p>Main Features:
 * <ul>
 *   <li>Dashboard with counters for players, teams, matches, and events</li>
 *   <li>Side navigation panel with buttons for different sections</li>
 *   <li>CardLayout main panel to switch between dashboard, players, teams, matches, events, performance, and settings</li>
 *   <li>Menu bar with Help, About, Preferences, and Logout functionality</li>
 *   <li>CRUD operations via AdminDAO</li>
 *   <li>Tables styled and populated dynamically from the database</li>
 *   <li>Logout handling and session closing</li>
 * </ul>
 * </p>
 *
 * <p>Example usage:
 * <pre>
 * AdminFrame adminFrame = new AdminFrame();
 * adminFrame.setVisible(true);
 * </pre>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class AdminFrame extends JFrame implements ActionListener {

    // ------------ Variables ------------
    // UI components such as buttons, panels, and labels

    CardLayout cardLayout;

    // Buttons
    JButton dashboardBtn, editPlayerBtn, showPlayerBtn, editTeamBtn, showTeamBtn,
            editMatchesBtn, showMatchesBtn, editGameEventBtn,
            showGameEventBtn, performanceBtn, settingBtn, logOutBtn;

    // Panels
    JPanel sidePanel, mainPanel, dashboardPanel, showPlayerPanel, showTeamPanel, showMatchesPanel,
            showGameEventPanel, performancePanel, settingPanel;

    // Labels
    JLabel dashboardLabel, showPlayerLabel, showTeamLabel, showMatchesLabel,
            showGameEventLabel, performanceLabel, settingLabel, playersCounterLabel,
            teamCounterLabel, matchesCounterLabel, eventsCounterLabel,
            showPlayerCountLabel, showTeamCountLabel, showMatchesCountLabel, showEventsCountLabel;

    // Background images
    ImageIcon dashboardIcon = new ImageIcon("main.jpg");
    final Image dashboardImage = dashboardIcon.getImage();

    /**
     * Constructor.
     *
     * <p>
     * Initializes the frame size, layout, buttons, panels, menu bar, side panel,
     * dashboard counters, tables, and admin settings panel.
     * </p>
     */
    public AdminFrame() {

        this.setTitle("Admin Page");
        this.setResizable(false);
        this.setSize(1000, 630);
        this.setVisible(true);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //--------- Initialization -------------
        dashboardLabel = new JLabel("Welcome to the Dashboard");
        showPlayerLabel = new JLabel("Players Information");
        showTeamLabel = new JLabel("Teams Information");
        showMatchesLabel = new JLabel("Matches Information");
        showGameEventLabel = new JLabel("Game Event Page");
        performanceLabel = new JLabel("Players Performance");
        settingLabel = new JLabel("Setting Page");

        playersCounterLabel = new JLabel("Number of Players ");
        teamCounterLabel = new JLabel("Number of Teams");
        matchesCounterLabel = new JLabel("Number of Available Matches ");
        eventsCounterLabel = new JLabel("Number of Events");

        showMatchesCountLabel = new JLabel();
        showPlayerCountLabel = new JLabel();
        showTeamCountLabel = new JLabel();
        showEventsCountLabel = new JLabel();

        //----- Buttons :
        dashboardBtn = new JButton("Dashboard");
        editPlayerBtn = new JButton("Players Setting");
        showPlayerBtn = new JButton("Show Players");
        editTeamBtn = new JButton("Teams Setting");
        showTeamBtn = new JButton("Show Teams");
        editMatchesBtn = new JButton("Matches Setting");
        showMatchesBtn = new JButton("Show Matches");
        editGameEventBtn = new JButton("Events Setting");
        showGameEventBtn = new JButton("Show Events");
        performanceBtn = new JButton("Performance");
        settingBtn = new JButton("Setting");
        logOutBtn = new JButton("Logout");

        //------ Panels :
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        dashboardPanel = createBackgroundPanel();
        showPlayerPanel = createBackgroundPanel();
        showTeamPanel = createBackgroundPanel();
        showMatchesPanel = createBackgroundPanel();
        showGameEventPanel = createBackgroundPanel();
        performancePanel = createBackgroundPanel();
        settingPanel = createBackgroundPanel();

        mainPanel.add(dashboardPanel, "dashboard");
        mainPanel.add(showPlayerPanel, "showPlayers");
        mainPanel.add(showTeamPanel, "showTeams");
        mainPanel.add(showMatchesPanel, "showMatches");
        mainPanel.add(showGameEventPanel, "showEvents");
        mainPanel.add(performancePanel, "performance");
        mainPanel.add(settingPanel, "setting");

        // ---------- Menu Bar -----------
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(40, 40, 40));
        menuBar.setBorderPainted(false);

        JMenu helpMenu = new JMenu("❓ Help");
        helpMenu.setForeground(Color.WHITE);
        JMenuItem howToUse = new JMenuItem("How to Use the Program");
        JMenuItem shortcuts = new JMenuItem("Keyboard Shortcuts");
        helpMenu.add(howToUse);
        helpMenu.add(shortcuts);

        JMenu aboutMenu = new JMenu("ℹ️ About");
        aboutMenu.setForeground(Color.WHITE);
        JMenuItem aboutSystem = new JMenuItem("About Soccer Management System");
        JMenuItem contactUs = new JMenuItem("Contact Us");
        aboutMenu.add(aboutSystem);
        aboutMenu.add(contactUs);

        JMenu settingsMenu = new JMenu("⚙️ Preferences");
        settingsMenu.setForeground(Color.WHITE);
        JMenuItem changeTheme = new JMenuItem("Change Theme");
        JMenuItem language = new JMenuItem("Language Settings");
        settingsMenu.add(changeTheme);
        settingsMenu.add(language);

        JMenu logoutMenu = new JMenu("🚪 Logout");
        logoutMenu.setForeground(Color.RED);
        JMenuItem logoutBtnMenu = new JMenuItem("Logout Now");
        logoutMenu.add(logoutBtnMenu);

        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);
        menuBar.add(settingsMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(logoutMenu);
        setJMenuBar(menuBar);

        // ------------- Sidebar -----------
        ImageIcon sideImageIcon = new ImageIcon("sidebar.jpg");
        final Image sideImage = sideImageIcon.getImage();

        sidePanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(sideImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        sidePanel.setLayout(null);
        this.add(sidePanel);
        sidePanel.setBounds(0, 0, 200, 630);

        dashboardBtn.setBounds(5, 10, 190, 40);
        sidePanel.add(dashboardBtn);
        editPlayerBtn.setBounds(5, 55, 190, 40);
        sidePanel.add(editPlayerBtn);
        showPlayerBtn.setBounds(5, 100, 190, 40);
        sidePanel.add(showPlayerBtn);
        editTeamBtn.setBounds(5, 145, 190, 40);
        sidePanel.add(editTeamBtn);
        showTeamBtn.setBounds(5, 190, 190, 40);
        sidePanel.add(showTeamBtn);
        editMatchesBtn.setBounds(5, 235, 190, 40);
        sidePanel.add(editMatchesBtn);
        showMatchesBtn.setBounds(5, 280, 190, 40);
        sidePanel.add(showMatchesBtn);
        editGameEventBtn.setBounds(5, 325, 190, 40);
        sidePanel.add(editGameEventBtn);
        showGameEventBtn.setBounds(5, 370, 190, 40);
        sidePanel.add(showGameEventBtn);
        performanceBtn.setBounds(5, 415, 190, 40);
        sidePanel.add(performanceBtn);
        settingBtn.setBounds(5, 460, 190, 40);
        sidePanel.add(settingBtn);
        logOutBtn.setBounds(5, 525, 190, 40);
        sidePanel.add(logOutBtn);
        logOutBtn.setBackground(Color.RED);
        logOutBtn.setForeground(Color.black);

        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(200, 0, 800, 630);
        this.add(mainPanel);

        //------ Dashboard Panel
        dashboardLabel.setBounds(20, 20, 300, 30);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dashboardLabel.setForeground(Color.WHITE);
        dashboardPanel.add(dashboardLabel);

        playersCounterLabel.setBounds(20, 50, 350, 120);
        playersCounterLabel.setFont(new Font(null, Font.BOLD, 22));
        playersCounterLabel.setForeground(Color.WHITE);
        dashboardPanel.add(playersCounterLabel);
        showPlayerCountLabel.setBounds(380, 50, 100, 120);
        showPlayerCountLabel.setFont(new Font(null, Font.BOLD, 22));
        showPlayerCountLabel.setForeground(Color.WHITE);
        dashboardPanel.add(showPlayerCountLabel);

        teamCounterLabel.setBounds(20, 150, 350, 120);
        teamCounterLabel.setFont(new Font(null, Font.BOLD, 22));
        teamCounterLabel.setForeground(Color.WHITE);
        dashboardPanel.add(teamCounterLabel);
        showTeamCountLabel.setBounds(380, 150, 100, 120);
        showTeamCountLabel.setFont(new Font(null, Font.BOLD, 22));
        showTeamCountLabel.setForeground(Color.WHITE);
        dashboardPanel.add(showTeamCountLabel);

        matchesCounterLabel.setBounds(20, 250, 350, 120);
        matchesCounterLabel.setFont(new Font(null, Font.BOLD, 22));
        matchesCounterLabel.setForeground(Color.WHITE);
        dashboardPanel.add(matchesCounterLabel);
        showMatchesCountLabel.setBounds(380, 250, 100, 120);
        showMatchesCountLabel.setFont(new Font(null, Font.BOLD, 22));
        showMatchesCountLabel.setForeground(Color.WHITE);
        dashboardPanel.add(showMatchesCountLabel);

        eventsCounterLabel.setBounds(20, 350, 350, 120);
        eventsCounterLabel.setFont(new Font(null, Font.BOLD, 22));
        eventsCounterLabel.setForeground(Color.WHITE);
        dashboardPanel.add(eventsCounterLabel);
        showEventsCountLabel.setBounds(380, 350, 100, 120);
        showEventsCountLabel.setFont(new Font(null, Font.BOLD, 22));
        showEventsCountLabel.setForeground(Color.WHITE);
        dashboardPanel.add(showEventsCountLabel);

        // ------ Action for Buttons -------
        dashboardBtn.addActionListener(this);
        showMatchesBtn.addActionListener(this);
        editMatchesBtn.addActionListener(this);
        editGameEventBtn.addActionListener(this);
        showGameEventBtn.addActionListener(this);
        logOutBtn.addActionListener(this);
        editPlayerBtn.addActionListener(this);
        showPlayerBtn.addActionListener(this);
        performanceBtn.addActionListener(this);
        editTeamBtn.addActionListener(this);
        showTeamBtn.addActionListener(this);
        settingBtn.addActionListener(this);

        // ------ Action for Menu ----------
        howToUse.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "🏟️ How to Use the Program:\n1. Login with your account.\n2. Navigate using buttons.\n3. Use 'Edit' buttons to update/add data.\n4. Logout when finished.",
                "How to Use", JOptionPane.INFORMATION_MESSAGE));

        shortcuts.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "⌨️ Keyboard Shortcuts:\n- Ctrl+S: Save\n- Ctrl+L: Logout\n- Ctrl+F: Search\n- Esc: Close window",
                "Keyboard Shortcuts", JOptionPane.INFORMATION_MESSAGE));

        aboutSystem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "⚽ Soccer Management System\nVersion: 1.0\nDeveloped by: Loay Ezzat Mohamed",
                "About", JOptionPane.INFORMATION_MESSAGE));

        contactUs.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "📧 Contact Us:\n- Email: loayezzat20@gmail.com\n- LinkedIn: linkedin.com/in/loayezzat\n- GitHub: github.com/loayezzat",
                "Contact Us", JOptionPane.INFORMATION_MESSAGE));

        changeTheme.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "✨ Theme switching coming soon!", "Change Theme", JOptionPane.INFORMATION_MESSAGE));

        language.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "🌍 Multilanguage support coming soon.", "Language Settings", JOptionPane.INFORMATION_MESSAGE));

        logoutBtnMenu.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null, "✅ Logged out successfully!");
                DBConnection.closeConnection();
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        // -------- Populate Tables & Dashboard ----------

        updateDashboardCounters();
        populatePlayersTable();
        populateTeamsTable();
        populateMatchesTable();
        populateEventsTable();
        populatePerformancePanel();
        setupAdminSettingPanel();
    }

    /**
     * Handles the actions of different buttons.
     *
     * @param e The action event triggered by a button
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dashboardBtn) cardLayout.show(mainPanel, "dashboard");
        else if (e.getSource() == showPlayerBtn) cardLayout.show(mainPanel, "showPlayers");
        else if (e.getSource() == showTeamBtn) cardLayout.show(mainPanel, "showTeams");
        else if (e.getSource() == showMatchesBtn) cardLayout.show(mainPanel, "showMatches");
        else if (e.getSource() == showGameEventBtn) cardLayout.show(mainPanel, "showEvents");
        else if (e.getSource() == performanceBtn) cardLayout.show(mainPanel, "performance");
        else if (e.getSource() == settingBtn) cardLayout.show(mainPanel, "setting");
        else if (e.getSource() == logOutBtn) {
            JOptionPane.showMessageDialog(this, "Logging out...");
            DBConnection.closeConnection();
            dispose();
            new LoginFrame().setVisible(true);
        } else if (e.getSource() == editPlayerBtn) {
            dispose(); new EditPlayerFrame();
        } else if (e.getSource() == editTeamBtn) {
            dispose(); new EditTeamFrame();
        } else if (e.getSource() == editMatchesBtn) {
            dispose(); new EditMatchFrame();
        } else if (e.getSource() == editGameEventBtn) {
            dispose(); new EditEventFrame().setVisible(true);
        }
    }

    /**
     * Creates a JPanel with the dashboard background image.
     *
     * @return JPanel with background image
     */

    private JPanel createBackgroundPanel() {
        return new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dashboardImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }

    /**
     * Updates the counters on the Dashboard panel.
     */

    private void updateDashboardCounters() {
        try {
            int playersCount = AdminDAO.getPlayersCount();
            int teamsCount = AdminDAO.getTeamsCount();
            int matchesCount = AdminDAO.getMatchesCount();
            int eventsCount = AdminDAO.getEventsCount();

            showPlayerCountLabel.setText(String.valueOf(playersCount));
            showTeamCountLabel.setText(String.valueOf(teamsCount));
            showMatchesCountLabel.setText(String.valueOf(matchesCount));
            showEventsCountLabel.setText(String.valueOf(eventsCount));
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    /**
     * Populates the players table on the corresponding panel.
     */
    private void populatePlayersTable() {
        DefaultTableModel model = AdminDAO.getPlayersTable();
        styleAndAddTable(showPlayerPanel, model);
    }

    /**
     * Populates the teams table on the corresponding panel.
     */
    private void populateTeamsTable() {
        DefaultTableModel model = AdminDAO.getTeamsTable();
        styleAndAddTable(showTeamPanel, model);
    }

    /**
     * Populates the matches table on the corresponding panel.
     */
    private void populateMatchesTable() {
        DefaultTableModel model = AdminDAO.getMatchesTable();
        styleAndAddTable(showMatchesPanel, model);
    }

    /**
     * Populates the events table on the corresponding panel.
     */
    private void populateEventsTable() {
        DefaultTableModel model = AdminDAO.getEventsTable();
        styleAndAddTable(showGameEventPanel, model);
    }

    /**
     * Populates the performance table on the corresponding panel.
     */
    private void populatePerformancePanel() {
        performancePanel.removeAll();
        performancePanel.setLayout(new BorderLayout());

        // زرار Edit Performance أعلى الـ panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton editPerformanceBtn = new JButton("Edit Performance");
        topPanel.add(editPerformanceBtn);
        performancePanel.add(topPanel, BorderLayout.NORTH);

        editPerformanceBtn.addActionListener(e -> new EditPerformanceFrame().setVisible(true));

        // جدول الأداء في منتصف الـ panel
        DefaultTableModel model = AdminDAO.getPerformanceTable();
        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setForeground(Color.BLACK); // خليها أسود للوضوح
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        performancePanel.add(scrollPane, BorderLayout.CENTER);

        performancePanel.revalidate();
        performancePanel.repaint();
    }


    /**
     * Sets up the Admin settings panel with fields and actions.
     */
    private void setupAdminSettingPanel() {
        settingPanel.removeAll();
        settingPanel.setLayout(null);

        // ---- Admin Fields ----
        JLabel usernameLabel = new JLabel("Admin Username:");
        usernameLabel.setBounds(30, 50, 150, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(Color.WHITE);
        settingPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(180, 50, 300, 30);
        settingPanel.add(usernameField);

        JLabel emailLabel = new JLabel("Admin Email:");
        emailLabel.setBounds(30, 100, 150, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setForeground(Color.WHITE);
        settingPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(180, 100, 300, 30);
        settingPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 150, 150, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.WHITE);
        settingPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(180, 150, 300, 30);
        settingPanel.add(passwordField);

        JButton addAdminBtn = new JButton("Add Admin");
        addAdminBtn.setBounds(180, 200, 120, 30);
        addAdminBtn.setBackground(new Color(0, 123, 255));
        addAdminBtn.setForeground(Color.WHITE);
        addAdminBtn.setFocusPainted(false);
        settingPanel.add(addAdminBtn);

        addAdminBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if(username.isEmpty() || email.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean success = AdminDAO.addAdmin(username, email, password);
            if(success){
                JOptionPane.showMessageDialog(this, "Admin added successfully!");
                usernameField.setText(""); emailField.setText(""); passwordField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add Admin!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ---- Backup & Restore Buttons ----
        JButton backupBtn = new JButton("Backup Database");
        backupBtn.setBounds(180, 270, 150, 35);
        backupBtn.setBackground(new Color(40, 167, 69)); // أخضر
        backupBtn.setForeground(Color.WHITE);
        backupBtn.setFocusPainted(false);
        settingPanel.add(backupBtn);

        JButton restoreBtn = new JButton("Restore Database");
        restoreBtn.setBounds(340, 270, 150, 35);
        restoreBtn.setBackground(new Color(220, 53, 69)); // أحمر
        restoreBtn.setForeground(Color.WHITE);
        restoreBtn.setFocusPainted(false);
        settingPanel.add(restoreBtn);

// ---- Action Listeners ----
// استخدم النسخة الجديدة اللي مش محتاجة dbPath كـ parameter
        backupBtn.addActionListener(e -> util.BackupRestore.backupDatabase());
        restoreBtn.addActionListener(e -> util.BackupRestore.restoreDatabase());

// إعادة تحديث الـ Tables
        populatePlayersTable();
        populateTeamsTable();
        populateMatchesTable();
        populateEventsTable();
        populatePerformancePanel();

        settingPanel.revalidate();
        settingPanel.repaint();
    }


    /**
     * Styles a table and adds it to the given panel.
     *
     * @param panel The panel to contain the table
     * @param model The table model containing the data
     */
    private void styleAndAddTable(JPanel panel, DefaultTableModel model) {
        JTable table = new JTable(model);

        // ---- Style ----
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setGridColor(Color.LIGHT_GRAY);
        table.setForeground(Color.WHITE);
        table.setOpaque(false);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setOpaque(false);
        table.setDefaultRenderer(Object.class, renderer);

        // ---- Table Header ----
        table.getTableHeader().setBackground(new Color(0, 123, 255));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        // ---- Scroll Pane ----
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 750, 500);

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        panel.removeAll();
        panel.add(scrollPane);
        panel.revalidate();
        panel.repaint();
    }



}

