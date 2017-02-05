package net.hybridhacker.visualslice.visualizer.builder;

import net.hybridhacker.visualslice.visualizer.DebugVisualizer;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.beat.DrumkitBeatVisualizer;
import net.hybridhacker.visualslice.visualizer.decorators.ImageBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.decorators.PlainBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.frequency.BasicFrequencyLine;

import java.awt.*;
import java.net.URI;

/**
 *
 */
public class DebugBuilder implements VisualizerBuilder {
    
    private IVisualizer visualizer = null;
    
    public DebugBuilder() {
        
    }
    
    @Override
    public IVisualizer buildVisualizer() {
        if (this.visualizer == null) throw new IllegalStateException("No visualizer was chosen yet");
        this.visualizer.initialize();
        return this.visualizer;
    }
    
    /**
     * Decorates the visualizer with a background image
     *
     * @param image an URI pointing to the background image
     *
     * @return this builder instance
     */
    public DebugBuilder image(final URI image) {
        if (this.visualizer == null) throw new IllegalStateException("No visualizer was chosen yet");
    
        ImageBackgroundDecorator decorator = new ImageBackgroundDecorator(this.visualizer);
        decorator.setImageUri(image);
        this.visualizer = decorator;
        return this;
    }
    
    /**
     * Decorates the visualizer with a plain background color
     *
     * @param color background color
     *
     * @return this builder instance
     */
    public DebugBuilder background(final Color color) {
        if (this.visualizer == null) throw new IllegalStateException("No visualizer was chosen yet");
    
        this.visualizer = new PlainBackgroundDecorator(this.visualizer);
        return this;
    }
    
    /**
     * Sets a new instance of the debug visualizer as the current visualizer
     *
     * @return this builder instance
     */
    public DebugBuilder debugVisualizer(final Color color) {
        this.visualizer = new DebugVisualizer();
        ((DebugVisualizer) this.visualizer).setColor(color);
        return this;
    }
    
    /**
     * Sets a new instance of the {@link BasicFrequencyLine} visualizer as the current visualizer
     *
     * @return this builder instance
     */
    public DebugBuilder basicFrequencyLine(final int marginTop, final Color color) {
        this.visualizer = new BasicFrequencyLine();
        return this;
    }
    
    /**
     * Sets a new instance of the {@link DrumkitBeatVisualizer} visualizer as the current visualizer
     *
     * @return this builder instance
     */
    public DebugBuilder drumKitVisualizer() {
        this.visualizer = new DrumkitBeatVisualizer();
        return this;
    }
}
