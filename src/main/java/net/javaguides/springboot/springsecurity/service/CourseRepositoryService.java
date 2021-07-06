package net.javaguides.springboot.springsecurity.service;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;
import org.hibernate.Session;
import java.lang.module.Configuration;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.NewCourse;
import net.javaguides.springboot.springsecurity.model.Section;
import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.repository.CourseRepository;
import net.javaguides.springboot.springsecurity.repository.Employeerepository;

@Service
public class CourseRepositoryService {

	JdbcTemplate template;   
	@Autowired
    private Employeerepository userRepository;
	@Autowired
	private CourseRepository courserepo;
	
	

	
	public NewCourse savecourse(NewCourse course) {

		
		course.setUser_id(loadUserByUsername());
		return courserepo.save(course);		
	}	
	
	 public Long loadUserByUsername() throws UsernameNotFoundException {
	   
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String username;
		 if (principal instanceof UserDetails) {
			  username= ((UserDetails)principal).getUsername();
		 } else {
		    username = principal.toString();
		 }
		 System.out.println(username);
			Employee user = userRepository.findByEmail(username);
			 System.out.println(user.getId());
			return user.getId();
	 }

	 public List<NewCourse> getEmpById(){   
		 
		// Session session = HibernateUtil.getSessionFactory().openSession())
		 
		
		 //System.out.println(courserepo.findbyii((long) 7));
		 System.out.println(courserepo.findById((long) 1));
		
	
		 return (List<NewCourse>) courserepo.findbyuserid(loadUserByUsername());
		 
		     
		}     

	public void savecourse1(@Valid NewCourse userDto) {
		// TODO Auto-generated method stub
		
	}
	
	public Employee findbyemail(String email){
		
		return userRepository.findbyemail(email);
		
		
	}
	
	public List<NewCourse> findbyCousename(String coursename, Long id){
		
		return (List<NewCourse>) courserepo.findbyCousename(coursename,id);
	 }
	
		public List<NewCourse> finbyidcid(Long cid,Long uid){
						return (List<NewCourse>) courserepo.findbyidcid(cid,uid);
		}
	

	public List<NewCourse> findall() {
		
		return (List<NewCourse>) courserepo.findAll();
	}

	public List<NewCourse> getByciduid(Long cid, Long uid) {
		// TODO Auto-generated method stub
		return (List<NewCourse>) courserepo.findbyidcid(cid,uid);
	}
}
 