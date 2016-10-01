/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityController;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Mutexesisler;
import Entity.Daxilolan;
import Entity.Mutexesiswork;
import EntityController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pallas
 */
public class MutexesisworkJpaController implements Serializable {

    public MutexesisworkJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mutexesiswork mutexesiswork) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mutexesisler idMutexesisler = mutexesiswork.getIdMutexesisler();
            if (idMutexesisler != null) {
                idMutexesisler = em.getReference(idMutexesisler.getClass(), idMutexesisler.getIdMutexesisler());
                mutexesiswork.setIdMutexesisler(idMutexesisler);
            }
            Daxilolan idDaxilOlan = mutexesiswork.getIdDaxilOlan();
            if (idDaxilOlan != null) {
                idDaxilOlan = em.getReference(idDaxilOlan.getClass(), idDaxilOlan.getIdDaxilOlan());
                mutexesiswork.setIdDaxilOlan(idDaxilOlan);
            }
            em.persist(mutexesiswork);
            if (idMutexesisler != null) {
                idMutexesisler.getMutexesisworkCollection().add(mutexesiswork);
                idMutexesisler = em.merge(idMutexesisler);
            }
            if (idDaxilOlan != null) {
                idDaxilOlan.getMutexesisworkCollection().add(mutexesiswork);
                idDaxilOlan = em.merge(idDaxilOlan);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mutexesiswork mutexesiswork) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mutexesiswork persistentMutexesiswork = em.find(Mutexesiswork.class, mutexesiswork.getIdMutexesisWork());
            Mutexesisler idMutexesislerOld = persistentMutexesiswork.getIdMutexesisler();
            Mutexesisler idMutexesislerNew = mutexesiswork.getIdMutexesisler();
            Daxilolan idDaxilOlanOld = persistentMutexesiswork.getIdDaxilOlan();
            Daxilolan idDaxilOlanNew = mutexesiswork.getIdDaxilOlan();
            if (idMutexesislerNew != null) {
                idMutexesislerNew = em.getReference(idMutexesislerNew.getClass(), idMutexesislerNew.getIdMutexesisler());
                mutexesiswork.setIdMutexesisler(idMutexesislerNew);
            }
            if (idDaxilOlanNew != null) {
                idDaxilOlanNew = em.getReference(idDaxilOlanNew.getClass(), idDaxilOlanNew.getIdDaxilOlan());
                mutexesiswork.setIdDaxilOlan(idDaxilOlanNew);
            }
            mutexesiswork = em.merge(mutexesiswork);
            if (idMutexesislerOld != null && !idMutexesislerOld.equals(idMutexesislerNew)) {
                idMutexesislerOld.getMutexesisworkCollection().remove(mutexesiswork);
                idMutexesislerOld = em.merge(idMutexesislerOld);
            }
            if (idMutexesislerNew != null && !idMutexesislerNew.equals(idMutexesislerOld)) {
                idMutexesislerNew.getMutexesisworkCollection().add(mutexesiswork);
                idMutexesislerNew = em.merge(idMutexesislerNew);
            }
            if (idDaxilOlanOld != null && !idDaxilOlanOld.equals(idDaxilOlanNew)) {
                idDaxilOlanOld.getMutexesisworkCollection().remove(mutexesiswork);
                idDaxilOlanOld = em.merge(idDaxilOlanOld);
            }
            if (idDaxilOlanNew != null && !idDaxilOlanNew.equals(idDaxilOlanOld)) {
                idDaxilOlanNew.getMutexesisworkCollection().add(mutexesiswork);
                idDaxilOlanNew = em.merge(idDaxilOlanNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mutexesiswork.getIdMutexesisWork();
                if (findMutexesiswork(id) == null) {
                    throw new NonexistentEntityException("The mutexesiswork with id " + id + " no longer exists.");
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
            Mutexesiswork mutexesiswork;
            try {
                mutexesiswork = em.getReference(Mutexesiswork.class, id);
                mutexesiswork.getIdMutexesisWork();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mutexesiswork with id " + id + " no longer exists.", enfe);
            }
            Mutexesisler idMutexesisler = mutexesiswork.getIdMutexesisler();
            if (idMutexesisler != null) {
                idMutexesisler.getMutexesisworkCollection().remove(mutexesiswork);
                idMutexesisler = em.merge(idMutexesisler);
            }
            Daxilolan idDaxilOlan = mutexesiswork.getIdDaxilOlan();
            if (idDaxilOlan != null) {
                idDaxilOlan.getMutexesisworkCollection().remove(mutexesiswork);
                idDaxilOlan = em.merge(idDaxilOlan);
            }
            em.remove(mutexesiswork);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mutexesiswork> findMutexesisworkEntities() {
        return findMutexesisworkEntities(true, -1, -1);
    }

    public List<Mutexesiswork> findMutexesisworkEntities(int maxResults, int firstResult) {
        return findMutexesisworkEntities(false, maxResults, firstResult);
    }

    private List<Mutexesiswork> findMutexesisworkEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mutexesiswork.class));
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

    public Mutexesiswork findMutexesiswork(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mutexesiswork.class, id);
        } finally {
            em.close();
        }
    }

    public int getMutexesisworkCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mutexesiswork> rt = cq.from(Mutexesiswork.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
