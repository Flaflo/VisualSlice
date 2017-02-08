package net.hybridhacker.visualslice.content;

import net.hybridhacker.visualslice.plugins.SlicePlugin;
import net.hybridhacker.visualslice.renderer.Graphics2DRenderEngine;
import net.hybridhacker.visualslice.renderer.RenderEngineRegistry;
import net.hybridhacker.visualslice.visualizer.DebugVisualizer;
import net.hybridhacker.visualslice.visualizer.DecoratorRegistry;
import net.hybridhacker.visualslice.visualizer.VisualizerRegistry;
import net.hybridhacker.visualslice.visualizer.decorators.BeatParticleDecorator;
import net.hybridhacker.visualslice.visualizer.decorators.ImageBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.decorators.PlainBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.frequency.BasicFrequencyLine;

/**
 *
 */
public class InternalPlugin implements SlicePlugin {
    @Override
    public void onEnable() {
        // visualizers
        VisualizerRegistry.getInstance().registerVisualizer(new DebugVisualizer());
        VisualizerRegistry.getInstance().registerVisualizer(new BasicFrequencyLine());
    
        // decorators
        DecoratorRegistry.getInstance().registerDecorator(new ImageBackgroundDecorator(null));
        DecoratorRegistry.getInstance().registerDecorator(new PlainBackgroundDecorator(null));
        DecoratorRegistry.getInstance().registerDecorator(new BeatParticleDecorator(null));
    
        // render engine
        RenderEngineRegistry.getInstance().registerEngine(new Graphics2DRenderEngine());
    }
    
    @Override
    public String getName() {
        return "internal content plugin";
    }
    
    @Override
    public String getAuthor() {
        return "internal";
    }
    
    @Override
    public String getVersion() {
        return "internal";
    }
}
