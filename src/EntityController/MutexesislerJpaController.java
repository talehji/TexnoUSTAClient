/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityController;

import Entity.Mutexesisler;
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
public class MutexesislerJpaController implements Serializable {

    public MutexesislerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mutexesisler mutexesisler) {
        if (mutexesisler.getMutexesisworkCollection() == null) {
            mutexesisler.setMutexesisworkCollection(new ArrayList<Mutexesiswork>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Mutexesiswork> attachedMutexesisworkCollection = new ArrayList<Mutexesiswork>();
            for (Mutexesiswork mutexesisworkCollectionMutexesisworkToAttach : mutexesisler.getMutexesisworkCollection()) {
                mutexesisworkCollectionMutexesisworkToAttach = em.getReference(mutexesisworkCollectionMutexesisworkToAttach.getClass(), mutexesisworkCollectionMutexesisworkToAttach.getIdMutexesisWork());
                attachedMutexesisworkCollection.add(mutexesisworkCollectionMutexesisworkToAttach);
            }
            mutexesisler.setMutexesisworkCollection(attachedMutexesisworkCollection);
            em.persist(mutexesisler);
            for (Mutexesiswork mutexesisworkCollectionMutexesiswork : mutexesisler.getMutexesisworkCollection()) {
                Mutexesisler oldIdMutexesislerOfMutexesisworkCollectionMutexesiswork = mutexesisworkCollectionMutexesiswork.getIdMutexesisler();
                mutexesisworkCollectionMutexesiswork.setIdMutexesisler(mutexesisler);
                mutexesisworkCollectionMutexesiswork = em.merge(mutexesisworkCollectionMutexesiswork);
                if (oldIdMutexesislerOfMutexesisworkCollectionMutexesiswork != null) {
                    oldIdMutexesislerOfMutexesisworkCollectionMutexesiswork.getMutexesisworkCollection().remove(mutexesisworkCollectionMutexesiswork);
                    oldIdMutexesislerOfMutexesisworkCollectionMutexesiswork = em.merge(oldIdMutexesislerOfMutexesisworkCollectionMutexesiswork);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mutexesisler mutexesisler) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mutexesisler persistentMutexesisler = em.find(Mutexesisler.class, mutexesisler.getIdMutexesisler());
            Collection<Mutexesiswork> mutexesisworkCollectionOld = persistentMutexesisler.getMutexesisworkCollection();
            Collection<Mutexesiswork> mutexesisworkCollectionNew = mutexesisler.getMutexesisworkCollection();
            List<String> illegalOrphanMessages = null;
            for (Mutexesiswork mutexesisworkCollectionOldMutexesiswork : mutexesisworkCollectionOld) {
                if (!mutexesisworkCollectionNew.contains(mutexesisworkCollectionOldMutexesiswork)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mutexesiswork " + mutexesisworkCollectionOldMutexesiswork + " since its idMutexesisler field is not nullable.");
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
            mutexesisler.setMutexesisworkCollection(mutexesisworkCollectionNew);
            mutexesisler = em.merge(mutexesisler);
            for (Mutexesiswork mutexesisworkCollectionNewMutexesiswork : mutexesisworkCollectionNew) {
                if (!mutexesisworkCollectionOld.contains(mutexesisworkCollectionNewMutexesiswork)) {
                    Mutexesisler oldIdMutexesislerOfMutexesisworkCollectionNewMutexesiswork = mutexesisworkCollectionNewMutexesiswork.getIdMutexesisler();
                    mutexesisworkCollectionNewMutexesiswork.setIdMutexesisler(mutexesisler);
                    mutexesisworkCollectionNewMutexesiswork = em.merge(mutexesisworkCollectionNewMutexesiswork);
                    if (oldIdMutexesislerOfMutexesisworkCollectionNewMutexesiswork != null && !oldIdMutexesislerOfMutexesisworkCollectionNewMutexesiswork.equals(mutexesisler)) {
                        oldIdMutexesislerOfMutexesisworkCollectionNewMutexesiswork.getMutexesisworkCollection().remove(mutexesisworkCollectionNewMutexesiswork);
                        oldIdMutexesislerOfMutexesisworkCollectionNewMutexesiswork = em.merge(oldIdMutexesislerOfMutexesisworkCollectionNewMutexesiswork);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mutexesisler.getIdMutexesisler();
                if (findMutexesisler(id) == null) {
                    throw new NonexistentEntityException("The mutexesisler with id " + id + " no longer exists.");
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
            Mutexesisler mutexesisler;
            try {
                mutexesisler = em.getReference(Mutexesisler.class, id);
                mutexesisler.getIdMutexesisler();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mutexesisler with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Mutexesiswork> mutexesisworkCollectionOrphanCheck = mutexesisler.getMutexesisworkCollection();
            for (Mutexesiswork mutexesisworkCollectionOrphanCheckMutexesiswork : mutexesisworkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mutexesisler (" + mutexesisler + ") cannot be destroyed since the Mutexesiswork " + mutexesisworkCollectionOrphanCheckMutexesiswork + " in its mutexesisworkCollection field has a non-nullable idMutexesisler field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(mutexesisler);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mutexesisler> findMutexesislerEntities() {
        return findMutexesislerEntities(true, -1, -1);
    }

    public List<Mutexesisler> findMutexesislerEntities(int maxResults, int firstResult) {
        return findMutexesislerEntities(false, maxResults, firstResult);
    }

    private List<Mutexesisler> findMutexesislerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mutexesisler.class));
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

    public Mutexesisler findMutexesisler(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mutexesisler.class, id);
        } finally {
            em.close();
        }
    }

    public int getMutexesislerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mutexesisler> rt = cq.from(Mutexesisler.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
