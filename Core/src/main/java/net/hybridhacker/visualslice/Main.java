package net.hybridhacker.visualslice;

import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.renderer.VisualizerRenderer;
import net.hybridhacker.visualslice.visualizer.DebugVisualizer;
import net.hybridhacker.visualslice.visualizer.decorators.PlainBackgroundDecorator;

import java.awt.*;
import java.io.File;

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
        if (args.length >= 1) {
            tempTestPlayer.play(new File(String.join(" ", args)).toURI());
        }
        display.addRenderer(new VisualizerRenderer(new PlainBackgroundDecorator(new DebugVisualizer(), Color.BLACK), tempTestPlayer));
        display.start();
    }
}
