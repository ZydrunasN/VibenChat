package lt.vibenchat.demo.dto.entityDto;

import lombok.*;
import lt.vibenchat.demo.pojo.Authority;
import lt.vibenchat.demo.pojo.ChatMessage;
import lt.vibenchat.demo.pojo.Room;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Room room;
    private Set<ChatMessage> chatMessageSet;
    private Set<Authority> authorities;
}
