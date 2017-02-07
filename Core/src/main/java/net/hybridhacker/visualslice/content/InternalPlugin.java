package net.hybridhacker.visualslice.content;

import net.hybridhacker.visualslice.plugins.SlicePlugin;
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
        VisualizerRegistry.getInstance().registerVisualizer(new DebugVisualizer());
        VisualizerRegistry.getInstance().registerVisualizer(new BasicFrequencyLine());
        
        DecoratorRegistry.getInstance().registerDecorator(new ImageBackgroundDecorator(null));
        DecoratorRegistry.getInstance().registerDecorator(new PlainBackgroundDecorator(null));
        DecoratorRegistry.getInstance().registerDecorator(new BeatParticleDecorator(null));
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
