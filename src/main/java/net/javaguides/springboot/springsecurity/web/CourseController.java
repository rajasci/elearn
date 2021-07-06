package net.javaguides.springboot.springsecurity.web;

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

import net.javaguides.springboot.springsecurity.model.NewCourse;
import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.service.CourseRepositoryService;
import net.javaguides.springboot.springsecurity.service.EmployeeService;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;


@Controller
@RequestMapping("trainer/register")
public class CourseController {

	 @Autowired
	    private EmployeeService userService;
	 
	 @Autowired
	    private CourseRepositoryService courseservice;
	

	    @ModelAttribute("newcourse")
	    public NewCourse courseRegistration() {
	        return new NewCourse();
	    }

	    @GetMapping
	    public String showRegistrationForm(Model model) {
	    	return "trainer/CourseRegister";
	    }

	    @PostMapping
	    public String registerUserAccount(@ModelAttribute("newcourse") @Valid NewCourse course,
	        BindingResult result) {
	    	
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
			
	    	
	    	if(course.getCourse_name()=="")
	    	{
	    		result.rejectValue("Course_name", null, "Enter the Course name");
	    	}
	    	else{
	    		int size=(courseservice.findbyCousename(course.getCourse_name(),id)).size();
	    		System.out.println("abcd");
	    		System.out.println(course.getCourse_name());
	    		System.out.println(id);
	    		System.out.println(size);
	    		if(size>0)
	    		result.rejectValue("Course_name", null, "Course name was already present");
	    	}
	    	
	    	if(course.getDescription()=="")
	    	{
	    		result.rejectValue("description", null, "Enter the Course name");
	    	}
	    	if(course.getCategory()=="")
	    	{
	    		result.rejectValue("Category", null, "Enter the Course name");
	    	}
	    	if(course.getUser_name()=="")
	    	{
	    		result.rejectValue("User_name", null, "Enter the Course name");
	    	}
	        if (result.hasErrors()) {
	            return "trainer/CourseRegister";
	        }
	        
	        courseservice.savecourse(course);
	        return "redirect:/trainer/cresult";
	    }
	
	
	
	
}
