package ui;

import dao.TeamDAO;
import util.DBConnection;
import model.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EditTeamFrame provides a GUI interface to manage Teams in the Soccer Management System.
 *
 * <p>This frame allows the admin to perform the following operations:
 * <ul>
 *     <li>Add a new team</li>
 *     <li>Update existing team details</li>
 *     <li>Delete a team</li>
 * </ul>
 * </p>
 *
 * <p>Main Features:
 * <ul>
 *     <li>Three main panels (Add, Edit, Delete) using CardLayout</li>
 *     <li>Sidebar buttons for quick navigation between operations</li>
 *     <li>Text fields for team name, coach, and foundation year</li>
 *     <li>Integration with TeamDAO for database CRUD operations</li>
 *     <li>Success/failure feedback messages via JOptionPane</li>
 *     <li>Back button to return to AdminFrame</li>
 * </ul>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class EditTeamFrame extends JFrame implements ActionListener {

    // ------- Variables ---------
    CardLayout cardLayout;
    JButton addTeamBtn, editTeamBtn, deleteTeamBtn, backBtn,
            insertTeamBtn, confirmUpdateBtn, confirmDeleteBtn;

    JPanel sidePanel, mainPanel, addJPanel, editJPanel, deleteJPanel;

    JLabel addTeamLabel, nameLabel, coachLabel, foundationLabel;

    JTextField nameField, coachField, foundationField,
            editNameField, editCoachField, editFoundationField, editIdField,
            deleteNameField, deleteIdField;

    ImageIcon dashboardIcon = new ImageIcon("main2.jpg");
    final Image dashboardImage = dashboardIcon.getImage();

    /**
     * Constructor: Initializes the EditTeamFrame GUI window and its components.
     *
     * <p>This includes:
     * <ul>
     *     <li>Setting up window properties like title, size, and layout</li>
     *     <li>Creating panels for add, edit, and delete operations with background images</li>
     *     <li>Adding sidebar buttons and configuring the main panel</li>
     *     <li>Adding form fields and labels for team details</li>
     *     <li>Attaching action listeners to all buttons</li>
     * </ul>
     * </p>
     */
    public EditTeamFrame() {

        this.setTitle("Teams Setting");
        this.setResizable(false);
        this.setSize(1000, 630);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // ---------- TextFields ----------
        nameField = new JTextField();
        coachField = new JTextField();
        foundationField = new JTextField();

        editNameField = new JTextField();
        editCoachField = new JTextField();
        editFoundationField = new JTextField();
        editIdField = new JTextField();

        deleteNameField = new JTextField();
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
        addTeamBtn = new JButton("Add Team");
        editTeamBtn = new JButton("Update Team");
        deleteTeamBtn = new JButton("Delete Team");
        backBtn = new JButton("Back");
        insertTeamBtn = new JButton("Insert");
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

        addTeamBtn.setBounds(5, 55, 190, 40);
        sidePanel.add(addTeamBtn);
        editTeamBtn.setBounds(5, 125, 190, 40);
        sidePanel.add(editTeamBtn);
        deleteTeamBtn.setBounds(5, 195, 190, 40);
        sidePanel.add(deleteTeamBtn);
        backBtn.setBounds(5, 525, 190, 40);
        backBtn.setBackground(Color.RED);
        backBtn.setForeground(Color.BLACK);
        sidePanel.add(backBtn);

        // ---------- Main Panel ----------
        mainPanel.setBounds(200, 0, 800, 630);
        this.add(mainPanel);

        // --------- Add Team Panel ----------
        addTeamLabel = new JLabel("Add Team Page");
        addTeamLabel.setBounds(20, 20, 300, 30);
        addTeamLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addTeamLabel.setForeground(Color.WHITE);
        addJPanel.add(addTeamLabel);

        JLabel nameLabel = new JLabel("Team Name:");
        nameLabel.setBounds(30, 80, 150, 40);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        addJPanel.add(nameLabel);
        nameField.setBounds(180, 85, 320, 30);
        addJPanel.add(nameField);

        JLabel coachLabel = new JLabel("Coach Name:");
        coachLabel.setBounds(30, 130, 150, 40);
        coachLabel.setForeground(Color.WHITE);
        coachLabel.setFont(new Font("Arial", Font.BOLD, 17));
        addJPanel.add(coachLabel);
        coachField.setBounds(180, 135, 320, 30);
        addJPanel.add(coachField);

        JLabel foundationLabel = new JLabel("Foundation Year:");
        foundationLabel.setBounds(30, 180, 150, 40);
        foundationLabel.setForeground(Color.WHITE);
        foundationLabel.setFont(new Font("Arial", Font.BOLD, 17));
        addJPanel.add(foundationLabel);
        foundationField.setBounds(180, 185, 320, 30);
        addJPanel.add(foundationField);

        insertTeamBtn.setBounds(280, 430, 170, 30);
        insertTeamBtn.setFont(new Font(null, Font.BOLD, 15));
        addJPanel.add(insertTeamBtn);

        // --------- Edit Team Panel ----------
        JLabel editTeamLabel = new JLabel("Update Team Page");
        editTeamLabel.setBounds(20, 20, 300, 30);
        editTeamLabel.setFont(new Font("Arial", Font.BOLD, 20));
        editTeamLabel.setForeground(Color.WHITE);
        editJPanel.add(editTeamLabel);

        JLabel editNameLabel = new JLabel("Team Name:");
        editNameLabel.setBounds(30, 80, 150, 40);
        editNameLabel.setForeground(Color.WHITE);
        editNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editJPanel.add(editNameLabel);
        editNameField.setBounds(180, 85, 320, 30);
        editJPanel.add(editNameField);

        JLabel editCoachLabel = new JLabel("Coach Name:");
        editCoachLabel.setBounds(30, 130, 150, 40);
        editCoachLabel.setForeground(Color.WHITE);
        editCoachLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editJPanel.add(editCoachLabel);
        editCoachField.setBounds(180, 135, 320, 30);
        editJPanel.add(editCoachField);

        JLabel editFoundationLabel = new JLabel("Foundation Year:");
        editFoundationLabel.setBounds(30, 180, 150, 40);
        editFoundationLabel.setForeground(Color.WHITE);
        editFoundationLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editJPanel.add(editFoundationLabel);
        editFoundationField.setBounds(180, 185, 320, 30);
        editJPanel.add(editFoundationField);

        JLabel editIdLabel = new JLabel("Team ID:");
        editIdLabel.setBounds(30, 230, 150, 40);
        editIdLabel.setForeground(Color.WHITE);
        editIdLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editJPanel.add(editIdLabel);
        editIdField.setBounds(180, 235, 320, 30);
        editJPanel.add(editIdField);

        confirmUpdateBtn.setBounds(280, 500, 170, 30);
        confirmUpdateBtn.setFont(new Font(null, Font.BOLD, 15));
        editJPanel.add(confirmUpdateBtn);

        // --------- Delete Team Panel ----------
        JLabel deleteTeamLabel = new JLabel("Delete Team Page");
        deleteTeamLabel.setBounds(20, 20, 300, 30);
        deleteTeamLabel.setFont(new Font("Arial", Font.BOLD, 20));
        deleteTeamLabel.setForeground(Color.WHITE);
        deleteJPanel.add(deleteTeamLabel);

        JLabel deleteNameLabel = new JLabel("Team Name:");
        deleteNameLabel.setBounds(30, 100, 150, 40);
        deleteNameLabel.setForeground(Color.WHITE);
        deleteNameLabel.setFont(new Font("Arial", Font.BOLD, 17));
        deleteJPanel.add(deleteNameLabel);
        deleteNameField.setBounds(180, 105, 320, 30);
        deleteJPanel.add(deleteNameField);

        JLabel deleteIdLabel = new JLabel("Team ID:");
        deleteIdLabel.setBounds(30, 160, 150, 40);
        deleteIdLabel.setForeground(Color.WHITE);
        deleteIdLabel.setFont(new Font("Arial", Font.BOLD, 17));
        deleteJPanel.add(deleteIdLabel);
        deleteIdField.setBounds(180, 165, 320, 30);
        deleteJPanel.add(deleteIdField);

        confirmDeleteBtn.setBounds(280, 300, 170, 30);
        confirmDeleteBtn.setFont(new Font(null, Font.BOLD, 15));
        deleteJPanel.add(confirmDeleteBtn);

        // ---------- Button Actions ----------
        addTeamBtn.addActionListener(this);
        editTeamBtn.addActionListener(this);
        deleteTeamBtn.addActionListener(this);
        backBtn.addActionListener(this);
        insertTeamBtn.addActionListener(this);
        confirmUpdateBtn.addActionListener(this);
        confirmDeleteBtn.addActionListener(this);

        this.setVisible(true);
    }

    /**
     * Creates a JPanel with the dashboard background image.
     *
     * @return JPanel with dashboard image painted
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
     *     <li>Switching between Add, Edit, and Delete panels</li>
     *     <li>Going back to the AdminFrame</li>
     *     <li>Adding a team using TeamDAO.AddTeam</li>
     *     <li>Updating a team using TeamDAO.UpdateTeam</li>
     *     <li>Deleting a team using TeamDAO.DeleteTeam</li>
     *     <li>Showing success or failure messages using JOptionPane</li>
     * </ul>
     * </p>
     *
     * @param e ActionEvent triggered by button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTeamBtn) {
            cardLayout.show(mainPanel, "add");
        } else if (e.getSource() == editTeamBtn) {
            cardLayout.show(mainPanel, "edit");
        } else if (e.getSource() == deleteTeamBtn) {
            cardLayout.show(mainPanel, "delete");
        } else if (e.getSource() == backBtn) {
            JOptionPane.showMessageDialog(this, "Back to Admin Page...");
            dispose();
            new AdminFrame().setVisible(true);
        } else if (e.getSource() == insertTeamBtn) {
            Team t = new Team();
            t.setTeamName(nameField.getText().trim());
            t.setCoachName(coachField.getText().trim());
            t.setFoundationYear(Integer.parseInt(foundationField.getText().trim()));
            boolean success = TeamDAO.AddTeam(t);
            if (success) JOptionPane.showMessageDialog(this, "✅ Team added successfully!");
            else JOptionPane.showMessageDialog(this, "❌ Failed to add team.");
        } else if (e.getSource() == confirmUpdateBtn) {
            Team t = new Team();
            t.setTeamId(Integer.parseInt(editIdField.getText().trim()));
            t.setTeamName(editNameField.getText().trim());
            t.setCoachName(editCoachField.getText().trim());
            t.setFoundationYear(Integer.parseInt(editFoundationField.getText().trim()));
            boolean success = TeamDAO.UpdateTeam(t);
            if (success) JOptionPane.showMessageDialog(this, "✅ Team updated successfully!");
            else JOptionPane.showMessageDialog(this, "❌ Failed to update team.");
        } else if (e.getSource() == confirmDeleteBtn) {
            Team t = new Team();
            t.setTeamId(Integer.parseInt(deleteIdField.getText().trim()));
            t.setTeamName(deleteNameField.getText().trim());
            boolean success = TeamDAO.DeleteTeam(t);
            if (success) JOptionPane.showMessageDialog(this, "✅ Team deleted successfully!");
            else JOptionPane.showMessageDialog(this, "❌ Failed to delete team.");
        }
    }
}
