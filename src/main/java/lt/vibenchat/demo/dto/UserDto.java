package lt.vibenchat.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lt.vibenchat.demo.pojo.Room;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String email;
    private String hashedPassword;
    private String salt;
    private Room room;
}
