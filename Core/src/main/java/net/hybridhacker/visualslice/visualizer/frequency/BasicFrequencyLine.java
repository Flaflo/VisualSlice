package net.hybridhacker.visualslice.visualizer.frequency;

import ddf.minim.analysis.FFT;
import lombok.Setter;
import net.hybridhacker.visualslice.gui.Controller;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.awt.*;

/**
 * A basic frequency analysis visualizer
 */
public class BasicFrequencyLine implements IVisualizer {
    
    @Setter
    private double scale;
    
    @Setter
    private int barMinHeight;
    
    @Setter
    private int barWidth;
    
    @Setter
    private int gap;
    
    @Setter
    private int topMargin;
    
    @Setter
    private Color color;
    private final Setting<?>[] settings = new Setting<?>[] {new Setting<>("scale", this.scale, this::setScale),
                                                            new Setting<>("barMinHeight", this.barMinHeight, this::setBarMinHeight),
                                                            new Setting<>("barWidth", this.barWidth, this::setBarWidth),
                                                            new Setting<>("gap", this.gap, this::setGap),
                                                            new Setting<>("topMargin", this.topMargin, this::setTopMargin),
                                                            new Setting<>("color", this.color, this::setColor)};
    
    @Override
    public void initialize() {}
    
    @Override
    public void onDraw(final int playerLength, final int playerPosition, final float[] leftAudioBuffer,
                       final float[] rightAudioBuffer, final float[] mixAudioBuffer, final FFT fft) {
        
        for (int i = 0; i < Controller.getInstance().getDisplay().getWidth() / (barWidth + gap); i++) {
            final int band = (int) Math.max(fft.getBand(i * barWidth) * scale, barMinHeight);
            
            Controller.getInstance().getRenderEngine().setColor(color);
            Controller.getInstance().getRenderEngine().fillRect(i * (barWidth + gap), topMargin - band, barWidth, band);
        }
    }
    
    @Override
    public String getName() {
        return "Basic Frequency Line";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return this.settings;
    }
}
