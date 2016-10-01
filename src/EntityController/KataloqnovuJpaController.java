/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityController;

import Entity.Kataloqnovu;
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
public class KataloqnovuJpaController implements Serializable {

    public KataloqnovuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kataloqnovu kataloqnovu) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(kataloqnovu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kataloqnovu kataloqnovu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            kataloqnovu = em.merge(kataloqnovu);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kataloqnovu.getIdKataloqNovu();
                if (findKataloqnovu(id) == null) {
                    throw new NonexistentEntityException("The kataloqnovu with id " + id + " no longer exists.");
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
            Kataloqnovu kataloqnovu;
            try {
                kataloqnovu = em.getReference(Kataloqnovu.class, id);
                kataloqnovu.getIdKataloqNovu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kataloqnovu with id " + id + " no longer exists.", enfe);
            }
            em.remove(kataloqnovu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kataloqnovu> findKataloqnovuEntities() {
        return findKataloqnovuEntities(true, -1, -1);
    }

    public List<Kataloqnovu> findKataloqnovuEntities(int maxResults, int firstResult) {
        return findKataloqnovuEntities(false, maxResults, firstResult);
    }

    private List<Kataloqnovu> findKataloqnovuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kataloqnovu.class));
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

    public Kataloqnovu findKataloqnovu(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kataloqnovu.class, id);
        } finally {
            em.close();
        }
    }

    public int getKataloqnovuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kataloqnovu> rt = cq.from(Kataloqnovu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
