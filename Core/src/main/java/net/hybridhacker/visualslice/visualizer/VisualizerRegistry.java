package net.hybridhacker.visualslice.visualizer;

import net.hybridhacker.visualslice.visualizer.settings.Setting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton registry for visualizers
 */
public class VisualizerRegistry {
    
    private static final VisualizerRegistry instance;
    
    private final List<IVisualizer> visualizerList = new ArrayList<>();
    private final Map<IVisualizer, Setting[]> visualizerSettings = new HashMap<>();
    
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
        this.visualizerSettings.put(visualizer, visualizer.getSettings());
    }
    
    /**
     * @return an array of all registered visualizers
     */
    public IVisualizer[] getRegisteredVisualizers() {
        return this.visualizerList.toArray(new IVisualizer[this.visualizerList.size()]);
    }
    
    /**
     * Get all registered settings of a visualizer
     *
     * @param visualizer visualizer instance
     *
     * @return all for the given instance registered settings
     */
    public Setting<?>[] getSettingsOfVisualizer(final IVisualizer visualizer) {
        return Arrays.copyOf(this.visualizerSettings.get(visualizer), this.visualizerSettings.get(visualizer).length);
    }
}
