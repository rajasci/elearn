package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.springsecurity.model.NewCourse;
import net.javaguides.springboot.springsecurity.model.Content;
import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.model.Section;
import net.javaguides.springboot.springsecurity.repository.SectionRepository;
import net.javaguides.springboot.springsecurity.repository.CourseRepository;
import net.javaguides.springboot.springsecurity.repository.Employeerepository;
@Service
public class sectionService {
	
	@Autowired
    private Employeerepository userRepository;
	@Autowired
	private CourseRepository courserepo;
	
	@Autowired
	private SectionRepository content;
	
	
	
	
public Section savecourse(Section contents) {

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

	 
	 public List<Section> getEmpById(Long id, Long uid){   
		 
			 System.out.println(courserepo.findById((long) 1));
			 return (List<Section>) content.findbyuserid(id,uid);
			}
	 


	public List<Section> findbycid() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Section> findbysectioname(String sectionname, Long id){
		
		return (List<Section>) content.findbysectionname(sectionname,id);
	 }


	public List<Section> getbysiduid(Long sid, Long id) {
	
		return (List<Section>) content.getbysiduid(sid,id);
	}

	     
}
