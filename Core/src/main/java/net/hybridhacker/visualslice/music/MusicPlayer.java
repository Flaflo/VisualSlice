package net.hybridhacker.visualslice.music;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.net.URI;
import java.util.Optional;

/**
 * A simple file music player
 */
public class MusicPlayer {
    
    private final Minim minim;
    private AudioPlayer audioPlayer;
    
    private FFT fft;
    
    /**
     * Initialize a new audio player
     */
    public MusicPlayer() {
        this.minim = new Minim();
    }
    
    /**
     * Start player
     */
    public void play() {
        this.audioPlayer.play();
    }
    
    /**
     * Load track into player and start playing
     *
     * @param audioSource audio track location
     */
    public void play(final URI audioSource) {
        loadTrack(audioSource);
        play();
    }
    
    /**
     * Stop and close the audio player
     */
    public void stop() {
        this.audioPlayer.close();
    }
    
    /**
     * Pause the current track
     */
    public void pause() {
        throw new NotImplementedException();
    }
    
    /**
     * Skip an amount of milliseconds
     *
     * @param ms milliseconds to skip
     */
    public void skip(final int ms) {
        this.audioPlayer.skip(ms);
    }
    
    /**
     * Set the players volume
     *
     * @param volume a float between 0 and 1
     */
    public void setVolume(final float volume) {
        audioPlayer.setGain((float) (20 * Math.log10(volume)));
    }
    
    /**
     * @return the player's volume between 0 and 1
     */
    public float getVolume() {
        return (float) Math.pow(10, audioPlayer.getGain() / 20);
    }
    
    /**
     * @return the current track's length in ms
     */
    public Optional<Integer> getLength() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.length()) : Optional.empty();
    }
    
    /**
     * @return the player's position in track in ms
     */
    public Optional<Integer> getPosition() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.position()) : Optional.empty();
    }
    
    /**
     * @return the right audio buffer for the current track
     */
    public Optional<AudioBuffer> getRightAudioBuffer() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.right) : Optional.empty();
    }
    
    /**
     * @return the left audio buffer for the current track
     */
    public Optional<AudioBuffer> getLeftAudioBuffer() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.left) : Optional.empty();
    }
    
    /**
     * @return the mixed audio buffer for current track
     */
    public Optional<AudioBuffer> getMixedAudioBuffer() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.right) : Optional.empty();
    }
    
    /**
     * @return the FFT instance for the current track
     */
    public Optional<FFT> getFFT() {
        return Optional.ofNullable(this.fft);
    }
    
    /**
     * Load a track into the audio player. Old tracks will be stopped and disposed
     *
     * @param audioSource audio track location
     */
    public void loadTrack(final URI audioSource) {
        if (this.audioPlayer != null && this.audioPlayer.isPlaying()) {
            this.audioPlayer.close();
        }
        
        final File audioFile = new File(audioSource);
        if (!audioFile.exists()) {
            throw new IllegalArgumentException("The given URI does not point to a file");
        }
        
        this.audioPlayer = minim.loadFile(audioFile.getAbsolutePath());
        
        this.fft = new FFT(this.audioPlayer.bufferSize(), this.audioPlayer.sampleRate());
    }
}
