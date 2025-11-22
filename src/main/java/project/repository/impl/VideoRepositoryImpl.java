package project.repository.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import project.config.JPAConfig;
import project.entity.Video;
import project.repository.VideoRepository;

public class VideoRepositoryImpl implements VideoRepository{
	 @Override
	    public List<Video> findAll() {
	        EntityManager em = JPAConfig.getEntityManager();
	        try {
	            TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
	            return query.getResultList();
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public Video findById(int id) {
	        EntityManager em = JPAConfig.getEntityManager();
	        try {
	            return em.find(Video.class, id);
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public void insert(Video video) {
	        EntityManager em = JPAConfig.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.persist(video);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public void update(Video video) {
	        EntityManager em = JPAConfig.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            em.merge(video);
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public void delete(int id) {
	        EntityManager em = JPAConfig.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            Video v = em.find(Video.class, id);
	            if (v != null) {
	                em.remove(v);
	            }
	            em.getTransaction().commit();
	        } catch (Exception e) {
	            em.getTransaction().rollback();
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public List<Video> search(String keyword) {
	        EntityManager em = JPAConfig.getEntityManager();
	        try {
	            String jpql = "SELECT v FROM Video v WHERE v.title LIKE :keyword OR v.description LIKE :keyword";
	            TypedQuery<Video> query = em.createQuery(jpql, Video.class);
	            query.setParameter("keyword", "%" + keyword + "%");
	            return query.getResultList();
	        } finally {
	            em.close();
	        }
	    }
}
