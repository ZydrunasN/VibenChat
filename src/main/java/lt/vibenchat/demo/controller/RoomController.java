package lt.vibenchat.demo.controller;

import jakarta.validation.Valid;
import lt.vibenchat.demo.dto.entityDto.ChatMessageDto;
import lt.vibenchat.demo.dto.entityDto.RoomDto;
import lt.vibenchat.demo.dto.inputValidateDto.chat.SendChatMessageDto;
import lt.vibenchat.demo.service.ChatMessageService;
import lt.vibenchat.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomController {
    private final RoomService roomService;
    private final ChatMessageService chatMessageService;

    @Autowired
    public RoomController(RoomService roomService, ChatMessageService chatMessageService) {
        this.roomService = roomService;
        this.chatMessageService = chatMessageService;
    }

    @PostMapping("/search-room")
    public String searchRoom(Model model, @RequestParam String genre, @RequestParam(name = "room-name") String name) {
        List<RoomDto> rooms =  roomService.searchRoomsByNameAndGenre(name,genre);
        model.addAttribute("roomList",rooms);
        return "index";
    }

    @GetMapping("/room/{roomId}/")
    public String room(Model model, @PathVariable String roomId) {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            roomService.addUserAsMember(roomId);
        }

        model.addAttribute("sendChatMessageDto", SendChatMessageDto.builder().build());
        model.addAttribute("chatMessageList",chatMessageService.getSortedListOfMessages(roomId));
        model.addAttribute("roomMemberList",roomService.getRoomByUUID(roomId).getMembers());

        return "room";
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/room/{roomId}/chat")
    public String sendMessage(@Valid SendChatMessageDto sendChatMessageDto,
                              BindingResult errors, Model model, @PathVariable String roomId){
        if(errors.hasErrors()) {
            return "room";
        }
        var chatMessageDto = new ChatMessageDto();
        chatMessageDto.setMessageText(sendChatMessageDto.getMessageText());
        chatMessageService.saveMessage(chatMessageDto, roomId);

        return "redirect:/room/{roomId}/";
    }
}
