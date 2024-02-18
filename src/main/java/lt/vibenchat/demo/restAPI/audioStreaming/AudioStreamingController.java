package lt.vibenchat.demo.restAPI.audioStreaming;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import lt.vibenchat.demo.restAPI.service.AudioStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Log4j2
public class AudioStreamingController {

    AudioStreamingService streamingService;

    @Autowired
    public AudioStreamingController(AudioStreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping("/stream")
    public ResponseEntity<ByteArrayResource> streamAudio(HttpServletResponse response) {
        try {
            var headers = streamingService.getAudioHeaders();
            var byteArrayResource = streamingService.getAudioByteArrayResource();

            return  new ResponseEntity<>(byteArrayResource,headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Streaming failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
