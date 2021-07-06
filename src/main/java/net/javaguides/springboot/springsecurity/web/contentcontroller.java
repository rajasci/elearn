package net.javaguides.springboot.springsecurity.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.util.StringUtils;
import net.javaguides.springboot.springsecurity.service.CourseRepositoryService;
import net.javaguides.springboot.springsecurity.service.EmployeeService;
import net.javaguides.springboot.springsecurity.model.*;
import net.javaguides.springboot.springsecurity.service.*;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



@Controller
//@RequestMapping("teacher/addcontent")
public class contentcontroller {
	
	//Long cid,uid,sid;
	@Autowired
	ServletContext servletContext;
	 @Autowired
	    private EmployeeService userService;
	 
	 @Autowired
	    private CourseRepositoryService courseservice;
	 
	 @Autowired
	    private sectionService sectionservice;
	 
	 @Autowired
	    private contentService content;
	 long filename=0;
	
	 private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir");
	 
	    @ModelAttribute("contents")
	    public Content content() {
	        return new Content();
	    }
	    
	    
	    @RequestMapping(path="trainer/addcontent", method=RequestMethod.GET)
	    public String addcontent( @RequestParam(value = "param1", required = false) Long sid,
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
			 
			 List<Section> courses = sectionservice.getbysiduid(sid,uid);
			 
			 	if(courses.size()==0)
			 	return "NULL";
	    	
		 System.out.println(sid);
		 model.addAttribute("uid",uid);
		 model.addAttribute("sid",sid);
		 
		 net.javaguides.springboot.springsecurity.model.Content abc = new Content();
		 abc.setUser_id(uid);
		 abc.setSection_id(sid);
		 model.addAttribute("contents", abc);
		 
	        return "trainer/addcontent";
	    }	    
	    
	    
	    @PostMapping(path="trainer/addcontent")
	    public String registerUserAccount(@RequestParam(value = "file") MultipartFile file, HttpSession s,@ModelAttribute("contents") @Valid Content course,
		        BindingResult result) throws IllegalStateException, IOException  {
	    	
	    	Long uid=course.getUser_id();
	    	Long sid=course.getSection_id();
	    	  
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
			 
			 List<Section> courses = sectionservice.getbysiduid(sid,uid);
			 	if(courses.size()==0)
			 	return "NULL";
			 	
			 	
			 
		    	if(course.getContent_name().equals(""))
		    	{
		    		result.rejectValue("Content_name", null, "Enter the Course name");
		    	}
		    	else {
		        	
		        	int size=(content.findbycontentname(course.getContent_name(),id)) .size();
		        	
		    		if(size>0)
		    		result.rejectValue("Content_name", null, "content name was already present");
		        	
		        }
		        
		    	if(course.getCourse_name().equals(""))
		    	{
		    		result.rejectValue("Course_name", null, "Enter the Course name");
		    	}
		    	if(course.getDescription().equals(""))
		    	{
		    		result.rejectValue("Description", null, "Enter the Course name");
		    	}
		    	if(course.getUser_name().equals(""))
		    	{
		    		result.rejectValue("user_name", null, "Enter the Course name");
		    	}
		    	if(file.isEmpty())
		    	{
		    		result.rejectValue("vedio", null, "Enter the Course name");
		    	}
		    	if (result.hasErrors()) {
		    		return "trainer/addcontent";
		    	}
		    	
		    	
	        
	        File directory;
	        
			String check = File.separator; 
			String path = null;
			
			if (check.equalsIgnoreCase("\\")) {
				path = servletContext.getRealPath("").replace("build\\", ""); 
			}
			
			if (check.equalsIgnoreCase("/")) {
				path = servletContext.getRealPath("").replace("build/", "");
				path += "/"; // Adding trailing slash for Mac systems.
			}
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
				course.setVedio(localFile.getPath());
			} else {
				System.out.println("Failed to create directory!");
			}
				
				
				
	    	content.savecourse(course);
	    	
	        return "redirect:/trainer/cresult";
	    }
	
}


