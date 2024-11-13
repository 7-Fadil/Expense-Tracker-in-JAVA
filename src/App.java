import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public App(){
         // Frame settings
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create main panel with background color
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(34, 45, 65)); // Dark background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title label
        JLabel titleLabel = new JLabel("Welcome to MyApp");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        // Username and password labels
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);

        // Username and password fields
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(44, 56, 76));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // Message label for errors or success
        messageLabel = new JLabel("");
        messageLabel.setForeground(Color.RED);

        // Add components to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx++;
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx++;
        mainPanel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        gbc.gridy++;
        mainPanel.add(messageLabel, gbc);

        add(mainPanel);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
    }

    // Database login method
    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (authenticateUser(username, password)) {
            messageLabel.setText("Login successful!");
            messageLabel.setForeground(Color.GREEN);

            Dashboard myDashboard = new Dashboard();
            myDashboard.setVisible(true);

            // Close the login window
            dispose();

            // Proceed to next window or application dashboard
        } else {
            messageLabel.setText("Invalid username or password.");
            messageLabel.setForeground(Color.RED);
        }
    }

    // Authentication method
    private boolean authenticateUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/my_database";
        String dbUsername = "root";  // Replace with your DB username
        String dbPassword = "Perdail__1";  // Replace with your DB password

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a matching user is found

        } catch (SQLException e) {
            messageLabel.setText("Database error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            App appLoginForm = new App();
            appLoginForm.setVisible(true);
        });
    }
}
