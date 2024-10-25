package EJB.service;

import EJB.entity.CD;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class AdminService {
    @PersistenceContext
    private EntityManager em;

    public void addCD(String title, String artist) {
        CD cd = new CD();
        cd.setTitle(title);
        cd.setArtist(artist);
        cd.setBorrowed(false);
        em.persist(cd);
    }

    public void updateCD(Long id, String title, String artist) {
        CD cd = em.find(CD.class, id);
        cd.setTitle(title);
        cd.setArtist(artist);
    }

    public void deleteCD(Long id) {
        CD cd = em.find(CD.class, id);
        if (cd != null) em.remove(cd);
    }

    public List<CD> listAllCDs() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}
