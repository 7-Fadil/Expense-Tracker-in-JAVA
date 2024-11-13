import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageCategoriesForm extends JFrame {

    private JTextField categoryNameField;
    private JTable categoryTable;
    private DefaultTableModel tableModel;

    public ManageCategoriesForm() {
        setTitle("Manage Categories");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Components
        categoryNameField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton dashboard = new JButton("Back To Dashboard");
        dashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the AddExpenseForm window
            }
        });
        
        tableModel = new DefaultTableModel(new String[]{"ID", "Category Name"}, 0);
        categoryTable = new JTable(tableModel);
        
        // Load categories into the table
        loadCategories();
        
        // Layout
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Category Name:"));
        inputPanel.add(categoryNameField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(deleteButton);
        inputPanel.add(dashboard); // Add it to your form layout as needed

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(categoryTable), BorderLayout.CENTER);

        // Event Listeners
        addButton.addActionListener(e -> addCategory());
        editButton.addActionListener(e -> editCategory());
        deleteButton.addActionListener(e -> deleteCategory());
    }

    private void loadCategories() {
        tableModel.setRowCount(0); // Clear existing data
        String query = "SELECT * FROM categories";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                tableModel.addRow(new Object[]{id, name});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCategory() {
        String name = categoryNameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Category name cannot be empty.");
            return;
        }

        String query = "INSERT INTO categories (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            loadCategories(); // Refresh the table
            categoryNameField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Category already exists or database error.");
        }
    }

    private void editCategory() {
        int selectedRow = categoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a category to edit.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String name = categoryNameField.getText().trim();

        String query = "UPDATE categories SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            loadCategories(); // Refresh the table
            categoryNameField.setText("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating category: " + e.getMessage());
        }
    }

    private void deleteCategory() {
        int selectedRow = categoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a category to delete.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        String query = "DELETE FROM categories WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            loadCategories(); // Refresh the table
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting category: " + e.getMessage());
        }
    }
}
