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
        if (!musicPlayer.getMixedAudioBuffer().isPresent() || !musicPlayer.getLeftAudioBuffer().isPresent()
                || !musicPlayer.getRightAudioBuffer().isPresent() || !musicPlayer.getLength().isPresent()
                || !musicPlayer.getPosition().isPresent() && !musicPlayer.getBeatDetect().isPresent() && !musicPlayer.getFFT().isPresent()) {
            return;
        }

        //Forward fft to mixed audio buffer
        this.musicPlayer.getFFT().get().forward(this.musicPlayer.getMixedAudioBuffer().get());

        this.visualizer.onDraw(musicPlayer.getLength().get(), musicPlayer.getPosition().get(), musicPlayer.getLeftAudioBuffer().get(), musicPlayer.getRightAudioBuffer().get(),
                musicPlayer.getMixedAudioBuffer().get(), musicPlayer.getBeatDetect().get(), musicPlayer.getFFT().get());
    }
}
