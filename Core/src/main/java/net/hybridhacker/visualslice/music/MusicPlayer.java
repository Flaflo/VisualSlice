package net.hybridhacker.visualslice.music;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioListener;
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
public class MusicPlayer implements IMusicPlayer, AudioListener {

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

        this.fft = new FFT(this.audioPlayer.bufferSize(), this.audioPlayer.sampleRate());
        this.beatDetect = new BeatDetect(this.audioPlayer.bufferSize(), this.audioPlayer.sampleRate());

        this.audioPlayer.addListener(this);
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
    public void setVolume(final float volume) {
        throw new NotImplementedException();
    }

    @Override
    public float getVolume() {
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
        return this.audioPlayer != null ? Optional.of(this.beatDetect) : Optional.empty();
    }

    @Override
    public Optional<FFT> getFFT() {
        return this.audioPlayer != null ? Optional.of(this.fft) : Optional.empty();
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
