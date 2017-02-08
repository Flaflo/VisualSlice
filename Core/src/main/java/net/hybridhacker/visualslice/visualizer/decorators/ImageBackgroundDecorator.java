package net.hybridhacker.visualslice.visualizer.decorators;

import ddf.minim.analysis.FFT;
import lombok.Setter;
import net.hybridhacker.visualslice.gui.Controller;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Draws an image as background for a visualizer
 */
public class ImageBackgroundDecorator extends AbstractVisualizerDecorator {
    
    @Setter
    private URI imageUri;
    private final Setting<?>[] settings;
    
    private BufferedImage image;
    
    public ImageBackgroundDecorator(final IVisualizer visualizer) {
        super(visualizer);
        
        try {
            imageUri = new URI("");
        } catch (URISyntaxException e) {
            assert false;
        }
    
        settings = new Setting<?>[] {new Setting<>("Image URI", this.imageUri, this::setImageUri)};
    }
    
    @Override
    protected void doInitialize() {
        try {
            image = ImageIO.read(imageUri.toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doDraw(final float[] leftAudioBuffer, final float[] rightAudioBuffer, final float[] mixAudioBuffer, final FFT fft,
                          final int trackLength, final int trackPosition) {
        if (this.image != null) {
            Controller.getInstance().getRenderEngine().drawImage(this.image, 0, 0, Controller.getInstance().getDisplay().getWidth(),
                                                                 Controller.getInstance().getDisplay().getHeight());
        }
    }
    
    @Override
    public AbstractVisualizerDecorator create(final IVisualizer embeddedVisualizer) {
        return new ImageBackgroundDecorator(embeddedVisualizer);
    }
    
    @Override
    public String getName() {
        return "Image Background Decorator";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return this.settings;
    }
}
