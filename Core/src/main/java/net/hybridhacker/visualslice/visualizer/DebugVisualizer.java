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
        
        final int bufferSize = mixAudioBuffer.size() - 1;

        for (int i = 0; i < bufferSize; i++) {
            G2D.line(i, (int) (50 + leftAudioBuffer.get(i) * 20), i, (int) (50 - leftAudioBuffer.get(i + 1) * 20), rainbow[playerPosition]);
            G2D.line(i, (int) (50 + rightAudioBuffer.get(i) * 20), i, (int) (50 - rightAudioBuffer.get(i + 1) * 20), rainbow[playerPosition]);
        }
    }

    @Override
    public String getName() {
        return "Debug Visualizer";
    }
}
