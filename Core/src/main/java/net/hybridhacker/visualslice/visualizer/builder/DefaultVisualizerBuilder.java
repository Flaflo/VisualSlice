package net.hybridhacker.visualslice.visualizer.builder;

import net.hybridhacker.visualslice.visualizer.DebugVisualizer;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.decorators.ImageBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.decorators.PlainBackgroundDecorator;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 */
public class DefaultVisualizerBuilder implements VisualizerBuilder {
    
    private IVisualizer visualizer = null;
    
    public DefaultVisualizerBuilder() {
        
    }
    
    @Override
    public IVisualizer buildVisualizer() {
        if (this.visualizer == null) throw new IllegalStateException("No visualizer was chosen yet");
        
        return this.visualizer;
    }
    
    /**
     * Decorates the visualizer with a background image
     *
     * @param image a buffered image containing the background image
     *
     * @return this builder instance
     */
    public DefaultVisualizerBuilder image(final BufferedImage image) {
        if (this.visualizer == null) throw new IllegalStateException("No visualizer was chosen yet");
        
        this.visualizer = new ImageBackgroundDecorator(this.visualizer, image);
        return this;
    }
    
    /**
     * Decorates the visualizer with a plain background color
     *
     * @param color background color
     *
     * @return this builder instance
     */
    public DefaultVisualizerBuilder background(final Color color) {
        if (this.visualizer == null) throw new IllegalStateException("No visualizer was chosen yet");
        
        this.visualizer = new PlainBackgroundDecorator(this.visualizer, color);
        return this;
    }
    
    /**
     * Sets a new instance of the debug visualizer as the current visualizer
     *
     * @return this builder instance
     */
    public DefaultVisualizerBuilder debugVisualizer() {
        this.visualizer = new DebugVisualizer();
        return this;
    }
}
