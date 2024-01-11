package lt.vibenchat.demo.service;

import lt.vibenchat.demo.dao.RoomDao;
import lt.vibenchat.demo.pojo.Room;

import java.util.List;
import java.util.UUID;

public class RoomService {
    RoomDao roomDao;

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

    public Room getRoomByUUID(UUID roomId) {
        return roomDao.getByUUID(roomId);
    }

    public void deleteRoomByUUID(UUID roomId) {
        roomDao.deleteByUUID(roomId);
    }
}
