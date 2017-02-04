package net.hybridhacker.visualslice.visualizer.decorators;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import lombok.Setter;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.image.BufferedImage;

/**
 * Draws an image as background for a visualizer
 */
public class ImageBackgroundDecorator extends AbstractVisualizerDecorator {
    
    @Setter
    private BufferedImage image;
    
    public ImageBackgroundDecorator(final IVisualizer visualizer) {
        super(visualizer);
    }
    
    @Override
    protected void doDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer, final BeatDetect beatDetect, final FFT fft) {
        if (this.image != null) G2D.texture(0, 0, G2D.canvas().getWidth(), G2D.canvas().getHeight(), this.image);
    }
    
    @Override
    public AbstractVisualizerDecorator create(final IVisualizer embeddedVisualizer) {
        return new ImageBackgroundDecorator(embeddedVisualizer);
    }
    
    @Override
    public Setting<?>[] getSettings() {
        throw new NotImplementedException();
    }
}
