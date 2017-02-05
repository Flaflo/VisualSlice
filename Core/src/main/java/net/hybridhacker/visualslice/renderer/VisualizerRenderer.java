package net.hybridhacker.visualslice.renderer;

import net.hybridhacker.visualslice.music.IMusicPlayer;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

import java.util.NoSuchElementException;

/**
 *
 */
public class VisualizerRenderer implements Runnable {
    
    private final IVisualizer visualizer;
    private final IMusicPlayer musicPlayer;
    
    /**
     * Initializes the given visualizers and starts to render it
     *
     * @param visualizer  (decorated) visualizer to render
     * @param musicPlayer music player providing data to the visualizer
     */
    public VisualizerRenderer(final IVisualizer visualizer, final IMusicPlayer musicPlayer) {
        this.visualizer = visualizer;
        this.musicPlayer = musicPlayer;
    }
    
    @Override
    public void run() {
        if (!musicPlayer.getMixedAudioBuffer().isPresent() || !musicPlayer.getLeftAudioBuffer().isPresent() ||
            !musicPlayer.getRightAudioBuffer().isPresent() || !musicPlayer.getLength().isPresent() ||
            !musicPlayer.getPosition().isPresent() && !musicPlayer.getBeatDetect().isPresent() && !musicPlayer.getFFT().isPresent()) {
            return;
        }
        
        //Forward fft to mixed audio buffer
        try {
            this.musicPlayer.getFFT().get().forward(this.musicPlayer.getMixedAudioBuffer().get());
        } catch (NoSuchElementException ignored) {}
        
        this.visualizer.onDraw(musicPlayer.getLength().get(), musicPlayer.getPosition().get(), musicPlayer.getLeftAudioBuffer().get(),
                               musicPlayer.getRightAudioBuffer().get(), musicPlayer.getMixedAudioBuffer().get(),
                               musicPlayer.getBeatDetect().get(), musicPlayer.getFFT().get(), musicPlayer.getLength().get(),
                               musicPlayer.getPosition().get());
    }
}
