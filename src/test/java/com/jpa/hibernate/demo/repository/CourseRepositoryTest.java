package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.DemoApplication;
import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Test
	public void findById_basic() {
		Course course = courseRepository.findById(10002L);
		assertEquals("Spring in 50 steps", course.getName());
		logger.info("Testing is Running");
	}


	@Test
	public void findById_first_level_cache() {

		Course course = courseRepository.findById(10002L);
		logger.info("First Course Retrieved {}", course);

		Course course1 = courseRepository.findById(10002L);
		logger.info("First Course Retrieved again {}",course1);

		assertEquals("Spring in 50 steps", course.getName());
		assertEquals("Spring in 50 steps", course1.getName());
		logger.info("Testing is Running");
	}

	@Test
	@DirtiesContext
	public void deleteById_basic() {
		courseRepository.deleteById(10002L);
		assertNull(courseRepository.findById(10002L));
	}

	@Test
	@DirtiesContext
	public void save_basic() {
		// get a course
		Course course = courseRepository.findById(10002L);

		// check value
		assertEquals("Spring in 50 steps", course.getName());

		// modify course
		course.setName("Spring in 50 steps updated");

		// update course and check
		courseRepository.saveOrUpdate(course);
		assertEquals("Spring in 50 steps updated", course.getName());
	}

	@Test
	@DirtiesContext
	public void playWithEntityManager() {
		courseRepository.playWithEntityManager();
	}


	@Test
	@Transactional
	public void retrieveReviewsForCourse() {
		Course course = courseRepository.findById(10001L);
		logger.info("Course reviews--> {}", course.getReviews());
	}

	@Test
	@Transactional
	public void retrieveCourseForRewiev() {
		Review review = reviewRepository.findById(50001L);
		logger.info("Course review--> {}", review.getCourse());
	}

}

