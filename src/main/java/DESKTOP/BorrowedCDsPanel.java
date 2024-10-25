package DESKTOP;


import EJB.entity.CD;
import EJB.service.AdminService;
import jakarta.ejb.EJB;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BorrowedCDsPanel extends JPanel {
    @EJB
    private AdminService adminService;

    private DefaultListModel<CD> borrowedCDsModel;
    private JList<CD> borrowedCDsList;

    public BorrowedCDsPanel() {
        setLayout(new BorderLayout());

        borrowedCDsModel = new DefaultListModel<>();
        borrowedCDsList = new JList<>(borrowedCDsModel);

        add(new JScrollPane(borrowedCDsList), BorderLayout.CENTER);

        loadBorrowedCDs();
    }

    private void loadBorrowedCDs() {
        borrowedCDsModel.clear();
        List<CD> borrowedCDs = adminService.getBorrowedCDs();
        borrowedCDs.forEach(borrowedCDsModel::addElement);
    }
}

