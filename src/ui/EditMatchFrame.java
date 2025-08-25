package ui;

import dao.MatchDAO;
import model.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EditMatchFrame provides the GUI for managing Matches in the Soccer Management System.
 *
 * <p>This frame allows the admin to add, update, or delete match records.</p>
 *
 * <p>Main Features:
 * <ul>
 *   <li>Three main panels for adding, editing, and deleting matches</li>
 *   <li>Sidebar navigation with buttons for each operation and back to Admin page</li>
 *   <li>Form fields for inputting match details: home team, away team, match date, venue, and scores</li>
 *   <li>Integration with MatchDAO for database operations</li>
 *   <li>Feedback via message dialogs for operation success or failure</li>
 * </ul>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class EditMatchFrame extends JFrame implements ActionListener {

    // ------- Variables ---------
    CardLayout cardLayout;
    JButton addMatchBtn, editMatchBtn, deleteMatchBtn, backBtn,
            insertMatchBtn, confirmUpdateBtn, confirmDeleteBtn;

    JPanel sidePanel, mainPanel, addJPanel, editJPanel, deleteJPanel;

    JLabel addMatchLabel;

    JTextField homeTeamField, awayTeamField, dateField, venueField,
            homeScoreField, awayScoreField,
            editHomeTeamField, editAwayTeamField, editDateField, editVenueField,
            editHomeScoreField, editAwayScoreField, editIdField,
            deleteIdField;

    ImageIcon dashboardIcon = new ImageIcon("main2.jpg");
    final Image dashboardImage = dashboardIcon.getImage();

    /**
     * Constructor: Initializes the EditMatchFrame window and its components.
     *
     * <p>Includes:
     * <ul>
     *   <li>Setting window properties like title, size, and layout</li>
     *   <li>Creating panels for add, edit, and delete operations</li>
     *   <li>Creating sidebar buttons for navigation and adding them to the side panel</li>
     *   <li>Adding form fields and buttons to each panel</li>
     *   <li>Attaching ActionListeners to all buttons</li>
     * </ul>
     * </p>
     */
    public EditMatchFrame() {

        this.setTitle("Matches Setting");
        this.setResizable(false);
        this.setSize(1000, 630);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // ---------- TextFields ----------
        homeTeamField = new JTextField();
        awayTeamField = new JTextField();
        dateField = new JTextField();
        venueField = new JTextField();
        homeScoreField = new JTextField();
        awayScoreField = new JTextField();

        editHomeTeamField = new JTextField();
        editAwayTeamField = new JTextField();
        editDateField = new JTextField();
        editVenueField = new JTextField();
        editHomeScoreField = new JTextField();
        editAwayScoreField = new JTextField();
        editIdField = new JTextField();

        deleteIdField = new JTextField();

        // ---------- Panels ----------
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        addJPanel = createBackgroundPanel();
        editJPanel = createBackgroundPanel();
        deleteJPanel = createBackgroundPanel();

        mainPanel.add(addJPanel, "add");
        mainPanel.add(editJPanel, "edit");
        mainPanel.add(deleteJPanel, "delete");

        // ---------- Buttons ----------
        addMatchBtn = new JButton("Add Match");
        editMatchBtn = new JButton("Update Match");
        deleteMatchBtn = new JButton("Delete Match");
        backBtn = new JButton("Back");
        insertMatchBtn = new JButton("Insert");
        confirmUpdateBtn = new JButton("Update");
        confirmDeleteBtn = new JButton("Confirm");

        // ---------- Sidebar ----------
        ImageIcon sideImageIcon = new ImageIcon("sidebar.jpg");
        final Image sideImage = sideImageIcon.getImage();

        sidePanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(sideImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        sidePanel.setBounds(0, 0, 200, 630);
        sidePanel.setLayout(null);
        this.add(sidePanel);

        addMatchBtn.setBounds(5, 55, 190, 40);
        sidePanel.add(addMatchBtn);
        editMatchBtn.setBounds(5, 125, 190, 40);
        sidePanel.add(editMatchBtn);
        deleteMatchBtn.setBounds(5, 195, 190, 40);
        sidePanel.add(deleteMatchBtn);
        backBtn.setBounds(5, 525, 190, 40);
        backBtn.setBackground(Color.RED);
        backBtn.setForeground(Color.BLACK);
        sidePanel.add(backBtn);

        // ---------- Main Panel ----------
        mainPanel.setBounds(200, 0, 800, 630);
        this.add(mainPanel);

        // --------- Add Match Panel ----------
        addMatchLabel = new JLabel("Add Match Page");
        addMatchLabel.setBounds(20, 20, 300, 30);
        addMatchLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addMatchLabel.setForeground(Color.WHITE);
        addJPanel.add(addMatchLabel);

        addLabelAndField(addJPanel, "Home Team ID:", 80, homeTeamField);
        addLabelAndField(addJPanel, "Away Team ID:", 130, awayTeamField);
        addLabelAndField(addJPanel, "Match Date:", 180, dateField);
        addLabelAndField(addJPanel, "Venue:", 230, venueField);
        addLabelAndField(addJPanel, "Home Score:", 280, homeScoreField);
        addLabelAndField(addJPanel, "Away Score:", 330, awayScoreField);

        insertMatchBtn.setBounds(280, 430, 170, 30);
        insertMatchBtn.setFont(new Font(null, Font.BOLD, 15));
        addJPanel.add(insertMatchBtn);

        // --------- Edit Match Panel ----------
        JLabel editMatchLabel = new JLabel("Update Match Page");
        editMatchLabel.setBounds(20, 20, 300, 30);
        editMatchLabel.setFont(new Font("Arial", Font.BOLD, 20));
        editMatchLabel.setForeground(Color.WHITE);
        editJPanel.add(editMatchLabel);

        addLabelAndField(editJPanel, "Home Team ID:", 80, editHomeTeamField);
        addLabelAndField(editJPanel, "Away Team ID:", 130, editAwayTeamField);
        addLabelAndField(editJPanel, "Match Date:", 180, editDateField);
        addLabelAndField(editJPanel, "Venue:", 230, editVenueField);
        addLabelAndField(editJPanel, "Home Score:", 280, editHomeScoreField);
        addLabelAndField(editJPanel, "Away Score:", 330, editAwayScoreField);

        JLabel editIdLabel = new JLabel("Match ID:");
        editIdLabel.setBounds(30, 380, 150, 40);
        editIdLabel.setForeground(Color.WHITE);
        editIdLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editJPanel.add(editIdLabel);
        editIdField.setBounds(180, 385, 320, 30);
        editJPanel.add(editIdField);

        confirmUpdateBtn.setBounds(280, 500, 170, 30);
        confirmUpdateBtn.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(confirmUpdateBtn);

        // --------- Delete Match Panel ----------
        JLabel deleteMatchLabel = new JLabel("Delete Match Page");
        deleteMatchLabel.setBounds(20, 20, 300, 30);
        deleteMatchLabel.setFont(new Font("Arial", Font.BOLD, 20));
        deleteMatchLabel.setForeground(Color.WHITE);
        deleteJPanel.add(deleteMatchLabel);

        JLabel deleteIdLabel = new JLabel("Match ID:");
        deleteIdLabel.setBounds(30, 100, 150, 40);
        deleteIdLabel.setForeground(Color.WHITE);
        deleteIdLabel.setFont(new Font("Arial", Font.BOLD, 17));
        deleteJPanel.add(deleteIdLabel);
        deleteIdField.setBounds(180, 105, 320, 30);
        deleteJPanel.add(deleteIdField);

        confirmDeleteBtn.setBounds(280, 300, 170, 30);
        confirmDeleteBtn.setFont(new Font(null, Font.BOLD, 15));
        deleteJPanel.add(confirmDeleteBtn);

        // ---------- Button Actions ----------
        addMatchBtn.addActionListener(this);
        editMatchBtn.addActionListener(this);
        deleteMatchBtn.addActionListener(this);
        backBtn.addActionListener(this);
        insertMatchBtn.addActionListener(this);
        confirmUpdateBtn.addActionListener(this);
        confirmDeleteBtn.addActionListener(this);

        this.setVisible(true);
    }

    /**
     * Adds a JLabel and a corresponding JTextField to a panel.
     *
     * @param panel The panel to add the components to
     * @param text The label text
     * @param y The vertical position for the label and field
     * @param field The JTextField to be added
     */
    private void addLabelAndField(JPanel panel, String text, int y, JTextField field) {
        JLabel label = new JLabel(text);
        label.setBounds(30, y, 150, 40);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 17));
        panel.add(label);
        field.setBounds(180, y + 5, 320, 30);
        panel.add(field);
    }

    /**
     * Creates a JPanel with a background image.
     *
     * @return JPanel with dashboard background image
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
     *   <li>Switching panels for add, edit, or delete operations</li>
     *   <li>Going back to the AdminFrame</li>
     *   <li>Inserting, updating, or deleting matches using MatchDAO</li>
     *   <li>Displaying success or failure messages</li>
     * </ul>
     * </p>
     *
     * @param e The ActionEvent triggered by a button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMatchBtn) cardLayout.show(mainPanel, "add");
        else if (e.getSource() == editMatchBtn) cardLayout.show(mainPanel, "edit");
        else if (e.getSource() == deleteMatchBtn) cardLayout.show(mainPanel, "delete");
        else if (e.getSource() == backBtn) {
            JOptionPane.showMessageDialog(this, "Back to Admin Page...");
            dispose();
            new AdminFrame().setVisible(true);
        } else if (e.getSource() == insertMatchBtn) {
            Match m = new Match();
            m.setHomeTeam(homeTeamField.getText().trim());
            m.setAwayTeam(awayTeamField.getText().trim());
            m.setMatchDate(dateField.getText().trim());
            m.setVenue(venueField.getText().trim());
            m.setHomeScore(Integer.parseInt(homeScoreField.getText().trim()));
            m.setAwayScore(Integer.parseInt(awayScoreField.getText().trim()));
            boolean success = MatchDAO.AddMatch(m);
            JOptionPane.showMessageDialog(this, success ? "✅ Match added successfully!" : "❌ Failed to add match.");
        } else if (e.getSource() == confirmUpdateBtn) {
            Match m = new Match();
            m.setMatchId(Integer.parseInt(editIdField.getText().trim()));
            m.setHomeTeam(editHomeTeamField.getText().trim());
            m.setAwayTeam(editAwayTeamField.getText().trim());
            m.setMatchDate(editDateField.getText().trim());
            m.setVenue(editVenueField.getText().trim());
            m.setHomeScore(Integer.parseInt(editHomeScoreField.getText().trim()));
            m.setAwayScore(Integer.parseInt(editAwayScoreField.getText().trim()));
            boolean success = MatchDAO.UpdateMatch(m);
            JOptionPane.showMessageDialog(this, success ? "✅ Match updated successfully!" : "❌ Failed to update match.");
        } else if (e.getSource() == confirmDeleteBtn) {
            Match m = new Match();
            m.setMatchId(Integer.parseInt(deleteIdField.getText().trim()));
            boolean success = MatchDAO.DeleteMatch(m);
            JOptionPane.showMessageDialog(this, success ? "✅ Match deleted successfully!" : "❌ Failed to delete match.");
        }
    }
}
