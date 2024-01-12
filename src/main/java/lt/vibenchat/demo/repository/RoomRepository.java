package lt.vibenchat.demo.repository;

import lt.vibenchat.demo.pojo.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository  extends JpaRepository<Room,Long>{
    Room getByRoomId(UUID id);
    void deleteByRoomId(UUID id);
}
