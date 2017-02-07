package net.hybridhacker.visualslice.plugins.loader;

import net.hybridhacker.visualslice.content.InternalPlugin;

import java.util.Collections;
import java.util.List;

/**
 * Loads the internal plugin that provides the internal content
 */
public class InternalPluginLoader implements PluginLoader {
    
    @Override
    public List<Object> loadPlugins() {
        return Collections.singletonList(new InternalPlugin());
    }
}
