package net.hybridhacker.visualslice;

import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.renderer.VisualizerRenderer;
import net.hybridhacker.visualslice.visualizer.builder.DefaultVisualizerBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
    
        BufferedImage theImage = null;
        try {
            theImage = ImageIO.read(new URI("http://ni341745_1.vweb16.nitrado.net/visualslice.jpg").toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        display.addRenderer(new VisualizerRenderer(new DefaultVisualizerBuilder().debugVisualizer().image(theImage).buildVisualizer(),
                                                   tempTestPlayer));
        display.start();
    }
}
