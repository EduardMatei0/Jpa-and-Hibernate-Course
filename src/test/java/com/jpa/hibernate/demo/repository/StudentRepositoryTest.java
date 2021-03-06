package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.DemoApplication;
import com.jpa.hibernate.demo.entity.Address;
import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.entity.Passport;
import com.jpa.hibernate.demo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StudentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	public void someTest() {
		Student student = entityManager.find(Student.class, 20001L);
		Passport passport = student.getPassport();

		passport.setNumber("E12050");
		student.setName("Cristina Vestemean");

	}

	@Test
	@Transactional
	public void retrieveStudentAndPassportDetails() {
		Student student = entityManager.find(Student.class, 20001L);
		logger.info("student --> {}" , student);
		logger.info("passport --> {}", student.getPassport());
	}

	@Test
	@Transactional
	public void retrievePassportAndStudentDetails() {
		Passport passport = entityManager.find(Passport.class, 40001L);
		logger.info("passport --> {}" , passport);
		logger.info("student --> {}", passport.getStudent());
	}

	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = entityManager.find(Student.class, 20001L);
		logger.info("student ---> {}", student);
		logger.info("courses ---> {}", student.getCourses());
	}


	@Test
	@Transactional
	public void setAddressDetails() {
		Student student = entityManager.find(Student.class, 20001L);
		student.setAddress(new Address("Calea Mosilor", "294", "Bucuresti"));
		entityManager.flush();
		logger.info("student ---> {}", student);
		logger.info("courses ---> {}", student.getCourses());
	}

	@Test
	@Transactional
	public void retrieveCourseAndStudents() {
		Course course = entityManager.find(Course.class, 10001L);
		logger.info("course ---> {}", course);
		logger.info("students ---> {}", course.getStudents());
	}


}

