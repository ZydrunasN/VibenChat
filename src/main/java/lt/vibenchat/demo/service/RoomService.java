package lt.vibenchat.demo.service;

import lombok.extern.log4j.Log4j2;
import lt.vibenchat.demo.Mapper;
import lt.vibenchat.demo.dao.RoomDao;
import lt.vibenchat.demo.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Log4j2
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
        List<RoomDto> rooms = roomDao.getAll().stream()
                .map(mapper::toRoomDto)
                .collect(Collectors.toList());

        if (name.isEmpty() && !genre.equalsIgnoreCase("All")) {
            System.out.println("veikia");
             return rooms.stream()
                     .filter(room -> room.getGenre().equals(genre))
                     .collect(Collectors.toList());
        } else if (name.isEmpty() && genre.equalsIgnoreCase("All")) {
            return rooms;
        } else if (!name.isEmpty()){
            return rooms.stream()
                    .filter(room -> room.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        } else {
            log.error("Room search failed!!");
            return new ArrayList<>();
        }
    }

    public RoomDto getRoomById(Long id) {
        return mapper.toRoomDto(roomDao.getByID(id));
    }
}
