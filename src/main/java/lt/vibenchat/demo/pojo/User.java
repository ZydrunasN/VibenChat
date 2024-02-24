package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user")
    private Room room;

    @OneToOne(mappedBy = "user")
    private CurrentSong currentSong;

    @OneToMany(mappedBy = "user")
    private Set<ChatMessage> chatMessageSet;

    @OneToMany(mappedBy = "user")
    private Set<QueueSong> queue;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_room_id")
    private Room memberRoom;

    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Authority> authorities;


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
