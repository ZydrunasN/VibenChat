window.addEventListener('DOMContentLoaded', (event) => {
    let audioPlayer = document.getElementById("audioPlayer");
    let xhr = new XMLHttpRequest();
    let mediaSource = new MediaSource();
    let roomVariable = window.location.pathname.split("/")[2];

    audioPlayer.src = URL.createObjectURL(mediaSource);

    mediaSource.addEventListener('sourceopen', function() {
        let sourceBuffer = mediaSource.addSourceBuffer('audio/mpeg');
        fetchChunk(sourceBuffer);
    });

    audioPlayer.addEventListener('timeupdate', function() {
        // Check if audio playback is near the end
        let bufferDuration = xhr.getResponseHeader("Buffer-Duration");
        let chunkNumber = xhr.getResponseHeader("Chunk-Number");

        console.log(bufferDuration+" "+chunkNumber+" "+audioPlayer.currentTime)
        if (bufferDuration * chunkNumber - audioPlayer.currentTime < 5) { // Adjust buffer distance as needed
            let sourceBuffer = mediaSource.sourceBuffers[0]; // Get the source buffer
            fetchChunk(sourceBuffer); // Fetch next chunk
        }
    });

    function fetchChunk(sourceBuffer) {
        xhr.open("GET", '/stream/'+roomVariable, true);
        xhr.responseType = "arraybuffer";
        xhr.onload = function () {
            if (xhr.status === 200) { // Accept Partial Content status
                sourceBuffer.appendBuffer(xhr.response);
            }
        };
        xhr.send();
    }
});