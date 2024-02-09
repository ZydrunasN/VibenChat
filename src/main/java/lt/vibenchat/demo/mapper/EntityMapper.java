package lt.vibenchat.demo.mapper;

import lt.vibenchat.demo.dto.entityDto.ChatMessageDto;
import lt.vibenchat.demo.dto.entityDto.RoomDto;
import lt.vibenchat.demo.dto.entityDto.UserDto;
import lt.vibenchat.demo.pojo.ChatMessage;
import lt.vibenchat.demo.pojo.Room;
import lt.vibenchat.demo.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRoom(),
                user.getChatMessageSet(),
                user.getAuthorities()
        );
    }

    public User toUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .room(userDto.getRoom())
                .build();
    }

    public RoomDto toRoomDto(Room room) {
        return new RoomDto(
                room.getName(),
                room.getGenre(),
                room.getRoomUUID(),
                room.getUser(),
                room.getChatMessageSet()
        );
    }

    public Room toRoom(RoomDto roomDto) {
        return Room.builder()
                .name(roomDto.getName())
                .genre(roomDto.getGenre())
                .roomUUID(roomDto.getRoomUUID())
                .user(roomDto.getUser())
                .build();
    }

    public ChatMessageDto toChatMessageDto(ChatMessage chatMessage) {
        return new ChatMessageDto(
                chatMessage.getTime(),
                chatMessage.getMessageText(),
                chatMessage.getRoom(),
                chatMessage.getUser()
        );
    }

    public ChatMessage toChatMessage(ChatMessageDto chatMessageDto) {
        return ChatMessage.builder()
                .time(chatMessageDto.getTime())
                .messageText(chatMessageDto.getMessageText())
                .room(chatMessageDto.getRoom())
                .user(chatMessageDto.getUser())
                .build();
    }
}
