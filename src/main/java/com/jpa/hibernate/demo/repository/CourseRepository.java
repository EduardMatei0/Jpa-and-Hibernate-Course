package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.entity.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class CourseRepository  {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager entityManager;

    @Autowired
    public CourseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Course findById(Long id)
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    // save or update Course
    public Course saveOrUpdate(Course course) {
        if (course.getId() <= 0) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        return course;
    }
    // delete Course

    public void deleteById(Long id) {

        Course course = findById(id);
        entityManager.remove(course);
    }

    public void playWithEntityManager() {
       Course course1 = new Course("Web Services in 100 steps");
       entityManager.persist(course1);

       Course course2 = findById(10001L);
       course2.setName("JPA in 50 steps UPDATED");

    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        Course course = findById(courseId);
        logger.info("Course ---> {}", course);
        logger.info("Course reviews ---> {}", course.getReviews());

        for (Review review: reviews) {
            course.addReview(review);
            entityManager.persist(review);
        }
    }
}
