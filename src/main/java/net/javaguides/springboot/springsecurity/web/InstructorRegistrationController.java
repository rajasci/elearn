package net.javaguides.springboot.springsecurity.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguides.springboot.springsecurity.model.Employee;
import net.javaguides.springboot.springsecurity.service.EmployeeService;
import net.javaguides.springboot.springsecurity.web.dto.EmployeeRegistrationDto;

@Controller
@RequestMapping("trainer/registration")
public class InstructorRegistrationController {

    @Autowired
    private EmployeeService userService;

    @ModelAttribute("employee")
    public EmployeeRegistrationDto userRegistrationDto() {
        return new EmployeeRegistrationDto();
    }
    
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "trainer/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("employee") @Valid EmployeeRegistrationDto userDto,
        BindingResult result) {
    	
    	System.out.println("yes");
        Employee existing = userService.findByEmail(userDto.getEmail());
        
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "trainer/login";
        }

        userService.teachersave(userDto);
        return "redirect:/trainer/login?success";
    }
}