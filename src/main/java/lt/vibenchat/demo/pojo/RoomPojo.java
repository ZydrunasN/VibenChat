package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RoomPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "admin_id")
    private Long adminId;
    @Column(name = "music_genre")
    private String genre;
}
