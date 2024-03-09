package lt.vibenchat.demo.dto.entityDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lt.vibenchat.demo.pojo.Room;
import lt.vibenchat.demo.pojo.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDto {
    private Long id;
    private LocalDateTime time;
    private Long passedTime;
    private String messageText;
    private Room room;
    private User user;
}
