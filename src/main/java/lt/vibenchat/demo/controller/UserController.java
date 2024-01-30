package lt.vibenchat.demo.controller;

import jakarta.validation.Valid;
import lt.vibenchat.demo.dto.inputValidateDto.RegisterUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerUserDto", RegisterUserDto.builder().build());
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid RegisterUserDto registerUserDto, BindingResult errors, Model model) {
        if(errors.hasErrors()) {
            return "register";
        }
        return "index";
    }

    @PostMapping("/login")
    public String loginUser() {
        return "index";
    }
}
