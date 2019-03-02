package com.jpa.hibernate.demo.repository;


import com.jpa.hibernate.demo.entity.Course;
import com.jpa.hibernate.demo.entity.Passport;
import com.jpa.hibernate.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager entityManager;

    @Autowired
    public StudentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Course findById(Long id)
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    // save or update Course
    public Student saveOrUpdate(Student student) {
        if (student.getId() <= 0) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        return student;
    }
    // delete Course

    public void deleteById(Long id) {

        Student student = findById(id);
        entityManager.remove(student);
    }

    public void saveStudentWithPassport() {

        Passport passport = new Passport("Z12034");
        entityManager.persist(passport);

        Student student = new Student("Mihai Capitanul");
        student.setPassport(passport);
        entityManager.persist(student);
    }

    public void insertStudentAndCourse() {
        Student student = new Student("Elena");
        Course course = new Course("Angular Complete Course");

        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
    }

    public void insertStudentAndCourseWithParameters(Student student, Course course) {

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
        entityManager.persist(course);
    }
}
