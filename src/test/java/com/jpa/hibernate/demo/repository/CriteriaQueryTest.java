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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CriteriaQueryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	@Test
	public void criteria_query_basic() {

		// "Select c from Course c"

		// 1.Use Criteria builder to create a Criteria Query returining the expected result object4

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query

		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder


		// 4. Add predicates etc to the Criteria Query
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course --> {}", resultList);
	}

	@Test
	public void criteria_query_all_courses_having_100_steps() {

		// "Select c from Course c where name like '%100 Steps'"

		// 1.Use Criteria builder to create a Criteria Query returining the expected result object4

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query

		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder
		Predicate like = cb.like(courseRoot.get("name"), "%50 steps");


		// 4. Add predicates etc to the Criteria Query
		cq.where(like);
		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course --> {}", resultList);
	}

	@Test
	public void criteria_query_all_courses_without_students() {

		// "Select c from Course c where c.students is empty"

		// 1.Use Criteria builder to create a Criteria Query returining the expected result object4

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query

		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder
		Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));


		// 4. Add predicates etc to the Criteria Query

		cq.where(studentsIsEmpty);

		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course --> {}", resultList);
	}


	@Test
	public void join() {

		// "Select c from Course c join c.students s"

		// 1.Use Criteria builder to create a Criteria Query returining the expected result object4

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Course> cq = cb.createQuery(Course.class);

		// 2. Define roots for tables which are involved in the query

		Root<Course> courseRoot = cq.from(Course.class);

		// 3. Define predicates etc using Criteria Builder

		Join<Object, Object> students = courseRoot.join("students", JoinType.LEFT);

		// 4. Add predicates etc to the Criteria Query



		// 5. Build the TypedQuery using the entity manager and criteria query
		TypedQuery<Course> query = entityManager.createQuery(cq.select(courseRoot));
		List<Course> resultList = query.getResultList();
		logger.info("Select c from Course --> {}", resultList);
	}

}

