import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddExpenseForm extends JFrame {

    public AddExpenseForm() {

        // Frame settings
        setTitle("Add New Expense");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Set layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Expense Category
        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryComboBox = new JComboBox<>();
        // You can set font, color, and icons for category dropdown and other components
        categoryComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        // Expense Amount
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(10);
        amountField.setBackground(new Color(230, 230, 250)); // Light Lavender color
        // Adding tooltips for further clarification
        amountField.setToolTipText("Enter amount (e.g., 50.75)");


        // Expense Date
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField(10);
        dateField.setBackground(new Color(230, 230, 250));
        dateField.setToolTipText("Enter date in format YYYY-MM-DD");


        // Description
        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionArea = new JTextArea(3, 15);
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        // Add button
        JButton addButton = new JButton("Add Expense");

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the AddExpenseForm window
            }
        });
        panel.add(backButton); // Add it to your form layout as needed

        // Adding components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(categoryLabel, gbc);

        gbc.gridx = 1;
        panel.add(categoryComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(amountLabel, gbc);

        gbc.gridx = 1;
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(dateLabel, gbc);

        gbc.gridx = 1;
        panel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        panel.add(new JScrollPane(descriptionArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(addButton, gbc);

        add(panel);

        // Action listener for the add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String category = (String) categoryComboBox.getSelectedItem();
                String amount = amountField.getText();
                String date = dateField.getText();
                String description = descriptionArea.getText();

                // Validate the input data before saving to the database
                if (amount.isEmpty() || date.isEmpty()) {
                    JOptionPane.showMessageDialog(AddExpenseForm.this, "Amount and Date are required fields.");
                    return;
                }

                try {
                    double amountValue = Double.parseDouble(amount);

                    // Save expense to the database
                    saveExpenseToDatabase(category, amountValue, date, description);

                    // Confirmation message
                    JOptionPane.showMessageDialog(AddExpenseForm.this, "Expense added successfully!");

                    // Clear fields after adding
                    amountField.setText("");
                    dateField.setText("");
                    descriptionArea.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddExpenseForm.this, "Please enter a valid amount.");
                }
            }
        });
    }

    private void saveExpenseToDatabase(String category, double amount, String date, String description) {
        String query = "INSERT INTO expenses (category, amount, date, description) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, category);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, description);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving expense: " + e.getMessage());
        }
    }

    
}
