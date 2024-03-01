window.addEventListener('DOMContentLoaded', (event) => {
    let audioPlayer = document.getElementById("audioPlayer");
    let xhr = new XMLHttpRequest();
    let mediaSource = new MediaSource();
    let roomVariable = window.location.pathname.split("/")[2];
    let sourceBuffer;

    const DELAY_TIME = 10000;

    audioPlayer.src = URL.createObjectURL(mediaSource);
    audioPlayer.volume = 0.05;

    mediaSource.addEventListener('sourceopen', function() {
        sourceBuffer = mediaSource.addSourceBuffer('audio/mpeg');
        fetchChunk(true);
        audioPlayer.play();
    });

    audioPlayer.addEventListener('timeupdate', function() {
        if (sourceBuffer.timestampOffset.toString() - audioPlayer.currentTime < 1) {
            fetchChunk(false);
        }
    });

    function fetchChunk(isFirstChunk) {
        xhr.open("GET", '/stream/'+roomVariable+'?isFirstChunk='+isFirstChunk, true);
        xhr.responseType = "arraybuffer";

        xhr.onload = function () {
            if (xhr.status === 200) {
                let restart = xhr.getResponseHeader("Restart");

                if(restart === "true") {
                    if (sourceBuffer.buffered.length > 0) {
                        restartPlayer();
                    } else fetchChunk(false);
                } else {
                    sourceBuffer.appendBuffer(xhr.response);
                }

            } else if (xhr.status === 204) {
                if (sourceBuffer.buffered.length > 0) restartPlayer();

                setTimeout(function() {
                    fetchChunk(false)
                    }, DELAY_TIME);
            }
        };

        xhr.send();
    }

    function restartPlayer() {
        mediaSource.removeSourceBuffer(sourceBuffer)
        audioPlayer.pause();
        audioPlayer.removeAttribute("src")
        audioPlayer.src = URL.createObjectURL(mediaSource);
    }
});