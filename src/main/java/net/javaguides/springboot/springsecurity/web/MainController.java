package net.javaguides.springboot.springsecurity.web;
import net.javaguides.springboot.springsecurity.model.*;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {

	
	 
	@GetMapping("/i")
    public String root(Model model) {
        return "i";
    }
	
	@ModelAttribute("employee")
    public EmployeeRegistrationDto userRegistrationDto() {
        return new EmployeeRegistrationDto();
    }
//	@GetMapping("/")
//    public String index1(Model model) {
//        return "i";
//    }
//	@GetMapping("")
//    public String rt() {
//        return "i";
//    }
	
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
 
    @GetMapping("/")
    public String index(Model model) {
        return "i";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    

}