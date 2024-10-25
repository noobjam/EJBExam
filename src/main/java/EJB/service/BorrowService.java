package EJB.service;

import EJB.entity.CD;
import EJB.entity.BorrowRecord;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class BorrowService {
    @PersistenceContext
    private EntityManager em;

    public List<CD> listAvailableCDs() {
        return em.createQuery("SELECT c FROM CD c WHERE c.isBorrowed = false", CD.class).getResultList();
    }

    public void borrowCD(Long cdId, String borrowerName) {
        CD cd = em.find(CD.class, cdId);
        cd.setBorrowed(true);

        BorrowRecord record = new BorrowRecord();
        record.setCd(cd);
        record.setBorrowerName(borrowerName);
        record.setBorrowDate(LocalDate.now());

        em.persist(record);
    }

    public void returnCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        cd.setBorrowed(false);

        BorrowRecord record = em.createQuery("SELECT r FROM BorrowRecord r WHERE r.cd.id = :cdId", BorrowRecord.class)
                .setParameter("cdId", cdId)
                .getSingleResult();
        record.setReturnDate(LocalDate.now());
    }
}

