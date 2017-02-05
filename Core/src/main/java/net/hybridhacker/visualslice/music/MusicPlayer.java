package net.hybridhacker.visualslice.music;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioListener;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.net.URI;
import java.util.Optional;

/**
 * A simple file music player
 */
public class MusicPlayer implements AudioListener {
    
    private final Minim minim;
    private AudioPlayer audioPlayer;
    
    private FFT fft;
    private BeatDetect beatDetect;
    
    /**
     * Initialize a new audio player
     */
    public MusicPlayer() {
        this.minim = new Minim();
    }
    
    public void play(final URI audioSource) {
        if (this.audioPlayer != null && this.audioPlayer.isPlaying()) {
            this.audioPlayer.close();
        }
        
        final File audioFile = new File(audioSource);
        if (!audioFile.exists()) {
            throw new IllegalArgumentException("The given URI does not point to a file");
        }
        
        this.audioPlayer = minim.loadFile(audioFile.getAbsolutePath());
        this.audioPlayer.play();
        
        this.fft = new FFT(this.audioPlayer.bufferSize(), this.audioPlayer.sampleRate());
        this.beatDetect = new BeatDetect(this.audioPlayer.bufferSize(), this.audioPlayer.sampleRate());
        this.beatDetect.setSensitivity(150);
        
        this.audioPlayer.addListener(this);
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
     * Set the players volume
     *
     * @param volume a float between 0 and 1
     */
    public void setVolume(final float volume) {
        throw new NotImplementedException();
    }
    
    /**
     * @return the player's volume between 0 and 1
     */
    public float getVolume() {
        throw new NotImplementedException();
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
     * @return the beat detect object for the current track
     */
    public Optional<BeatDetect> getBeatDetect() {
        return Optional.ofNullable(this.beatDetect);
    }
    
    /**
     * @return the FFT instance for the current track
     */
    public Optional<FFT> getFFT() {
        return Optional.ofNullable(this.fft);
    }
    
    @Override
    public void samples(float[] mono) {
        this.analizeBeat();
    }
    
    @Override
    public void samples(float[] left, float[] right) {
        this.analizeBeat();
    }
    
    /**
     * Analies the beat with the beat detect class
     */
    private void analizeBeat() {
        if (this.getBeatDetect().isPresent() && this.getMixedAudioBuffer().isPresent()) {
            this.getBeatDetect().get().detect(this.getMixedAudioBuffer().get());
        }
    }
}
