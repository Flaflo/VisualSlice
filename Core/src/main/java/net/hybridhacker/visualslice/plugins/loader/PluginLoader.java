package net.hybridhacker.visualslice.plugins.loader;

import java.util.List;

/**
 * An interface for a plugin loading strategy
 */
public interface PluginLoader {
    
    /**
     * Load plugins that are provided in a form this loader implementation supports
     *
     * @return a (potentially empty) list of loaded plugin instances
     */
    public List<Object> loadPlugins();
}
