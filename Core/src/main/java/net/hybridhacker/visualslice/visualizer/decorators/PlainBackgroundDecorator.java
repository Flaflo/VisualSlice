package net.hybridhacker.visualslice.visualizer.decorators;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import lombok.Setter;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.awt.*;

/**
 * Draws a plain colored background for a visualizer
 */
public class PlainBackgroundDecorator extends AbstractVisualizerDecorator {
    
    @Setter
    private Color color;
    
    public PlainBackgroundDecorator(final IVisualizer visualizer) {
        super(visualizer);
    }
    
    @Override
    protected void doDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
                          final BeatDetect beatDetect, final FFT fft) {
        G2D.clear(this.color);
    }
    
    @Override
    public AbstractVisualizerDecorator create(final IVisualizer embeddedVisualizer) {
        return new PlainBackgroundDecorator(embeddedVisualizer);
    }
    
    @Override
    public void initialize() {}
    
    @Override
    public String getName() {
        return "Plain Colored Background Decorator";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return new Setting<?>[] {new Setting<>("Color", this.color, this::setColor)};
    }
}
