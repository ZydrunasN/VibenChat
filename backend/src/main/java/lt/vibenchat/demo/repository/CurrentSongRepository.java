package lt.vibenchat.demo.repository;

import lt.vibenchat.demo.pojo.CurrentSong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentSongRepository extends JpaRepository<CurrentSong,Long> {
}
