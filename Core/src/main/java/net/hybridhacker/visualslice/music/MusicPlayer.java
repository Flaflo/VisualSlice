package net.hybridhacker.visualslice.music;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;

import java.io.File;

/**
 *
 */
public class MusicPlayer {
    
    private final Minim minim;
    private AudioPlayer audioPlayer;
    
    /**
     * Initialize a new audio player
     */
    public MusicPlayer() {
        this.minim = new Minim();
    }
    
    /**
     * Load and play an audio file
     *
     * @param audioFile audio file
     */
    public void play(final File audioFile) {
        if (this.audioPlayer.isPlaying()) {
            this.audioPlayer.close();
        }
        
        this.audioPlayer = minim.loadFile(audioFile.getAbsolutePath());
        this.audioPlayer.play();
    }
    
    /**
     * Stop and close the audio player
     */
    public void stop() {
        this.audioPlayer.close();
    }
    
    /**
     * @return the right audio buffer
     */
    public AudioBuffer getRightAudioBuffer() {
        return this.audioPlayer.right;
    }
    
    /**
     * @return the left audio buffer
     */
    public AudioBuffer getLeftAudioBuffer() {
        return this.audioPlayer.left;
    }
    
    /**
     * @return the audio buffer mixed from right and left
     */
    public AudioBuffer getMixedAudioBuffer() {
        return this.audioPlayer.mix;
    }
    
    /**
     * @return the beat detect object of the current played track
     */
    public BeatDetect getBeatDetector() {
        // TODO
        return null;
    }
    
    /**
     * @return the fast fourier transform object of the current played track. The player handles (un-)registering that object
     */
    public FFT getFFT() {
        return null;
    }
}
