package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    @Column(name = "message_text")
    private String messageText;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="room_id")
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
}
