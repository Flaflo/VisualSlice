package net.hybridhacker.visualslice.visualizer.builder;

import net.hybridhacker.visualslice.visualizer.IVisualizer;

/**
 * A builder for any visualizer
 */
public interface VisualizerBuilder {
    
    /**
     * Builds and returns a potentially decorated visualizer
     *
     * @return the decorated visualizer instance
     */
    IVisualizer buildVisualizer();
}
