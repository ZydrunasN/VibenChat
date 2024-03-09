package lt.vibenchat.demo.repository;

import lt.vibenchat.demo.pojo.Room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository  extends JpaRepository<Room,Long>{
    Room getByRoomUUID(String id);
    void deleteByRoomUUID(String id);
}
