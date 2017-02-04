package net.hybridhacker.visualslice.visualizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton registry for visualizers
 */
public class VisualizerRegistry {
    
    private static final VisualizerRegistry instance;
    
    private final List<IVisualizer> visualizerList = new ArrayList<>();
    
    static {
        instance = new VisualizerRegistry();
    }
    
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
        this.visualizerList.add(visualizer);
    }
    
    /**
     * @return an array of all registered visualizers
     */
    public IVisualizer[] getRegisteredVisualizers() {
        return this.visualizerList.toArray(new IVisualizer[this.visualizerList.size()]);
    }
}
