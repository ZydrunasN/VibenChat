package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_id")
    private UUID roomId;
    private String name;
    @Column(name = "admin_id")
    private Long adminId;
    @Column(name = "music_genre")
    private String genre;
}
