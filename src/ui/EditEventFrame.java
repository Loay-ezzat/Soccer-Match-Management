package ui;

import dao.GameEventDAO;
import model.GameEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EditEventFrame provides the GUI for managing Game Events in the Soccer Management System.
 *
 * <p>This frame allows the admin to add, update, or delete game events.</p>
 *
 * <p>Main Features:
 * <ul>
 *   <li>Three main panels for adding, editing, and deleting events</li>
 *   <li>Sidebar navigation with buttons for each action and back to Admin page</li>
 *   <li>Form fields to input event data: match ID, player ID, event type, and event time</li>
 *   <li>Integration with GameEventDAO for database operations</li>
 *   <li>Feedback via message dialogs for success or failure of operations</li>
 * </ul>
 * </p>
 *
 * @author Loay
 * @version 1.0
 */
public class EditEventFrame extends JFrame implements ActionListener {

    // ------------ Variables ------------
    // UI components for panels, buttons, and text fields
    CardLayout cardLayout;
    JPanel sidePanel, mainPanel, addJPanel, editJPanel, deleteJPanel;
    JButton addEventBtn, editEventBtn, deleteEventBtn, backBtn,
            insertEventBtn, confirmUpdateBtn, confirmDeleteBtn;

    JTextField matchIdField, playerIdField, eventTypeField, eventTimeField,
            editEventIdField, editMatchIdField, editPlayerIdField, editEventTypeField, editEventTimeField,
            deleteEventIdField;

    ImageIcon dashboardIcon = new ImageIcon("main2.jpg");
    final Image dashboardImage = dashboardIcon.getImage();

    /**
     * Constructor: Initializes the EditEventFrame window and its components.
     *
     * <p>Includes:
     * <ul>
     *   <li>Setting window properties like title, size, and layout</li>
     *   <li>Creating and setting up panels for add, edit, and delete operations</li>
     *   <li>Creating sidebar buttons and adding them to the side panel</li>
     *   <li>Adding form fields and buttons to the respective panels</li>
     *   <li>Attaching ActionListeners to all buttons</li>
     * </ul>
     * </p>
     */
    public EditEventFrame() {
        this.setTitle("Game Events Setting");
        this.setResizable(false);
        this.setSize(1000, 630);
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        // Panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        addJPanel = createBackgroundPanel();
        editJPanel = createBackgroundPanel();
        deleteJPanel = createBackgroundPanel();
        mainPanel.add(addJPanel, "add");
        mainPanel.add(editJPanel, "edit");
        mainPanel.add(deleteJPanel, "delete");

        // Sidebar
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

        addEventBtn = new JButton("Add Event");
        editEventBtn = new JButton("Update Event");
        deleteEventBtn = new JButton("Delete Event");
        backBtn = new JButton("Back");

        addEventBtn.setBounds(5, 55, 190, 40);
        editEventBtn.setBounds(5, 125, 190, 40);
        deleteEventBtn.setBounds(5, 195, 190, 40);
        backBtn.setBounds(5, 525, 190, 40);
        backBtn.setBackground(Color.RED);
        backBtn.setForeground(Color.BLACK);
        sidePanel.add(addEventBtn);
        sidePanel.add(editEventBtn);
        sidePanel.add(deleteEventBtn);
        sidePanel.add(backBtn);

        mainPanel.setBounds(200, 0, 800, 630);
        this.add(mainPanel);

        // ---------- Add Event Panel ----------
        addLabelAndField(addJPanel, "Match ID:", 80, matchIdField = new JTextField());
        addLabelAndField(addJPanel, "Player ID:", 130, playerIdField = new JTextField());
        addLabelAndField(addJPanel, "Event Type:", 180, eventTypeField = new JTextField());
        addLabelAndField(addJPanel, "Event Time (min):", 230, eventTimeField = new JTextField());

        insertEventBtn = new JButton("Insert");
        insertEventBtn.setBounds(280, 300, 170, 30);
        addJPanel.add(insertEventBtn);

        // ---------- Edit Event Panel ----------
        addLabelAndField(editJPanel, "Match ID:", 80, editMatchIdField = new JTextField());
        addLabelAndField(editJPanel, "Player ID:", 130, editPlayerIdField = new JTextField());
        addLabelAndField(editJPanel, "Event Type:", 180, editEventTypeField = new JTextField());
        addLabelAndField(editJPanel, "Event Time (min):", 230, editEventTimeField = new JTextField());

        JLabel editEventIdLabel = new JLabel("Event ID:");
        editEventIdLabel.setBounds(30, 280, 150, 40);
        editEventIdLabel.setForeground(Color.WHITE);
        editEventIdLabel.setFont(new Font("Arial", Font.BOLD, 17));
        editJPanel.add(editEventIdLabel);
        editEventIdField = new JTextField();
        editEventIdField.setBounds(180, 285, 320, 30);
        editJPanel.add(editEventIdField);

        confirmUpdateBtn = new JButton("Update");
        confirmUpdateBtn.setBounds(280, 350, 170, 30);
        editJPanel.add(confirmUpdateBtn);

        // ---------- Delete Event Panel ----------
        JLabel deleteEventLabel = new JLabel("Delete Event");
        deleteEventLabel.setBounds(20, 20, 300, 30);
        deleteEventLabel.setForeground(Color.WHITE);
        deleteEventLabel.setFont(new Font("Arial", Font.BOLD, 20));
        deleteJPanel.add(deleteEventLabel);

        JLabel deleteEventIdLabel = new JLabel("Event ID:");
        deleteEventIdLabel.setBounds(30, 100, 150, 40);
        deleteEventIdLabel.setForeground(Color.WHITE);
        deleteEventIdLabel.setFont(new Font("Arial", Font.BOLD, 17));
        deleteJPanel.add(deleteEventIdLabel);
        deleteEventIdField = new JTextField();
        deleteEventIdField.setBounds(180, 105, 320, 30);
        deleteJPanel.add(deleteEventIdField);

        confirmDeleteBtn = new JButton("Confirm");
        confirmDeleteBtn.setBounds(280, 160, 170, 30);
        deleteJPanel.add(confirmDeleteBtn);

        // ---------- Actions ----------
        addEventBtn.addActionListener(this);
        editEventBtn.addActionListener(this);
        deleteEventBtn.addActionListener(this);
        backBtn.addActionListener(this);
        insertEventBtn.addActionListener(this);
        confirmUpdateBtn.addActionListener(this);
        confirmDeleteBtn.addActionListener(this);
    }

