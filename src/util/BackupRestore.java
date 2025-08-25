package util;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BackupRestore {

    /**
     * Backup the SQLite database to a selected location
     */
    public static void backupDatabase() {
        String sourceDBPath = DBConnection.getDatabasePath();

        File dbFile = new File(sourceDBPath);
        if(!dbFile.exists()){
            JOptionPane.showMessageDialog(null, "❌ Database file not found!\nPlease make sure the database exists at: " + sourceDBPath);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose location to save backup");
        fileChooser.setSelectedFile(new File("backup.db")); // default name
        int userSelection = fileChooser.showSaveDialog(null);

        if(userSelection == JFileChooser.APPROVE_OPTION){
            File backupFile = fileChooser.getSelectedFile();
            try {
                Files.copy(dbFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "✅ Backup successful!\nSaved to: " + backupFile.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "❌ Backup failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Restore the SQLite database from a backup file
     */
    public static void restoreDatabase() {
        String targetDBPath = DBConnection.getDatabasePath();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select backup file to restore");
        int userSelection = fileChooser.showOpenDialog(null);

        if(userSelection == JFileChooser.APPROVE_OPTION){
            File backupFile = fileChooser.getSelectedFile();
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Restoring will overwrite current database. Continue?",
                    "Confirm Restore",
                    JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                try {
                    Files.copy(backupFile.toPath(), Paths.get(targetDBPath), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(null, "✅ Restore successful!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "❌ Restore failed: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    // Optional: Dialog to choose action
    public static void showBackupRestoreDialog() {
        int choice = JOptionPane.showOptionDialog(null, "Choose Action", "Backup / Restore",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new String[]{"Backup", "Restore"}, "Backup");

        if(choice == 0){
            backupDatabase();
        } else if(choice == 1){
            restoreDatabase();
        }
    }

}
