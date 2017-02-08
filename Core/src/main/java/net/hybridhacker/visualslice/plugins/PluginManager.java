package net.hybridhacker.visualslice.plugins;

import net.hybridhacker.visualslice.plugins.loader.PluginLoader;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * A manager for all loaded plugins. Different {@link PluginLoader}s can be attached to the manager which will be called upon plugin
 * loading. Note, that a plugin cannot attach more plugin loaders, since the plugin is enabled after all plugins are already loaded.
 * Though it is possible to attach another loader by using a static constructor in the plugin class, this will result in a
 * {@link ConcurrentModificationException} and crash the plugin loading progress.
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
