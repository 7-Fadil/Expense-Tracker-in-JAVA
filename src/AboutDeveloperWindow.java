import javax.swing.*;
import java.awt.*;

public class AboutDeveloperWindow {

    public static void main(String[] args) {
        // Simulate opening the About Developer window from a menu
        SwingUtilities.invokeLater(() -> new AboutDeveloperWindow().showAboutWindow());
    }

    public void showAboutWindow() {
        // Create a new JFrame
        JFrame aboutFrame = new JFrame("About Developer");
        aboutFrame.setSize(400, 300);
        aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutFrame.setLayout(new BorderLayout());

        // Create a panel for the content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Add a profile picture
        JLabel profilePicLabel = new JLabel();
        ImageIcon profileIcon = new ImageIcon("profile.jpg"); // Replace "profile.jpg" with your image path
        Image scaledImage = profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profileIcon = new ImageIcon(scaledImage);
        profilePicLabel.setIcon(profileIcon);
        profilePicLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add developer details
        JLabel titleLabel = new JLabel("About the Developer", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Name: Salihu Ismail");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel roleLabel = new JLabel("Role: Software Developer");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel emailLabel = new JLabel("Email: 7.fadil01@gmail.com");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel phoneLabel = new JLabel("Phone: +234 811 8709 34");
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel linkedInLabel = new JLabel("LinkedIn: linkedin.com/in/yourprofile");
        linkedInLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        linkedInLabel.setForeground(new Color(0, 102, 204)); // Blue color
        linkedInLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel githubLabel = new JLabel("GitHub: https://github.com/7-Fadil");
        githubLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        githubLabel.setForeground(new Color(0, 102, 204)); // Blue color
        githubLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add all components to the content panel
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        contentPanel.add(nameLabel);
        contentPanel.add(roleLabel);
        contentPanel.add(emailLabel);
        contentPanel.add(phoneLabel);
        contentPanel.add(linkedInLabel);
        contentPanel.add(githubLabel);

        // Add a close button at the bottom
        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> aboutFrame.dispose());
        buttonPanel.add(closeButton);

        // Add panels to the frame
        aboutFrame.add(contentPanel, BorderLayout.CENTER);
        aboutFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Center the frame and make it visible
        aboutFrame.setLocationRelativeTo(null);
        aboutFrame.setVisible(true);
    }
}
