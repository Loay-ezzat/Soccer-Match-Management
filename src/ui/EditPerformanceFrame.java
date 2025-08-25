package ui;

import dao.PerformanceDAO;
import model.Performance;
import util.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * EditPerformanceFrame provides GUI for managing player performance.
 * Allows admin to add, update, or delete performance entries.
 */
public class EditPerformanceFrame extends JFrame implements ActionListener {

    // Panels
    CardLayout cardLayout;
    JPanel mainPanel, addPanel, editPanel, deletePanel, sidePanel;

    // Buttons
    JButton addPerfBtn, editPerfBtn, deletePerfBtn, backBtn;
    JButton insertPerfBtn, confirmUpdateBtn, confirmDeleteBtn;
    // Fields
    JComboBox<String> addPlayerCombo, addMatchCombo;
    JTextField addMinutesField,addGoalsField, addAssistsField;
    JTextField editPerfIdField, editPlayerIdField, editGoalsField, editAssistsField;
    JTextField deletePerfIdField;

    ImageIcon dashboardIcon = new ImageIcon("main2.jpg");
    final Image dashboardImage = dashboardIcon.getImage();

    public EditPerformanceFrame() {
        this.setTitle("Edit Player Performance");
        this.setSize(1000, 630);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setLayout(null);

        // --------- Main Panel ------------
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        addPanel = createBackgroundPanel();
        editPanel = createBackgroundPanel();
        deletePanel = createBackgroundPanel();

        mainPanel.add(addPanel, "add");
        mainPanel.add(editPanel, "edit");
        mainPanel.add(deletePanel, "delete");
        mainPanel.setBounds(200, 0, 800, 630);
        this.add(mainPanel);

        // --------- Sidebar ------------
        ImageIcon sideImageIcon = new ImageIcon("sidebar.jpg");
        final Image sideImage = sideImageIcon.getImage();
        sidePanel = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(sideImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        sidePanel.setBounds(0,0,200,630);
        sidePanel.setLayout(null);
        this.add(sidePanel);

        addPerfBtn = new JButton("Add Performance");
        editPerfBtn = new JButton("Edit Performance");
        deletePerfBtn = new JButton("Delete Performance");
        backBtn = new JButton("Back");

        addPerfBtn.setBounds(5, 55, 190, 40);
        editPerfBtn.setBounds(5, 125, 190, 40);
        deletePerfBtn.setBounds(5, 195, 190, 40);
        backBtn.setBounds(5, 525, 190, 40);
        backBtn.setBackground(Color.RED);
        backBtn.setForeground(Color.BLACK);

        sidePanel.add(addPerfBtn);
        sidePanel.add(editPerfBtn);
        sidePanel.add(deletePerfBtn);
        sidePanel.add(backBtn);

        // ---------- Add Panel ----------
        addLabelAndCombo(addPanel, "Player:", 80, addPlayerCombo = new JComboBox<>());
        addLabelAndCombo(addPanel, "Match:", 130, addMatchCombo = new JComboBox<>());
        addLabelAndField(addPanel, "Goals:", 180, addGoalsField = new JTextField());
        addLabelAndField(addPanel, "Assists:", 230, addAssistsField = new JTextField());

        insertPerfBtn = new JButton("Insert");
        insertPerfBtn.setBounds(280, 330, 170, 30);
        addPanel.add(insertPerfBtn);


        addLabelAndField(addPanel, "Minutes Played:", 280, addMinutesField = new JTextField());

        populatePlayerCombo();
        populateMatchCombo();

        // ---------- Edit Panel ----------
        addLabelAndField(editPanel, "Performance ID:", 50, editPerfIdField = new JTextField());
        addLabelAndField(editPanel, "Player ID:", 100, editPlayerIdField = new JTextField());
        addLabelAndField(editPanel, "Goals:", 150, editGoalsField = new JTextField());
        addLabelAndField(editPanel, "Assists:", 200, editAssistsField = new JTextField());

        confirmUpdateBtn = new JButton("Update");
        confirmUpdateBtn.setBounds(280, 260, 170, 30);
        editPanel.add(confirmUpdateBtn);

        // ---------- Delete Panel ----------
        addLabelAndField(deletePanel, "Performance ID:", 100, deletePerfIdField = new JTextField());
        confirmDeleteBtn = new JButton("Delete");
        confirmDeleteBtn.setBounds(280, 160, 170, 30);
        deletePanel.add(confirmDeleteBtn);

        // ---------- Actions ----------
        addPerfBtn.addActionListener(this);
        editPerfBtn.addActionListener(this);
        deletePerfBtn.addActionListener(this);
        backBtn.addActionListener(this);
        insertPerfBtn.addActionListener(this);
        confirmUpdateBtn.addActionListener(this);
        confirmDeleteBtn.addActionListener(this);
    }

    private void addLabelAndField(JPanel panel, String labelText, int y, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setBounds(30, y, 150, 40);
        label.setFont(new Font("Arial", Font.BOLD, 17));
        label.setForeground(Color.WHITE);
        panel.add(label);

        field.setBounds(180, y + 5, 320, 30);
        panel.add(field);
    }

    private void addLabelAndCombo(JPanel panel, String labelText, int y, JComboBox<String> combo) {
        JLabel label = new JLabel(labelText);
        label.setBounds(30, y, 150, 40);
        label.setFont(new Font("Arial", Font.BOLD, 17));
        label.setForeground(Color.WHITE);
        panel.add(label);

        combo.setBounds(180, y + 5, 320, 30);
        panel.add(combo);
    }

    private JPanel createBackgroundPanel() {
        return new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(dashboardImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }

    private void populatePlayerCombo() {
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT player_id, full_name FROM players");
            while(rs.next()) {
                addPlayerCombo.addItem(rs.getInt("player_id") + " - " + rs.getString("full_name"));
            }
            rs.close();
        } catch(SQLException ex){ ex.printStackTrace(); }
    }

    private void populateMatchCombo() {
        try (Connection conn = DBConnection.getConnection()) {
            ResultSet rs = conn.createStatement().executeQuery(
                    "SELECT match_id, home_team || ' vs ' || away_team AS match_name FROM matches"
            );
            while(rs.next()) {
                addMatchCombo.addItem(rs.getInt("match_id") + " - " + rs.getString("match_name"));
            }
            rs.close();
        } catch(SQLException ex){ ex.printStackTrace(); }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addPerfBtn) cardLayout.show(mainPanel, "add");
        else if(e.getSource() == editPerfBtn) cardLayout.show(mainPanel, "edit");
        else if(e.getSource() == deletePerfBtn) cardLayout.show(mainPanel, "delete");
        else if(e.getSource() == backBtn) {
            dispose();
            new AdminFrame().setVisible(true);
        }
        else if(e.getSource() == insertPerfBtn) {
            try {
                Performance p = new Performance();

                // Get selected player ID
                String playerItem = (String) addPlayerCombo.getSelectedItem();
                p.setPlayerId(Integer.parseInt(playerItem.split(" - ")[0]));

                // Get selected match ID
                String matchItem = (String) addMatchCombo.getSelectedItem();
                p.setMatchId(Integer.parseInt(matchItem.split(" - ")[0]));
                p.setMinutesPlayed(Integer.parseInt(addMinutesField.getText().trim()));

                p.setGoals(Integer.parseInt(addGoalsField.getText().trim()));
                p.setAssists(Integer.parseInt(addAssistsField.getText().trim()));

                boolean success = PerformanceDAO.AddPerformance(p);
                JOptionPane.showMessageDialog(this, success ? "✅ Performance added!" : "❌ Failed!");
            } catch(Exception ex){ JOptionPane.showMessageDialog(this, "⚠️ Check input fields!"); }
        }
        else if(e.getSource() == confirmUpdateBtn) {
            try {
                Performance p = new Performance();
                p.setPerformanceId(Integer.parseInt(editPerfIdField.getText().trim()));
                p.setPlayerId(Integer.parseInt(editPlayerIdField.getText().trim()));
                p.setGoals(Integer.parseInt(editGoalsField.getText().trim()));
                p.setAssists(Integer.parseInt(editAssistsField.getText().trim()));

                boolean success = PerformanceDAO.UpdatePerformance(p);
                JOptionPane.showMessageDialog(this, success ? "✅ Updated!" : "❌ Failed!");
            } catch(Exception ex){ JOptionPane.showMessageDialog(this, "⚠️ Check input fields!"); }
        }
        else if(e.getSource() == confirmDeleteBtn) {
            try {
                int id = Integer.parseInt(deletePerfIdField.getText().trim());
                boolean success = PerformanceDAO.DeletePerformance(id);
                JOptionPane.showMessageDialog(this, success ? "✅ Deleted!" : "❌ Failed!");
            } catch(Exception ex){ JOptionPane.showMessageDialog(this, "⚠️ Enter valid Performance ID!"); }
        }
    }
}
