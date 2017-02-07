package net.hybridhacker.visualslice;

import net.hybridhacker.visualslice.gui.Controller;
import net.hybridhacker.visualslice.music.MusicPlayer;
import net.hybridhacker.visualslice.plugins.PluginManager;
import net.hybridhacker.visualslice.utils.CommandLineInterface;
import net.hybridhacker.visualslice.visualizer.DebugVisualizer;
import net.hybridhacker.visualslice.visualizer.DecoratorRegistry;
import net.hybridhacker.visualslice.visualizer.VisualizerRegistry;
import net.hybridhacker.visualslice.visualizer.decorators.BeatParticleDecorator;
import net.hybridhacker.visualslice.visualizer.decorators.ImageBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.decorators.PlainBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.frequency.BasicFrequencyLine;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;

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
    
        // the formatter is sadly too bad for this
        // @formatter:off
        commandLineInterface.addOption("w", "set the GUI width", "width", false,
                                       width -> Controller.getInstance().getDisplaySettings().setWidth(Integer.valueOf(width.orElse("" + Controller.getInstance().getDisplaySettings().getWidth()))));
        // @formatter:on
    
        commandLineInterface.addOption("h", "set the GUI height", "height", false,
                                       height -> Controller.getInstance().getDisplaySettings().setHeight(Integer.valueOf(height.orElse("" + Controller.getInstance().getDisplaySettings().getHeight()))));
    
        commandLineInterface.addOption("fps", "the frames per second", "fps", false,
                                       frames -> Controller.getInstance().getDisplaySettings().setFps(Integer.valueOf(frames.orElse("" + Controller.getInstance().getDisplaySettings().getFps()))));
        
        // shortcut for help page printing
        if (args.length == 1 && args[0].equalsIgnoreCase("--help")) {
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
    
        Controller.getInstance().getDisplay().start();
    }
    
    /**
     * Register all visualizers provided by default
     */
    public static void registerDefaultVisualizers() {
        VisualizerRegistry.getInstance().registerVisualizer(new DebugVisualizer());
        VisualizerRegistry.getInstance().registerVisualizer(new BasicFrequencyLine());
    
        DecoratorRegistry.getInstance().registerDecorator(new ImageBackgroundDecorator(null));
        DecoratorRegistry.getInstance().registerDecorator(new PlainBackgroundDecorator(null));
        DecoratorRegistry.getInstance().registerDecorator(new BeatParticleDecorator(null));
    }
}
