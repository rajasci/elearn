package net.javaguides.springboot.springsecurity.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import antlr.collections.List;
import net.javaguides.springboot.springsecurity.model.Role;
import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.repository.Employeerepository;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;
import net.javaguides.springboot.springsecurity.model.*;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private Employeerepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Employee findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Employee save(EmployeeRegistrationDto registration) {
        Employee user = new Employee();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_EMPLOYEE")));
        return userRepository.save(user);
    }
    
    public Employee teachersave(EmployeeRegistrationDto registration) {
        Employee user = new Employee();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_TRAINER")));
        return userRepository.save(user);
    }
    
    public Employee adminsave(EmployeeRegistrationDto registration) {
        Employee user = new Employee();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        
        Collection < Role > rols=user.getRoles();
      System.out.println(rols);
        Role role=new Role();
        System.out.println(role.toString());
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
        
    }
    
    public Collection<Role> print() {
    	Employee user=new Employee();
    	return user.getRoles();
    }

    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }
}