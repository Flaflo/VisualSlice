package net.hybridhacker.visualslice.visualizer;

import net.hybridhacker.visualslice.visualizer.settings.Setting;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Singleton registry for visualizers
 */
public class VisualizerRegistry {
    
    private static final VisualizerRegistry visualizerRegistry;
    
    static {
        visualizerRegistry = new VisualizerRegistry();
    }
    
    private VisualizerRegistry() {
        
    }
    
    public void registerVisualizer(final IVisualizer visualizer, final Setting... settings) {
        throw new NotImplementedException();
    }
}
