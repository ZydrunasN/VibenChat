package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @Column (name = "hashed_password")
    private String hashedPassword;
    private String salt;

    @OneToOne(mappedBy = "user")
    private Room room;

    @OneToMany(mappedBy = "user")
    Set<ChatMessage> chatMessageSet;
}
