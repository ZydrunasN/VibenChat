package lt.vibenchat.demo.controller;

import lt.vibenchat.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private final RoomService roomService;

    @Autowired
    public MainController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String home (Model model) {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            roomService.disconnectFromRoom();
        }

        model.addAttribute("roomList",roomService.getListOfRooms());
        return "index";
    }

}
