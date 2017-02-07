package net.hybridhacker.visualslice;

import net.hybridhacker.visualslice.gui.GuiController;
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
    
    /**
     * Main method
     *
     * @param args terminal arguments
     */
    public static void main(final String... args) throws URISyntaxException, ParseException {
        PluginManager.getInstance().attachLoader(new InternalPluginLoader());
        PluginManager.getInstance().attachLoader(new PluginFolderLoader(PLUGIN_FOLDER));
        PluginManager.getInstance().loadPlugins();
        
        final CommandLineInterface commandLineInterface = new CommandLineInterface();
    
        // display settings from command line
    
        // the formatter is sadly too bad for this
        // @formatter:off
        commandLineInterface.addOption("w", "set the GUI width", "width", false,
                                       width -> GuiController.getInstance().getDisplaySettings().setWidth(Integer.valueOf(
                                               width.orElse("" + GuiController.getInstance().getDisplaySettings().getWidth()))));
        // @formatter:on
    
        commandLineInterface.addOption("h", "set the GUI height", "height", false,
                                       height -> GuiController.getInstance().getDisplaySettings().setHeight(Integer.valueOf(
                                               height.orElse("" + GuiController.getInstance().getDisplaySettings().getHeight()))));
    
        commandLineInterface.addOption("fps", "the frames per second", "fps", false,
                                       frames -> GuiController.getInstance().getDisplaySettings().setFps(Integer.valueOf(
                                               frames.orElse("" + GuiController.getInstance().getDisplaySettings().getFps()))));
        
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
    
        GuiController.getInstance().getDisplay().start();
    }
}
