package lt.vibenchat.demo.restAPI.service;

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
    private int start = 0;
    private double chunkNumber = 0;
    private InputStream inputStream;
    private int bytesRead;
    double bufferDuration;
    private final int BUFFER_SIZE = 1024 * 1024; //1MB
    private final byte[] buffer = new byte[BUFFER_SIZE];

    private static final String AUDIO_DIRECTORY = "src/main/resources/";
    private final String FILENAME = "ChalkOutlines.mp3";

    public HttpHeaders getAudioHeaders() throws IOException {
        setAudio();
        updateChunk();

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(bytesRead);
        headers.set("Buffer-Duration", Double.toString(bufferDuration));
        headers.set("Chunk-Number", Double.toString(chunkNumber));

        return headers;
    }

    public ByteArrayResource getAudioByteArrayResource() {
        return new ByteArrayResource(buffer);
    }

    private void setAudio() throws IOException {
        Path audioPath = Paths.get(AUDIO_DIRECTORY + FILENAME);
        Resource resource = new UrlResource(audioPath.toUri());
        inputStream = resource.getInputStream();
    }

    private void updateChunk() throws IOException {
        bufferDuration = (BUFFER_SIZE * 8.0) / (192 * 1024);
        chunkNumber++;

        inputStream.skip(start);
        bytesRead = inputStream.read(buffer, 0, BUFFER_SIZE);
        start += BUFFER_SIZE;
    }
}
