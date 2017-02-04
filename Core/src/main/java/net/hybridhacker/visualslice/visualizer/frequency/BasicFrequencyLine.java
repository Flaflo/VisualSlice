package net.hybridhacker.visualslice.visualizer.frequency;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import lombok.RequiredArgsConstructor;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

import java.awt.*;

/**
 * A basic frequency analysis visualizer
 */
@RequiredArgsConstructor
public class BasicFrequencyLine implements IVisualizer {
    
    private final double scale;
    private final int barMinHeight;
    private final int barWidth;
    private final int gap;
    private final int topMargin;
    private final Color color;
    
    public BasicFrequencyLine(final int topMargin, final Color color) {
        this(0.8, 2, 8, 3, topMargin, color);
    }
    
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
}
