package net.hybridhacker.visualslice.visualizer.frequency;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import lombok.Setter;
import net.hybridhacker.visualslice.utils.G2D;
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
    
    @Override
    public void onDraw(final int playerLength, final int playerPosition, final AudioBuffer leftAudioBuffer,
                       final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer, final BeatDetect beatDetect, final FFT fft) {
        
        for (int i = 0; i < G2D.canvas().getWidth() / (barWidth + gap); i++) {
            final int band = (int) Math.max(fft.getBand(i * barWidth) * scale, barMinHeight);
            
            G2D.rect(i * (barWidth + gap), topMargin - band, barWidth, band, this.color);
        }
    }
    
    @Override
    public String getName() {
        return "Basic Frequency Line";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return new Setting<?>[0];
    }
}
