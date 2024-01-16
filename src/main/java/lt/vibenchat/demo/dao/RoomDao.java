package lt.vibenchat.demo.dao;

import lt.vibenchat.demo.pojo.Room;
import lt.vibenchat.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class RoomDao implements CommonDaoActions<Room> {

    private final RoomRepository repository;

    @Autowired
    public RoomDao(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Room room) {
        room.setRoomId(UUID.randomUUID().toString());
        repository.save(room);
    }

    @Override
    public void update(Room room) {
        repository.save(room);
    }

    @Override
    public List<Room> getAll() {
        return repository.findAll();
    }

    @Override
    public Room getByID(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void deleteByID(Long id) {
        repository.deleteById(id);
    }

    public Room getByUUID(String roomId) {
        return repository.getByRoomId(roomId);
    }

    public void deleteByUUID(String roomId) {
        repository.deleteByRoomId(roomId);
    }
}
