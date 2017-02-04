package net.hybridhacker.visualslice.visualizer.decorators;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

import java.awt.*;

/**
 * Draws a plain colored background for a visualizer
 */
public class PlainBackgroundDecorator extends AbstractVisualizerDecorator {
    
    private final Color color;
    
    public PlainBackgroundDecorator(final IVisualizer visualizer, final Color color) {
        super(visualizer);
        this.color = color;
    }
    
    @Override
    protected void doDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
                          final BeatDetect beatDetect, final FFT fft) {
        G2D.clear(this.color);
    }
}