    /**
     * Adds a JLabel and a corresponding JTextField to a panel.
     *
     * @param panel The panel to add the components to
     * @param labelText The text for the label
     * @param y The vertical position for the label and field
     * @param field The JTextField to be added
     */
    private void addLabelAndField(JPanel panel, String labelText, int y, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setBounds(30, y, 150, 40);
        label.setFont(new Font("Arial", Font.BOLD, 17));
        label.setForeground(Color.WHITE);
        panel.add(label);

        field.setBounds(180, y + 5, 320, 30);
        field.setFont(new Font(null, Font.BOLD, 15));
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
     *   <li>Inserting, updating, or deleting game events using GameEventDAO</li>
     *   <li>Displaying success or failure messages</li>
     * </ul>
     * </p>
     *
     * @param e The ActionEvent triggered by a button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEventBtn) {
            cardLayout.show(mainPanel, "add");
        } else if (e.getSource() == editEventBtn) {
            cardLayout.show(mainPanel, "edit");
        } else if (e.getSource() == deleteEventBtn) {
            cardLayout.show(mainPanel, "delete");
        } else if (e.getSource() == backBtn) {
            dispose();
            new AdminFrame().setVisible(true);
        } else if (e.getSource() == insertEventBtn) {
            GameEvent event = new GameEvent();
            event.setMatchId(Integer.parseInt(matchIdField.getText().trim()));
            event.setPlayerId(Integer.parseInt(playerIdField.getText().trim()));
            event.setEventType(eventTypeField.getText().trim());
            event.setEventTime(Integer.parseInt(eventTimeField.getText().trim()));
            boolean success = GameEventDAO.AddEvent(event);
            JOptionPane.showMessageDialog(this, success ? "✅ Event added!" : "❌ Failed!");
        } else if (e.getSource() == confirmUpdateBtn) {
            GameEvent event = new GameEvent();
            event.setEventId(Integer.parseInt(editEventIdField.getText().trim()));
            event.setMatchId(Integer.parseInt(editMatchIdField.getText().trim()));
            event.setPlayerId(Integer.parseInt(editPlayerIdField.getText().trim()));
            event.setEventType(editEventTypeField.getText().trim());
            event.setEventTime(Integer.parseInt(editEventTimeField.getText().trim()));
            boolean success = GameEventDAO.UpdateEvent(event);
            JOptionPane.showMessageDialog(this, success ? "✅ Event updated!" : "❌ Failed!");
        } else if (e.getSource() == confirmDeleteBtn) {
            GameEvent event = new GameEvent();
            event.setEventId(Integer.parseInt(deleteEventIdField.getText().trim()));
            boolean success = GameEventDAO.DeleteEvent(event);
            JOptionPane.showMessageDialog(this, success ? "✅ Event deleted!" : "❌ Failed!");
        }
    }
}
