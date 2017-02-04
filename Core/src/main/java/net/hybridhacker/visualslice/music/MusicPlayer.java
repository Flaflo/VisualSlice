package net.hybridhacker.visualslice.music;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import java.io.File;
import java.net.URI;
import java.util.Optional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        if (this.audioPlayer != null && this.audioPlayer.isPlaying()) {
            this.audioPlayer.close();
        }

        final File audioFile = new File(audioSource);
        if (!audioFile.exists()) {
            throw new IllegalArgumentException("The given URI does not point to a file");
        }

        this.audioPlayer = minim.loadFile(audioFile.getAbsolutePath());
        this.audioPlayer.play();
    }

    /**
     * Stop and close the audio player
     */
    @Override
    public void stop() {
        this.audioPlayer.close();
    }

    @Override
    public void pause() {
        throw new NotImplementedException();
    }

    @Override
    public Optional<Integer> getLength() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.length()) : Optional.empty();
    }

    @Override
    public Optional<Integer> getPosition() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.position()) : Optional.empty();
    }

    @Override
    public Optional<AudioBuffer> getRightAudioBuffer() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.right) : Optional.empty();
    }

    @Override
    public Optional<AudioBuffer> getLeftAudioBuffer() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.left) : Optional.empty();
    }

    @Override
    public Optional<AudioBuffer> getMixedAudioBuffer() {
        return this.audioPlayer != null ? Optional.of(this.audioPlayer.right) : Optional.empty();
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
