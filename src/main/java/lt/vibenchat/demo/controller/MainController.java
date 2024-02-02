package lt.vibenchat.demo.controller;

import lt.vibenchat.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final RoomService service;

    @Autowired
    public MainController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public String home (Model model) {
        model.addAttribute("roomList",service.getListOfRooms());
        return "index";
    }

}
