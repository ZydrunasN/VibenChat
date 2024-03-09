package lt.vibenchat.demo.repository;

import lt.vibenchat.demo.pojo.QueueSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueSongRepository extends JpaRepository<QueueSong,Long> {
}
