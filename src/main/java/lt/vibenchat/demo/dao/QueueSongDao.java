package lt.vibenchat.demo.dao;

import lt.vibenchat.demo.pojo.QueueSong;
import lt.vibenchat.demo.repository.QueueSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueueSongDao implements CommonDaoActions<QueueSong>{

    private final QueueSongRepository repository;

    @Autowired
    public QueueSongDao(QueueSongRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(QueueSong queueSong) {
        repository.save(queueSong);
    }

    @Override
    public void update(QueueSong queueSong) {
        repository.save(queueSong);
    }

    @Override
    public List<QueueSong> getAll() {
        return repository.findAll();
    }

    @Override
    public QueueSong getByID(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void deleteByID(Long id) {
        repository.deleteById(id);
    }
}
