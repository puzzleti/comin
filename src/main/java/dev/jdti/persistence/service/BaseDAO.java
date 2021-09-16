package dev.jdti.persistence.service;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author thim
 * @version 1.0
 * 
 * initial basedao implementation
 * 
 * The Class BaseDAO.
 *
 * @param <T> the generic type
 */
public class BaseDAO<T extends Serializable> implements AutoCloseable {

	private Class<T> clazz = null;

	private EntityManagerFactory emf = null;

	private EntityManager em = null;

	private EntityTransaction transaction = null;

	/**
	 * Instantiates a new base DAO.
	 *
	 * @param persistenceUnitName the persistence unit name
	 */
	@SuppressWarnings("unchecked")
	public BaseDAO(String persistenceUnitName) {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();

		//OK, reflection ist ein hack...!
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		this.clazz = (Class<T>) params[0];
	}

	/**
	 * persists and creates the entity in db
	 *
	 * @param entity the entity
	 */
	public void create(T entity) {
		try {
			begin();
			em.persist(entity);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw ex;
		}
	}
	

	/**
	 * Find all entities
	 *
	 * @return the list
	 */
	public List<T> findAll() {
		validateClazz();
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(clazz);
        Root<T> root = cq.from(clazz);
        return em.createQuery(cq.select(root)).getResultList();
    }

    /**
     * Count all entities
     *
     * @return the long
     */
    public Long countAll() {
    	validateClazz();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(clazz)));
        return em.createQuery(cq).getSingleResult();
    }

	/**
	 * Read entity
	 *
	 * @param primaryKey the primary key
	 * @return the t
	 */
	public T read(Long primaryKey) {
		validateClazz();
		return (T) em.find(clazz, primaryKey);
	}

	/**
	 * Update entity
	 *
	 * @param entity the entity
	 */
	public void update(T entity) {
		try {
			begin();
			em.merge(entity);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw ex;
		}
	}

	/**
	 * Delete entity
	 *
	 * @param entity the entity
	 */
	public void delete(T entity) {
		try {
			begin();
			em.remove(entity);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw ex;
		}
	}

	/**
	 * begin the transaction
	 */
	private void begin() {
		validateClazz();
		transaction = em.getTransaction();
		transaction.begin();
	}

	/**
	 * Validate clazz-object is set
	 */
	private void validateClazz() {
		if (clazz == null)
			throw new IllegalArgumentException("no clazz given");
	}

	/**
	 * Sets the clazz.
	 *
	 * @param clazz the new clazz
	 */
	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Close the entitymanager
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void close() throws Exception {
		System.out.println("calling close()");
		if (em.isOpen() == true)
			em.close();
		if (emf.isOpen() == true)
			emf.close();
	}

	/**
	 * Gets the em.
	 *
	 * @return the em
	 */
	protected EntityManager getEM() {
		return this.em;
	}

	/**
	 * Sets the em.
	 *
	 * @param em the new em
	 */
	protected void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * Gets the transaction for mock.
	 *
	 * @return the transaction for mock
	 */
	protected EntityTransaction getTransactionForMock() {
		return getEM().getTransaction();
	}
}
