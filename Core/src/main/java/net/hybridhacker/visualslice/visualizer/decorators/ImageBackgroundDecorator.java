package net.hybridhacker.visualslice.visualizer.decorators;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import lombok.Setter;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;

/**
 * Draws an image as background for a visualizer
 */
public class ImageBackgroundDecorator extends AbstractVisualizerDecorator {
    
    @Setter
    private URI imageUri;
    
    private BufferedImage image;
    
    public ImageBackgroundDecorator(final IVisualizer visualizer) {
        super(visualizer);
    }
    
    @Override
    protected void doDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
                          final BeatDetect beatDetect, final FFT fft) {
        if (this.image != null) G2D.texture(0, 0, G2D.canvas().getWidth(), G2D.canvas().getHeight(), this.image);
    }
    
    @Override
    public AbstractVisualizerDecorator create(final IVisualizer embeddedVisualizer) {
        return new ImageBackgroundDecorator(embeddedVisualizer);
    }
    
    @Override
    public void initialize() {
        try {
            image = ImageIO.read(imageUri.toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String getName() {
        return "Image Background Decorator";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return new Setting<?>[] {new Setting<>("Image URI", this.imageUri, this::setImageUri),};
    }
}
