package net.hybridhacker.visualslice.visualizer.decorators;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;

/**
 * Draws an image as background for a visualizer
 */
public class ImageBackgroundDecorator extends AbstractVisualizerDecorator {
    
    private final BufferedImage image;
    
    public ImageBackgroundDecorator(final IVisualizer visualizer, final URI image) {
        super(visualizer);
        
        BufferedImage theImage = null;
        try {
            theImage = ImageIO.read(image.toURL());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.image = theImage;
        }
    }
    
    @Override
    protected void doDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
                          final BeatDetect beatDetect, final FFT fft) {
        if (this.image != null) G2D.texture(0, 0, G2D.canvas().getWidth(), G2D.canvas().getHeight(), this.image);
    }
}