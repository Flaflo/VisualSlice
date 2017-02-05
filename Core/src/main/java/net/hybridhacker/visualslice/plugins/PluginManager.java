package net.hybridhacker.visualslice.plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PluginManager {
    private static final String PLUGIN_FOLDER = "plugins/";
    
    private final static PluginManager instance;
    
    private final List<Object> plugins = new ArrayList<>();
    
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
    
    public void loadPlugins() {
        File pluginsFolder = new File(PLUGIN_FOLDER);
    
        if (!pluginsFolder.exists()) //noinspection ResultOfMethodCallIgnored
            pluginsFolder.mkdir();
    
        if (pluginsFolder.listFiles() != null) {
            //noinspection ConstantConditions
            for (File file : pluginsFolder.listFiles()) {
                if (file.getName().endsWith(".jar")) {
                    loadPlugin(file);
                }
            }
        }
    }
    
    private void loadPlugin(final File file) {
        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[] {file.toURI().toURL()}, this.getClass().getClassLoader());
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(AutoRegister.PLUGIN_META_FILE)));
            
            String canonicalClassName = reader.readLine();
            Object plugin = classLoader.loadClass(canonicalClassName).newInstance();
            plugin.getClass().getMethod("onEnable").invoke(plugin);
            this.plugins.add(plugin);
        } catch (IOException | IllegalAccessException | ClassNotFoundException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
