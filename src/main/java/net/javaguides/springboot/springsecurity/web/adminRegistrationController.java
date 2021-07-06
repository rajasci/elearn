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
@RequestMapping("admin/registration")
public class adminRegistrationController {

    @Autowired
    private EmployeeService userService;

    @ModelAttribute("admin")
    public EmployeeRegistrationDto userRegistrationDto() {
        return new EmployeeRegistrationDto();
    }
    
    @GetMapping
    public String showRegistrationForm(Model model) {
        return "admin/login";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("admin") @Valid EmployeeRegistrationDto userDto,
        BindingResult result) {

        Employee existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "admin/login";
        }

        userService.adminsave(userDto);
        return "redirect:/admin/registration?success";
    }
}