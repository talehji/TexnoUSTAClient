/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityController;

import Entity.Daxilolannov;
import EntityController.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Pallas
 */
public class DaxilolannovJpaController implements Serializable {

    public DaxilolannovJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Daxilolannov daxilolannov) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(daxilolannov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Daxilolannov daxilolannov) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            daxilolannov = em.merge(daxilolannov);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = daxilolannov.getIdDaxilOlanNov();
                if (findDaxilolannov(id) == null) {
                    throw new NonexistentEntityException("The daxilolannov with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Daxilolannov daxilolannov;
            try {
                daxilolannov = em.getReference(Daxilolannov.class, id);
                daxilolannov.getIdDaxilOlanNov();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The daxilolannov with id " + id + " no longer exists.", enfe);
            }
            em.remove(daxilolannov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Daxilolannov> findDaxilolannovEntities() {
        return findDaxilolannovEntities(true, -1, -1);
    }

    public List<Daxilolannov> findDaxilolannovEntities(int maxResults, int firstResult) {
        return findDaxilolannovEntities(false, maxResults, firstResult);
    }

    private List<Daxilolannov> findDaxilolannovEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Daxilolannov.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Daxilolannov findDaxilolannov(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Daxilolannov.class, id);
        } finally {
            em.close();
        }
    }

    public int getDaxilolannovCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Daxilolannov> rt = cq.from(Daxilolannov.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
