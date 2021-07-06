package net.javaguides.springboot.springsecurity.web;
import net.javaguides.springboot.springsecurity.model.*;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class adminController {

	@GetMapping("/")
    public String root() {
        return "index";
    }
	 @GetMapping("/login")
	    public String login(Model model) {
	        return "admin/login";
	    }
	 
	   @ModelAttribute("admin")
	    public EmployeeRegistrationDto userRegistrationDto() {
	        return new EmployeeRegistrationDto();
	    }
	  @GetMapping("/index")
	    public String index(Model model) {
	        return "admin/index";
	    }

}
