package net.javaguides.springboot.springsecurity.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.javaguides.springboot.springsecurity.model.NewCourse;
import net.javaguides.springboot.springsecurity.model.Section;
import net.javaguides.springboot.springsecurity.model.Content;
import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.service.CourseRepositoryService;
import net.javaguides.springboot.springsecurity.service.EmployeeService;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;


@Controller
//@RequestMapping("trainer/register")
public class CourseController {
	ServletContext servletContext;
		long filename=0;
		private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir");
	 @Autowired
	    private EmployeeService userService;
	 
	 @Autowired
	    private CourseRepositoryService courseservice;
	

	    @ModelAttribute("newcourse")
	    public NewCourse courseRegistration() {
	        return new NewCourse();
	    }

	    @RequestMapping(path="trainer/register")
	    public String showRegistrationForm(Model model) {
	    	return "trainer/CourseRegister";
	    }

	    
	    @PostMapping(path="trainer/register")

	    public String registerUserAccount(@RequestParam(value = "file") MultipartFile file, HttpSession s,@ModelAttribute("newcourse") @Valid NewCourse course,
		        BindingResult result) throws IllegalStateException, IOException  {
	    	
	    	
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
	    	
		   
	        
	        File directory;
			String check = File.separator; 
			String path = null;
			
//			if (check.equalsIgnoreCase("\\")) {
//				path = servletContext.getRealPath("").replace("build\\", ""); 
//			}
//
//			if (check.equalsIgnoreCase("/")) {
//				path = servletContext.getRealPath("").replace("build/", "");
//				path += "/"; // Adding trailing slash for Mac systems.
//			}
			
			path="D:\\virus";
			String currentPath = new java.io.File(".").getCanonicalPath();
			System.out.println("Current dir:" + currentPath);
			path=currentPath;
			filename++;
			String s1=Long.toString(filename);
			//directory = new File(path + "\\"+s1+"\\"+file.getName());
			directory = new File(path + "\\file\\"+s1);
			boolean temp = directory.exists();
			if (!temp) {
				temp = directory.mkdir();
			}
			if (temp) {
				
				// We need to transfer to a file
				
				MultipartFile photoInMemory = file;

				String fileName = photoInMemory.getOriginalFilename();
				// could generate file names as well

				File localFile = new File(directory.getPath(), fileName);

				// move the file from memory to the file

				//photoInMemory.transferTo(localFile);
				
				MultipartFile multipartFile=file;
				multipartFile.transferTo(localFile);
				//file.setFilename(localFile.getPath());
				System.out.println("File is stored at" + localFile.getPath());
				
				System.out.print("registerNewUser");
				course.setImage(localFile.getPath());
			} else {
				System.out.println("Failed to create directory!");
			}
			courseservice.savecourse(course);
	    	
	        //return "redirect:/trainer/cresult";
	        return "redirect:/trainer/index?success";
	    }
	    
//	    public String registerUserAccount(@RequestParam(value = "file") MultipartFile file, HttpSession s,@ModelAttribute("newcourse") @Valid NewCourse course,
//		        BindingResult result) throws IllegalStateException, IOException  {
//	    	
//	    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			 String username;
//			 if (principal instanceof UserDetails) {
//				 
//				  username= ((UserDetails)principal).getUsername();
//				  System.out.println("size=");
//				  
//			 } else {
//			    username = principal.toString();
//			 }
//			 
//			 Employee employee=courseservice.findbyemail(username);
//			 Long id=employee.getId();
//			
//	    	
//	    	if(course.getCourse_name()=="")
//	    	{
//	    		result.rejectValue("Course_name", null, "Enter the Course name");
//	    	}
//	    	else{
//	    		int size=(courseservice.findbyCousename(course.getCourse_name(),id)).size();
//	    		System.out.println("abcd");
//	    		System.out.println(course.getCourse_name());
//	    		System.out.println(id);
//	    		System.out.println(size);
//	    		if(size>0)
//	    		result.rejectValue("Course_name", null, "Course name was already present");
//	    	}
//	    	
//	    	if(course.getDescription()=="")
//	    	{
//	    		result.rejectValue("description", null, "Enter the Course name");
//	    	}
//	    	if(course.getCategory()=="")
//	    	{
//	    		result.rejectValue("Category", null, "Enter the Course name");
//	    	}
//	    	if(course.getUser_name()=="")
//	    	{
//	    		result.rejectValue("User_name", null, "Enter the Course name");
//	    	}
//	        if (result.hasErrors()) {
//	            return "trainer/CourseRegister";
//	        }
//	        
//
//	        File directory;
//	        
//			String check = File.separator; 
//			String path = null;
//			
//			if (check.equalsIgnoreCase("\\")) {
//				path = servletContext.getRealPath("").replace("build\\", ""); 
//			}
//			
//			if (check.equalsIgnoreCase("/")) {
//				path = servletContext.getRealPath("").replace("build/", "");
//				path += "/"; // Adding trailing slash for Mac systems.
//			}
//			path="D:\\virus";
//			String currentPath = new java.io.File(".").getCanonicalPath();
//			System.out.println("Current dir:" + currentPath);
//			path=currentPath;
//			filename++;
//			String s1=Long.toString(filename);
//			//directory = new File(path + "\\"+s1+"\\"+file.getName());
//			directory = new File(path + "\\file\\"+s1);
//			boolean temp = directory.exists();
//			if (!temp) {
//				temp = directory.mkdir();
//			}
//			if (temp) {
//				// We need to transfer to a file
//				MultipartFile photoInMemory = file;
//
//				String fileName = photoInMemory.getOriginalFilename();
//				// could generate file names as well
//
//				File localFile = new File(directory.getPath(), fileName);
//
//				// move the file from memory to the file
//
//				//photoInMemory.transferTo(localFile);
//				
//				MultipartFile multipartFile=file;
//				multipartFile.transferTo(localFile);
//				//file.setFilename(localFile.getPath());
//				System.out.println("File is stored at" + localFile.getPath());
//				
//				System.out.print("registerNewUser");
//				course.setImage(localFile.getPath());
//			} else {
//				System.out.println("Failed to create directory!");
//			}
//				
//	        
//	        courseservice.savecourse(course);
//	        return "redirect:/trainer/cresult";
//	    }
//	
//	
	
	
}
