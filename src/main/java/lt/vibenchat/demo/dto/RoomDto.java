package lt.vibenchat.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lt.vibenchat.demo.pojo.ChatMessage;
import lt.vibenchat.demo.pojo.User;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class RoomDto {
    private String name;
    private String genre;
    private String roomUUID;
    private User user;
    private Set<ChatMessage> chatMessageSet;
}
