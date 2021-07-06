package net.javaguides.springboot.springsecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.model.NewCourse;

@Repository
public interface Employeerepository extends JpaRepository < Employee, Long > {
    Employee findByEmail(String email);
    
    @Query("SELECT u FROM Employee u WHERE u.email = ?1")
    public Employee findbyemail(String employee);
}