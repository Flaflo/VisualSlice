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
    public void stop() {
        this.audioPlayer.close();
    }

    @Override
    public void pause() {
        throw new NotImplementedException();
    }

    @Override
    public Optional<Integer> getLength() {
        if (this.getAudioPlayer().isPresent()) {
            return Optional.ofNullable(this.getAudioPlayer().get().length());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> getPosition() {
        if (this.getAudioPlayer().isPresent()) {
            return Optional.ofNullable(this.getAudioPlayer().get().position());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AudioBuffer> getRightAudioBuffer() {
        if (this.getAudioPlayer().isPresent()) {
            return Optional.ofNullable(this.getAudioPlayer().get().right);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AudioBuffer> getLeftAudioBuffer() {
        if (this.getAudioPlayer().isPresent()) {
            return Optional.ofNullable(this.getAudioPlayer().get().left);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AudioBuffer> getMixedAudioBuffer() {
        if (this.getAudioPlayer().isPresent()) {
            return Optional.ofNullable(this.getAudioPlayer().get().mix);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AudioPlayer> getAudioPlayer() {
        return Optional.ofNullable(this.audioPlayer);
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
