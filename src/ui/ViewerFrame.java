package ui;

import dao.AdminDAO;
import util.DBConnection;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ViewerFrame provides the graphical interface for "Viewer" users in the Soccer Matches System.
 *
 * <p>Features:
 * <ul>
 *     <li>CardLayout panels for Dashboard, Players, Teams, Matches, Game Events, and Performance.</li>
 *     <li>Bottom toolbar with buttons to navigate between panels and logout.</li>
 *     <li>Searchable tables for Players, Teams, Matches, Game Events, and Performance.</li>
 *     <li>Menu bar with Help, About, Settings, and Logout options.</li>
 * </ul>
 * </p>
 *
 * <p>Search functionality allows filtering tables dynamically using a placeholder search field.</p>
 *
 * <p>Uses AdminDAO methods to populate tables with data from the database.</p>
 *
 * <p>On logout, closes DB connection and redirects to LoginFrame.</p>
 *
 * @author Loay
 * @version 1.0
 */
public class ViewerFrame extends JFrame implements ActionListener {

    // ------------ Variables ------------
    CardLayout cardLayout;
    JButton dashboardBtn, showPlayerBtn, showTeamBtn, showMatchesBtn,
            showGameEventBtn, performanceBtn, logOutBtn;

    JPanel mainPanel, dashboardPanel, showPlayerPanel, showTeamPanel,
            showMatchesPanel, showGameEventPanel, performancePanel;

    JLabel dashboardLabel;

    ImageIcon dashboardIcon = new ImageIcon("main.jpg");
    final Image dashboardImage = dashboardIcon.getImage();

