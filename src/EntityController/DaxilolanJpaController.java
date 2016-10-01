/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityController;

import Entity.Daxilolan;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Mutexesiswork;
import EntityController.exceptions.IllegalOrphanException;
import EntityController.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Pallas
 */
public class DaxilolanJpaController implements Serializable {

    public DaxilolanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Daxilolan daxilolan) {
        if (daxilolan.getMutexesisworkCollection() == null) {
            daxilolan.setMutexesisworkCollection(new ArrayList<Mutexesiswork>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Mutexesiswork> attachedMutexesisworkCollection = new ArrayList<Mutexesiswork>();
            for (Mutexesiswork mutexesisworkCollectionMutexesisworkToAttach : daxilolan.getMutexesisworkCollection()) {
                mutexesisworkCollectionMutexesisworkToAttach = em.getReference(mutexesisworkCollectionMutexesisworkToAttach.getClass(), mutexesisworkCollectionMutexesisworkToAttach.getIdMutexesisWork());
                attachedMutexesisworkCollection.add(mutexesisworkCollectionMutexesisworkToAttach);
            }
            daxilolan.setMutexesisworkCollection(attachedMutexesisworkCollection);
            em.persist(daxilolan);
            for (Mutexesiswork mutexesisworkCollectionMutexesiswork : daxilolan.getMutexesisworkCollection()) {
                Daxilolan oldIdDaxilOlanOfMutexesisworkCollectionMutexesiswork = mutexesisworkCollectionMutexesiswork.getIdDaxilOlan();
                mutexesisworkCollectionMutexesiswork.setIdDaxilOlan(daxilolan);
                mutexesisworkCollectionMutexesiswork = em.merge(mutexesisworkCollectionMutexesiswork);
                if (oldIdDaxilOlanOfMutexesisworkCollectionMutexesiswork != null) {
                    oldIdDaxilOlanOfMutexesisworkCollectionMutexesiswork.getMutexesisworkCollection().remove(mutexesisworkCollectionMutexesiswork);
                    oldIdDaxilOlanOfMutexesisworkCollectionMutexesiswork = em.merge(oldIdDaxilOlanOfMutexesisworkCollectionMutexesiswork);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Daxilolan daxilolan) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Daxilolan persistentDaxilolan = em.find(Daxilolan.class, daxilolan.getIdDaxilOlan());
            Collection<Mutexesiswork> mutexesisworkCollectionOld = persistentDaxilolan.getMutexesisworkCollection();
            Collection<Mutexesiswork> mutexesisworkCollectionNew = daxilolan.getMutexesisworkCollection();
            List<String> illegalOrphanMessages = null;
            for (Mutexesiswork mutexesisworkCollectionOldMutexesiswork : mutexesisworkCollectionOld) {
                if (!mutexesisworkCollectionNew.contains(mutexesisworkCollectionOldMutexesiswork)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mutexesiswork " + mutexesisworkCollectionOldMutexesiswork + " since its idDaxilOlan field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Mutexesiswork> attachedMutexesisworkCollectionNew = new ArrayList<Mutexesiswork>();
            for (Mutexesiswork mutexesisworkCollectionNewMutexesisworkToAttach : mutexesisworkCollectionNew) {
                mutexesisworkCollectionNewMutexesisworkToAttach = em.getReference(mutexesisworkCollectionNewMutexesisworkToAttach.getClass(), mutexesisworkCollectionNewMutexesisworkToAttach.getIdMutexesisWork());
                attachedMutexesisworkCollectionNew.add(mutexesisworkCollectionNewMutexesisworkToAttach);
            }
            mutexesisworkCollectionNew = attachedMutexesisworkCollectionNew;
            daxilolan.setMutexesisworkCollection(mutexesisworkCollectionNew);
            daxilolan = em.merge(daxilolan);
            for (Mutexesiswork mutexesisworkCollectionNewMutexesiswork : mutexesisworkCollectionNew) {
                if (!mutexesisworkCollectionOld.contains(mutexesisworkCollectionNewMutexesiswork)) {
                    Daxilolan oldIdDaxilOlanOfMutexesisworkCollectionNewMutexesiswork = mutexesisworkCollectionNewMutexesiswork.getIdDaxilOlan();
                    mutexesisworkCollectionNewMutexesiswork.setIdDaxilOlan(daxilolan);
                    mutexesisworkCollectionNewMutexesiswork = em.merge(mutexesisworkCollectionNewMutexesiswork);
                    if (oldIdDaxilOlanOfMutexesisworkCollectionNewMutexesiswork != null && !oldIdDaxilOlanOfMutexesisworkCollectionNewMutexesiswork.equals(daxilolan)) {
                        oldIdDaxilOlanOfMutexesisworkCollectionNewMutexesiswork.getMutexesisworkCollection().remove(mutexesisworkCollectionNewMutexesiswork);
                        oldIdDaxilOlanOfMutexesisworkCollectionNewMutexesiswork = em.merge(oldIdDaxilOlanOfMutexesisworkCollectionNewMutexesiswork);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = daxilolan.getIdDaxilOlan();
                if (findDaxilolan(id) == null) {
                    throw new NonexistentEntityException("The daxilolan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Daxilolan daxilolan;
            try {
                daxilolan = em.getReference(Daxilolan.class, id);
                daxilolan.getIdDaxilOlan();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The daxilolan with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Mutexesiswork> mutexesisworkCollectionOrphanCheck = daxilolan.getMutexesisworkCollection();
            for (Mutexesiswork mutexesisworkCollectionOrphanCheckMutexesiswork : mutexesisworkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Daxilolan (" + daxilolan + ") cannot be destroyed since the Mutexesiswork " + mutexesisworkCollectionOrphanCheckMutexesiswork + " in its mutexesisworkCollection field has a non-nullable idDaxilOlan field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(daxilolan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Daxilolan> findDaxilolanEntities() {
        return findDaxilolanEntities(true, -1, -1);
    }

    public List<Daxilolan> findDaxilolanEntities(int maxResults, int firstResult) {
        return findDaxilolanEntities(false, maxResults, firstResult);
    }

    private List<Daxilolan> findDaxilolanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Daxilolan.class));
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

    public Daxilolan findDaxilolan(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Daxilolan.class, id);
        } finally {
            em.close();
        }
    }

    public int getDaxilolanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Daxilolan> rt = cq.from(Daxilolan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
