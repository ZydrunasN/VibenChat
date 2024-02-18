package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "current_song")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CurrentSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "chunk_number")
    private Long chunkNumber;
    private Long position;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
