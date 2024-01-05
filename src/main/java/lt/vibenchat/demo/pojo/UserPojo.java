package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @Column (name = "hashed_password")
    private String hashedPassword;
    private String salt;
}
