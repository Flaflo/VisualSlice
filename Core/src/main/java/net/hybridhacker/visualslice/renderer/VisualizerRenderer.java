package net.hybridhacker.visualslice.renderer;

import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

import java.util.NoSuchElementException;

/**
 *
 */
public class VisualizerRenderer implements Runnable {
    
    private final IVisualizer visualizer;
    private final MusicPlayer musicPlayer;
    
    /**
     * Initializes the given visualizers and starts to render it
     *
     * @param visualizer  (decorated) visualizer to render
     * @param musicPlayer music player providing data to the visualizer
     */
    public VisualizerRenderer(final IVisualizer visualizer, final MusicPlayer musicPlayer) {
        this.visualizer = visualizer;
        this.musicPlayer = musicPlayer;
    }
    
    @Override
    public void run() {
        if (!musicPlayer.getMixedAudioBuffer().isPresent() || !musicPlayer.getLeftAudioBuffer().isPresent() ||
            !musicPlayer.getRightAudioBuffer().isPresent() || !musicPlayer.getLength().isPresent() ||
            !musicPlayer.getPosition().isPresent() || !musicPlayer.getFFT().isPresent()) {
            return;
        }
        
        //Forward fft to mixed audio buffer
        try {
            this.musicPlayer.getFFT().get().forward(this.musicPlayer.getMixedAudioBuffer().get());
        } catch (NoSuchElementException ignored) {}
    
        this.visualizer
                .onDraw(musicPlayer.getLength().get(), musicPlayer.getPosition().get(), musicPlayer.getLeftAudioBuffer().get().toArray(),
                        musicPlayer.getRightAudioBuffer().get().toArray(), musicPlayer.getMixedAudioBuffer().get().toArray(),
                        musicPlayer.getFFT().get());
    }
}
