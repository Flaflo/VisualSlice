package net.hybridhacker.visualslice;

import java.io.File;
import java.net.URISyntaxException;
import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.renderer.VisualizerRenderer;
import net.hybridhacker.visualslice.visualizer.builder.DefaultVisualizerBuilder;

/**
 * Application's main class
 */
public final class Main {
    
    private static final MusicPlayer tempTestPlayer = new MusicPlayer();
    
    /**
     * Main method
     *
     * @param args terminal arguments
     */
    public static void main(final String... args) throws URISyntaxException {
        final Display display = new Display("VisualSlice", 800, 250, 120);
        if (args.length >= 1) {
            tempTestPlayer.play(new File(String.join(" ", args)).toURI());
        }
        display.addRenderer(
                new VisualizerRenderer(new DefaultVisualizerBuilder().debugVisualizer().image(new File("http://hybridhacker.net/visualslice.jpg").toURI()).buildVisualizer(),
                                       tempTestPlayer));
        display.start();
    }
}
