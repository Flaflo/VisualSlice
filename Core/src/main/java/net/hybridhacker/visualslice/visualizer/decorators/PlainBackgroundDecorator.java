package net.hybridhacker.visualslice.visualizer.decorators;

import ddf.minim.analysis.FFT;
import lombok.Setter;
import net.hybridhacker.visualslice.gui.Controller;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.awt.*;

/**
 * Draws a plain colored background for a visualizer
 */
public class PlainBackgroundDecorator extends AbstractVisualizerDecorator {
    
    @Setter
    private Color color = Color.BLACK;
    private final Setting<?>[] settings = new Setting<?>[] {new Setting<>("Color", this.color, this::setColor)};
    
    public PlainBackgroundDecorator(final IVisualizer visualizer) {
        super(visualizer);
    }
    
    @Override
    protected void doInitialize() {}
    
    @Override
    protected void doDraw(final float[] leftAudioBuffer, final float[] rightAudioBuffer, final float[] mixAudioBuffer, final FFT fft,
                          final int trackLength, final int trackPosition) {
        Controller.getInstance().getRenderEngine().setColor(color);
        Controller.getInstance().getRenderEngine()
                  .fillRect(0, 0, Controller.getInstance().getDisplay().getWidth(), Controller.getInstance().getDisplay().getHeight());
    }
    
    @Override
    public AbstractVisualizerDecorator create(final IVisualizer embeddedVisualizer) {
        return new PlainBackgroundDecorator(embeddedVisualizer);
    }
    
    @Override
    public String getName() {
        return "Plain Colored Background Decorator";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return this.settings;
    }
}
