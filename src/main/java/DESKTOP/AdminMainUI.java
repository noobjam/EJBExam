package DESKTOP;


import javax.swing.*;
import java.awt.*;

public class AdminMainUI extends JFrame {

    public AdminMainUI() {
        setTitle("CD/DVD Admin Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up navigation panel
        JPanel navigationPanel = new JPanel();
        JButton addCDButton = new JButton("Add CD");
        JButton listCDButton = new JButton("List CDs");
        JButton borrowedCDsButton = new JButton("Borrowed CDs");

        navigationPanel.add(addCDButton);
        navigationPanel.add(listCDButton);
        navigationPanel.add(borrowedCDsButton);

        // Card layout to switch between panels
        JPanel contentPanel = new JPanel(new CardLayout());
        AddCDPanel addCDPanel = new AddCDPanel();
        ListCDPanel listCDPanel = new ListCDPanel();
        BorrowedCDsPanel borrowedCDsPanel = new BorrowedCDsPanel();

        contentPanel.add(addCDPanel, "AddCD");
        contentPanel.add(listCDPanel, "ListCD");
        contentPanel.add(borrowedCDsPanel, "BorrowedCDs");

        // Button actions to switch views
        addCDButton.addActionListener(e -> ((CardLayout) contentPanel.getLayout()).show(contentPanel, "AddCD"));
        listCDButton.addActionListener(e -> ((CardLayout) contentPanel.getLayout()).show(contentPanel, "ListCD"));
        borrowedCDsButton.addActionListener(e -> ((CardLayout) contentPanel.getLayout()).show(contentPanel, "BorrowedCDs"));

        // Frame layout setup
        setLayout(new BorderLayout());
        add(navigationPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminMainUI adminUI = new AdminMainUI();
            adminUI.setVisible(true);
        });
    }
}

