package net.hybridhacker.visualslice;

import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.renderer.VisualizerRenderer;
import net.hybridhacker.visualslice.utils.CommandLineInterface;
import net.hybridhacker.visualslice.visualizer.builder.DefaultVisualizerBuilder;
import org.apache.commons.cli.ParseException;

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
    public static void main(final String... args) throws URISyntaxException, ParseException {
    
        final CommandLineInterface commandLineInterface = new CommandLineInterface();
    
        final int[] dimensions = {800, 250};
        commandLineInterface.addOption("w", "set the GUI width", "width", false,
                                       width -> dimensions[0] = width.isPresent() ? Integer.parseInt(width.get()) : dimensions[0]);
        commandLineInterface.addOption("h", "set the GUI height", "height", false,
                                       height -> dimensions[1] = height.isPresent() ? Integer.parseInt(height.get()) : dimensions[1]);
    
        final String[] filename = new String[] {""};
        commandLineInterface
                .addOption("f", "the music file to play", "file", true, file -> filename[0] = file.isPresent() ? file.get() : filename[0]);
    
        final int[] fps = {120};
        commandLineInterface.addOption("fps", "the frames per second", "fps", false,
                                       frames -> fps[0] = frames.isPresent() ? Integer.parseInt(frames.get()) : fps[0]);
    
        commandLineInterface.parse(args);
    
        final Display display = new Display("VisualSlice", dimensions[0], dimensions[1], fps[0]);
        tempTestPlayer.play(new File(filename[0]).toURI());
        
        BufferedImage theImage = null;
        try {
            theImage = ImageIO.read(new URI("http://ni341745_1.vweb16.nitrado.net/visualslice.jpg").toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        display.addRenderer(
                new VisualizerRenderer(new DefaultVisualizerBuilder().debugVisualizer().image(theImage).buildVisualizer(), tempTestPlayer));
        display.start();
    }
}
