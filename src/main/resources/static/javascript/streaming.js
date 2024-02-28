window.addEventListener('DOMContentLoaded', (event) => {
    let audioPlayer = document.getElementById("audioPlayer");
    let xhr = new XMLHttpRequest();
    let mediaSource = new MediaSource();
    let roomVariable = window.location.pathname.split("/")[2];
    let sourceBuffer;

    audioPlayer.src = URL.createObjectURL(mediaSource);

    mediaSource.addEventListener('sourceopen', function() {
        sourceBuffer = mediaSource.addSourceBuffer('audio/mpeg');

        fetchChunk(sourceBuffer);
        audioPlayer.play();
    });

    audioPlayer.addEventListener('timeupdate', function() {
        if (sourceBuffer.timestampOffset.toString() - audioPlayer.currentTime < 1) {
            fetchChunk(sourceBuffer);
        }
    });

    function fetchChunk(sourceBuffer) {
        xhr.open("GET", '/stream/'+roomVariable, true);
        xhr.responseType = "arraybuffer";
        xhr.onload = function () {
            if (xhr.status === 200) {
                let restart = xhr.getResponseHeader("Restart");

                if(restart === "true") {
                    restartPlayer();
                    fetchChunk(sourceBuffer);
                } else {
                    sourceBuffer.appendBuffer(xhr.response);
                }

            } else if (xhr.status === 204) {
                if(sourceBuffer.buffered > 0) restartPlayer();
                let delayTime = 10000//10 Seconds In MilliSeconds

                setTimeout(function() {
                    fetchChunk(sourceBuffer)
                    }, delayTime);
            }
        };
        xhr.send();
    }

    function restartPlayer() {
        if(mediaSource.sourceBuffers[0].buffered > 0) {
            mediaSource.sourceBuffers[0].remove(0, Number.POSITIVE_INFINITY);
            audioPlayer.pause();
            audioPlayer.removeAttribute("src")
            audioPlayer.load();
            audioPlayer.src = URL.createObjectURL(mediaSource);
        }
    }
});