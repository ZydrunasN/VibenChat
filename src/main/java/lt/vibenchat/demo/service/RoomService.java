package lt.vibenchat.demo.service;

import lt.vibenchat.demo.dao.RoomDao;
import lt.vibenchat.demo.pojo.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoomService {
    RoomDao roomDao;

    @Autowired
    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void createRoom(Room room) {
        roomDao.save(room);
    }

    public void updateRoom(Room room) {
        roomDao.save(room);
    }

    public List<Room> getListOfRooms() {
        return roomDao.getAll();
    }

    public Room getRoomByUUID(String roomId) {
        return roomDao.getByUUID(roomId);
    }

    public void deleteRoomByUUID(String roomId) {
        roomDao.deleteByUUID(roomId);
    }

    public List<Room> searchRoomsByNameAndGenre(String name, String genre) {
        List<Room> rooms = roomDao.getAll();

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

    public Room getRoomById(Long id) {
        return roomDao.getByID(id);
    }
}
