package lt.vibenchat.demo.service;

import lombok.extern.log4j.Log4j2;
import lt.vibenchat.demo.dao.RoomDao;
import lt.vibenchat.demo.mapper.EntityMapper;
import lt.vibenchat.demo.dao.ChatMessageDao;
import lt.vibenchat.demo.dto.entityDto.ChatMessageDto;
import lt.vibenchat.demo.pojo.ChatMessage;
import lt.vibenchat.demo.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.Comparator.comparingLong;

@Service
@Log4j2
public class ChatMessageService {
    private final ChatMessageDao chatMessageDao;
    private final EntityMapper mapper;
    private final RoomDao roomDao;
    public ChatMessageService(ChatMessageDao chatMessageDao, EntityMapper entityMapper,
                              RoomDao roomDao) {
        this.chatMessageDao = chatMessageDao;
        this.mapper = entityMapper;
        this.roomDao = roomDao;
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

    public List<ChatMessageDto> getSortedListOfMessages(String roomUUID) {
        var messageList = roomDao.getByUUID(roomUUID).getChatMessageSet().stream()
                .map(mapper::toChatMessageDto).toList();
        var sorted = messageList.stream()
                .sorted(comparingLong(ChatMessageDto::getId).reversed())
                .collect(Collectors.toList());

        for (int x = 0; x < sorted.size(); x++) {
            var current = sorted.get(x);
            current.setPassedTime(Duration.between(current.getTime(),LocalDateTime.now()).toMinutes());
            sorted.set(x,current);
        }

        return sorted;
    }

    public void saveMessage(ChatMessageDto chatMessageDto, String roomId) {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var room = roomDao.getByUUID(roomId);

        var chatMessage = mapper.toChatMessage(chatMessageDto);

        chatMessage.setRoom(room);
        chatMessage.setUser(user);
        chatMessage.setTime(LocalDateTime.now());

        chatMessageDao.save(chatMessage);
    }
}
