package lt.vibenchat.demo.restAPI.service;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import lt.vibenchat.demo.dto.entityDto.RoomDto;
import lt.vibenchat.demo.pojo.CurrentSong;
import lt.vibenchat.demo.pojo.QueueSong;
import lt.vibenchat.demo.service.CurrentSongService;
import lt.vibenchat.demo.service.QueueSongService;
import lt.vibenchat.demo.service.RoomService;
import lt.vibenchat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;

@Service
@Log4j2
public class AudioStreamingService {
    @Getter
    private Long bytesRead;
    private static final int BUFFER_SIZE = 1024 * 1024; //1MB
    private static final long BITRATE = 192;
    private static final String AUDIO_DIRECTORY = "src/main/resources/static/audio/";
    private final byte[] buffer = new byte[BUFFER_SIZE];

    private final RoomService roomService;
    private final CurrentSongService currentSongService;
    private final UserService userService;
    private final QueueSongService queueSongService;

    @Autowired
    public AudioStreamingService(RoomService roomService, CurrentSongService currentSongService,
                                 UserService userService, QueueSongService queueSongService) {
        this.roomService = roomService;
        this.currentSongService = currentSongService;
        this.userService = userService;
        this.queueSongService = queueSongService;
    }

    @Transactional
    public HttpHeaders getAudioHeaders(String roomId, Long bytesReadTotal) throws IOException {
        var roomDto = roomService.getRoomByUUID(roomId);
        var currentSong =  getCurrentSong(roomDto);
        var inputStream = getInputStream(currentSong);
        var headers = new HttpHeaders();
        bytesRead = bytesReadTotal;

        final var currentSongTime = currentSong.getTime();
        final var timePassedSeconds = Duration.between(currentSongTime, LocalDateTime.now()).toSeconds();
        //IF FULL CURRENT SONG WAS SENT, REMOVE AND GET NEXT ONE FROM QUEUE
        if(timePassedSeconds >= audioFileLengthSeconds(currentSong)) {
            removeCurrentSong(roomDto);
            addSongFromQueueToCurrent(roomDto);
            bytesRead = 0L;
            headers.set("Restart","true");

            log.info("Song reached its end point, instructing client to restart");
            return headers;
        }

        inputStream.skip(bytesRead);
        long bytesReadBuffer = inputStream.read(buffer, 0, BUFFER_SIZE);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(bytesReadBuffer);
        headers.set("Restart","false");

        bytesRead += bytesReadBuffer;
        return headers;
    }

    public ByteArrayResource getAudioByteArrayResource() {
        return new ByteArrayResource(buffer);
    }

    public boolean isThereAnySong(String roomId) throws IOException {
        var roomDto = roomService.getRoomByUUID(roomId);

        if(roomDto.getCurrentSong() != null && roomDto.getQueueSongs().isEmpty()) {
            final var timePassedSeconds = Duration.between(roomDto.getCurrentSong().getTime(),
                    LocalDateTime.now()).toSeconds();

            if(timePassedSeconds >= audioFileLengthSeconds(roomDto.getCurrentSong())) {
                removeCurrentSong(roomDto);
                return false;
            }
        }

        return roomDto.getCurrentSong() != null || !roomDto.getQueueSongs().isEmpty();
    }

    @Transactional
    public CurrentSong getCurrentSong(RoomDto roomDto) {
        if(roomDto.getCurrentSong() == null) {
            return addSongFromQueueToCurrent(roomDto);
        }
        return roomDto.getCurrentSong();
    }

    private InputStream getInputStream(CurrentSong currentSong) throws IOException {
        final String filename = currentSong.getName();

        Path audioPath = Path.of(AUDIO_DIRECTORY + filename);
        Resource resource = new UrlResource(audioPath.toUri());
        return resource.getInputStream();
    }

    @Transactional
    public CurrentSong addSongFromQueueToCurrent(RoomDto roomDto) {
        QueueSong songToBePlayed = roomDto.getQueueSongs().stream()
                .min(Comparator.comparing(QueueSong::getId))
                .orElse(null); //TAKE FIRST SONG

        //CHECK IF SONG EXISTS
        if(songToBePlayed == null) {
            log.warn("Can't add song From Queue to Current Song in room "+roomDto.getId()+"(id)");
            return new CurrentSong();
        }

        //MAP IT TO CURRENT SONG
        CurrentSong currentSong = CurrentSong.builder()
                .time(LocalDateTime.now())
                .user(songToBePlayed.getUser())
                .room(songToBePlayed.getRoom())
                .name(songToBePlayed.getName())
                .build();

        log.info("Adding song from queue to current song in room "+roomDto.getId()+"(id)");
        //REMOVE FROM QUEUE AND ADD TO CURRENT
        currentSongService.newCurrentSong(currentSong);
        queueSongService.deleteSongFromQueueById(songToBePlayed.getId());
        return currentSong;
    }

    public void removeCurrentSong(RoomDto roomDto) {
        currentSongService.deleteCurrentSongById(roomDto.getCurrentSong().getId());
        roomDto.setCurrentSong(null);
    }

    public void addMockSongs() {
        QueueSong queueSong = QueueSong.builder()
                .name("ChalkOutlines.mp3")
                .time(LocalDateTime.now())
                .user(userService.getUserById(1L))
                .room(roomService.getEntityRoomByUUID("asdasw2dasd65asd5sad4sad4"))
                .build();

        QueueSong queueSong2 = QueueSong.builder()
                .name("Loco.mp3")
                .time(LocalDateTime.now())
                .user(userService.getUserById(6L))
                .room(roomService.getEntityRoomByUUID("asdasw2dasd65asd5sad4sad4"))
                .build();

        queueSongService.addSongToQueue(queueSong);
        queueSongService.addSongToQueue(queueSong2);
    }


    public Long calculateStartOfStream(final Long bytesRead, final String roomId,final boolean isFirstChunk) throws IOException {
        final var roomDto = roomService.getRoomByUUID(roomId);
        final var currentSong = roomDto.getCurrentSong();

        if (currentSong == null) return 0L;

        final var currentSongTime = currentSong.getTime();
        final var timePassedSeconds = Duration.between(currentSongTime, LocalDateTime.now()).toSeconds();
        long fileSecondsLength = audioFileLengthSeconds(currentSong);

        if(isFirstChunk && timePassedSeconds > 10 && timePassedSeconds < fileSecondsLength) {
            return (timePassedSeconds * BITRATE * 1024) / 8L;
        } else if(isFirstChunk && timePassedSeconds < 10) {
            return 0L;
        }

        return bytesRead;
    }

    private Long audioFileLengthSeconds(CurrentSong currentSong) throws IOException{
        var filename = currentSong.getName();
        Path audioPath = Path.of(AUDIO_DIRECTORY + filename);
        Resource resource = new UrlResource(audioPath.toUri());
        long fileByteLength = resource.contentLength();

        return (fileByteLength*8L)/(BITRATE*1024);
    }
}
