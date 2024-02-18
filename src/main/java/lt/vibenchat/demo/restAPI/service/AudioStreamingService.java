package lt.vibenchat.demo.restAPI.service;

import lt.vibenchat.demo.pojo.CurrentSong;
import lt.vibenchat.demo.service.CurrentSongService;
import lt.vibenchat.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AudioStreamingService {
    private Long start;
    private Long chunkNumber;
    private InputStream inputStream;
    private int bytesRead;
    double bufferDuration;
    private final int BUFFER_SIZE = 1024 * 1024; //1MB
    private final byte[] buffer = new byte[BUFFER_SIZE];

    private static final String AUDIO_DIRECTORY = "src/main/resources/";
    private  String filename;

    private final RoomService roomService;
    private final CurrentSongService currentSongService;

    @Autowired
    public AudioStreamingService(RoomService roomService, CurrentSongService currentSongService) {
        this.roomService = roomService;
        this.currentSongService = currentSongService;
    }

    public HttpHeaders getAudioHeaders(String roomId) throws IOException {
        var room = roomService.getRoomByUUID(roomId);
        var currentSong = room.getCurrentSong();

        setAudio(currentSong);
        updateChunk();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(bytesRead);
        headers.set("Buffer-Duration", Double.toString(bufferDuration));
        headers.set("Chunk-Number", Double.toString(chunkNumber));

        currentSong.setChunkNumber(currentSong.getChunkNumber()+1);
        currentSong.setPosition(start+BUFFER_SIZE);
        currentSongService.updateCurrentSong(currentSong);

        return headers;
    }

    public ByteArrayResource getAudioByteArrayResource() {
        return new ByteArrayResource(buffer);
    }

    private void setAudio(CurrentSong currentSong) throws IOException {
        start = currentSong.getPosition();
        chunkNumber = currentSong.getChunkNumber();
        filename = currentSong.getName();

        Path audioPath = Paths.get(AUDIO_DIRECTORY + filename);
        Resource resource = new UrlResource(audioPath.toUri());
        inputStream = resource.getInputStream();
    }

    private void updateChunk() throws IOException {
        bufferDuration = (BUFFER_SIZE * 8.0) / (192 * 1024);

        inputStream.skip(start);
        bytesRead = inputStream.read(buffer, 0, BUFFER_SIZE);
    }
}
