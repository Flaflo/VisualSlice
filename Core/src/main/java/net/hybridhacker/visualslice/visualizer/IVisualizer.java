package net.hybridhacker.visualslice.visualizer;

import ddf.minim.AudioBuffer;

/**
 * An audio visualizer interface
 */
public interface IVisualizer {
    
    /**
     * Draw the visualizer output. The {@link AudioBuffer} contains all currently buffered audio
     * information
     *
     * @param leftAudioBuffer  the left stereo audio buffer
     * @param rightAudioBuffer the right stereo audio buffer
     * @param mixAudioBuffer   the mixed audio buffer
     */
    void onDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer);
    
    /**
     * @return the name of the visualizer
     */
    String getName();
}
