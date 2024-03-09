package lt.vibenchat.demo.dao;

import lt.vibenchat.demo.pojo.CurrentSong;
import lt.vibenchat.demo.repository.CurrentSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CurrentSongDao implements CommonDaoActions<CurrentSong>{

    private final CurrentSongRepository repository;

    @Autowired
    public CurrentSongDao(CurrentSongRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(CurrentSong currentSong) {
        repository.save(currentSong);
    }

    @Override
    public void update(CurrentSong currentSong) {
        repository.save(currentSong);
    }

    @Override
    public List<CurrentSong> getAll() {
        return repository.findAll();
    }

    @Override
    public CurrentSong getByID(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void deleteByID(Long id) {
        repository.deleteById(id);
    }
}
