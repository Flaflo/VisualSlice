package net.hybridhacker.visualslice.music;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import java.net.URI;
import java.util.Optional;

/**
 * An interface representing a music player.
 */
public interface IMusicPlayer {
    
    /**
     * Play a track obtained from given audio source
     *
     * @param audioSource the source of the music (a file or web content etc)
     */
    void play(final URI audioSource);
    
    /**
     * Stop the current track
     */
    void stop();
    
    /**
     * Toggle pause state of the music player
     */
    void pause();
    
    /**
     * @return the player track length
     */
    Optional<Integer> getLength();
    
    /**
     * @return the current player position on track
     */
    Optional<Integer> getPosition();
    
    /**
     * @return the left audio buffer of the player or null, if no track is played
     */
    Optional<AudioBuffer> getLeftAudioBuffer();
    
    /**
     * @return the right audio buffer of the player or null, if no track is played
     */
    Optional<AudioBuffer> getRightAudioBuffer();
    
    /**
     * @return the mixed audio buffer of the player or null, if no track is played
     */
    Optional<AudioBuffer> getMixedAudioBuffer();
    
    /**
     * @return get the {@link BeatDetect} analysis object of the currently played track or null if no track is playing
     */
    Optional<BeatDetect> getBeatDetect();
    
    /**
     * @return get the {@link FFT} analysis object of the currently played track or null if no track is playing
     */
    Optional<FFT> getFFT();
}
