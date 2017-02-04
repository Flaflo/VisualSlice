package net.hybridhacker.visualslice.visualizer;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import java.awt.Color;
import net.hybridhacker.visualslice.utils.ColorUtil;
import net.hybridhacker.visualslice.utils.G2D;

/**
 * A visualizer for debugging purpose
 */
public class DebugVisualizer implements IVisualizer {

    private Color[] rainbow;

    @Override
    public void onDraw(final int playerLength, final int playerPosition, final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
            final BeatDetect beatDetect, final FFT fft) {

        if (rainbow == null) {
            rainbow = ColorUtil.generateRainbow(playerLength, false, false, false);
        }

        final int bufferSize = mixAudioBuffer.size();

        final Color color = rainbow[playerPosition];
        final Color color2 = color.darker();

        { //Volume
            for (int i = 0; i < bufferSize - 1; i++) {
                G2D.line(i, (int) (50 + leftAudioBuffer.get(i) * 20), i, (int) (50 - leftAudioBuffer.get(i + 1) * 20), color2);
                G2D.line(i, (int) (50 + rightAudioBuffer.get(i) * 20), i, (int) (50 - rightAudioBuffer.get(i + 1) * 20), color2);

                G2D.line(i, (int) (50 + leftAudioBuffer.get(i) * 5), i, (int) (50 - leftAudioBuffer.get(i + 1) * 5), color);
                G2D.line(i, (int) (50 + rightAudioBuffer.get(i) * 5), i, (int) (50 - rightAudioBuffer.get(i + 1) * 5), color);
            }
        }

        { //FFT Bands
            final double scale = 0.8;
            final int min = 2;
            final int step = 5;
            final int gap = 1;

            for (int i = 0; i < G2D.canvas().getWidth() / (step + gap); i++) {
                final int band = (int) Math.max(fft.getBand(i * step) * scale, min);

                G2D.rect(i * (step + gap), G2D.canvas().getHeight() - band, step, band, color2);
            }
        }

        { //Player progress 
            final int width = (int) Math.round(((double) playerPosition / (double) playerLength)* G2D.canvas().getWidth());
            final int height = 4;
            
            G2D.rect(0, 0, G2D.canvas().getWidth(), height, color2);
            G2D.rect(0, 0, width, height, color);
        }
    }

    @Override
    public String getName() {
        return "Debug Visualizer";
    }
}
