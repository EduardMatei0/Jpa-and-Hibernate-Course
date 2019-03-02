package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.DemoApplication;
import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	@Test
	public void jpql_basic() {
		List resultList = entityManager.createNamedQuery("query_get_all_courses").getResultList();
		logger.info("Select c from Course --> {}", resultList);
	}

	@Test
	public void jpql_type() {
		TypedQuery<Course> query = entityManager.createNamedQuery("query_get_all_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course --> {}", resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query =
				entityManager.createNamedQuery("query_get_100_Step_courses", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course --> {}", resultList);
	}

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query =
				entityManager.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results --> {}", resultList);
	}

	@Test
	public void jpql_courses_with_at_least_two_students() {
		TypedQuery<Course> query =
				entityManager.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results --> {}", resultList);
	}

	@Test
	public void jpql_courses_order_by_students() {
		TypedQuery<Course> query =
				entityManager.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results --> {}", resultList);
	}

	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query =
				entityManager.createQuery("Select s from Student s where s.passport.number like '%312%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results --> {}", resultList);
	}

	// JOIN -> Select c, s from Course c JOIN c.students s
	// LEFT JOIN -> Select c, s from Course c LEFT JOIN c.students s
	// CROSS JOIN -> Select c, s from Course c , Student s

	@Test
	public void join() {
		Query query =
				entityManager.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results size--> {}", resultList.size());

		for (Object[] result: resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}


	@Test
	public void left_join() {
		Query query =
				entityManager.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results size--> {}", resultList.size());

		for (Object[] result: resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}

	@Test
	public void cross_join() {
		Query query =
				entityManager.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results size--> {}", resultList.size());

		for (Object[] result: resultList) {
			logger.info("Course {} Student {}", result[0], result[1]);
		}
	}



}

