package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    private String name;
    @Column(name = "music_genre")
    private String genre;
    @Column(name = "room_id")
    private String roomUUID;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User user;

    @OneToMany(mappedBy = "room")
    private Set<ChatMessage> chatMessageSet = new HashSet<>();

    @OneToMany(mappedBy = "memberRoom")
    private Set<User> members = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private Set<QueueSong> queueSongs;

    @OneToOne(mappedBy = "room")
    private CurrentSong currentSong;
}
