package WEB.controller;

import EJB.service.BorrowService;
import EJB.entity.CD;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class BorrowController {
    @EJB
    private BorrowService borrowService;

    private Long cdId;
    private String borrowerName;

    public List<CD> getAvailableCDs() {
        return borrowService.listAvailableCDs();
    }

    public void borrowCD() {
        borrowService.borrowCD(cdId, borrowerName);
    }

    // Getters and Setters
    public Long getCdId() {
        return cdId;
    }

    public void setCdId(Long cdId) {
        this.cdId = cdId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }}
