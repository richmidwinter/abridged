package uk.co.wansdykehouse.shortn;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class PersistenceManager {
	
	private static PersistenceManager _instance = new PersistenceManager();

	private EntityManager em;
	
	private PersistenceManager() {
		em = Persistence.createEntityManagerFactory("shortn").createEntityManager();
	}
	
	public static PersistenceManager get() {
		return _instance;
	}
	
	public Mapping find(final String link) {
		try {
			return (Mapping) em.createQuery("select m from Mapping m where m.url = :l").setParameter("l", link).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Mapping lookup(final String hash) {
		return (Mapping) em.createQuery("select m from Mapping m where m.hash = :h").setParameter("h", hash).getSingleResult();
	}
	
	public boolean exists(final String hash) {
		return em.createQuery("select m from Mapping m where m.hash = :h").setParameter("h", hash).getResultList().size() > 0;
	}
	
	public void persist(final Mapping mapping) {
		em.getTransaction().begin();
		
		em.merge(mapping);
		
		em.getTransaction().commit();
	}
}
