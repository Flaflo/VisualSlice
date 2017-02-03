package net.hybridhacker.visualslice.music;

import ddf.minim.AudioBuffer;
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
public class MusicPlayer implements IMusicPlayer {
    
    private final Minim minim;
    private AudioPlayer audioPlayer;
    
    /**
     * Initialize a new audio player
     */
    public MusicPlayer() {
        this.minim = new Minim();
    }
    
    @Override
    public void play(final URI audioSource) {
        if (this.audioPlayer.isPlaying()) {
            this.audioPlayer.close();
        }
        
        final File audioFile = new File(audioSource);
        if (!audioFile.exists()) throw new IllegalArgumentException("The given URI does not point to a file");
        
        this.audioPlayer = minim.loadFile(audioFile.getAbsolutePath());
        this.audioPlayer.play();
    }
    
    /**
     * Stop and close the audio player
     */
    public void stop() {
        this.audioPlayer.close();
    }
    
    @Override
    public void pause() {
        throw new NotImplementedException();
    }
    
    @Override
    public Optional<AudioBuffer> getRightAudioBuffer() {
        return this.audioPlayer == null ? Optional.empty() : Optional.of(this.audioPlayer.right);
    }
    
    @Override
    public Optional<AudioBuffer> getLeftAudioBuffer() {
        return this.audioPlayer == null ? Optional.empty() : Optional.of(this.audioPlayer.left);
    }
    
    @Override
    public Optional<AudioBuffer> getMixedAudioBuffer() {
        return this.audioPlayer == null ? Optional.empty() : Optional.of(this.audioPlayer.mix);
    }
    
    @Override
    public Optional<BeatDetect> getBeatDetect() {
        // TODO generate beat detect
        return Optional.empty();
    }
    
    @Override
    public Optional<FFT> getFFT() {
        // TODO register fft
        return Optional.empty();
    }
}
