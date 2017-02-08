package net.hybridhacker.visualslice;

import net.hybridhacker.visualslice.gui.Controller;
import net.hybridhacker.visualslice.plugins.PluginManager;
import net.hybridhacker.visualslice.plugins.loader.InternalPluginLoader;
import net.hybridhacker.visualslice.plugins.loader.PluginFolderLoader;
import net.hybridhacker.visualslice.utils.CommandLineInterface;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;

import java.net.URISyntaxException;

/**
 * Application's main class
 */
public final class Main {
    
    private static final String PLUGIN_FOLDER = "plugins/";
    
    private static final CommandLineInterface commandLineInterface;
    
    static {
        commandLineInterface = new CommandLineInterface();
        
        // display settings from command line
        commandLineInterface.addOption("w", "set the GUI width", "width", false,
                                       width -> Controller.getInstance().getDisplaySettings().setWidth(Integer.valueOf(width.get())));
        commandLineInterface.addOption("h", "set the GUI height", "height", false,
                                       height -> Controller.getInstance().getDisplaySettings().setHeight(Integer.valueOf(height.get())));
        commandLineInterface.addOption("fps", "the frames per second", "fps", false,
                                       frames -> Controller.getInstance().getDisplaySettings().setFps(Integer.valueOf(frames.get())));
        
        commandLineInterface.addOption("plugins", "load an additional plugins folder", "folder", false,
                                       folder -> PluginManager.getInstance().attachLoader(new PluginFolderLoader(folder.get())));
    }
    
    /**
     * Main method
     *
     * @param args terminal arguments
     */
    public static void main(final String... args) throws URISyntaxException, ParseException {
        // shortcut for help page printing
        if (args.length == 1 && args[0].equalsIgnoreCase("--help")) {
            commandLineInterface.printHelp();
            return;
        }
        
        // parse and handle command line arguments
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
        
        // load plugins
        PluginManager.getInstance().attachLoader(new InternalPluginLoader());
        PluginManager.getInstance().attachLoader(new PluginFolderLoader(PLUGIN_FOLDER));
        PluginManager.getInstance().loadPlugins();
        
        // start display
        Controller.getInstance().getDisplay().start();
    }
}
