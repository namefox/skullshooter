package skullshooter.engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sound {

    private final Clip clip;

    public Sound(File file) throws IOException {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void play() {
        clip.start();
    }

    public void pause() {
        clip.stop();
    }

    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }
}