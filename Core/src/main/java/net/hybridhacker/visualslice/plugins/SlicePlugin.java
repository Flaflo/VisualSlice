package net.hybridhacker.visualslice.plugins;

/**
 * An API interface for plugins
 */
public interface SlicePlugin {
    
    /**
     * Called upon plugin enabling. Should register all plugin contents
     */
    void onEnable();
    
    /**
     * Called upon plugin disabling.
     */
    void onDisable();
    
    /**
     * @return the plugin's author (nick-) name
     */
    String getAuthor();
    
    /**
     * @return the plugin's version
     */
    String getVersion();
}
