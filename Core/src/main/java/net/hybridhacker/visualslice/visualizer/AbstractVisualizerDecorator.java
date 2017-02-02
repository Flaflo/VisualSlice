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
    private IVisualizer visualizer;
    
    @Override
    public final void onDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer, final BeatDetect beatDetect, final FFT fft) {
        this.doDraw(leftAudioBuffer, rightAudioBuffer, mixAudioBuffer, beatDetect, fft);
        this.visualizer.onDraw(leftAudioBuffer, rightAudioBuffer, mixAudioBuffer, beatDetect, fft);
    }
    
    /**
     * Draw the decorator output
     *
     * @param leftAudioBuffer  left audio buffer
     * @param rightAudioBuffer right audio buffer
     * @param mixAudioBuffer   mixed audio buffer
     */
    protected abstract void doDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer, final BeatDetect beatDetect, final FFT fft);
    
    @Override
    public final String getName() {
        return this.visualizer.getName();
    }
}
