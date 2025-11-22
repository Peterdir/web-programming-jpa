package project.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import project.config.JPAConfig;
import project.entity.User;
import project.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
	@Override
	public User findByUsername(String username) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT u FROM User u WHERE LOWER(u.fullname) = LOWER(:username)";
			return em.createQuery(jpql, User.class).setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public void update(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.merge(user);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
}
