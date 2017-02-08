package net.hybridhacker.visualslice.visualizer;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton registry for visualizers
 */
public class VisualizerRegistry {
    
    private static final VisualizerRegistry instance;
    
    private final Map<String, IVisualizer> visualizers = new HashMap<>();
    
    static {
        instance = new VisualizerRegistry();
    }
    
    /**
     * This class is a singleton and thus handles its instantiating itself
     */
    private VisualizerRegistry() {
        
    }
    
    /**
     * @return the singleton instance of the visualizer registry
     */
    public static VisualizerRegistry getInstance() {
        return instance;
    }
    
    /**
     * Register a visualizer and its settings
     *
     * @param visualizer visualizer implementation
     */
    public void registerVisualizer(final IVisualizer visualizer) {
        this.visualizers.put(visualizer.getName(), visualizer);
    }
    
    /**
     * @return an array of all registered visualizers
     */
    public String[] getRegisteredVisualizers() {
        return this.visualizers.keySet().toArray(new String[this.visualizers.keySet().size()]);
    }
    
    /**
     * @param visualizer name of a registered visualizer
     *
     * @return the visualizer instance with given name or null if no such exists
     */
    public IVisualizer getVisualizerByName(final String visualizer) {
        return this.visualizers.get(visualizer);
    }
}
