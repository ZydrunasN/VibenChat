package lt.vibenchat.demo.service;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import lt.vibenchat.demo.dao.UserDao;
import lt.vibenchat.demo.mapper.EntityMapper;
import lt.vibenchat.demo.dao.RoomDao;
import lt.vibenchat.demo.dto.entityDto.RoomDto;
import lt.vibenchat.demo.pojo.Room;
import lt.vibenchat.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RoomService {
    private final RoomDao roomDao;
    private final UserDao userDao;
    private final EntityMapper mapper;

    @Autowired
    public RoomService(RoomDao roomDao, EntityMapper entityMapper, UserDao userDao) {
        this.roomDao = roomDao;
        this.mapper = entityMapper;
        this.userDao = userDao;
    }

    public void createRoom(RoomDto roomDto) {
        roomDao.save(mapper.toRoom(roomDto));
    }

    public void updateRoom(RoomDto roomDto) {
        roomDao.save(mapper.toRoom(roomDto));
    }

    public List<RoomDto> getListOfRooms() {
        return roomDao.getAll().stream().map(mapper::toRoomDto).collect(Collectors.toList());
    }

    public RoomDto getRoomByUUID(String roomUUID) {
        return mapper.toRoomDto(roomDao.getByUUID(roomUUID));
    }

    public Room getEntityRoomByUUID(String roomUUID) {
        return roomDao.getByUUID(roomUUID);
    }

    public void deleteRoomByUUID(String roomUUID) {
        roomDao.deleteByUUID(roomUUID);
    }

    public List<RoomDto> searchRoomsByNameAndGenre(String name, String genre) {
        var rooms = getListOfRooms();

        if (name.isEmpty() && !genre.equalsIgnoreCase("All")) {
             return rooms.stream()
                     .filter(room -> room.getGenre().equals(genre))
                     .collect(Collectors.toList());
        } else if (name.isEmpty() && genre.equalsIgnoreCase("All")) {
            return rooms;
        } else if (!name.isEmpty()){
            return rooms.stream()
                    .filter(room -> room.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        log.error("Room search failed!!");
        return rooms;
    }

    public void addUserAsMember(String roomUUID){
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var room = roomDao.getByUUID(roomUUID);

        if(user.getMemberRoom() == null || !user.getMemberRoom().getId().equals(room.getId())) {
            user.setMemberRoom(room);
            userDao.save(user);
            log.info("user ("+user.getUsername()+") added to room ("+room.getName()+") memberList");
        }
    }

    public void disconnectFromRoom(HttpSession session) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")) {
            disconnectAnonFromRoom(session);
            return;
        }

        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getMemberRoom() != null) user.setMemberRoom(null);

        if(session.getAttribute("bytesRead") != null) {
            session.removeAttribute("bytesRead");
        }

        userDao.save(user);
        log.info("user ("+user.getUsername()+") removed from room memberList");
    }

    private void disconnectAnonFromRoom(HttpSession session) {
        if(session.getAttribute("bytesRead") != null) {
            session.removeAttribute("bytesRead");
        }
    }

    public RoomDto getRoomById(Long id) {
        return mapper.toRoomDto(roomDao.getByID(id));
    }
}
