package net.hybridhacker.visualslice.music;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

import java.io.File;

/**
 *
 */
public class MusicPlayer {
    
    private final Minim       minim;
    private       AudioPlayer audioPlayer;
    
    /**
     * Initialize a new audio player
     */
    public MusicPlayer() {
        this.minim = new Minim();
    }
    
    public void play(final File audioFile) {
        if (this.audioPlayer.isPlaying()) {
            this.audioPlayer.close();
        }
        
        this.audioPlayer = minim.loadFile(audioFile.getAbsolutePath());
        this.audioPlayer.play();
    }
}
