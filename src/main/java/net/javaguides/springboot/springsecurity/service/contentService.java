package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.model.Section;
import net.javaguides.springboot.springsecurity.repository.CourseRepository;
import net.javaguides.springboot.springsecurity.repository.SectionRepository;
import net.javaguides.springboot.springsecurity.repository.Employeerepository;
import net.javaguides.springboot.springsecurity.repository.contentRepository;
import net.javaguides.springboot.springsecurity.model.*;
@Service
public class contentService {
	@Autowired
    private Employeerepository userRepository;
	@Autowired
	private CourseRepository courserepo;
	
	@Autowired
	private contentRepository content;
	
	
	
	
public Content savecourse(Content contents) {

		contents.setUser_id(loadUserByUsername());
		return content.save(contents);		
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

	 
	 public List<Content> getEmpById(Long id){   
		 
			// Session session = HibernateUtil.getSessionFactory().openSession())
			 //System.out.println(courserepo.findbyii((long) 7));
		 
			 System.out.println(courserepo.findById((long) 1));
			
		
			 return (List<Content>) content.findbyuserid(id);
			 
			     
			}     
	 
	 
	 public Content contentid(Long id){   
		 
			// Session session = HibernateUtil.getSessionFactory().openSession())
			 //System.out.println(courserepo.findbyii((long) 7));
			 return content.contentid(id);
			 
			     
			}


	public List<Content> findbycontentname(String content_name, Long id) {
		
		return (List<Content>) content.findbycontentame(content_name,id);
		
	}



	     
	 
	 
}
