import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewExpensesForm extends JFrame {

    public ViewExpensesForm() {
        // Frame settings
        setTitle("View Expenses");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Table setup
        String[] columnNames = {"Category", "Amount", "Date", "Description", "Action"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable expensesTable = new JTable(model);

        // Load data into the table from the database
        loadExpensesFromDatabase(model);

        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(expensesTable);

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadExpensesFromDatabase(DefaultTableModel model) {
        String query = "SELECT category, amount, date, description FROM expenses";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            // Fetch each row from the result set
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                String description = resultSet.getString("description");

                // Add row to the table model
                model.addRow(new Object[]{category, String.format("%.2f", amount), date, description});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading expenses: " + e.getMessage());
        }
    }
}
