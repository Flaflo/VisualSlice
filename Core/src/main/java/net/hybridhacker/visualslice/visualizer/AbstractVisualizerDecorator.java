package net.hybridhacker.visualslice.visualizer;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * A decorator for visualizers that can decorate extra output to existing visualizers
 */
@RequiredArgsConstructor
public abstract class AbstractVisualizerDecorator implements IVisualizer {
    
    @Getter
    private final IVisualizer visualizer;
    
    @Override
    public final void onDraw(final int playerLength, final int playerPosition, final AudioBuffer leftAudioBuffer,
                             final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer, final BeatDetect beatDetect,
                             final FFT fft) {
        this.doDraw(leftAudioBuffer, rightAudioBuffer, mixAudioBuffer, beatDetect, fft);
        this.visualizer.onDraw(playerLength, playerPosition, leftAudioBuffer, rightAudioBuffer, mixAudioBuffer, beatDetect, fft);
    }
    
    @Override
    public final void initialize() {
        this.doInitialize();
        this.visualizer.initialize();
    }
    
    /**
     * Initialize the decorator
     */
    protected abstract void doInitialize();
    
    /**
     * Draw the decorator output
     *
     * @param leftAudioBuffer  left audio buffer
     * @param rightAudioBuffer right audio buffer
     * @param mixAudioBuffer   mixed audio buffer
     */
    protected abstract void doDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
                                   final BeatDetect beatDetect, final FFT fft);
    
    /**
     * @return an instance of this class. This method must not use any instance specific data or objects
     */
    public abstract AbstractVisualizerDecorator create(final IVisualizer embeddedVisualizer);
}
