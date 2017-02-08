package net.hybridhacker.visualslice.visualizer;

import ddf.minim.AudioBuffer;
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
     * @param playerLength     the track's length
     * @param playerPosition   the track's position
     * @param leftAudioBuffer  the left stereo audio buffer
     * @param rightAudioBuffer the right stereo audio buffer
     * @param mixAudioBuffer   the mixed audio buffer
     * @param fft              a fast fourier transformation object for frequency analysis
     */
    void onDraw(final int playerLength, final int playerPosition, final float[] leftAudioBuffer, final float[] rightAudioBuffer,
                final float[] mixAudioBuffer, final FFT fft);
    
    /**
     * @return the name of the visualizer. This method must return the same string for any instance
     */
    String getName();
    
    /**
     * @return the settings of this visualizer
     */
    Setting<?>[] getSettings();
}
