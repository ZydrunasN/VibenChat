package lt.vibenchat.demo.service;

import lombok.extern.log4j.Log4j2;
import lt.vibenchat.demo.Mapper;
import lt.vibenchat.demo.dao.ChatMessageDao;
import lt.vibenchat.demo.dto.ChatMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ChatMessageService {
    private final ChatMessageDao chatMessageDao;
    private final Mapper mapper;

    public ChatMessageService(ChatMessageDao chatMessageDao, Mapper mapper) {
        this.chatMessageDao = chatMessageDao;
        this.mapper = mapper;
    }

    public void createMessage(ChatMessageDto chatMessageDto) {
        chatMessageDao.save(mapper.toChatMessage(chatMessageDto));
    }

    public void updateMessage(ChatMessageDto chatMessageDto) {
        chatMessageDao.save(mapper.toChatMessage(chatMessageDto));
    }

    public void deleteMessageById(Long id) {
        if (id == null) log.debug("ChatMessageService.deleteMessageById: id is null");
        else chatMessageDao.deleteByID(id);
    }

    public ChatMessageDto getMessageById(Long id) {
        if (id == null) {
            log.debug("ChatMessageService.getMessageById: id is null");
            return new ChatMessageDto();
        }
        return mapper.toChatMessageDto(chatMessageDao.getByID(id));
    }

    public List<ChatMessageDto> getAllMessages() {
        return chatMessageDao.getAll().stream()
                .map(mapper::toChatMessageDto)
                .collect(Collectors.toList());
    }
}
