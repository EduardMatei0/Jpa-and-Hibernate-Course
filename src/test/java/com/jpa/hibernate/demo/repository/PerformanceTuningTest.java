package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.DemoApplication;
import com.jpa.hibernate.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PerformanceTuningTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@Transactional
	public void creatingNPlusOneProblem() {
		List<Course> courses = entityManager.createNamedQuery("query_get_all_courses", Course.class).getResultList();

		for (Course course: courses) {
			logger.info("Course -> {} Students -> {}", course, course.getStudents());
		}
	}

	@Test
	@Transactional
	public void solvingNPlusOneProblem() {
		EntityGraph<Course> entityGraph = entityManager.createEntityGraph(Course.class);
		Subgraph<Object> subGrapth = entityGraph.addSubgraph("students");

		List<Course> courses = entityManager
				.createNamedQuery("query_get_all_courses", Course.class)
				.setHint("javax.persistence.loadgraph", entityGraph )
				.getResultList();

		for (Course course: courses) {
			logger.info("Course -> {} Students -> {}", course, course.getStudents());
		}
	}


	@Test
	@Transactional
	public void solvingNPlusOneProblem_JoinFetch() {
		List<Course> courses = entityManager.createNamedQuery("query_get_all_courses_join_fetch", Course.class).getResultList();

		for (Course course: courses) {
			logger.info("Course -> {} Students -> {}", course, course.getStudents());
		}
	}



}

