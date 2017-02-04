package net.hybridhacker.visualslice.renderer;

import lombok.RequiredArgsConstructor;
import net.hybridhacker.visualslice.music.IMusicPlayer;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

/**
 *
 */
@RequiredArgsConstructor
public class VisualizerRenderer implements Runnable {
    
    private final IVisualizer visualizer;
    private final IMusicPlayer musicPlayer;
    
    @Override
    public void run() {
        // TODO add present checks for beat detect and fft
        if (!musicPlayer.getMixedAudioBuffer().isPresent() || !musicPlayer.getLeftAudioBuffer().isPresent() ||
            !musicPlayer.getRightAudioBuffer().isPresent() || !musicPlayer.getLength().isPresent() ||
            !musicPlayer.getPosition().isPresent()) return;
    
        this.visualizer.onDraw(musicPlayer.getLength().get(), musicPlayer.getPosition().get(), musicPlayer.getLeftAudioBuffer().get(), musicPlayer.getRightAudioBuffer().get(),
                               musicPlayer.getMixedAudioBuffer().get(), musicPlayer.getBeatDetect().get(), musicPlayer.getFFT().get());
    }
}
