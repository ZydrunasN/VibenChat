package lt.vibenchat.demo.restAPI.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import lt.vibenchat.demo.restAPI.service.AudioStreamingService;
import lt.vibenchat.demo.service.QueueSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/stream/{roomId}")
    public ResponseEntity<ByteArrayResource> streamAudio(@PathVariable String roomId,HttpSession session) {
        try {
            final Long attributeBytesRead = (Long) session.getAttribute("bytesRead");
            final Long bytesRead = streamingService.calculateStartOfStream(attributeBytesRead,roomId);

            //ADD SONGS MOCK METHOD
            if(!streamingService.isThereAnySong(roomId)) {
                streamingService.addMockSongs();
                log.info("No songs left to stream in room "+roomId+", users entering waiting state...");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            var headers = streamingService.getAudioHeaders(roomId,bytesRead);
            var byteArrayResource = streamingService.getAudioByteArrayResource();
            var totalBufferedByteCount = streamingService.getBytesRead();

            log.info("buffer of size: "+(totalBufferedByteCount-bytesRead)+" bytes was sent");
            session.setAttribute("bytesRead",totalBufferedByteCount);
            return  new ResponseEntity<>(byteArrayResource,headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error("Streaming failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
