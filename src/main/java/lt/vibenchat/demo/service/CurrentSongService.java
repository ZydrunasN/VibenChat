package lt.vibenchat.demo.service;

import lt.vibenchat.demo.dao.CurrentSongDao;
import lt.vibenchat.demo.dto.entityDto.CurrentSongDto;
import lt.vibenchat.demo.mapper.EntityMapper;
import lt.vibenchat.demo.pojo.CurrentSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentSongService {

    private final CurrentSongDao currentSongDao;
    private final EntityMapper mapper;

    @Autowired
    public CurrentSongService(CurrentSongDao currentSongDao, EntityMapper mapper) {
        this.currentSongDao = currentSongDao;
        this.mapper = mapper;
    }

    public void newCurrentSong(CurrentSongDto currentSongDto) {
        currentSongDao.save(mapper.toCurrentSong(currentSongDto));
    }

    public void updateCurrentSong(CurrentSong currentSong) {
        currentSongDao.save(currentSong);
    }

    public void deleteCurrentSongById(Long id) {
        currentSongDao.deleteByID(id);
    }

}
