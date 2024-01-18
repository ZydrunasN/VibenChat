package lt.vibenchat.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lt.vibenchat.demo.pojo.User;

@Data
@AllArgsConstructor
public class RoomDto {
    private String name;
    private String genre;
    private String roomUUID;
    private User user;
}
