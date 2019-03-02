package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.DemoApplication;
import com.jpa.hibernate.demo.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class CourseSpringDataRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseJpaRepository repository;

	@Test
	public void findById() {
		Optional<Course> courseOptional = repository.findById(10001L);
		logger.info("{}", courseOptional.isPresent());
		assertTrue(courseOptional.isPresent());
	}

	@Test
	public void play_around_with_Spring_Data_Repository() {
		logger.info("Courses --> {}", repository.findAll());
		logger.info("Count --> {}", repository.count());
	}

	@Test
	public void sort() {
		Sort sort = new Sort(Sort.Direction.DESC, "name");
		logger.info("Sorted Courses --> {}", repository.findAll(sort));
	}

	@Test
	public void pagination() {
		PageRequest pageRequest = PageRequest.of(0, 1);

		Page<Course> firstPage = repository.findAll(pageRequest);
		logger.info("First Page --> {}", firstPage.getContent());

		Pageable secondPageable = firstPage.nextPageable();
		Page<Course> secondPage = repository.findAll(secondPageable);

		logger.info("Second Page --> {}", secondPage.getContent());
	}

	@Test
	public void findUsingName() {
		logger.info("FindByName --> {}", repository.findByName("JPA in 50 steps"));
	}
}

