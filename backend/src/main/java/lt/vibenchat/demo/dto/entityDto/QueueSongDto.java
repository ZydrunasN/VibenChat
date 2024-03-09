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
public class QueueSongDto {
    private Long id;
    private String name;
    private LocalDateTime time;
    private User user;
    private Room room;
}
