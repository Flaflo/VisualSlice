package net.hybridhacker.visualslice.visualizer;

import ddf.minim.AudioBuffer;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;

/**
 * A visualizer for debugging purpose
 */
public class DebugVisualizer implements IVisualizer {
    
    @Override
    public void onDraw(final AudioBuffer leftAudioBuffer, final AudioBuffer rightAudioBuffer, final AudioBuffer mixAudioBuffer,
                       final BeatDetect beatDetect, final FFT fft) {
        
    }
    
    @Override
    public String getName() {
        return "Debug Visualizer";
    }
}
