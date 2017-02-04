package net.hybridhacker.visualslice.visualizer;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import lombok.Data;
import net.hybridhacker.visualslice.utils.FXUtil;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.awt.*;

/**
 * A visualizer for debugging purpose
 */
@Data
public class DebugVisualizer implements IVisualizer {

    private final Color color;

    @Override
    public void onDraw(final int playerLength, final int playerPosition, final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
            final BeatDetect beatDetect, final FFT fft) {

        final int bufferSize = mixAudioBuffer.size();

        final Color color2 = this.getColor().brighter();

        { //Volume
            for (int i = 0; i < bufferSize - 1; i++) {
                G2D.line(i, (int) (50 + leftAudioBuffer.get(i) * 20), i, (int) (50 - leftAudioBuffer.get(i + 1) * 20), color2);
                G2D.line(i, (int) (50 + rightAudioBuffer.get(i) * 20), i, (int) (50 - rightAudioBuffer.get(i + 1) * 20), color2);

                G2D.line(i, (int) (50 + leftAudioBuffer.get(i) * 5), i, (int) (50 - leftAudioBuffer.get(i + 1) * 5), this.getColor());
                G2D.line(i, (int) (50 + rightAudioBuffer.get(i) * 5), i, (int) (50 - rightAudioBuffer.get(i + 1) * 5), this.getColor());
            }
        }

        { //FFT Bands
            final double scale = 0.8;
            final int min = 2;
            final int width = 20;
            final int gap = 2;

            for (int i = 0; i < G2D.canvas().getWidth() / (width + gap); i++) {
                final int height = (int) Math.max(fft.getBand(i * width) * scale, min);
                G2D.texture(i * (width + gap) - 10, G2D.canvas().getHeight() - height - 15, FXUtil.generateGlow(width, height, 10, Color.CYAN, 0.8F));
//                G2D.rect(i * (width + gap), G2D.canvas().getHeight() - height, width, height, color2);
            }
        }

        { //Player progress 
            final int width = (int) Math.round(((double) playerPosition / (double) playerLength) * G2D.canvas().getWidth());
            final int height = 4;

            G2D.rect(0, 0, G2D.canvas().getWidth(), height, color);
            G2D.rect(0, 0, width, height, color2);
        }
    }

    @Override
    public String getName() {
        return "Debug Visualizer";
    }
    
    @Override
    public Setting<?>[] getSettings() {
        return new Setting<?>[0];
    }
}
