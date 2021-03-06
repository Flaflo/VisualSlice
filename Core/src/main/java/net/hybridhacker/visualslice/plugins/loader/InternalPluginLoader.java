package net.hybridhacker.visualslice.plugins.loader;

import net.hybridhacker.visualslice.content.InternalPlugin;
import net.hybridhacker.visualslice.plugins.SlicePlugin;

import java.util.Collections;
import java.util.List;

/**
 * Loads the internal plugin that provides the internal content
 */
public class InternalPluginLoader implements PluginLoader {
    
    /**
     * @return a {@link Collections.SingletonList} of only the internal plugin
     */
    @Override
    public List<SlicePlugin> loadPlugins() {
        return Collections.singletonList(new InternalPlugin());
    }
}
