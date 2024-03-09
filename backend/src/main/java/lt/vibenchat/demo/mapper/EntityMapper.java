package lt.vibenchat.demo.mapper;

import lt.vibenchat.demo.dto.entityDto.*;
import lt.vibenchat.demo.pojo.*;
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
                room.getId(),
                room.getName(),
                room.getGenre(),
                room.getRoomUUID(),
                room.getUser(),
                room.getChatMessageSet(),
                room.getMembers(),
                room.getQueueSongs(),
                room.getCurrentSong()
        );
    }

    public Room toRoom(RoomDto roomDto) {
        return Room.builder()
                .name(roomDto.getName())
                .genre(roomDto.getGenre())
                .roomUUID(roomDto.getRoomUUID())
                .user(roomDto.getUser())
                .chatMessageSet(roomDto.getChatMessageSet())
                .members(roomDto.getMembers())
                .currentSong(roomDto.getCurrentSong())
                .queueSongs(roomDto.getQueueSongs())
                .build();
    }

    public ChatMessageDto toChatMessageDto(ChatMessage chatMessage) {
        return ChatMessageDto.builder()
                .messageText(chatMessage.getMessageText())
                .id(chatMessage.getId())
                .time(chatMessage.getTime())
                .room(chatMessage.getRoom())
                .user(chatMessage.getUser())
                .build();
    }

    public ChatMessage toChatMessage(ChatMessageDto chatMessageDto) {
        return ChatMessage.builder()
                .time(chatMessageDto.getTime())
                .messageText(chatMessageDto.getMessageText())
                .room(chatMessageDto.getRoom())
                .user(chatMessageDto.getUser())
                .build();
    }

    public CurrentSongDto toCurrentSongDto(CurrentSong currentSong) {
        return CurrentSongDto.builder()
                .name(currentSong.getName())
                .room(currentSong.getRoom())
                .user(currentSong.getUser())
                .time(currentSong.getTime())
                .build();
    }

    public CurrentSong toCurrentSong(CurrentSongDto currentSongDto) {
        return CurrentSong.builder()
                .name(currentSongDto.getName())
                .room(currentSongDto.getRoom())
                .user(currentSongDto.getUser())
                .time(currentSongDto.getTime())
                .build();
    }

    public QueueSongDto toQueueSongDto(QueueSong queueSong) {
        return QueueSongDto.builder()
                .name(queueSong.getName())
                .room(queueSong.getRoom())
                .user(queueSong.getUser())
                .time(queueSong.getTime())
                .build();
    }

    public QueueSong toQueueSong(QueueSongDto queueSongDto) {
        return QueueSong.builder()
                .name(queueSongDto.getName())
                .room(queueSongDto.getRoom())
                .user(queueSongDto.getUser())
                .time(queueSongDto.getTime())
                .build();
    }
}
