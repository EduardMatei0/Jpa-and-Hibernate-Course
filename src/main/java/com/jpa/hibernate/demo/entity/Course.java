package com.jpa.hibernate.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "query_get_all_courses",
                query = "Select c from Course c"),
        @NamedQuery(name = "query_get_all_courses_join_fetch",
                query = "Select c from Course c JOIN FETCH c.students s"),
        @NamedQuery(name = "query_get_100_Step_courses",
        query = "Select c from Course c where name like '%50 Steps'")
})
@Cacheable
@SQLDelete(sql = "update course set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
public class Course {

    private static Logger logger = LoggerFactory.getLogger(Course.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;

    private boolean isDeleted;


    @PreRemove
    private void preRemove() {
        logger.info("Setting isDeleted to true");
        this.isDeleted = true;
    }

    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    protected  Course() {}

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        review.setCourse(this);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        review.setCourse(this);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
