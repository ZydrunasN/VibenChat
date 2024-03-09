package lt.vibenchat.demo.repository;

import lt.vibenchat.demo.pojo.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
}
