package com.example.daosism.CRUD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversalService {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UniversalService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public <T> List<T> getAll(Class<T> entityClass) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public <T> T getById(Class<T> entityClass, Integer id) {
        return entityManager.find(entityClass, id);
    }

    @Transactional
    public <T> void create(T entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public <T> void update(T entity) {
        
        entityManager.merge(entity);
    }

    @Transactional
    public <T> void delete(Class<T> entityClass, Integer id) {
        T entity = entityManager.find(entityClass, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}
