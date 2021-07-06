package net.javaguides.springboot.springsecurity.web;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.springsecurity.model.*;
import net.javaguides.springboot.springsecurity.service.CourseRepositoryService;
import net.javaguides.springboot.springsecurity.service.contentService;
import net.javaguides.springboot.springsecurity.service.sectionService;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
    private CourseRepositoryService courseservice;
	@Autowired
    private MyResourceHttpRequestHandler handler;
	@Autowired
    private sectionService sectionservice;
	
	@Autowired
    private contentService contentservice;
	
	private File MP4_FILE =new File("D:\\virus\\file\\abc.mp4");
	
	@GetMapping("/")
    public String root() {
        return "index";
    }
	
	 @ModelAttribute("employee")
	    public EmployeeRegistrationDto userRegistrationDto() {
	        return new EmployeeRegistrationDto();
	    }
	 @GetMapping("/login")
	    public String login(Model model) {
	        return "employee/login";
	    }
	  @GetMapping("/index")
	    public String index(Model model) {
		  
		  
		  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 String username;
			 if (principal instanceof UserDetails) {
				 
				  username= ((UserDetails)principal).getUsername();
				  System.out.println("size=");
				  System.out.println(username);
				  
			 } else {
			    username = principal.toString();
			 }
			 
			 Employee employee=courseservice.findbyemail(username);
			 Long id=employee.getId();
		  
		  List<NewCourse> courselist= (List<NewCourse>) courseservice.findall();
		  model.addAttribute("courselist",courselist);
		  model.addAttribute("id",id);
	        return "employee/index";
	    }
	  
	  
	  @RequestMapping(path="/section", method=RequestMethod.GET)
	    public String section( @RequestParam(value = "param1", required = false) Long cid,
	            @RequestParam(value = "param2", required = false) Long cuid, @RequestParam(value = "param3", required = false) Long uid , Model model) {
		  

			 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 String username;
			 if (principal instanceof UserDetails) {
				 
				  username= ((UserDetails)principal).getUsername();
				  System.out.println("size=");
				  System.out.println(username);
				  
			 } else {
			    username = principal.toString();
			 }
			 
			 Employee employee=courseservice.findbyemail(username);
			 Long id=employee.getId();
			 
			 System.out.println(uid);
			 System.out.println(cid);
			 System.out.print(id);
			 
			 	if(!(id==uid))
			 		return "NUll";
		  
		  
		  List<Section> courselist= (List<Section>) sectionservice.getEmpById(cid,cuid);
		  model.addAttribute("courselist",courselist);
		  model.addAttribute("id",id);
	        return "employee/section";
	    }

	  
	  @RequestMapping(path="/content", method=RequestMethod.GET)
	    public String content( @RequestParam(value = "param1", required = false) Long sid,
	            @RequestParam(value = "param2", required = false) Long uid, Model model) throws IOException {
		  
		  Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			 String username;
			 if (principal instanceof UserDetails) {
				 
				  username= ((UserDetails)principal).getUsername();
				  System.out.println("size=");
				  System.out.println(username);
				  
			 } else {
			    username = principal.toString();
			 }
			 
			 Employee employee=courseservice.findbyemail(username);
			 Long id=employee.getId();
			 
			 if(!(id==uid))
			 		return "NUll";
			 
//			 Employee Employee=courseservice.findbyemail(username);
//			 Long id=Employee.getId();
		  
		  
		  List<Content> courselist= (List<Content>) contentservice.getEmpById(sid);
		  
		  model.addAttribute("courselist",courselist);
		  model.addAttribute("id",id);
		  
	        return "employee/content";
		  
	    }
	  
	  
	  @RequestMapping(path="/vedio", method=RequestMethod.GET)
	    public String vedio( @RequestParam(value = "param1", required = false) Long cid,
	            @RequestParam(value = "param2", required = false) Long uid, Model model) throws IOException {
		  
		Content courselist=contentservice.contentid(cid);
		  model.addAttribute("courselist",courselist);
		  
		  System.out.println(courselist.getVedio());
		  
		  
		  MP4_FILE =new File(courselist.getVedio());
		  
	        return "employee/vedio";
		  
	    }
	  
	  
	  @RequestMapping(path="/vedios", method=RequestMethod.GET)
	    public void vedios( @RequestParam(value = "param1", required = false) Long cid,
	            @RequestParam(value = "param2", required = false) Long uid, HttpServletRequest request,
                HttpServletResponse response ,Model model) throws IOException, ServletException {
		  
		  Content courselist=contentservice.contentid(cid);
		  MP4_FILE =new File(courselist.getVedio());
		  request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
	        System.out.println(MP4_FILE.getName());
	        handler.handleRequest(request, response);
	    }
	  
	  
	    
	  
	  @RequestMapping(path="/byterange" , method=RequestMethod.GET)
	    public void byterange(@RequestParam(value = "param1", required = false) Long cid,HttpServletRequest request,
	                          HttpServletResponse response)
	            throws ServletException, IOException {
		  
	        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
	        System.out.println(MP4_FILE.getName());
	        handler.handleRequest(request, response);
	    }
	  
	  
	  
	  
	  
	  
	  
	  }


