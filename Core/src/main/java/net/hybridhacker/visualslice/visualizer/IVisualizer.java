package net.hybridhacker.visualslice.visualizer;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

/**
 * An audio visualizer interface
 */
public interface IVisualizer {
    
    /**
     * Initialize the visualizer
     */
    void initialize();
    
    /**
     * Draw the visualizer output. The {@link AudioBuffer} contains all currently buffered audio
     * information
     *
     * @param playerLength     the player length
     * @param playerPosition   the player position
     * @param leftAudioBuffer  the left stereo audio buffer
     * @param rightAudioBuffer the right stereo audio buffer
     * @param mixAudioBuffer   the mixed audio buffer
     * @param beatDetect       a beat detector previously registered in the player
     * @param fft              a fast fourier transformation object for frequency analysis
     * @param trackLength      length of the current track in ms
     * @param trackPosition    current position in track in ms
     */
    void onDraw(final int playerLength, final int playerPosition, final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer,
                final AudioBuffer mixAudioBuffer, final BeatDetect beatDetect, final FFT fft, final int trackLength,
                final int trackPosition);
    
    /**
     * @return the name of the visualizer. This method must return the same string for any instance
     */
    String getName();
    
    /**
     * @return the settings of this visualizer
     */
    Setting<?>[] getSettings();
}
