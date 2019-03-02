package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class ReviewRepository {

    @Autowired
    private EntityManager entityManager;

    public Review findById(Long id) {
        return entityManager.find(Review.class, id);
    }
}
