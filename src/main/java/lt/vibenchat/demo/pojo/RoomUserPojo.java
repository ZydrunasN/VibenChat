package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user_music")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoomUserPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "music_id")
    private Long musicID;
}
