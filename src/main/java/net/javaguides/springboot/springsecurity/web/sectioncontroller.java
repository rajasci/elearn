package net.javaguides.springboot.springsecurity.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.springsecurity.model.NewCourse;
import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.service.CourseRepositoryService;
import net.javaguides.springboot.springsecurity.service.EmployeeService;
import net.javaguides.springboot.springsecurity.model.*;
import net.javaguides.springboot.springsecurity.service.*;


@Controller
public class sectioncontroller {
	
	
	
	 @Autowired
	    private EmployeeService userService;
	 
	 @Autowired
	    private CourseRepositoryService courseservice;
	 
	 @Autowired
	    private sectionService sectionService;
	
	 
	    @ModelAttribute("contents")
	    public Section section() {
	        return new Section();
	    }


	    @RequestMapping(path="trainer/addsection", method=RequestMethod.GET)
	    public String addcontent( @RequestParam(value = "param1", required = false) Long cid,
	            @RequestParam(value = "param2", required = false) Long uid, Model model) {
		 System.out.println("cid=");
		 System.out.println(cid);
		    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 String username;
			 if (principal instanceof UserDetails) {
				 
				  username= ((UserDetails)principal).getUsername();
				  System.out.println("size=");
				  
			 } else {
			    username = principal.toString();
			 }
			 
			 Employee employee=courseservice.findbyemail(username);
			 Long id=employee.getId();
			 
			 if(!(id==uid))
			 return "NULL";
			 
			 	List<NewCourse> courses = courseservice.finbyidcid(cid,uid);
			 	System.out.println("courses");
			 	System.out.println(courses.size());
			 	System.out.println(courses.isEmpty());
			 	
			 	if(courses.size()==0)
			 	return "NULL";
			 	
			 	model.addAttribute("uid",uid);
			 	model.addAttribute("cid",cid);
			 	net.javaguides.springboot.springsecurity.model.Section abc = new Section();
			 	abc.setUser_id(uid);
			 	abc.setCourse_id(cid);
			 	
			 	model.addAttribute("contents", abc);
			 	return "trainer/addsection";
	        
	    }

	    @PostMapping(path="trainer/addsection")
	    public String registerUserAccount(@ModelAttribute("contents") @Valid Section course, 
	        BindingResult result,Model model) {
	    	
	    	
	    	  
		    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 String username;
			 if (principal instanceof UserDetails) {
				 
				  username= ((UserDetails)principal).getUsername();
				  System.out.println("size=");
				  
			 } else {
			    username = principal.toString();
			 }
			Long uid=course.getUser_id();
			Long Cid=course.getCourse_id();
			System.out.println("values");
			System.out.println(uid);
			 System.out.println(Cid);
			 Employee employee=courseservice.findbyemail(username);
			 Long id=employee.getId();
			 System.out.println(id);
			 System.out.println(uid);
			 if(!(id==uid))
			 return "NULLmas";
			 
			 List<NewCourse> courses = courseservice.finbyidcid(Cid,uid);
			 	System.out.println("courses");
			 	System.out.println(courses.size());
			 	System.out.println(courses.isEmpty());

			 	if(courses.size()==0)
			 	return "NULL";
	    	

	        Employee existing = userService.findByEmail("abc@gmail.com");
	     
	        if(course.getSection_name()=="")
	    	{
	    		result.rejectValue("section_name", null, "Enter the Course name");
	    	}
	        else {
	        	
	        	int size=(sectionService.findbysectioname(course.getSection_name(),id)).size();

	    		if(size>0)
	    		result.rejectValue("section_name", null, "Course name was already present");
	        	
	        }
	        
	        if(course.getDescription()=="")
	    	{
	    		result.rejectValue("description", null, "Enter the Course name");
	    	}
	    	if(course.getUser_name()=="")
	    	{
	    		result.rejectValue("User_name", null, "Enter the Course name");
	    	}
	   
	        
	        if (result.hasErrors()) {
	            return "trainer/addsection";
	        }
	        
	        
	        System.out.println("exe");
	        System.out.println("caed=");
	        course.setUser_id(uid);
	        course.setCourse_id(Cid);
	    	System.out.println(course.getCourse_id());
	    	System.out.println(course.getUser_id());
	    	sectionService.savecourse(course);
	        return "redirect:/trainer/cresult";
	    }
	
}


