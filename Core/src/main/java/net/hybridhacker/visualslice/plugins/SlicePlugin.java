package net.hybridhacker.visualslice.plugins;

/**
 * An API interface for plugins
 */
public interface SlicePlugin {
    
    /**
     * Called upon plugin enabling. Should register all plugin contents
     */
    public void onEnable();
    
    /**
     * @return the plugin's name
     */
    public String getName();
    
    /**
     * @return the plugin's author (nick-) name
     */
    public String getAuthor();
    
    /**
     * @return the plugin's version
     */
    public String getVersion();
}
