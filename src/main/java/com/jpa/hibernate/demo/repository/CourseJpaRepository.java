package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {

   List<Course> findByName(String name);
   List<Course> countByName(String name);
}
