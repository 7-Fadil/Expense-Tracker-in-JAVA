import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    public Dashboard(){
        // Frame settings
        setTitle("Expense Management System - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutMenuItem = new JMenuItem("Logout");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(logoutMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(Dashboard.this, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose(); // Close the dashboard window
                    new App().setVisible(true); // Re-open the login form
                }
            }
        });

         // Expenses Menu
        JMenu expensesMenu = new JMenu("Expenses");
        JMenuItem addExpenseMenuItem = new JMenuItem("Add Expense");
        JMenuItem viewExpensesMenuItem = new JMenuItem("View Expenses");
        JMenuItem manageCategoriesMenuItem = new JMenuItem("Manage Categories");
        expensesMenu.add(addExpenseMenuItem);
        expensesMenu.add(viewExpensesMenuItem);
        expensesMenu.add(manageCategoriesMenuItem);
        menuBar.add(expensesMenu);

        // Reports Menu
        JMenu reportsMenu = new JMenu("Reports");
        JMenuItem monthlyReportMenuItem = new JMenuItem("Monthly Report");
        JMenuItem annualReportMenuItem = new JMenuItem("Annual Report");
        reportsMenu.add(monthlyReportMenuItem);
        reportsMenu.add(annualReportMenuItem);
        menuBar.add(reportsMenu);

        JMenu aboutMenu = new JMenu("About"); // Create a new menu
        JMenuItem aboutDeveloperMenuItem = new JMenuItem("About Developer"); // Create the menu item
        aboutMenu.add(aboutDeveloperMenuItem); // Add the item to the menu
        menuBar.add(aboutMenu); // Add the menu to the menu bar

        // Set the menu bar
        setJMenuBar(menuBar);

         // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to the Expense Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Adding welcome label to the frame
        add(welcomeLabel, BorderLayout.CENTER);

        // Action listeners for menu items
        logoutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logout logic here
                JOptionPane.showMessageDialog(Dashboard.this, "You have been logged out.");
                dispose();
                new App().setVisible(true);
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        addExpenseMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JOptionPane.showMessageDialog(Dashboard.this, "Add Expense clicked.");
                // Open Add Expense form here
                AddExpenseForm addExpenseForm = new AddExpenseForm();
                addExpenseForm.setVisible(true);
            }
        });

        // Add an action listener to open the About Developer window
        aboutDeveloperMenuItem.addActionListener(e -> {
            new AboutDeveloperWindow().showAboutWindow(); // Call the method to open the About Developer window
        });

        viewExpensesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JOptionPane.showMessageDialog(Dashboard.this, "View Expenses clicked.");
                // Open View Expenses form here
                ViewExpensesForm viewExpensesForm = new ViewExpensesForm();
                viewExpensesForm.setVisible(true);
            }
        });

        manageCategoriesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JOptionPane.showMessageDialog(Dashboard.this, "Manage Categories clicked.");
                // Open Manage Categories form here
                ManageCategoriesForm manageCategoriesForm = new ManageCategoriesForm();
                manageCategoriesForm.setVisible(true);
            }
        });
        

        monthlyReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this, "Monthly Report clicked.");
                // Show Monthly Report form here
            }
        });

        annualReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Dashboard.this, "Annual Report clicked.");
                // Show Annual Report form here
            }
        });

    }

}
