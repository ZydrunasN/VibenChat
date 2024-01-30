package lt.vibenchat.demo.dto.entityDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String hashedPassword;
    private String salt;
    private Room room;
    private Set<ChatMessage> chatMessageSet;
}
