package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Repository<E> {
	
	private static final String REF_NAME = "spdjpa";

	private final EntityManager manager = Persistence.createEntityManagerFactory(REF_NAME).createEntityManager();
	private final Class<E> entityClass;
	
	public Repository(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public E selectById(int id) {
		return manager.find(entityClass, id);
	}

	public void save(E entity) {
		manager.merge(entity);
	}

	public void delete(E entity) {
		manager.remove(entity);
	}

}
