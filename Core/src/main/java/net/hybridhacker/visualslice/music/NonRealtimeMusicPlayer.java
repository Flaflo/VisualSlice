package net.hybridhacker.visualslice.music;

import ddf.minim.AudioSample;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

import java.io.File;
import java.net.URI;

/**
 * For offline audio analysis, a player is not suitable, so this class will provide any needed data for non-realtime audio analysis
 * for example for exporting. The class is named player, because it does simulate the behaviour of {@link MusicPlayer} so that
 * the {@link IVisualizer} implementations work just as fine
 */
public class NonRealtimeMusicPlayer {
    
    private final Minim minim;
    
    private AudioSample sample;
    
    private FFT fft;
    private BeatDetect beatDetect;
    
    public NonRealtimeMusicPlayer() {
        this.minim = new Minim();
    }
    
    public void load(final URI audioSource) {
        this.sample = minim.loadSample(new File(audioSource).getAbsolutePath());
    }
}
