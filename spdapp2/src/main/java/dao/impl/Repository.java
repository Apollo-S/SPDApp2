package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Repository<E> {
	
	private static final String REF_NAME = "spd";

	private final EntityManager manager = Persistence.createEntityManagerFactory(REF_NAME).createEntityManager();
	private final Class<E> entityClass;
	
	public Repository(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public E selectById(int id) {
		return manager.find(entityClass, id);
	}

	public List<E> selectAll() {
		return manager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
	}
	
	public E save(E entity) {
		manager.getTransaction().begin();;
		entity = manager.merge(entity);
		manager.getTransaction().commit();
		return entity;
	}

	public void delete(E entity) {
		manager.getTransaction().begin();;
		manager.remove(entity);
		manager.getTransaction().commit();
	}

}
