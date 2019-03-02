package com.jpa.hibernate.demo.repository;

import com.jpa.hibernate.demo.entity.Employee;
import com.jpa.hibernate.demo.entity.FullTimeEmployee;
import com.jpa.hibernate.demo.entity.PartTimeEmployee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final EntityManager entityManager;

    @Autowired
    public EmployeeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // insert an Employee

    public void insert(Employee employee) {
        entityManager.persist(employee);
    }

    // retrieve an Employee

    public List<Employee> retrieveAllEmployees(){
        return entityManager
                .createQuery("Select e from Employee e", Employee.class).getResultList();
    }

    public List<PartTimeEmployee> retrieveAllPartTimeEmployees(){
        return entityManager
                .createQuery("Select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> retrieveAllFullTimeEmployees(){
        return entityManager
                .createQuery("Select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }

}
