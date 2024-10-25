package DESKTOP;


import EJB.entity.CD;
import EJB.service.AdminService;
import jakarta.ejb.EJB;

import javax.swing.*;
import java.awt.*;

public class AddCDPanel extends JPanel {
    @EJB
    private AdminService adminService;

    private JTextField titleField = new JTextField(20);
    private JTextField artistField = new JTextField(20);
    private JButton addButton = new JButton("Add CD");

    public AddCDPanel() {
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Title:"));
        add(titleField);
        add(new JLabel("Artist:"));
        add(artistField);
        add(addButton);

        addButton.addActionListener(e -> addCD());
    }

    private void addCD() {
        String title = titleField.getText();
        String artist = artistField.getText();

        if (title.isEmpty() || artist.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Both Title and Artist are required.");
            return;
        }

        CD newCD = new CD();
        newCD.setTitle(title);
        newCD.setArtist(artist);
        adminService.addCD(newCD.getTitle(), newCD.getArtist());

        JOptionPane.showMessageDialog(this, "CD added successfully.");
        titleField.setText("");
        artistField.setText("");
    }
}

