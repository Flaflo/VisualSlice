package net.hybridhacker.visualslice.plugins;

import net.hybridhacker.visualslice.plugins.loader.PluginLoader;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PluginManager {
    private final static PluginManager instance;
    
    private final List<PluginLoader> pluginLoaders = new ArrayList<>();
    private final List<SlicePlugin> plugins = new ArrayList<>();
    
    static {
        instance = new PluginManager();
    }
    
    /**
     * This class is a singleton and handles instantiating itself
     */
    private PluginManager() {
        
    }
    
    /**
     * @return the singleton instance of this class
     */
    public static PluginManager getInstance() {
        return instance;
    }
    
    /**
     * Invoke all attached plugin loaders and collect their loaded plugins
     */
    public void loadPlugins() {
        this.pluginLoaders.forEach(loader -> this.plugins.addAll(loader.loadPlugins()));
        this.plugins.forEach(plugin -> {
            System.out.println("Enabling " + plugin.getName() + " by " + plugin.getAuthor() + " (" + plugin.getVersion() + ")");
            plugin.onEnable();
        });
    }
    
    /**
     * Attach a loader for plugins
     *
     * @param loader a plugin loader implementation
     */
    public void attachLoader(final PluginLoader loader) {
        this.pluginLoaders.add(loader);
    }
}
