package net.hybridhacker.visualslice.renderer;

import lombok.RequiredArgsConstructor;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.visualizer.IVisualizer;

/**
 *
 */
@RequiredArgsConstructor
public class VisualizerRenderer implements Runnable {
    
    private final IVisualizer visualizer;
    private final MusicPlayer musicPlayer;
    
    @Override
    public void run() {
        this.visualizer.onDraw(musicPlayer.getLeftAudioBuffer(), musicPlayer.getRightAudioBuffer(), musicPlayer.getMixedAudioBuffer(),
                               musicPlayer.getBeatDetector(), musicPlayer.getFFT());
    }
}
