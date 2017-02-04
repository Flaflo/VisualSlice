package net.hybridhacker.visualslice;

import java.awt.*;
import java.io.File;
import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.renderer.VisualizerRenderer;
import net.hybridhacker.visualslice.utils.G2D;
import net.hybridhacker.visualslice.visualizer.DebugVisualizer;

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
    public static void main(final String... args) {
        final Display display = new Display("VisualSlice", 800, 600, 120);
        tempTestPlayer.play(new File("C:\\Users\\Flaflo\\Desktop\\Desktop\\Fler - Vibe.mp3").toURI());
        display.addRenderer(() -> G2D.clear(Color.BLACK));
        display.addRenderer(new VisualizerRenderer(new DebugVisualizer(), tempTestPlayer));
        display.start();
    }
}
