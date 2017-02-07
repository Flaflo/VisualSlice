package net.hybridhacker.visualslice.plugins.loader;

import lombok.RequiredArgsConstructor;
import net.hybridhacker.visualslice.plugins.AutoRegister;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A plugin loader that loads plugin archive files from a folder
 */
@RequiredArgsConstructor
public class PluginFolderLoader implements PluginLoader {
    
    private final String pluginFolder;
    
    @Override
    public List<Object> loadPlugins() {
        final List<Object> loadedPlugins = new ArrayList<>();
        final File pluginsFolder = new File(pluginFolder);
        
        if (!pluginsFolder.exists()) //noinspection ResultOfMethodCallIgnored
            pluginsFolder.mkdir();
        
        if (pluginsFolder.listFiles() != null) {
            //noinspection ConstantConditions
            for (File file : pluginsFolder.listFiles()) {
                if (file.getName().endsWith(".jar")) {
                    loadPlugin(file).ifPresent(loadedPlugins::add);
                }
            }
        }
        
        return loadedPlugins;
    }
    
    /**
     * Load a single plugin from a file
     *
     * @param file plugin archive
     */
    private Optional<Object> loadPlugin(final File file) {
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[] {file.toURI().toURL()}, this.getClass().getClassLoader());
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(AutoRegister.PLUGIN_META_FILE)));
            
            String canonicalClassName = reader.readLine();
            Object plugin = classLoader.loadClass(canonicalClassName).newInstance();
            plugin.getClass().getMethod("onEnable").invoke(plugin);
            return Optional.of(plugin);
        } catch (IOException | IllegalAccessException | ClassNotFoundException | InstantiationException | NoSuchMethodException |
                InvocationTargetException e) {
            e.printStackTrace();
        }
        
        return Optional.empty();
    }
}
