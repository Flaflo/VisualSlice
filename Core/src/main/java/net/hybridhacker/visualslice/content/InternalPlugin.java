package net.hybridhacker.visualslice.content;

import net.hybridhacker.visualslice.Main;
import net.hybridhacker.visualslice.plugins.SlicePlugin;
import net.hybridhacker.visualslice.renderer.Graphics2DRenderEngine;
import net.hybridhacker.visualslice.renderer.IRenderEngine;
import net.hybridhacker.visualslice.renderer.RenderEngineRegistry;
import net.hybridhacker.visualslice.visualizer.AbstractVisualizerDecorator;
import net.hybridhacker.visualslice.visualizer.DebugVisualizer;
import net.hybridhacker.visualslice.visualizer.DecoratorRegistry;
import net.hybridhacker.visualslice.visualizer.IVisualizer;
import net.hybridhacker.visualslice.visualizer.VisualizerRegistry;
import net.hybridhacker.visualslice.visualizer.decorators.ImageBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.decorators.PlainBackgroundDecorator;
import net.hybridhacker.visualslice.visualizer.frequency.BasicFrequencyLine;

/**
 * The internal plugin registers all built-in contents like {@link IVisualizer}, {@link AbstractVisualizerDecorator} or {@link
 * IRenderEngine}. Although those resources could be registered in another place, e.g. the main class, the plugin system handles the
 * adding of resources anyways and thus it reduces the amount of code spreading if it also handles registering the internal contents
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
        return Main.VERSION;
    }
}
