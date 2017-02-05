package net.hybridhacker.visualslice;

import net.hybridhacker.visualslice.display.Display;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.plugins.PluginManager;
import net.hybridhacker.visualslice.renderer.VisualizerRenderer;
import net.hybridhacker.visualslice.utils.ColorUtil;
import net.hybridhacker.visualslice.utils.CommandLineInterface;
import net.hybridhacker.visualslice.visualizer.DebugVisualizer;
import net.hybridhacker.visualslice.visualizer.DecoratorRegistry;
import net.hybridhacker.visualslice.visualizer.VisualizerRegistry;
import net.hybridhacker.visualslice.visualizer.builder.DebugBuilder;
import net.hybridhacker.visualslice.visualizer.decorators.ImageBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.decorators.PlainBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.frequency.BasicFrequencyLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
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
        // register the application's visualizers
        registerDefaultVisualizers();
        PluginManager.getInstance().loadPlugins();
        
        final CommandLineInterface commandLineInterface = new CommandLineInterface();
    
        // display settings from command line
        final int[] displaySettings = {800, 250, 60};
        commandLineInterface.addOption("w", "set the GUI width", "width", false, width -> displaySettings[0] =
                width.isPresent() ? Integer.parseInt(width.get()) : displaySettings[0]);
        commandLineInterface.addOption("h", "set the GUI height", "height", false, height -> displaySettings[1] =
                height.isPresent() ? Integer.parseInt(height.get()) : displaySettings[1]);
        commandLineInterface.addOption("fps", "the frames per second", "fps", false, frames -> displaySettings[2] =
                frames.isPresent() ? Integer.parseInt(frames.get()) : displaySettings[2]);
    
        // resources from command line
        final String[] resources = new String[] {"", ""};
        commandLineInterface.addOption("f", "the music file to play", "file", true,
                                       file -> resources[0] = file.isPresent() ? file.get() : resources[0]);
    
        commandLineInterface
                .addOption("img", "background image URI", "image", true, uri -> resources[1] = uri.isPresent() ? uri.get() : resources[1]);
        
        // shortcut for help page printing
        if (args[0].equalsIgnoreCase("--help")) {
            commandLineInterface.printHelp();
            return;
        }
    
        // parse command line
        try {
            commandLineInterface.parse(args);
        } catch (final MissingOptionException e) {
            //noinspection unchecked
            System.err.println("Missing required command line option(s): " + String.join(", ", e.getMissingOptions()));
            System.err.println("Try --help for more information");
            return;
        } catch (final MissingArgumentException e) {
            System.err
                    .println("Missing required argument \"" + e.getOption().getArgName() + "\" for option: " + e.getOption().getLongOpt());
            System.err.println("Try --help for more information");
            return;
        }
    
        // setup display
        final Display display = new Display("VisualSlice", displaySettings[0], displaySettings[1], displaySettings[2]);
        BufferedImage theImage = null;
        try {
            theImage = ImageIO.read(new URI(resources[1]).toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        display.addRenderer(new VisualizerRenderer(
                new DebugBuilder().debugVisualizer(ColorUtil.getAverageColor(theImage)).image(new URI(resources[1])).buildVisualizer(),
                tempTestPlayer));
        display.start();
    
        // setup music player
        tempTestPlayer.play(new File(resources[0]).toURI());
    }
    
    /**
     * Register all visualizers provided by default
     */
    public static void registerDefaultVisualizers() {
        VisualizerRegistry.getInstance().registerVisualizer(new DebugVisualizer());
        VisualizerRegistry.getInstance().registerVisualizer(new BasicFrequencyLine());
    
        DecoratorRegistry.getInstance().registerDecorator(new ImageBackgroundDecorator(null));
        DecoratorRegistry.getInstance().registerDecorator(new PlainBackgroundDecorator(null));
    }
}