    /**
     * Constructor: Initializes the Viewer GUI window with all panels, buttons, menu, and tables.
     */
    public ViewerFrame() {
        this.setTitle("Viewer Page");
        this.setResizable(false);
        this.setSize(1000, 630);
        this.setVisible(true);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //------ Buttons (Bottom Toolbar) :
        dashboardBtn = new JButton("Dashboard");
        showPlayerBtn = new JButton("Show Players");
        showTeamBtn = new JButton("Show Teams");
        showMatchesBtn = new JButton("Show Matches");
        showGameEventBtn = new JButton("Show Events");
        performanceBtn = new JButton("Performance");
        logOutBtn = new JButton("Logout");
        logOutBtn.setBackground(Color.RED);
        logOutBtn.setForeground(Color.black);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(new Color(30, 30, 30));
        bottomPanel.add(dashboardBtn);
        bottomPanel.add(showPlayerBtn);
        bottomPanel.add(showTeamBtn);
        bottomPanel.add(showMatchesBtn);
        bottomPanel.add(showGameEventBtn);
        bottomPanel.add(performanceBtn);
        bottomPanel.add(logOutBtn);

        this.add(bottomPanel, BorderLayout.SOUTH);

        //------ Panels :
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        dashboardPanel = createBackgroundPanel();
        showPlayerPanel = createBackgroundPanel();
        showTeamPanel = createBackgroundPanel();
        showMatchesPanel = createBackgroundPanel();
        showGameEventPanel = createBackgroundPanel();
        performancePanel = createBackgroundPanel();

        mainPanel.add(dashboardPanel, "dashboard");
        mainPanel.add(showPlayerPanel, "showPlayers");
        mainPanel.add(showTeamPanel, "showTeams");
        mainPanel.add(showMatchesPanel, "showMatches");
        mainPanel.add(showGameEventPanel, "showEvents");
        mainPanel.add(performancePanel, "performance");

        this.add(mainPanel, BorderLayout.CENTER);

        //------ Dashboard Panel
        dashboardLabel = new JLabel("Welcome Viewer 👀");
        dashboardLabel.setBounds(20, 20, 400, 30);
        dashboardLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dashboardLabel.setForeground(Color.WHITE);
        dashboardPanel.add(dashboardLabel);

        // ---------- Menu Bar (Copied from AdminFrame) -----------
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

        // ------ Actions -------
        dashboardBtn.addActionListener(this);
        showMatchesBtn.addActionListener(this);
        showGameEventBtn.addActionListener(this);
        logOutBtn.addActionListener(this);
        showPlayerBtn.addActionListener(this);
        performanceBtn.addActionListener(this);
        showTeamBtn.addActionListener(this);

        // ------ Menu Actions -------
        howToUse.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "🏟️ How to Use the Program:\n1. Login with your account.\n2. Navigate using buttons below.\n3. Use Search to find info.\n4. Logout when finished.",
                "How to Use", JOptionPane.INFORMATION_MESSAGE));

        shortcuts.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "⌨️ Keyboard Shortcuts:\n- Ctrl+L: Logout\n- Ctrl+F: Search\n- Esc: Close window",
                "Keyboard Shortcuts", JOptionPane.INFORMATION_MESSAGE));

        aboutSystem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "⚽ Soccer Management System\nVersion: 1.0\nDeveloped by: Loay Ezzat Mohamed",
                "About", JOptionPane.INFORMATION_MESSAGE));

        contactUs.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "📧 Contact Us:\n- Email: loayezzat20@gmail.com\n- GitHub: github.com/loayezzat",
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

        // -------- Populate Tables ----------
        populatePlayersTable();
        populateTeamsTable();
        populateMatchesTable();
        populateEventsTable();
        populatePerformancePanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dashboardBtn) cardLayout.show(mainPanel, "dashboard");
        else if (e.getSource() == showPlayerBtn) cardLayout.show(mainPanel, "showPlayers");
        else if (e.getSource() == showTeamBtn) cardLayout.show(mainPanel, "showTeams");
        else if (e.getSource() == showMatchesBtn) cardLayout.show(mainPanel, "showMatches");
        else if (e.getSource() == showGameEventBtn) cardLayout.show(mainPanel, "showEvents");
        else if (e.getSource() == performanceBtn) cardLayout.show(mainPanel, "performance");
        else if (e.getSource() == logOutBtn) {
            JOptionPane.showMessageDialog(this, "Logging out...");
            DBConnection.closeConnection();
            dispose();
            new LoginFrame().setVisible(true);
        }
    }

    /**
     * Creates a panel with the dashboard background image.
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

    // ---------- Populate + Search ----------
    private void populatePlayersTable() {
        DefaultTableModel model = AdminDAO.getPlayersTable();
        addSearchableTable(showPlayerPanel, model);
    }

    private void populateTeamsTable() {
        DefaultTableModel model = AdminDAO.getTeamsTable();
        addSearchableTable(showTeamPanel, model);
    }

    private void populateMatchesTable() {
        DefaultTableModel model = AdminDAO.getMatchesTable();
        addSearchableTable(showMatchesPanel, model);
    }

    private void populateEventsTable() {
        DefaultTableModel model = AdminDAO.getEventsTable();
        addSearchableTable(showGameEventPanel, model);
    }

    private void populatePerformancePanel() {
        DefaultTableModel model = AdminDAO.getPerformanceTable();
        addSearchableTable(performancePanel, model);
    }

    private void addSearchableTable(JPanel panel, DefaultTableModel model) {
        panel.removeAll();

        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // للعرض فقط
            }
        };

        // -------- Table Style (شفاف) --------
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setForeground(Color.WHITE);
        table.setOpaque(false);

        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);

        // Header Style
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 123, 255, 200));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // -------- ScrollPanel --------
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 750, 480);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 1));

        panel.add(scrollPane);

        // ---- Search Field ----
        JTextField searchField = new JTextField(" 🔍 Search...");
        searchField.setBounds(20, 20, 200, 30);
        searchField.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        searchField.setForeground(Color.GRAY);
        searchField.setBackground(new Color(255, 255, 255, 180));
        searchField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Placeholder behavior
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals(" 🔍 Search...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(" 🔍 Search...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        panel.add(searchField);

        // ---- Search Logic ----
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { search(); }
            public void removeUpdate(DocumentEvent e) { search(); }
            public void changedUpdate(DocumentEvent e) { search(); }

            private void search() {
                String text = searchField.getText();
                if (text.trim().length() == 0 || text.equals(" 🔍 Search...")) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        panel.revalidate();
        panel.repaint();
    }

}
