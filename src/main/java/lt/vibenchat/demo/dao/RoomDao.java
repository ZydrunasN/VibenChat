package lt.vibenchat.demo.dao;

import lt.vibenchat.demo.pojo.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RoomDao {

    RoomRepository repository;

    @Autowired
    public RoomDao(RoomRepository repository) {
        this.repository = repository;
    }

    public void save(Room room) {
        room.setRoomId(UUID.randomUUID());
        repository.save(room);
    }

    public void update(Room room) {
        repository.save(room);
    }

    public List<Room> getAll() {
        return repository.findAll();
    }

    public Room getByUUID(UUID roomId) {
        return repository.getByRoomId(roomId);
    }

    public void deleteByUUID(UUID roomId) {
        repository.deleteByRoomId(roomId);
    }
}
