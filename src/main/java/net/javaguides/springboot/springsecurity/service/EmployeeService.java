package net.javaguides.springboot.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;

public interface EmployeeService extends UserDetailsService {

    Employee findByEmail(String email);

    Employee save(EmployeeRegistrationDto registration);
    Employee teachersave(EmployeeRegistrationDto registration);
    Employee adminsave(EmployeeRegistrationDto registration);
}