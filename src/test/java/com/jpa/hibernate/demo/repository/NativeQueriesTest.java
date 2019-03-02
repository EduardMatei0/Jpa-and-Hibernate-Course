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

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class NativeQueriesTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager entityManager;

	@Test
	public void native_queries_basic() {
		List resultList = entityManager.createNativeQuery("SELECT * FROM COURSE", Course.class).getResultList();
		logger.info("Select c from Course --> {}", resultList);
	}

	@Test
	public void native_queries_where() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = ?", Course.class);
		query.setParameter(1, 10001L);
		List resultList = query.getResultList();
		logger.info("Select c from Course id = 10001 --> {}", resultList);
	}

	@Test
	public void native_queries_named_parameter() {
		Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE id = :id", Course.class);
		query.setParameter("id", 10001L);
		List resultList = query.getResultList();
		logger.info("Select c from Course where id = 10001 --> {}", resultList);
	}

	@Test
	@Transactional
	public void native_queries_to_update() {
		Query query = entityManager.createNativeQuery("UPDATE Course set last_updated_date=sysdate", Course.class);
		int numberOfRowsUpdated = query.executeUpdate();
		logger.info("Number of rows updated ---> {} ", numberOfRowsUpdated);
	}


}

