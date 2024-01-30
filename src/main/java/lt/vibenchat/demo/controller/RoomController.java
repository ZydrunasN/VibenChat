package lt.vibenchat.demo.controller;

import lt.vibenchat.demo.dto.entityDto.RoomDto;
import lt.vibenchat.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoomController {
        private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping("/search-room")
    public String SearchRoom(Model model, @RequestParam String genre, @RequestParam(name = "room-name") String name) {
        List<RoomDto> rooms =  service.searchRoomsByNameAndGenre(name,genre);
        model.addAttribute("roomList",rooms);
        return "index";
    }

    @GetMapping("/room/{roomId}/")
    public String Room(Model model, @PathVariable String roomId) {

        return "room";
    }
}
