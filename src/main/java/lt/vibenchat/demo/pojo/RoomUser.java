package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "room_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "room_id")
    private Long roomId;
}
