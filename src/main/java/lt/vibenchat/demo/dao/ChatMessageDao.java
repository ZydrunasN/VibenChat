package lt.vibenchat.demo.dao;

import lt.vibenchat.demo.pojo.ChatMessage;
import lt.vibenchat.demo.repository.ChatMessageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatMessageDao implements CommonDaoActions<ChatMessage> {

    private final ChatMessageRepository repository;

    public ChatMessageDao(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(ChatMessage chatMessage) {
        repository.save(chatMessage);
    }

    @Override
    public void update(ChatMessage chatMessage) {
        repository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getAll() {
        return repository.findAll();
    }

    @Override
    public ChatMessage getByID(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void deleteByID(Long id) {
        repository.deleteById(id);
    }
}
