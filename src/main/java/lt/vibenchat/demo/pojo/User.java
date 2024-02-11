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

    @OneToMany(mappedBy = "user")
    Set<ChatMessage> chatMessageSet;

    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Authority> authorities;

    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Music> music;

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
