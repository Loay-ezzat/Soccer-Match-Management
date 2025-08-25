package ui;

import dao.PlayerDAO;
import util.DBConnection;

import model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EditPlayerFrame provides the GUI for managing Players in the Soccer Management System.
 *
 * <p>This frame allows the admin to add, update, or delete player records.</p>
 *
 * <p>Main Features:
 * <ul>
 *   <li>Three main panels for adding, editing, and deleting players</li>
 *   <li>Sidebar navigation and menu bar for easy access to operations</li>
 *   <li>Form fields for player details: name, age, nationality, position, team, jersey number</li>
 *   <li>Integration with PlayerDAO for database operations</li>
 *   <li>Feedback via message dialogs for operation success or failure</li>
 *   <li>Menu options for help, about, settings, and logout</li>
 * </ul>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class EditPlayerFrame extends JFrame implements ActionListener {

    // ------- Variables ---------
    CardLayout cardLayout;
    JButton editPlayerBtn, addPlayerBtn, deletePlayerBtn, backBtn
            , insertPlayerBtn , confirmDeleteBtn ,confirmUpdateBtn  ;

    JPanel sidePanel, mainPanel, editJPanel, addJPanel, deleteJPanel ;

    JLabel addPlayerLabel , nameLabel , ageLabel , nationalityLabel ,
            positionLabel , teamLabel , jerseyNumberLabel ;

    JTextField nameField , ageField , nationalityField , positionField ,
                teamField , jerseyNumberField , deleteByIdField , editIdField,
            editNameField , editJerseyField , editTeamField , editPositionField ,
            editNationalityField , editAgeField , deleteJerseyField ,deleteNameField;


    ImageIcon dashboardIcon = new ImageIcon("main2.jpg");
    final Image dashboardImage = dashboardIcon.getImage();

    /**
     * Constructor: Initializes the EditPlayerFrame window and its components.
     *
     * <p>Includes:
     * <ul>
     *   <li>Setting window properties like title, size, and layout</li>
     *   <li>Creating panels for add, edit, and delete operations</li>
     *   <li>Creating sidebar buttons and menu bar for navigation</li>
     *   <li>Adding form fields and labels to each panel</li>
     *   <li>Attaching ActionListeners to buttons and menu items</li>
     * </ul>
     * </p>
     */
    public EditPlayerFrame() {

        this.setTitle(" Players Setting");
        this.setResizable(false);
        this.setSize(1000, 630);
        this.setVisible(true);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        //--------- Initialization -------------

        // ------ TextField :
        nameField = new JTextField();
        ageField = new JTextField();
        nationalityField = new JTextField();
        positionField = new JTextField();
        teamField = new JTextField();
        jerseyNumberField = new JTextField();

        // ------ Labels :
        addPlayerLabel = new JLabel("Add Players Page");
        nameLabel = new JLabel("Full Name :");
        ageLabel = new JLabel("Age :");
        nationalityLabel = new JLabel("Nationality :");
        positionLabel = new JLabel("Position :");
        teamLabel = new JLabel("Team ID :");
        jerseyNumberLabel = new JLabel("Jersey Number :");

        //------ Panels :
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        editJPanel = createBackgroundPanel();
        addJPanel = createBackgroundPanel();
        deleteJPanel = createBackgroundPanel();


        mainPanel.add(editJPanel, "edit");
        mainPanel.add(addJPanel, "add");
        mainPanel.add(deleteJPanel, "delete");

        //----- Buttons :
        addPlayerBtn = new JButton("Add Player");
        editPlayerBtn = new JButton("Update Player");
        deletePlayerBtn = new JButton("Delete Player");
        backBtn = new JButton("Back ");
        insertPlayerBtn = new JButton("Insert");
        confirmUpdateBtn = new JButton("Update");
        confirmDeleteBtn = new JButton("Confirm");



        // ---------- Menu Bar -----------
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(40, 40, 40));
        menuBar.setBorderPainted(false);
        // ------- Players Menu :
        JMenu playersMenu = new JMenu("⚽ Players");
        playersMenu.setForeground(Color.WHITE);

        JMenuItem addMenuItem = new JMenuItem("Add Player");
        JMenuItem editMenuItem = new JMenuItem("Update Player");
        JMenuItem deleteMenuItem = new JMenuItem("Delete Player");
        playersMenu.add(addMenuItem);
        playersMenu.add(editMenuItem);
        playersMenu.add(deleteMenuItem);

        // ------- Help :
        JMenu helpMenu = new JMenu("❓ Help");
        helpMenu.setForeground(Color.WHITE);

        JMenuItem howToUse = new JMenuItem("How to Use the Program");
        JMenuItem shortcuts = new JMenuItem("Keyboard Shortcuts");
        helpMenu.add(howToUse);
        helpMenu.add(shortcuts);
        // ------- About :
        JMenu aboutMenu = new JMenu("ℹ️ About");
        aboutMenu.setForeground(Color.WHITE);

        JMenuItem aboutSystem = new JMenuItem("About Soccer Management System");
        JMenuItem contactUs = new JMenuItem("Contact Us");
        aboutMenu.add(aboutSystem);
        aboutMenu.add(contactUs);

        // ------- Preferences :
        JMenu settingsMenu = new JMenu("⚙️ Preferences");
        settingsMenu.setForeground(Color.WHITE);

        JMenuItem changeTheme = new JMenuItem("Change Theme");
        JMenuItem language = new JMenuItem("Language Settings");
        settingsMenu.add(changeTheme);
        settingsMenu.add(language);

        // ------- Logout :
        JMenu logoutMenu = new JMenu("🚪 Logout");
        logoutMenu.setForeground(Color.RED);
        JMenuItem logoutBtn = new JMenuItem("Logout Now");
        logoutMenu.add(logoutBtn);
        // ------- Add Menus :
        menuBar.add(playersMenu);
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


        addPlayerBtn.setBounds(5, 55, 190, 40);
        sidePanel.add(addPlayerBtn);

        editPlayerBtn.setBounds(5, 125, 190, 40);
        sidePanel.add(editPlayerBtn);

        deletePlayerBtn.setBounds(5, 195, 190, 40);
        sidePanel.add(deletePlayerBtn);

        backBtn.setBounds(5, 525, 190, 40);
        sidePanel.add(backBtn);
        backBtn.setBackground(Color.RED);
        backBtn.setForeground(Color.black);

        // ----------- Main Panel --------------
        mainPanel.setLayout(cardLayout);
        mainPanel.setBounds(200, 0, 800, 630);
        this.add(mainPanel);

        // ------ Add Player Page :

        addPlayerLabel.setBounds(20,20,300,30);
        addPlayerLabel.setFont(new Font("Arial",Font.BOLD,20));
        addPlayerLabel.setForeground(Color.WHITE);
        addJPanel.add(addPlayerLabel);

        insertPlayerBtn.setBounds(200,430,120,30);
        insertPlayerBtn.setFont(new Font(null , Font.BOLD,15));
        addJPanel.add(insertPlayerBtn);

        nameLabel.setBounds(30, 80, 100, 40);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        nameLabel.setForeground(Color.WHITE);
        addJPanel.add(nameLabel);
        nameField.setBounds(180, 85, 320, 30);
        nameField.setFont(new Font(null, Font.BOLD, 15));
        addJPanel.add(nameField);

        ageLabel.setBounds(30, 130, 150, 40);
        ageLabel.setFont(new Font("Arial", Font.BOLD, 17));
        ageLabel.setForeground(Color.WHITE);
        addJPanel.add(ageLabel);
        ageField.setBounds(180, 135, 320, 30);
        ageField.setFont(new Font(null, Font.BOLD, 15));
        addJPanel.add(ageField);

        nationalityLabel.setBounds(30, 180, 150, 40);
        nationalityLabel.setFont(new Font("Arial", Font.BOLD, 17));
        nationalityLabel.setForeground(Color.WHITE);
        addJPanel.add(nationalityLabel);
        nationalityField.setBounds(180, 185, 320, 30);
        nationalityField.setFont(new Font(null, Font.BOLD, 15));
        addJPanel.add(nationalityField);

        positionLabel.setBounds(30, 230, 150, 40);
        positionLabel.setFont(new Font("Arial", Font.BOLD, 17));
        positionLabel.setForeground(Color.WHITE);
        addJPanel.add(positionLabel);
        positionField.setBounds(180, 235, 320, 30);
        positionField.setFont(new Font(null, Font.BOLD, 15));
        addJPanel.add(positionField);

        teamLabel.setBounds(30, 280, 150, 40);
        teamLabel.setFont(new Font("Arial", Font.BOLD, 17));
        teamLabel.setForeground(Color.WHITE);
        addJPanel.add(teamLabel);
        teamField.setBounds(180, 285, 320, 30);
        teamField.setFont(new Font(null, Font.BOLD, 15));
        addJPanel.add(teamField);

        jerseyNumberLabel.setBounds(30, 330, 150, 40);
        jerseyNumberLabel.setFont(new Font("Arial", Font.BOLD, 17));
        jerseyNumberLabel.setForeground(Color.WHITE);
        addJPanel.add(jerseyNumberLabel);
        jerseyNumberField.setBounds(180, 335, 320, 30);
        jerseyNumberField.setFont(new Font(null, Font.BOLD, 15));
        addJPanel.add(jerseyNumberField);


        // ------ Action for Buttons -------
        addPlayerBtn.addActionListener(this);
        editPlayerBtn.addActionListener(this);
        deletePlayerBtn.addActionListener(this);
        backBtn.addActionListener(this);
        insertPlayerBtn.addActionListener(this);
        confirmDeleteBtn.addActionListener(this);
        confirmUpdateBtn.addActionListener(this);

        // ------ Edit Player Page :
        JLabel editPlayerLabel = new JLabel("Update Player Page");
        editPlayerLabel.setBounds(20,20,300,30);
        editPlayerLabel.setFont(new Font("Arial",Font.BOLD,20));
        editPlayerLabel.setForeground(Color.WHITE);
        editJPanel.add(editPlayerLabel);

        confirmUpdateBtn.setBounds(280,500,170,30);
        confirmUpdateBtn.setFont(new Font(null , Font.BOLD,15));
        editJPanel.add(confirmUpdateBtn);

        JLabel editNameLabel = new JLabel("Full Name :");
        editNameLabel.setBounds(30, 80, 100, 40);
        editNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editNameLabel.setForeground(Color.WHITE);
        editJPanel.add(editNameLabel);
        editNameField = new JTextField();
        editNameField.setBounds(180, 85, 320, 30);
        editNameField.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(editNameField);

        JLabel editAgeLabel = new JLabel("Age :");
        editAgeLabel.setBounds(30, 130, 150, 40);
        editAgeLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editAgeLabel.setForeground(Color.WHITE);
        editJPanel.add(editAgeLabel);
        editAgeField = new JTextField();
        editAgeField.setBounds(180, 135, 320, 30);
        editAgeField.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(editAgeField);

        JLabel editNationalityLabel = new JLabel("Nationality :");
        editNationalityLabel.setBounds(30, 180, 150, 40);
        editNationalityLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editNationalityLabel.setForeground(Color.WHITE);
        editJPanel.add(editNationalityLabel);
        editNationalityField = new JTextField();
        editNationalityField.setBounds(180, 185, 320, 30);
        editNationalityField.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(editNationalityField);

        JLabel editPositionLabel = new JLabel("Position :");
        editPositionLabel.setBounds(30, 230, 150, 40);
        editPositionLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editPositionLabel.setForeground(Color.WHITE);
        editJPanel.add(editPositionLabel);
        editPositionField = new JTextField();
        editPositionField.setBounds(180, 235, 320, 30);
        editPositionField.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(editPositionField);

        JLabel editTeamLabel = new JLabel("Team ID :");
        editTeamLabel.setBounds(30, 280, 150, 40);
        editTeamLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editTeamLabel.setForeground(Color.WHITE);
        editJPanel.add(editTeamLabel);
        editTeamField = new JTextField();
        editTeamField.setBounds(180, 285, 320, 30);
        editTeamField.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(editTeamField);

        JLabel editJerseyLabel = new JLabel("Jersey Number :");
        editJerseyLabel.setBounds(30, 330, 150, 40);
        editJerseyLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editJerseyLabel.setForeground(Color.WHITE);
        editJPanel.add(editJerseyLabel);
        editJerseyField = new JTextField();
        editJerseyField.setBounds(180, 335, 320, 30);
        editJerseyField.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(editJerseyField);

        JLabel editIdLabel = new JLabel("Player ID :");
        editIdLabel.setBounds(30,380,150,40);
        editIdLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editIdLabel.setForeground(Color.WHITE);
        editJPanel.add(editIdLabel);
        editIdField = new JTextField();
        editIdField.setBounds(180,385,320,30);
        editIdField.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(editIdField);


        //------ Delete Player Page :
        JLabel deletePlayerLabel = new JLabel("Delete Player Page");
        deletePlayerLabel.setBounds(20,20,300,30);
        deletePlayerLabel.setFont(new Font("Arial",Font.BOLD,20));
        deletePlayerLabel.setForeground(Color.WHITE);
        deleteJPanel.add(deletePlayerLabel);

        JLabel deleteByNameLabel = new JLabel("Player Name :");
        deleteByNameLabel.setBounds(30, 100, 150, 40);
        deleteByNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        deleteByNameLabel.setForeground(Color.WHITE);
        deleteJPanel.add(deleteByNameLabel);
        deleteNameField = new JTextField();
        deleteNameField.setBounds(180, 105, 320, 30);
        deleteNameField.setFont(new Font(null, Font.BOLD, 15));
        deleteJPanel.add(deleteNameField);

        JLabel deleteByJerseyLabel = new JLabel("Jersey Number :");
        deleteByJerseyLabel.setBounds(30, 160, 150, 40);
        deleteByJerseyLabel.setFont(new Font("Arial", Font.BOLD, 17));
        deleteByJerseyLabel.setForeground(Color.WHITE);
        deleteJPanel.add(deleteByJerseyLabel);
        deleteJerseyField = new JTextField();
        deleteJerseyField.setBounds(180, 165, 320, 30);
        deleteJerseyField.setFont(new Font(null, Font.BOLD, 15));
        deleteJPanel.add(deleteJerseyField);

        JLabel deleteByIDLabel = new JLabel("Player ID :");
        deleteByIDLabel.setBounds(30,220,150,40);
        deleteByIDLabel.setFont(new Font("Arial", Font.BOLD, 17));
        deleteByIDLabel.setForeground(Color.WHITE);
        deleteJPanel.add(deleteByIDLabel);
        deleteByIdField = new JTextField();
        deleteByIdField.setBounds(180,225,320,30);
        deleteByIdField.setFont(new Font(null, Font.BOLD, 15));
        deleteJPanel.add(deleteByIdField);

        confirmDeleteBtn.setBounds(280,300,170,30);
        confirmDeleteBtn.setFont(new Font(null , Font.BOLD,15));
        deleteJPanel.add(confirmDeleteBtn);

        // ------ Action for Menu ----------
        howToUse.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                🏟️ How to Use the Program:
                1. Login with your account.
                2. Navigate between Players, Teams, Matches using the buttons.
                3. Use 'Edit' buttons to update or add data.
                4. Logout when you finish.
                """,
                "How to Use", JOptionPane.INFORMATION_MESSAGE));

        shortcuts.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                ⌨️ Keyboard Shortcuts:
                - Ctrl + S : Save changes
                - Ctrl + L : Logout
                - Ctrl + F : Search
                - Esc      : Close current window
                """,
                "Keyboard Shortcuts", JOptionPane.INFORMATION_MESSAGE));

        aboutSystem.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                ⚽ Soccer Management System
                Version: 1.0
                Developed by: Loay Ezzat Mohamed
                Faculty of Computers & Informatics
                """,
                "About", JOptionPane.INFORMATION_MESSAGE));

        contactUs.addActionListener(e -> JOptionPane.showMessageDialog(this,
                """
                📧 Contact Us:
                - Email: loayezzat20@gmail.com
                - LinkedIn: linkedin.com/in/loayezzat
                - GitHub: github.com/loayezzat
                """,
                "Contact Us", JOptionPane.INFORMATION_MESSAGE));

        changeTheme.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "✨ Theme switching feature coming soon!",
                "Change Theme", JOptionPane.INFORMATION_MESSAGE));

        language.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "🌍 Multilanguage support coming soon (Arabic/English).",
                "Language Settings", JOptionPane.INFORMATION_MESSAGE));

        logoutBtn.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to logout?",
                    "Confirm Logout", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "✅ Logged out successfully!");
                DBConnection.closeConnection();
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
        addMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "add"));

        editMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "edit"));

        deleteMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "delete"));
    }

    /**
     * Creates a JPanel with a background image.
     *
     * @return JPanel with dashboard background
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
     * Handles all button click actions in the frame.
     *
     * <p>Actions include:
     * <ul>
     *   <li>Switching between add, edit, and delete panels</li>
     *   <li>Going back to the AdminFrame</li>
     *   <li>Adding, updating, or deleting players using PlayerDAO</li>
     *   <li>Displaying success or failure messages for each operation</li>
     * </ul>
     * </p>
     *
     * @param e The ActionEvent triggered by a button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addPlayerBtn) {
            cardLayout.show(mainPanel, "add");
        } else if (e.getSource() == editPlayerBtn) {
            cardLayout.show(mainPanel, "edit");
        } else if (e.getSource() == deletePlayerBtn) {
            cardLayout.show(mainPanel, "delete");
        } else if (e.getSource() == backBtn) {
            JOptionPane.showMessageDialog(this, "Back to Admin Page...");
            dispose();
            new AdminFrame().setVisible(true);
        } else if (e.getSource()==insertPlayerBtn) {
            Player p = new Player();
            p.setUsername(nameField.getText().trim());
            p.setAge(Integer.parseInt(ageField.getText().trim()));
            p.setNationality(nationalityField.getText().trim());
            p.setPosition(positionField.getText().trim());
            p.setTeamName(teamField.getText().trim());
            p.setJerseyNumber(Integer.parseInt(jerseyNumberField.getText().trim()));
            boolean success = PlayerDAO.AddPlayer(p);
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Player added successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to add player.");
            }
        } else if (e.getSource()==confirmDeleteBtn) {
            Player p = new Player();

            p.setUsername(deleteNameField.getText().trim());
            p.setJerseyNumber(Integer.parseInt(deleteJerseyField.getText().trim()));
            p.setId(Integer.parseInt(deleteByIdField.getText().trim()));
            boolean success = PlayerDAO.DeletePlayer(p);
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Player deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to delete player.");
            }
        } else if (e.getSource()==confirmUpdateBtn) {
            Player p = new Player();
            p.setUsername(editNameField.getText().trim());
            p.setAge(Integer.parseInt(editAgeField.getText().trim()));
            p.setNationality(editNationalityField.getText().trim());
            p.setPosition(editPositionField.getText().trim());
            p.setTeamName(editTeamField.getText().trim());
            p.setJerseyNumber(Integer.parseInt(editJerseyField.getText().trim()));
            p.setId(Integer.parseInt(editIdField.getText().trim()));
            boolean success = PlayerDAO.UpdatePlayer(p);
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Player updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Failed to update player.");
            }

        }
    }
}
