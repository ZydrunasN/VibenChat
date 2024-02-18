package lt.vibenchat.demo.dto.entityDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lt.vibenchat.demo.pojo.Room;
import lt.vibenchat.demo.pojo.User;

@Data
@AllArgsConstructor
@Builder
public class CurrentSongDto {
    private Long id;
    private String name;
    private Long chunkNumber;
    private Long position;
    private User user;
    private Room room;
}
