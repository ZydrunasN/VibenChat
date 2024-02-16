package lt.vibenchat.demo.controller;

import jakarta.validation.Valid;
import lt.vibenchat.demo.dto.entityDto.UserDto;
import lt.vibenchat.demo.dto.inputValidateDto.user.LoginUserDto;
import lt.vibenchat.demo.dto.inputValidateDto.user.RegisterUserDto;
import lt.vibenchat.demo.service.RoomService;
import lt.vibenchat.demo.service.UsersRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    UsersRegistrationService usersRegistrationService;
    RoomService roomService;

    @Autowired
    public UserController(UsersRegistrationService usersRegistrationService,RoomService roomService) {
        this.usersRegistrationService = usersRegistrationService;
        this.roomService = roomService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerUserDto", RegisterUserDto.builder().build());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginUserDto", LoginUserDto.builder().build());
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid RegisterUserDto registerUserDto, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "/register";
        }
        try {
            usersRegistrationService.register(UserDto.builder()
                    .password(registerUserDto.getPassword())
                    .email(registerUserDto.getEmail())
                    .username(registerUserDto.getUsername())
                    .build());
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("USERNAME")) {
                errors.rejectValue("username", "Username is already used!");
            }
        }
        return "redirect:";
    }

    @PostMapping("/login")
    public String loginUser (@Valid LoginUserDto loginUserDto, BindingResult errors, Model model) {
        if(errors.hasErrors()) {
            return "login";
        }
        return "redirect:";
    }
}
