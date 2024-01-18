package lt.vibenchat.demo.service;

import lt.vibenchat.demo.Mapper;
import lt.vibenchat.demo.dao.RoomDao;
import lt.vibenchat.demo.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomDao roomDao;
    private final Mapper mapper;

    @Autowired
    public RoomService(RoomDao roomDao, Mapper mapper) {
        this.roomDao = roomDao;
        this.mapper = mapper;
    }

    public void createRoom(RoomDto roomDto) {
        roomDao.save(mapper.toRoom(roomDto));
    }

    public void updateRoom(RoomDto roomDto) {
        roomDao.save(mapper.toRoom(roomDto));
    }

    public List<RoomDto> getListOfRooms() {
        return roomDao.getAll().stream().map(mapper::toRoomDto).toList();
    }

    public RoomDto getRoomByUUID(String roomUUID) {
        return mapper.toRoomDto(roomDao.getByUUID(roomUUID));
    }

    public void deleteRoomByUUID(String roomUUID) {
        roomDao.deleteByUUID(roomUUID);
    }

    public List<RoomDto> searchRoomsByNameAndGenre(String name, String genre) {
        List<RoomDto> rooms = roomDao.getAll().stream().map(mapper::toRoomDto).toList();

        if (name == null) {
             return rooms.stream()
                     .filter(room -> room.getGenre().equals(genre))
                     .collect(Collectors.toList());
        } else {
            return rooms.stream()
                    .filter(room -> room.getName().contains(name))
                    .filter(room -> room.getGenre().equals(genre))
                    .collect(Collectors.toList());
        }
    }

    public RoomDto getRoomById(Long id) {
        return mapper.toRoomDto(roomDao.getByID(id));
    }
}
