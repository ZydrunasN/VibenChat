package lt.vibenchat.demo;

import lt.vibenchat.demo.dto.ChatMessageDto;
import lt.vibenchat.demo.dto.RoomDto;
import lt.vibenchat.demo.dto.UserDto;
import lt.vibenchat.demo.pojo.ChatMessage;
import lt.vibenchat.demo.pojo.Room;
import lt.vibenchat.demo.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getHashedPassword(),
                user.getSalt(),
                user.getRoom(),
                user.getChatMessageSet()
        );
    }

    public User toUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .hashedPassword(userDto.getHashedPassword())
                .salt(userDto.getSalt())
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
