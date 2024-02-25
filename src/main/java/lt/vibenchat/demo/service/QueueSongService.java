package lt.vibenchat.demo.service;

import lt.vibenchat.demo.dao.QueueSongDao;
import lt.vibenchat.demo.dto.entityDto.QueueSongDto;
import lt.vibenchat.demo.mapper.EntityMapper;
import lt.vibenchat.demo.pojo.QueueSong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueSongService {
    private final QueueSongDao queueSongDao;
    private final EntityMapper mapper;

    @Autowired
    public QueueSongService(QueueSongDao queueSongDao, EntityMapper mapper) {
        this.queueSongDao = queueSongDao;
        this.mapper = mapper;
    }

    public void addSongToQueue(QueueSongDto queueSongDto) {
        queueSongDao.save(mapper.toQueueSong(queueSongDto));
    }

    public void addSongToQueue(QueueSong queueSong) {
        queueSongDao.save(queueSong);
    }

    public void updateSongInQueue(QueueSong queueSong) {
        queueSongDao.save(queueSong);
    }

    public void deleteSongFromQueueById(Long id) {
        queueSongDao.deleteByID(id);
    }
}
