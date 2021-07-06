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
import org.springframework.web.servlet.ModelAndView;


import net.javaguides.springboot.springsecurity.model.*;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;
import net.javaguides.springboot.springsecurity.service.*;
@Controller
@RequestMapping("/trainer")
public class InstructorController {
	
	
	@Autowired
    private EmployeeService userService;
	 @Autowired
	    private CourseRepositoryService courseservice;
	 
	 @Autowired
	    private sectionService sectionService;
	 
	 @Autowired
	    private contentService contentService;
	 
	 
	 @Autowired
	 private CourseRepositoryService cr;
	
	 
	 
	 @ModelAttribute("employee")
	    public Section section() {
	        return new Section();
	    }
	 
	 @ModelAttribute("employee")
	    public EmployeeRegistrationDto userRegistrationDto() {
	        return new EmployeeRegistrationDto();
	    }
	@GetMapping("/")
    public String root() {
        return "index";
    }
	
	
	@GetMapping("/CourseRegisterResult")
    public String result() {
        return "CourseRegisterResult";
    }
	
	
	 @GetMapping("/login")
	    public String login(Model model) {
	        return "trainer/login";
	    }
	 
	 @RequestMapping(path="/section", method=RequestMethod.GET)
	    public String section( @RequestParam(value = "param1", required = false) Long cid,
	            @RequestParam(value = "param2", required = false) Long uid, Model model) {
		 
		    

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
		System.out.println(id);
		System.out.println(uid);
//		 if(!(id==uid))
	//	 return "NULL";
		 
		 
		 
		 model.addAttribute("uid",uid);
		 model.addAttribute("cid",cid);
		 
		
		  
		  List<NewCourse> contentlist= (List<NewCourse>) courseservice.getByciduid(cid,uid);
		  //model.addAttribute("contentlist",contentlist);
		  if(contentlist.isEmpty())
			  return "null";
		  
		  List<Section> contentlis= (List<Section>) sectionService.getEmpById(cid,uid);
		  model.addAttribute("contentlist",contentlis);
//		  net.javaguides.springboot.springsecurity.model.Section abc = new Section();
//		  model.addAttribute("contents", abc);
		  
	        return "trainer/section";
	    }	 
	 
	 
	 @PostMapping(path="trainer/section")
	    public String registerUserAccount(@ModelAttribute("contents") @Valid Section course,
	        BindingResult result) {
	    	
	        Employee existing = userService.findByEmail("abc@gmail.com");
	        if (existing != null) {
	          //result.rejectValue("Course_name", null, "There is already an account registered with that email");
	            System.out.println("exe");
	        }
	        System.out.println("exe");
	        if (result.hasErrors()) {
	            return "trainer/registration";
	        }
	        System.out.println("exe");
	        //userService.save(userDto);
	        //courseservice.savecourse(userDto);
	        System.out.println("caed=");
	        
	    	System.out.println(course.getCourse_id());
	    	System.out.println(course.getUser_id());
	    	sectionService.savecourse(course);
	        return "redirect:/trainer/cresult";
	    }
	 
	 
	 @RequestMapping(path="/content", method=RequestMethod.GET)
	    public String content( @RequestParam(value = "param1", required = false) Long sid,
	            @RequestParam(value = "param2", required = false) Long uid, Model model) {
		 
		    
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
		 
		 
		 List<Section> content= (List<Section>) sectionService.getbysiduid(sid,id);
		  if(content.isEmpty())
			  return "null";
		 
		 
		 
		 model.addAttribute("uid",uid);
		 model.addAttribute("sid",sid);
		 List<Content> contentlist= (List<Content>) contentService.getEmpById(sid);
		  model.addAttribute("contentlist",contentlist);

	        return "trainer/content";
	    }	 
	
	  @GetMapping("/index")
	    public String index(Model model) {
		 
		  List<NewCourse> courselist= (List<NewCourse>) courseservice.getEmpById();
		  model.addAttribute("courselist",courselist);
  
	        return "trainer/index";
	    }
	  
	  @GetMapping("/cresult")
	    public String ind(Model model) {
	        return "trainer/CourseRegisterResult";
	    }
	  
	  
	  
	   @GetMapping("/logg")
	    public String logg(Model model) {
	        return "trainer/logg";
	    }
	    
	  
	  
	  
	  
	  
	  
	  

}
