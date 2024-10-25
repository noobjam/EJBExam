package DESKTOP;


import EJB.entity.CD;
import EJB.service.AdminService;
import jakarta.ejb.EJB;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListCDPanel extends JPanel {
    @EJB
    private AdminService adminService;

    private JList<CD> cdList;
    private DefaultListModel<CD> cdListModel;
    private JTextField titleField = new JTextField(20);
    private JTextField artistField = new JTextField(20);
    private JButton updateButton = new JButton("Update CD");
    private JButton deleteButton = new JButton("Delete CD");

    public ListCDPanel() {
        setLayout(new BorderLayout());

        cdListModel = new DefaultListModel<>();
        cdList = new JList<>(cdListModel);
        JScrollPane scrollPane = new JScrollPane(cdList);

        JPanel detailsPanel = new JPanel(new GridLayout(3, 2));
        detailsPanel.add(new JLabel("Title:"));
        detailsPanel.add(titleField);
        detailsPanel.add(new JLabel("Artist:"));
        detailsPanel.add(artistField);
        detailsPanel.add(updateButton);
        detailsPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> updateCD());
        deleteButton.addActionListener(e -> deleteCD());

        loadCDs();
    }

    private void loadCDs() {
        cdListModel.clear();
        List<CD> cds = adminService.listAllCDs();
        cds.forEach(cdListModel::addElement);
    }

    private void updateCD() {
        CD selectedCD = cdList.getSelectedValue();
        if (selectedCD == null) {
            JOptionPane.showMessageDialog(this, "Please select a CD to update.");
            return;
        }
        selectedCD.setTitle(titleField.getText());
        selectedCD.setArtist(artistField.getText());
        adminService.updateCD(selectedCD);
        loadCDs();
    }

    private void deleteCD() {
        CD selectedCD = cdList.getSelectedValue();
        if (selectedCD == null) {
            JOptionPane.showMessageDialog(this, "Please select a CD to delete.");
            return;
        }
        adminService.deleteCD(selectedCD.getId());
        loadCDs();
    }
}

