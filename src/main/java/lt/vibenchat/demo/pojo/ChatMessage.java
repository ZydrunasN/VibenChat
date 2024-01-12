package lt.vibenchat.demo.pojo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "user_id")
    private Long userId;
    private LocalDateTime time;
    @Column(name = "message_text")
    private String messageText;
}
