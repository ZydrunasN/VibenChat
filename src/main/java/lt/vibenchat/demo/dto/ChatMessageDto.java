package lt.vibenchat.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lt.vibenchat.demo.pojo.Room;
import lt.vibenchat.demo.pojo.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ChatMessageDto {
    private LocalDateTime time;
    private String messageText;
    private Room room;
    private User user;
}
